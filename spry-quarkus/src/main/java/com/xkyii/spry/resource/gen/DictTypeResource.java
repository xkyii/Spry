package com.xkyii.spry.resource.gen;

import com.xkyii.spry.entity.DictType;
import com.xkyii.spry.repository.DictTypeRepository;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/api/dict-type")
public interface DictTypeResource extends PanacheRepositoryResource<DictTypeRepository, DictType, Long> {
}
