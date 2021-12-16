package com.hope.blog.quartz.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by lijin on  2021/7/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateStatusRequestDto {

    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Long id;

    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Integer isPause;

}
