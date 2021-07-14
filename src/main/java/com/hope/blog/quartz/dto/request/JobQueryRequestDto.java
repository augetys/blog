package com.hope.blog.quartz.dto.request;

import com.hope.blog.common.base.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;


/**
 * @author lijin
 * @date 2019-6-4 10:33:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobQueryRequestDto extends PageInfo {
    @ApiModelProperty(value = "人物名")
    private String jobName;
    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Integer isPause;
    @ApiModelProperty(value = "是否成功：1成功 0失败")
    private Integer isSuccess;
    @ApiModelProperty(value = "创建时间")
    private List<Date> createTime;
}
