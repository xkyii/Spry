package com.xkyii.spry.admin.dto.test;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class TestValidateDto {

    @NotBlank
    private String name;

    @Length(min=2, max=20)
    private String code;

    @Valid
    private List<InnerDto> inner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InnerDto> getInner() {
        return inner;
    }

    public void setInner(List<InnerDto> inner) {
        this.inner = inner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class InnerDto {

        @NotBlank
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
