package com.hope.blog.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hope.blog.common.constant.CommonConstant;
import com.hope.blog.common.exception.BusinessException;
import com.hope.blog.sys.dto.request.LoginRequestDto;
import com.hope.blog.sys.dto.request.RegisterRequestDto;
import com.hope.blog.sys.dto.request.UpdateSysUserStatusRequestDto;
import com.hope.blog.sys.dto.request.SysUserSearchRequestDto;
import com.hope.blog.sys.dto.response.SysUserInfoResponseDto;
import com.hope.blog.sys.mapper.SysRoleMapper;
import com.hope.blog.sys.mapper.SysUserMapper;
import com.hope.blog.sys.model.SysUser;
import com.hope.blog.sys.model.SysMenus;
import com.hope.blog.sys.model.SysRole;
import com.hope.blog.sys.model.SysUserRole;
import com.hope.blog.sys.service.SysUserRoleService;
import com.hope.blog.sys.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.blog.common.security.config.UserDetailsServiceImpl;
import com.hope.blog.utils.JwtTokenUtil;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author lijin
 * @since 2021-04-25
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysUserRoleService sysUserRoleService;


    @Override
    public IPage<SysUser> findListByPage(SysUserSearchRequestDto sysUserSearchRequestDto) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        // 构建条件
        String username = sysUserSearchRequestDto.getUsername();
        String nickName = sysUserSearchRequestDto.getNickName();
        Integer status = sysUserSearchRequestDto.getStatus();
        if (!StringUtils.isEmpty(username)) {
            queryWrapper.like("username", username);
        }
        if (!StringUtils.isEmpty(nickName)) {
            queryWrapper.like("nick_name", nickName);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("create_time");
        return sysUserMapper.selectPage(new Page<>(sysUserSearchRequestDto.getPageNum(), sysUserSearchRequestDto.getPageSize()), queryWrapper);
    }


    @Override
    public SysUser findUserByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        // 校验用户名是否存在
        SysUser sysUser = this.findUserByUserName(loginRequestDto.getUsername());
        if (sysUser == null) {
            throw new BusinessException("该用户不存在!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        // 密码是否正确
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userDetails.getPassword())) {
            throw new BusinessException("密码不正确!");
        }
        // 账号是否被禁用
        if (!userDetails.isEnabled()) {
            throw new BusinessException("帐号已被禁用!");
        }
        // 认证成功，分发凭证
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.createToken(userDetails.getUsername());
    }

    @Override
    public SysUser register(RegisterRequestDto registerRequestDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(registerRequestDto, sysUser);
        // 查询用户是否已经存在
        SysUser user = findUserByUserName(registerRequestDto.getUsername());
        if (user != null) {
            throw new BusinessException("该用户已经被注册啦!");
        }
        // 密码加密
        String encodePassword = passwordEncoder.encode(registerRequestDto.getPassword());
        sysUser.setPassword(encodePassword);
        // 注册成功
        int insert = sysUserMapper.insert(sysUser);
        if (insert > 0) {
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUserInfoResponseDto getUserInfo(Principal principal) {
        String name = principal.getName();
        // 返回用户基础信息
        SysUser sysUser = this.findUserByUserName(name);
        // 查询角色信息
        String id = sysUser.getId();
        List<SysRole> roles = sysUserMapper.getRoles(id);
        // 查询用户权限
        List<SysMenus> authority = sysUserMapper.getMenus(id);
        SysUserInfoResponseDto sysUserInfoResponseDto = new SysUserInfoResponseDto();
        sysUserInfoResponseDto.setAdmin(sysUser);
        sysUserInfoResponseDto.setMenus(authority);
        sysUserInfoResponseDto.setRoles(roles);
        return sysUserInfoResponseDto;
    }

    @Override
    public SysUser logout() {
        return null;
    }

    @Override
    public List<SysRole> getRoles(String userId) {
        return sysUserMapper.getRoles(userId);
    }

    @Override
    public List<SysMenus> getAuthority(String userId) {
        return sysUserMapper.getMenus(userId);
    }

    @Override
    public boolean updateStatusRequest(UpdateSysUserStatusRequestDto updateSysUserStatusRequestDto) {
        return sysUserMapper.updateStatus(updateSysUserStatusRequestDto) > 0;
    }

    @Override
    public List<SysRole> getRoleList(String userId) {
        return roleMapper.getRoleList(userId);
    }

    @Override
    public int updateRole(String userId, List<String> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        // 先删除原来的关系
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserRole::getUserId, userId);
        sysUserRoleService.remove(wrapper);
        // 建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysUserRole> list = new ArrayList<>();
            for (String roleId : roleIds) {
                SysUserRole roleRelation = new SysUserRole();
                roleRelation.setUserId(userId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            sysUserRoleService.saveBatch(list);
        }
        return count;
    }

    @Override
    public boolean deleteById(String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setIsDelete(1);
        return sysUserMapper.updateById(sysUser) > 0;
    }
}
