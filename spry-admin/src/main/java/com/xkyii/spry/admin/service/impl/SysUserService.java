package com.xkyii.spry.admin.service.impl;

import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysUserRepository;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.common.error.ApiException;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SysUserService implements ISysUserService {

    @Inject
    Logger logger;

    @Inject
    SysUserRepository userRepository;

    public Uni<SysUser> register(RegisterInput input) {
        String username = input.getUsername();

        return userRepository.find("username", username).firstResult()
            .onItem().ifNotNull().failWith(new ApiException(AdminError.用户名已经被注册, username))
            .onItem().ifNull().switchTo(() -> {
                String password = input.getPassword();

                SysUser user = new SysUser();
                user.setUserName(username);
                user.setPassword(password);
                return userRepository.persist(user);
            })
            ;
    }
}
