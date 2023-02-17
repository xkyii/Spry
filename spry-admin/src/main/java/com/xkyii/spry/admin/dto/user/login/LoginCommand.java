package com.xkyii.spry.admin.dto.user.login;

import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import static com.xkyii.spry.admin.constant.AdminError.*;

@Schema(description = "用户登录参数")
public class LoginCommand {

    @NotBlank(message = 校验用户名不能为空)
    @Length(min=2, max=20, message = 校验用户名长度)
    @Schema(title="用户名, 长度区间: [2, 20]", required = true)
    private String username;

    @NotBlank(message = 校验用户密码不能为空)
    @Schema(title="用户密码(密文), 非空", required = true)
    private String password;

    @Schema(title="验证码")
    private String code;

    @Schema(title="唯一标识")
    private String uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
