package com.hope.blog.common.security.config;

import com.hope.blog.sys.model.SysUser;
import com.hope.blog.sys.service.SysUserService;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by lijin on  2021/4/26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByUserName(username);

        return new AuthUserDetails(sysUser, sysUser.getId());
    }
}
