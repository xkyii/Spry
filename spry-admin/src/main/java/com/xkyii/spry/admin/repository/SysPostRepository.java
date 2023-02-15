
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysPost;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysPostRepository implements PanacheRepository<SysPost> {
}
