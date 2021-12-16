package com.hope.blog.sys.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Created by lijin on  2021/4/25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

}
