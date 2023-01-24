package com.xkyii.spry.admin.filter;


import com.xkyii.spry.admin.dto.error.ValidateOutput;
import com.xkyii.spry.common.constant.ErrorCode;
import com.xkyii.spry.common.dto.Response;
import com.xkyii.spry.common.error.ErrorMessageManager;
import com.xkyii.spry.common.util.Strings;
import io.quarkus.hibernate.validator.runtime.jaxrs.ViolationReport;
import org.hibernate.reactive.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerResponseContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
class ExceptionMappers {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageManager emm;

    @ServerExceptionMapper
    public RestResponse<String> mapException(ConstraintViolationException x) {
        return RestResponse.status(RestResponse.Status.NOT_FOUND, "mapped Exception > " + x.getClass().getName());
    }

    @ServerResponseFilter
    public void  mapResponse(ContainerResponseContext context) {
        // 参数校验异常
        // TODO: 优化此判断
        if ("true".equals(context.getHeaderString("validation-exception"))) {
            Object entity = context.getEntity();
            if (entity instanceof ViolationReport) {
                List<ValidateOutput> outputs = new ArrayList<>();
                ViolationReport vr = (ViolationReport) entity;
                for (ViolationReport.Violation violation: vr.getViolations()) {
                    if (violation.getMessage() != null && violation.getMessage().length() > 0) {
                        String[] split = violation.getMessage().trim().split("\\s*,\\s*");;
                        String key = split[0].trim();
                        ValidateOutput vo = new ValidateOutput();
                        vo.setField(violation.getField());
                        vo.setCode(key);
                        if (split.length > 1) {
                            String message = Strings.arrayFormat(emm.getMessage(key), Arrays.copyOfRange(split, 1, split.length));
                            vo.setMessage(message);
                        }
                        outputs.add(vo);
                    }
                }
                Response<List<ValidateOutput>> r = new Response<>(ErrorCode.参数校验失败, emm.getMessage(ErrorCode.参数校验失败), outputs);
                context.setEntity(r);
            }
            return;
        }

        if (context.hasEntity()) {
            Object entity = context.getEntity();
            // 对自定义Response填充message
            if (entity instanceof Response) {
                @SuppressWarnings("rawtypes") Response r = (Response) entity;
                if (r.getMessage() == null) {
                    r.setMessage(emm.getMessage(r.getCode()));
                }
            }
        }
    }
}