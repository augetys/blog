package com.hope.blog.quartz.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lijin
 * @since 2021-07-02
 */
@Data
@TableName("sys_quartz_log")
public class QuartzLog implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "任务名称不能为空")
    @ApiModelProperty(value = "任务名称", hidden = true)
    private String jobName;

    @NotBlank(message = "Bean名称不能为空")
    @ApiModelProperty(value = "bean名称", hidden = true)
    private String beanName;

    @NotBlank(message = "方法名称不能为空")
    @ApiModelProperty(value = "方法名称", hidden = true)
    private String methodName;

    @ApiModelProperty(value = "参数", hidden = true)
    private String params;

    @NotBlank(message = "cron表达式不能为空")
    @ApiModelProperty(value = "cron表达式", hidden = true)
    private String cronExpression;

    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "状态", hidden = true)
    private Integer isSuccess;

    @ApiModelProperty(value = "异常详情", hidden = true)
    private String exceptionDetail;

    @NotBlank(message = "执行耗时不能为空")
    @ApiModelProperty(value = "执行耗时", hidden = true)
    private Long time;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "执行日期", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "执行人", hidden = true)
    private String createBy;
}
