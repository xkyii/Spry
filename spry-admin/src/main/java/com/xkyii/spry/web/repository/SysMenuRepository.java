package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysMenu;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SysMenuRepository implements PanacheRepository<SysMenu> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Uni<List<String>> selectMenuPermsByUserId(Long userId) {
        Map<String, Object> parameters = Parameters.with("userId", userId).map();

        return find("#SysMenu.selectMenuPermsByUserId", parameters)
            .project(String.class)
            .list();
    }
    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    public Uni<List<String>> selectMenuPermsByRoleId(Long roleId) {
        Map<String, Object> parameters = Parameters.with("roleId", roleId).map();
        return find("#SysMenu.selectMenuPermsByRoleId", parameters)
            .project(String.class)
            .list();
    }
}
