package com.hope.blog.resource.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * Created by lijin on  2021/12/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QiniuUpload {
    @ApiModelProperty(value = "文件备注名")
    private String name;

    @ApiModelProperty(value = "bucket")
    private String bucket;
}
