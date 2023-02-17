package com.xkyii.spry.admin.dto.error;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "参数校验输出")
public class ValidateDto {

    @Schema(title="字段名")
    private String field;

    @Schema(title="信息")
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
