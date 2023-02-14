package com.xkyii.spry.admin.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.admin.dto.login.LoginInput;
import com.xkyii.spry.admin.dto.login.RegisterInput;
import com.xkyii.spry.admin.dto.login.RegisterOutput;
import com.xkyii.spry.admin.dto.login.TokenOutput;
import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.repository.SysUserRepository;
import com.xkyii.spry.admin.service.ISysUserService;
import com.xkyii.spry.common.error.ApiException;
import com.xkyss.mocky.unit.text.Strings;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.jwt.util.KeyUtils;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.security.PrivateKey;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class SysUserService implements ISysUserService {

    @Inject
    Logger logger;

    @Inject
    SysUserRepository userRepository;

    @Inject
    TokenService tokenService;

    @ConfigProperty(name = "smallrye.jwt.sign.key.location")
    String privateKeyLocation;

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
                user.setUserName(username);

                Strings strings = new Strings(ThreadLocalRandom.current());
                user.setSalt(strings.size(16).get());
                user.setPassword(BcryptUtil.bcryptHash(password, 10, user.getSalt().getBytes()));
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
                    if (Objects.equals(decryptPassword(input.getPassword()), u.getPassword())) {
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

    private String decryptPassword(String password) {
        try {
            PrivateKey privateKey = KeyUtils.readPrivateKey(privateKeyLocation.trim());
            RSA rsa = SecureUtil.rsa(privateKey.getEncoded(), null);
            byte[] decrypt = rsa.decrypt(Base64.decode(password), KeyType.PrivateKey);
            String decryptPassword = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
            logger.info("解出明文: " + decryptPassword);
            return decryptPassword;

        } catch (Exception e) {
            throw new ApiException(AdminError.密码格式错误);
        }
    }
}
