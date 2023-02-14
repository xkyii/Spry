package com.xkyii.spry.admin.dto.login;

import com.xkyii.spry.admin.entity.SysUser;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class RegisterOutput {

    @Schema(title="用户名")
    private String username;

    public static RegisterOutput from(SysUser user) {
        RegisterOutput output = new RegisterOutput();
        output.setUsername(user.getUserName());
        return output;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
