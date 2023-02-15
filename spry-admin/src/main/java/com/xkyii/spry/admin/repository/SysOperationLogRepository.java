
package com.xkyii.spry.admin.repository;

import com.xkyii.spry.admin.entity.SysOperationLog;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SysOperationLogRepository implements PanacheRepository<SysOperationLog> {
}
