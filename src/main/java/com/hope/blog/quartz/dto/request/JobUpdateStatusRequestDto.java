package com.hope.blog.quartz.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lijin on  2021/7/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateStatusRequestDto {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Integer isPause;
}
