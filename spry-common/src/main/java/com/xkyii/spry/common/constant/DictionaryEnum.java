package com.xkyii.spry.common.constant;

/**
 * 字典类型 接口
 * @author xkyii
 */
public interface DictionaryEnum<T> extends BasicEnum<T> {

    /**
     * 获取css标签
     * @return css标签
     */
    String cssTag();
}
