package com.xkyii.spry.repository;

import com.xkyii.spry.entity.Dept;
import com.xkyii.spry.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeptRepository implements PanacheRepository<Dept> {
}
