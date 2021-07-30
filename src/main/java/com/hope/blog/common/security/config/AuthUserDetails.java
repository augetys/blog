package com.hope.blog.common.security.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.blog.sys.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by lijin on  2021/4/26
 */
public class AuthUserDetails implements UserDetails {

    private final SysUser sysUser;
    private final String uid;


    public AuthUserDetails(SysUser sysUser, String uid) {
        this.sysUser = sysUser;
        this.uid = uid;
    }

    @JsonIgnore
    public String getUid() {
        return uid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals(1);
    }
}
