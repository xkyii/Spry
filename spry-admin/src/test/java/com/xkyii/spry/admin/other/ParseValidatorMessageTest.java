package com.xkyii.spry.admin.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ParseValidatorMessageTest {

    /**
     * 测试解析Hibernate Validate自定义字符串解析
     */
    @Test
    public void testParse() {
        String message = "10001, 1, 10";
        String[] split = message.trim().split("\\s*,\\s*");
        String key = split[0];
        String[] values = Arrays.copyOfRange(split, 1, split.length);

        Assertions.assertEquals("10001", key);
        Assertions.assertEquals("1", values[0]);
        Assertions.assertEquals("10", values[1]);
    }
}
