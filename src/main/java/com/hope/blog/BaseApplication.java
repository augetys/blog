package com.hope.blog;

import com.hope.blog.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BaseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return application.sources(BaseApplication.class);
    }
}
