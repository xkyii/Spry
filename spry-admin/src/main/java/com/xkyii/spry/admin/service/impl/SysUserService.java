package com.xkyii.spry.admin.service.impl;

import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.service.ISysUserService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysUserService implements ISysUserService {

    public String register(RegisterInput input) {
        return "register";
    }
}
