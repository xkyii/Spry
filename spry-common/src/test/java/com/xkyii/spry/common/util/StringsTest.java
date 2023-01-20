package com.xkyii.spry.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringsTest {

    @Test
    public void stringFormatTest() {
        Assertions.assertEquals("Hello World", Strings.format("Hello {}", "World"));
        Assertions.assertEquals("Hello 1", Strings.format("Hello {}", 1));
        Assertions.assertEquals("Hello 1.1", Strings.format("Hello {}", 1.1));
    }
}
