package com.xkyii.spry.web.service;

import com.xkyii.spry.common.constant.DelFlagType;
import com.xkyii.spry.common.constant.StatusType;
import com.xkyii.spry.web.entity.SysRole;
import com.xkyii.spry.web.repository.SysRoleRepository;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import io.quarkus.test.vertx.UniAsserter;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

@QuarkusTest
public class SysRoleServiceTest {
    @InjectMock
    SysRoleRepository roleRepository;

    @Inject
    SysRoleService roleService;

    @Test
    @RunOnVertxContext
    public void test_count(UniAsserter asserter) {

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

    @Test
    @RunOnVertxContext
    public void test_selectRolePermissionByUserId(UniAsserter asserter) {

        Long roleId = 1L;

        // 准备一个测试用的SysRole
        SysRole defaultRole = new SysRole();
        defaultRole.setRoleId(roleId);
        defaultRole.setRoleName("超级管理员");
        defaultRole.setRoleKey("admin");
        defaultRole.setRoleSort(1);
        defaultRole.setDataScope("1");
        defaultRole.setMenuCheckStrictly(true);
        defaultRole.setDeptCheckStrictly(true);
        defaultRole.setStatus(StatusType.正常.name());
        defaultRole.setDelFlag(DelFlagType.未删除.name());
        defaultRole.setFlag(false);
        defaultRole.setRemark(defaultRole.getRoleName());
        defaultRole.setCreateBy(defaultRole.getRoleKey());
        defaultRole.setCreateTime(new Date());

        // Mock数据
        asserter.execute(() -> {
            Mockito.when(roleRepository.selectRolePermissionByUserId(roleId)).thenReturn(Uni.createFrom().item(List.of(defaultRole)));
        });

        // 检查一下Mock数据
        asserter.assertThat(
            () -> roleRepository.selectRolePermissionByUserId(roleId),
            roles -> {
                Assertions.assertNotNull(roles);
                Assertions.assertEquals(1, roles.size());
                SysRole role = roles.get(0);
                Assertions.assertEquals(role.getRoleId(), defaultRole.getRoleId());
            }
        );

        // 检查真正需要测试的功能: selectRolePermissionByUserId
        asserter.assertThat(
            () -> roleService.selectRolePermissionByUserId(roleId),
            permissions -> {
                Assertions.assertNotNull(permissions);
                Assertions.assertEquals(1, permissions.size());
                Assertions.assertTrue(permissions.contains("admin"));
            }
        );

        asserter.surroundWith(u -> Panache.withSession(() -> u));
    }
}
