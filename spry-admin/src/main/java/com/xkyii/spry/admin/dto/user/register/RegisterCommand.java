package com.xkyii.spry.admin.dto.user.register;

import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import static com.xkyii.spry.admin.constant.AdminError.*;
import static com.xkyii.spry.admin.constant.AdminError.校验用户密码长度;


@Schema(description = "用户注册参数")
public class RegisterCommand {

    @NotBlank(message = 校验用户名不能为空)
    @Length(min=2, max=20, message = 校验用户名长度)
    @Schema(title="用户名, 长度区间: [2, 20]", required = true)
    private String username;

    @NotBlank(message = 校验用户密码不能为空)
    @Length(min=5, max=256, message = 校验用户密码长度)
    @Schema(title="用户密码(明文), 长度区间: [5, 256]", required = true)
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
