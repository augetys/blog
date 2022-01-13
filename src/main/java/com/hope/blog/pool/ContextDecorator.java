package com.hope.blog.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by lijin on  2022/1/12
 */
@Slf4j
public class ContextDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        log.info("装饰前：");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 子线程逻辑
        return () -> {
            try {
                SecurityContextHolder.setContext(securityContext);
                RequestContextHolder.setRequestAttributes(requestAttributes);
                runnable.run();
            } finally {
                log.info("清除上下文！！！");
                SecurityContextHolder.clearContext();
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}

