package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.dto.error.ValidateDto;
import com.xkyii.spry.common.constant.ErrorCode;
import com.xkyii.spry.common.error.ErrorMessageManager;
import com.xkyss.core.util.Stringx;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 校验异常统一处理
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Inject
    Logger logger;

    @Inject
    ErrorMessageManager emm;

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

        List<ValidateDto> outputs = cve.getConstraintViolations().stream()
                .map(cv -> {
                    ValidateDto vo = new ValidateDto();
                    vo.setMessage(cv.getMessage());
                    vo.setField(cv.getPropertyPath().toString());
                    return vo;
                })
                .collect(Collectors.toList());

        com.xkyii.spry.common.dto.Response<List<ValidateDto>> r =
                new com.xkyii.spry.common.dto.Response<>(ErrorCode.参数校验失败, emm.getMessage(ErrorCode.参数校验失败), outputs);
        builder.entity(r);
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        return builder.build();
    }
}
