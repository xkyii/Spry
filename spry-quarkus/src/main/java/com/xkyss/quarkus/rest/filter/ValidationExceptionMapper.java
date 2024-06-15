package com.xkyss.quarkus.rest.filter;

import com.xkyss.quarkus.rest.dto.ValidateInfo;
import com.xkyss.quarkus.rest.error.ErrorCode;
import com.xkyss.quarkus.rest.service.ErrorMessageService;
import io.quarkus.arc.properties.IfBuildProperty;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 校验异常统一处理
 */
@Provider
@IfBuildProperty(name = "xkyss.build.rest.exception-mapper.enabled", stringValue = "true")
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageService errorMessageService;

    @Override
    public Response toResponse(ValidationException ve) {
        if (ve instanceof ConstraintViolationException) {
            return toConstraintViolationResponse((ConstraintViolationException) ve);
        }

        logger.warn("未处理的[ValidationException]异常", ve);
        throw ve;
    }

    private Response toConstraintViolationResponse(ConstraintViolationException cve) {
        Response.Status status = Response.Status.BAD_REQUEST;
        Response.ResponseBuilder builder = Response.status(status);

        List<ValidateInfo> outputs = cve.getConstraintViolations().stream()
            .map(cv -> {
                ValidateInfo vi = new ValidateInfo();
                vi.setMessage(cv.getMessage());
                vi.setField(cv.getPropertyPath().toString());
                return vi;
            })
            .collect(Collectors.toList());

        com.xkyss.quarkus.rest.dto.Response<List<ValidateInfo>> r = new com.xkyss.quarkus.rest.dto.Response<>(
            ErrorCode.参数校验失败, errorMessageService.getMessage(ErrorCode.参数校验失败), outputs, null);
        builder.entity(r);
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        return builder.build();
    }
}
