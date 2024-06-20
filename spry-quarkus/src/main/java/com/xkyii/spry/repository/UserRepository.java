package com.xkyii.spry.repository;

import com.xkyii.spry.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Set<String> queryRoleCodes(Long userId) {
        PanacheQuery<String> query = find("SELECT r.code FROM UserRole ur join Role r ON ur.roleId=r.id WHERE ur.userId=?1", userId)
            .project(String.class);
        List<String> list = query.list();
        return new HashSet<>(list);
    }
}
