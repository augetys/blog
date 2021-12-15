package com.hope.blog.common.generator.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hope.blog.common.security.config.AuthUserDetails;
import com.hope.blog.utils.JwtTokenUtil;
import com.hope.blog.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lijin on  2021/5/7
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", SecurityUtil.getCurrentUser().getUid(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtil.getCurrentUser().getUid(), metaObject);
    }
}
