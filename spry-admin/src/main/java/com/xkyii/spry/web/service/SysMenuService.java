package com.xkyii.spry.web.service;

import com.xkyii.spry.web.repository.SysMenuRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class SysMenuService {
    @Inject
    SysMenuRepository menuRepository;

    public Uni<Set<String>> selectMenuPermsByUserId(Long userId) {
        return menuRepository.selectMenuPermsByUserId(userId)
            .onItem().transform(perms -> perms.stream()
                .filter(Objects::nonNull)
                .map(perm -> perm.trim().split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet()));
    }

    public Uni<Set<String>> selectMenuPermsByRoleId(Long roleId) {
        return menuRepository.selectMenuPermsByRoleId(roleId)
            .onItem().transform(perms -> perms.stream()
                .filter(Objects::nonNull)
                .map(perm -> perm.trim().split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet()));
    }
}
