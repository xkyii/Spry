package com.xkyii.spry.admin.dto.login;

import com.xkyii.spry.admin.entity.SysUser;

public class RegisterOutput {
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
