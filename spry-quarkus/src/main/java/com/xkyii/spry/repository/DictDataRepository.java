package com.xkyii.spry.repository;

import com.xkyii.spry.entity.DictData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DictDataRepository implements PanacheRepository<DictData> {
}
