package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
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

import java.util.Set;

@QuarkusTest
public class SysPermissionServiceTest {

    @Inject
    SysPermissionService permissionService;

    @InjectMock
    SysRoleService roleService;

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

}
