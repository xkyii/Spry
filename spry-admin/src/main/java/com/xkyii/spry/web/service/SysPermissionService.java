package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SysPermissionService {

    public Uni<Set<String>> getRolePermission(SysUser user) {
        Set<String> set = new HashSet<>();
        return Uni.createFrom().item(set);
    }
}
