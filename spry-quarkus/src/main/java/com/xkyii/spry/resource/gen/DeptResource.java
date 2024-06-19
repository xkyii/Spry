package com.xkyii.spry.resource.gen;

import com.xkyii.spry.entity.Dept;
import com.xkyii.spry.entity.User;
import com.xkyii.spry.repository.DeptRepository;
import com.xkyii.spry.repository.UserRepository;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/api/dept")
public interface DeptResource extends PanacheRepositoryResource<DeptRepository, Dept, Long> {
}
