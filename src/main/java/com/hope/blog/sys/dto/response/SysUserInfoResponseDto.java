package com.hope.blog.sys.dto.response;

import com.hope.blog.sys.model.SysUser;
import com.hope.blog.sys.model.SysMenus;
import com.hope.blog.sys.model.SysRole;
import lombok.*;
import java.util.List;

/**
 * Created by lijin on  2021/5/7
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserInfoResponseDto {
    private SysUser admin;
    private List<SysRole> roles;
    private List<SysMenus> menus;
}
