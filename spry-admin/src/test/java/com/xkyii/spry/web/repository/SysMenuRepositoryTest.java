package com.xkyii.spry.web.repository;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SysMenuRepositoryTest {

    @Inject
    SysMenuRepository menuRepository;

    @Test
    @RunOnVertxContext
    public void test_selectMenuPermsByUserId(UniAsserter asserter) {
        // debug
        asserter.execute(() -> menuRepository.selectMenuPermsByRoleId(2L).onItem().transform(c -> {
            System.out.println(c);
            return c;
        }));

        // test
        asserter.assertEquals(() -> menuRepository.count(), 85L);

        // IMPORTANT: We need to execute the asserter within a reactive session
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
