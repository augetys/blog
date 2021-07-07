package com.hope.blog.log.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 异常日志表
 * </p>
 *
 * @author lijin
 * @since 2021-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_exception_log")
@ApiModel(value="SysExceptionLog对象", description="异常日志表")
public class SysExceptionLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "唯一id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "uid")
    private String userId;

    @ApiModelProperty(value = "请求ip地址")
    private String ip;

    @ApiModelProperty(value = "ip所属地")
    private String ipAddress;

    @ApiModelProperty(value = "请求url")
    private String url;

    @ApiModelProperty(value = "请求方法名")
    private String method;

    @ApiModelProperty(value = "描述")
    private String operation;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "异常对象json格式")
    @TableField("exceptionJson")
    private String exceptionJson;

    @ApiModelProperty(value = "异常简单信息,等同于e.getMessage")
    @TableField("exceptionMessage")
    private String exceptionMessage;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
