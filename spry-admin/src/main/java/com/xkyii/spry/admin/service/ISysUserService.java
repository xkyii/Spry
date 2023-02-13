package com.xkyii.spry.admin.service;

import com.xkyii.spry.admin.dto.login.LoginInput;
import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.dto.login.RegisterOutput;
import com.xkyii.spry.admin.dto.login.TokenOutput;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;

public interface ISysUserService {

    /**
     * 注册用户
     */
    Uni<RegisterOutput> register(@Valid RegisterInput input);

    /**
     * 登录用户
     */
    Uni<TokenOutput> login(@Valid LoginInput input);
}
