package com.xkyii.spry.admin.service.impl;

import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysUserRepository;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.common.error.ApiException;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static com.xkyii.spry.admin.constant.AdminError.USERNAME_DUPLICATED;

@ApplicationScoped
public class SysUserService implements ISysUserService {

    @Inject
    private SysUserRepository userRepository;

    public Uni<SysUser> register(RegisterInput input) {
        String username = input.getUsername();
        if (isUsernameDuplicated(username)) {
            throw new ApiException(USERNAME_DUPLICATED, username);
        }

        String password = input.getPassword();

        SysUser user = new SysUser();
        user.userName = username;
        user.password = password;
        return userRepository.persist(user);
    }

    public boolean isUsernameDuplicated(String username) {
        return false;
    }
}
