package com.hope.blog.quartz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "定时器名称")
    private String jobName;

    @NotBlank
    @ApiModelProperty(value = "Bean名称")
    private String beanName;

    @NotBlank
    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @NotBlank
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "状态，暂时或启动")
    private Integer isPause;

    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    @ApiModelProperty(value = "报警邮箱")
    private String email;

    @ApiModelProperty(value = "子任务")
    private String subTask;

    @ApiModelProperty(value = "失败后暂停")
    private Integer pauseAfterFailure;

    @NotBlank
    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}