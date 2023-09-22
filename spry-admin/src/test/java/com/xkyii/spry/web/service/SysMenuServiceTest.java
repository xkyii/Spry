package com.xkyii.spry.web.service;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SysMenuServiceTest {
    @Inject
    SysMenuService menuService;


    @Test
    @RunOnVertxContext
    @DisplayName("selectMenuPermsByUserId-管理员")
    public void test_selectMenuPermsByUserId_admin(UniAsserter asserter) {

        // 管理员拥有所有权限,但没有入库
        asserter.assertThat(
            () -> menuService.selectMenuPermsByUserId(1L),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertTrue(permissions.isEmpty());
            });

        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }

    @Test
    @RunOnVertxContext
    @DisplayName("selectMenuPermsByUserId-管理员")
    public void test_selectMenuPermsByUserId_not_admin(UniAsserter asserter) {

        asserter.assertThat(
            () -> menuService.selectMenuPermsByUserId(2L),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertFalse(permissions.isEmpty());
                // 随便确认几个权限
                Assertions.assertTrue(permissions.contains("system:user:list"));
                Assertions.assertTrue(permissions.contains("monitor:online:list"));
            });

        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
