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
     * 判断用户名是否重复
     */
    public boolean isUsernameDuplicated(String username);

}
