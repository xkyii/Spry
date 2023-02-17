package com.xkyii.spry.admin.dto.user.register;

import com.xkyii.spry.admin.entity.SysUser;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class RegisterDto {

    @Schema(title="用户名")
    private String username;

    public static RegisterDto from(SysUser user) {
        RegisterDto output = new RegisterDto();
        output.setUsername(user.getUsername());
        return output;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
