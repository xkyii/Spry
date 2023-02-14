package com.xkyii.spry.admin.filter;

import com.xkyii.spry.admin.dto.error.ValidateOutput;
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

        List<ValidateOutput> outputs = new ArrayList<>();
        for (ConstraintViolation<?> cv: cve.getConstraintViolations()) {
            String[] split = cv.getMessage().trim().split("\\s*,\\s*");
            String key = split[0].trim();
            ValidateOutput vo = new ValidateOutput();
            vo.setField(cv.getPropertyPath().toString());
            vo.setCode(key);
            if (split.length > 1) {
                String message = Stringx.format(emm.getMessage(key), (Object[]) Arrays.copyOfRange(split, 1, split.length));
                vo.setMessage(message);
            }
            else {
                vo.setMessage(emm.getMessage(key));
            }
            outputs.add(vo);
        }

        com.xkyii.spry.common.dto.Response<List<ValidateOutput>> r =
                new com.xkyii.spry.common.dto.Response<>(ErrorCode.参数校验失败, emm.getMessage(ErrorCode.参数校验失败), outputs);
        builder.entity(r);
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        return builder.build();
    }
}
