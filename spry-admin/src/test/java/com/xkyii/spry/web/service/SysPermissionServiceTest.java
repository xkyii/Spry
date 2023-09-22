package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysRole;
import com.xkyii.spry.web.entity.SysUser;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

@QuarkusTest
public class SysPermissionServiceTest {

    @Inject
    SysPermissionService permissionService;

    @InjectMock
    SysRoleService roleService;

    @InjectMock
    SysMenuService menuService;

    @Test
    @RunOnVertxContext
    @DisplayName("getRolePermission: 管理员")
    public void test_getRolePermission_admin(UniAsserter asserter) {

        SysUser user = new SysUser();
        user.setUserId(1L);

        asserter.assertThat(
            () -> permissionService.getRolePermission(user),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertEquals(1, permissions.size());
                Assertions.assertTrue(permissions.contains("admin"));
            });
    }

    @Test
    @RunOnVertxContext
    @DisplayName("getRolePermission: 非管理员")
    public void test_getRolePermission_not_admin(UniAsserter asserter) {

        SysUser user = new SysUser();
        user.setUserId(2L);

        asserter.execute(() -> {
            Mockito.when(roleService.selectRolePermissionByUserId(user.getUserId()))
                .thenReturn(Uni.createFrom().item(Set.of("common")));
        });

        asserter.assertThat(
            () -> permissionService.getRolePermission(user),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertEquals(1, permissions.size());
                Assertions.assertTrue(permissions.contains("common"));
            });
    }

    @Test
    @RunOnVertxContext
    @DisplayName("getMenuPermission: 管理员")
    public void test_getMenuPermission_admin(UniAsserter asserter) {
        SysUser user = new SysUser();
        user.setUserId(1L);

        asserter.assertThat(
            () -> permissionService.getMenuPermission(user),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertEquals(1, permissions.size());
                Assertions.assertTrue(permissions.contains("*:*:*"));
            });
    }

    @Test
    @RunOnVertxContext
    @DisplayName("getMenuPermission: 非管理员 且角色为空")
    public void test_getMenuPermission_not_admin_and_noll_roles(UniAsserter asserter) {
        SysUser user = new SysUser();
        user.setUserId(2L);

        // Mock数据
        asserter.execute(() -> {
            Mockito.when(menuService.selectMenuPermsByUserId(user.getUserId()))
                .thenReturn(Uni.createFrom().item(Set.of("system:user:list", "monitor:online:list")));
        });

        asserter.assertThat(
            () -> permissionService.getMenuPermission(user),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertEquals(2, permissions.size());
                Assertions.assertTrue(permissions.contains("system:user:list"));
                Assertions.assertTrue(permissions.contains("monitor:online:list"));
            });
    }

    @Test
    @RunOnVertxContext
    @DisplayName("getMenuPermission: 非管理员 且角色不为空")
    public void test_getMenuPermission_not_admin_and_roles(UniAsserter asserter) {
        SysUser user = new SysUser();
        user.setUserId(2L);

        SysRole role1 = new SysRole();
        role1.setRoleId(1L);
        SysRole role2 = new SysRole();
        role2.setRoleId(2L);
        user.setRoles(List.of(role1, role2));

        // Mock数据
        asserter.execute(() -> {
            Mockito.when(menuService.selectMenuPermsByRoleId(role1.getRoleId()))
                .thenReturn(Uni.createFrom().item(Set.of("system:user:list", "monitor:online:list")));
            Mockito.when(menuService.selectMenuPermsByRoleId(role2.getRoleId()))
                .thenReturn(Uni.createFrom().item(Set.of("system:user:list", "tool:build:list")));
        });

        asserter.assertThat(
            () -> permissionService.getMenuPermission(user),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertEquals(3, permissions.size());
                Assertions.assertTrue(permissions.contains("system:user:list")); // 重复的被合并
                Assertions.assertTrue(permissions.contains("monitor:online:list"));
                Assertions.assertTrue(permissions.contains("tool:build:list"));
            });
    }
}
