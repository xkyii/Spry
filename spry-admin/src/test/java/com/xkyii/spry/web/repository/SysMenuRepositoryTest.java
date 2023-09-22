package com.xkyii.spry.web.repository;

import com.xkyii.spry.web.entity.SysMenu;
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
        // 纯粹只是看下role==2L的MenuPerms是多少
        asserter.execute(() -> menuRepository.selectMenuPermsByRoleId(2L).onItem().transform(c -> {
            System.out.println(c);
            return c;
        }));

        // 增加一个SysMenu
        asserter.execute(() -> {
            SysMenu menu = new SysMenu();
            menu.setMenuName("测试菜单");
            menu.setMenuType("9");
            menu.setPerms("a:b:c");
            menu.setIsCache("0");
            menu.setIsFrame("1");
            menu.setOrderNum(1);
            menu.setPath("pppp");
            return menuRepository.persist(menu);
        });

        // test
        // 初始化已经又85个了
        asserter.assertEquals(() -> menuRepository.count(), 86L);

        // 确保在同一个Session中执行
        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
