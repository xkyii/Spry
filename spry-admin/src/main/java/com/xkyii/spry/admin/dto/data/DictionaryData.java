package com.xkyii.spry.admin.dto.data;

import cn.hutool.core.util.StrUtil;
import com.xkyii.spry.common.constant.DictionaryEnum;

public class DictionaryData {

    private String label;
    private String value;
    private String cssTag;

    @SuppressWarnings("rawtypes")
    public DictionaryData(DictionaryEnum enumType) {
        if (enumType != null) {
            this.label = enumType.description();
            this.value = StrUtil.toString(enumType.getValue());
            this.cssTag = enumType.cssTag();
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCssTag() {
        return cssTag;
    }

    public void setCssTag(String cssTag) {
        this.cssTag = cssTag;
    }
}
