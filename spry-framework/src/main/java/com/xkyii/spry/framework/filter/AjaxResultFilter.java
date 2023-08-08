package com.xkyii.spry.framework.filter;

import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerResponseContext;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import static com.xkyii.spry.framework.constant.FrameworkConstants.SPRY_FRAMEWORK_AJAX_RESULT_FILTER_PRIORITY;


@ApplicationScoped
public class AjaxResultFilter {

    @Inject
    ErrorMessageService ems;

    @ServerResponseFilter(priority = SPRY_FRAMEWORK_AJAX_RESULT_FILTER_PRIORITY)
    public void mapResponse(ContainerResponseContext response) {

        // 没有返回体
        if (!response.hasEntity()) {
            return;
        }

        // 只处理AjaxResult类型
        if (!(response.getEntity() instanceof AjaxResult)) {
            return;
        }

        // 填充msg
        AjaxResult ar = (AjaxResult) response.getEntity();
        if (ar.getMessage() == null) {
            ar.setMessage(ems.getMessage(ar.getCode()));
        }
    }
}
