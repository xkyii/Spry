package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysRole;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysRoleRepository implements PanacheRepository<SysRole> {

    public Uni<SysRole> getRoleOfUser(Long userId) {
        // HQL
//        return find("Select r From SysRole as r LEFT JOIN SysUser as u ON r.deleted=0 AND u.userId=?1", userId)
//            .firstResult();

        return find("SELECT r.roleId, r.roleName FROM SysRole r")
            .project(Result.class)
            .firstResult()
            .map(r -> {
                return new SysRole();
            });

        // Named HQL
//        return find("#SysRole.getRoleOfUser", userId)
//            .firstResult();

        // Native SQL
//        return getSession().flatMap(session -> {
//            return session.createNativeQuery("SELECT * FROM sys_role WHERE role_id=:role_id", SysRole.class)
//                .setParameter("role_id", 1L)
//                .getResultList()
//                .map(list -> {
//                    return list.get(0);
//                })
//            ;
//        });
    }

    public static class Result {
        private Long id;

        private String name;

        public Result(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
