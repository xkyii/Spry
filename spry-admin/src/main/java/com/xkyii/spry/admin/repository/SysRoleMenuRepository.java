
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysRoleMenu;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysRoleMenuRepository implements PanacheRepository<SysRoleMenu> {
}
