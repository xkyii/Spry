package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.constant.AdminError;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

/**
 * 自动给http调用包装为Response
 */
@ApplicationScoped
public class ResponseWrapFilter {

    @ServerResponseFilter
    public void mapResponse(ContainerResponseContext response) {
        // 没有返回体
        if (!response.hasEntity()) {
            return;
        }

        // 已经是Response了
        if (response.getEntity() instanceof com.xkyii.spry.common.dto.Response) {
            return;
        }

        // 只对json返回类型进行处理
        if (!response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)) {
            return;
        }

        // 包装一下
        com.xkyii.spry.common.dto.Response<Object> r = new com.xkyii.spry.common.dto.Response<>(AdminError.成功);
        r.setData(response.getEntity());
        response.setEntity(r);
    }
}
