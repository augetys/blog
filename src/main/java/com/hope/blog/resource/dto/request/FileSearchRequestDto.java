package com.hope.blog.resource.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * Created by lijin on  2021/12/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileSearchRequestDto extends PageInfo {
    @ApiModelProperty(value = "文件名")
    private String fileKey;

    @ApiModelProperty(value = "bucket名称")
    private String bucket;

    @ApiModelProperty(value = "本地文件名")
    private String realName;
}
