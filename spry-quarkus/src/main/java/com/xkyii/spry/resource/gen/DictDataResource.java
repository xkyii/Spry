package com.xkyii.spry.resource.gen;

import com.xkyii.spry.entity.DictData;
import com.xkyii.spry.repository.DictDataRepository;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/api/dict-data")
public interface DictDataResource extends PanacheRepositoryResource<DictDataRepository, DictData, Long> {
}
