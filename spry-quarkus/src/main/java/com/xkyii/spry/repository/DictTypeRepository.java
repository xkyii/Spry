package com.xkyii.spry.repository;

import com.xkyii.spry.entity.DictType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DictTypeRepository implements PanacheRepository<DictType> {
}
