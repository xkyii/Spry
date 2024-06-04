package com.xkyii.spry.resource;

import com.xkyii.spry.entity.User;
import com.xkyii.spry.repository.UserRepository;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(hal = true)
public interface UserResource extends PanacheRepositoryResource<UserRepository, User, Long> {
}
