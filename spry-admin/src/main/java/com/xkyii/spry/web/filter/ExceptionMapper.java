package com.xkyii.spry.web.filter;

import com.xkyii.spry.framework.dto.AjaxResult;
import com.xkyii.spry.web.entity.SysLoginInfo;
import com.xkyss.quarkus.server.service.ErrorMessageService;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<Exception> {
    @Inject
    Logger logger;

    @Inject
    ErrorMessageService ems;

    @Override
    public Response toResponse(Exception e) {
        logger.warn("Exception", e);
        AjaxResult ar = AjaxResult.error();
        ar.setMessage(ems.getMessage(ar.getCode()));

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        ar.put("data", JsonObject.of(
            "type", escapeJsonString(e.getClass().getName()),
            "stack", escapeJsonString(stringWriter.toString().trim()))
            .getMap());

        return Response.ok(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(ar)
            .build();
    }

    static String escapeJsonString(final String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(ch);
            }
        }
        return sb.toString();
    }
}
