package com.hope.blog.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 邮箱配置
 * </p>
 *
 * @author lijin
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("config_email")
@ApiModel(value="ConfigEmail对象", description="邮箱配置")
public class ConfigEmail implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "收件人")
    private String fromUser;

    @ApiModelProperty(value = "邮件服务器SMTP地址")
    private String host;

    @ApiModelProperty(value = "密码")
    private String pass;

    @ApiModelProperty(value = "端口")
    private String port;

    @ApiModelProperty(value = "发件者用户名")
    private String user;


}
