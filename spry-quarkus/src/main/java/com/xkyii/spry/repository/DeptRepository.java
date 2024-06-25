package com.xkyii.spry.repository;

import com.xkyii.spry.entity.Dept;
import com.xkyii.spry.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.List;

@ApplicationScoped
public class DeptRepository implements PanacheRepository<Dept> {
    public List<Dept> findTree(Long id) {
        PanacheQuery<Dept> query = find("FROM Dept d WHERE d.id=?1 OR d.parentId=?1", id);
        List<Dept> list = query.list();
        return list;
    }
}
