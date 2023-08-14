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
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            set.add("admin");
        }
        else {
            set.add("aaa");
            set.add("bbb");
            set.add("ccc");
        }
        return Uni.createFrom().item(set);
    }

    public Uni<Set<String>> getMenuPermission(SysUser sysUser) {
        Set<String> set = new HashSet<>();
        set.add("User");
        set.add("Admin");
        return Uni.createFrom().item(set);
    }
}
