package com.hope.blog.quartz.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author lijin
 * @since 2021-07-02
 */
@Data
@TableName("sys_quartz_job")
public class QuartzJob {

    public static final String JOB_KEY = "JOB_KEY";

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "任务名称不能为空")
    @ApiModelProperty(value = "定时器名称")
    private String jobName;

    @NotBlank(message = "Bean名称不能为空")
    @ApiModelProperty(value = "Bean名称")
    private String beanName;

    @NotBlank(message = "方法名称不能为空")
    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @NotBlank(message = "cron表达式不能为空")
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "状态，暂时或启动")
    private Integer isPause;

    @NotBlank(message = "负责人不能为空")
    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    @NotBlank(message = "报警邮箱不能为空")
    @ApiModelProperty(value = "报警邮箱")
    private String email;

    @ApiModelProperty(value = "子任务")
    private String subTask;

    @ApiModelProperty(value = "失败后暂停")
    private Integer pauseAfterFailure;

    @NotBlank(message = "描述不能为空")
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
}