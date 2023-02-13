package com.xkyii.spry.admin.service;

import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.entity.SysUser;
import io.smallrye.mutiny.Uni;

public interface ISysUserService {

    /**
     * 注册用户
     */
    Uni<SysUser> register(RegisterInput input);

    /**
     * 登录用户
     */
    Uni<SysUser> findByUsername(String username);
}
