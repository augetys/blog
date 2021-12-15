package com.hope.blog.utils;

import com.hope.blog.common.security.config.AuthUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lijin on  2021/12/15
 */
public class SecurityUtil {
    public static AuthUserDetails getCurrentUser() {
        return (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
