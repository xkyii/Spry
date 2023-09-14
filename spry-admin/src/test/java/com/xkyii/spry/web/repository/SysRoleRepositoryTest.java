package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysRole;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SysRoleRepositoryTest {
    @Inject
    SysRoleRepository roleRepository;

    @Test
    @RunOnVertxContext
    public void test_selectMenuPermsByUserId(UniAsserter asserter) {
        asserter.execute(() -> {
        });

        asserter.assertThat(
            () -> roleRepository.selectRolePermissionByUserId(1L),
            roles -> {
                Assertions.assertNotNull(roles);
                Assertions.assertEquals(1, roles.size());
                SysRole role = roles.get(0);
                Assertions.assertEquals(1L, role.getRoleId());
                Assertions.assertEquals("admin", role.getRoleKey());
                Assertions.assertEquals("超级管理员", role.getRoleName());
            }
        );

        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
