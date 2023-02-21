package com.xkyii.spry.admin.constant.enums;


import com.xkyii.spry.admin.constant.CssTag;
import com.xkyii.spry.common.constant.DictionaryEnum;

/**
 * 对应sys_menu表的is_visible字段
 * @author valarchie
 */
public enum VisibleStatusEnum implements DictionaryEnum<Integer> {

    /**
     * 显示与否
     */
    SHOW(1, "显示", CssTag.PRIMARY),
    HIDE(0, "隐藏", CssTag.DANGER);

    public static final String Key = "sys_visible";
    private final int value;
    private final String description;
    private final String cssTag;

    VisibleStatusEnum(int value, String description, String cssTag) {
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
