package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SysPermissionService {

    /**
     * 获取用户的权限列表
     */
    public Uni<Set<String>> getRolePermission(SysUser user) {
        Set<String> set = new HashSet<>();
        set.add("User");
        set.add("Admin");
        return Uni.createFrom().item(set);
    }
}
