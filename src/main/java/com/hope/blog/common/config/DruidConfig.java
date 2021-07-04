package com.hope.blog.common.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijin on  2021/4/2
 * http://localhost:8800/druid/login.html
 */
@Configuration
public class DruidConfig {
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> beanFilter = new FilterRegistrationBean<>();
        beanFilter.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        beanFilter.setInitParameters(initParams);
        beanFilter.setUrlPatterns(Collections.singletonList("/*"));
        return  beanFilter;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> setStatViewServlet(){
        ServletRegistrationBean<StatViewServlet> beanServlet = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");
        beanServlet.setInitParameters(initParams);
        return  beanServlet;
    }
}
