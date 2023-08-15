package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysRole;
import com.xkyii.spry.web.repository.SysRoleRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class SysRoleService {

    @Inject
    SysRoleRepository roleRepository;

    public Uni<Set<String>> selectRolePermissionByUserId(Long userId) {
        return roleRepository.selectRolePermissionByUserId(userId)
            .onItem().transform(roles -> roles.stream()
                .filter(Objects::nonNull)
                .map(SysRole::getRoleKey)
                .map(key -> key.trim().split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet()));
    }

    public Uni<Long> count() {
        return roleRepository.count();
    }
}
