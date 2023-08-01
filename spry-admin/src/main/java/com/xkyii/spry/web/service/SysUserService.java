package com.xkyii.spry.web.service;

import com.xkyii.spry.common.dto.login.LoginCommand;
import com.xkyii.spry.common.dto.login.LoginOutput;
import com.xkyii.spry.common.error.ApiException;
import com.xkyii.spry.web.constant.AdminError;
import com.xkyii.spry.web.repository.SysUserRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SysUserService {
    @Inject
    TokenService tokenService;

    @Inject
    SysUserRepository userRepository;

    @WithSession
    public Uni<LoginOutput> login(LoginCommand input) {
        String username = input.getUsername();
        return userRepository.find("userName", username).firstResult()
            .onItem().ifNull().failWith(new ApiException(AdminError.用户不存在, username))
            // // 校验密码
            // .onItem().invoke(Unchecked.consumer(u -> {
            //     String decryptPassword = secureManager.decrypt(input.getPassword());
            //     String saltMd5 = DigestUtil.md5Hex16(username.toUpperCase()).toUpperCase();
            //     String decryptHash = BcryptUtil.bcryptHash(decryptPassword, 10, saltMd5.getBytes());
            //     if (!Objects.equals(decryptHash, u.getPassword())) {
            //         throw new ApiException(AdminError.密码错误);
            //     }
            // }))
            // // 置空密码
            // .onItem().invoke(user -> {
            //     user.setPassword(null);
            // })
            // 生成token
            .onItem().transform(u -> new LoginOutput(tokenService.generateToken(u)))
            ;
    }
}
