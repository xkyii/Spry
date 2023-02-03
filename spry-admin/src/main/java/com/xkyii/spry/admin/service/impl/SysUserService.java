package com.xkyii.spry.admin.service.impl;

import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysUserRepository;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.common.error.ApiException;
import com.xkyss.mocky.unit.text.Strings;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class SysUserService implements ISysUserService {

    @Inject
    Logger logger;

    @Inject
    SysUserRepository userRepository;

    public Uni<SysUser> register(RegisterInput input) {
        String username = input.getUsername();

        return userRepository.find("username", username).firstResult()
            // 如果查询到已有username的用户,报异常
            .onItem().ifNotNull().failWith(new ApiException(AdminError.用户名已经被注册, username))
            // 否则添加这个用户
            .onItem().ifNull().switchTo(() -> {
                String password = input.getPassword();

                SysUser user = new SysUser();
                user.setUserName(username);

                Strings strings = new Strings(ThreadLocalRandom.current());
                user.setSalt(strings.size(16).get());
                user.setPassword(BcryptUtil.bcryptHash(password, 10, user.getSalt().getBytes()));
                return userRepository.persist(user);
            })
            ;
    }
}
