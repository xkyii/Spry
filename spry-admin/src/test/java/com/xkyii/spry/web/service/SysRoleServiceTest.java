package com.xkyii.spry.web.service;

import com.xkyii.spry.web.repository.SysRoleRepository;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class SysRoleServiceTest {
    @InjectMock
    SysRoleRepository roleRepository;

    @Inject
    SysRoleService roleService;

    @Test
    @RunOnVertxContext
    public void test(UniAsserter asserter) {

        asserter.execute(() -> {
            Mockito.when(roleRepository.count()).thenReturn(Uni.createFrom().item(199L));
        });

        // 获得mock的值
        asserter.assertEquals(() -> roleRepository.count(), 199L);
        // 使用了注入的roleRepository,这里也是mock的值
        asserter.assertEquals(() -> roleService.count(), 199L);

        // IMPORTANT: We need to execute the asserter within a reactive session
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
