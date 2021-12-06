package com.hope.blog.resource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lijin
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class FileProperties {
    /**
     * 文件大小限制
     */
    private Long maxSize;
    /**
     * 文件上传路径
     */
    private String file;
}
