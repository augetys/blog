package com.hope.blog.log.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Created by lijin on  2021/6/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLogListRequestDto extends PageInfo {
    @ApiModelProperty(value = "时间段")
    private List<Date> createTime;
    @ApiModelProperty(value = "操作内容")
    private String operation;
    @ApiModelProperty(value = "操作人")
    private String userName;
}
