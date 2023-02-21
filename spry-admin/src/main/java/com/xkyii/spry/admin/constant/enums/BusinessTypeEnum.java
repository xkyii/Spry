package com.xkyii.spry.admin.constant.enums;

import com.xkyii.spry.admin.constant.CssTag;
import com.xkyii.spry.common.constant.DictionaryEnum;

public enum BusinessTypeEnum implements DictionaryEnum<Integer> {

    /**
     * 操作类型
     */
    OTHER(0, "其他操作", CssTag.INFO),
    ADD(1, "添加", CssTag.INFO),
    MODIFY(2, "修改", CssTag.INFO),
    DELETE(3, "删除", CssTag.DANGER),
    GRANT(4, "授权", CssTag.PRIMARY),
    EXPORT(5, "导出", CssTag.WARNING),
    IMPORT(6, "导入", CssTag.WARNING),
    FORCE_LOGOUT(7, "强退", CssTag.DANGER),
    CLEAN(8, "清空", CssTag.DANGER),
        ;

    public static final String Key = "sys_operation_type";

    private final int value;
    private final String description;
    private final String cssTag;

    BusinessTypeEnum(int value, String description, String cssTag) {
        this.value = value;
        this.description = description;
        this.cssTag = cssTag;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String cssTag() {
        return cssTag;
    }
}
