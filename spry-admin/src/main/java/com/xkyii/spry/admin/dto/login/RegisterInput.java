package com.xkyii.spry.admin.dto.login;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class RegisterInput {
    /**
     * 用户名
     */
    @NotBlank(message = "{spry.error-code.11001}")
    @Length(min=2, max=20, message = "{spry.error-code.11002}")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "{spry.error-code.11003}")
    @Length(min=5, max=20, message = "{spry.error-code.11004}")
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
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
