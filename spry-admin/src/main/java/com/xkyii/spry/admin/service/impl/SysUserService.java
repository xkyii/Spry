package com.xkyii.spry.admin.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.admin.constant.UserStatus;
import com.xkyii.spry.admin.dto.login.LoginInput;
import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.dto.login.RegisterOutput;
import com.xkyii.spry.admin.dto.login.TokenOutput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysUserRepository;
import com.xkyii.spry.admin.service.ISecureService;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.admin.service.ITokenService;
import com.xkyii.spry.common.error.ApiException;
import com.xkyss.mocky.unit.text.Strings;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class SysUserService implements ISysUserService {

    @Inject
    SysUserRepository userRepository;

    @Inject
    ITokenService tokenService;

    @Inject
    ISecureService secureService;


    @ReactiveTransactional
    public Uni<RegisterOutput> register(@Valid RegisterInput input) {
        String username = input.getUsername();

        return userRepository.find("username", username).firstResult()
            // 如果查询到已有username的用户,报异常
            .onItem().ifNotNull().failWith(new ApiException(AdminError.用户名已经被注册, username))
            // 否则添加这个用户
            .onItem().ifNull().switchTo(() -> {
                String password = input.getPassword();

                SysUser user = new SysUser();
                user.setUsername(username);
                String saltMd5 = DigestUtil.md5Hex16(username.toUpperCase()).toUpperCase();
                user.setPassword(BcryptUtil.bcryptHash(password, 10, saltMd5.getBytes()));
                // 默认值
                user.setDeleted((byte) 0);
                user.setNickName(input.getUsername());
                user.setStatus(UserStatus.正常);
                return userRepository.persist(user);
            })
            .onItem().transform(RegisterOutput::from)
            ;
    }

    @Override
    public Uni<TokenOutput> login(@Valid LoginInput input) {
        String username = input.getUsername();
        return userRepository.find("username", username).firstResult()
                .onItem().ifNull().failWith(new ApiException(AdminError.用户不存在, username))
                // 校验密码
                .onItem().invoke(Unchecked.consumer(u -> {
                    String decryptPassword = secureService.decrypt(input.getPassword());
                    String saltMd5 = DigestUtil.md5Hex16(username.toUpperCase()).toUpperCase();
                    String decryptHash = BcryptUtil.bcryptHash(decryptPassword, 10, saltMd5.getBytes());
                    if (!Objects.equals(decryptHash, u.getPassword())) {
                        throw new ApiException(AdminError.密码错误);
                    }
                }))
                // 置空密码
                .onItem().invoke(user -> {
                    user.setPassword(null);
                })
                // 生成token
                .onItem().transform(u -> new TokenOutput(tokenService.generateToken(u)))
                ;
    }

}
