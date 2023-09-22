package com.xkyii.spry.web.repository;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SysMenuRepositoryTest {

    @Inject
    SysMenuRepository menuRepository;

    @Test
    @RunOnVertxContext
    public void test_selectMenuPermsByUserId_admin(UniAsserter asserter) {
        asserter.assertThat(
            () -> menuRepository.selectMenuPermsByUserId(1L),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertTrue(permissions.isEmpty());
            }
        );
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }

    @Test
    @RunOnVertxContext
    public void test_selectMenuPermsByUserId_not_admin(UniAsserter asserter) {
        asserter.assertThat(
            () -> menuRepository.selectMenuPermsByUserId(2L),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertFalse(permissions.isEmpty());
                Assertions.assertTrue(permissions.contains("system:user:list"));
                Assertions.assertTrue(permissions.contains("monitor:online:list"));
            }
        );
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }

    @Test
    @RunOnVertxContext
    public void test_selectMenuPermsByRoleId_admin(UniAsserter asserter) {
        asserter.assertThat(
            () -> menuRepository.selectMenuPermsByRoleId(1L),
            permission -> {
                Assertions.assertNotNull(permission);
                Assertions.assertTrue(permission.isEmpty());
            }
        );
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }

    @Test
    @RunOnVertxContext
    public void test_selectMenuPermsByRoleId_not_admin(UniAsserter asserter) {
        asserter.assertThat(
            () -> menuRepository.selectMenuPermsByRoleId(2L),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertFalse(permissions.isEmpty());
                Assertions.assertTrue(permissions.contains("system:user:list"));
                Assertions.assertTrue(permissions.contains("monitor:online:list"));
            }
        );
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
