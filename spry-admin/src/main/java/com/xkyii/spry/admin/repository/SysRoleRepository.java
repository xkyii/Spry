
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysRole;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysRoleRepository implements PanacheRepository<SysRole> {

    public Uni<SysRole> getRoleOfUser(Long userId) {
//        return find("Select r From SysRole as r LEFT JOIN SysUser as u ON r.deleted=0 AND u.userId=?1", userId)
        return find("#SysRole.getRoleOfUser", userId)
            .firstResult();
    }

}
