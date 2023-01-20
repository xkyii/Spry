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

    /**
     * {}内指定数字无效
     */
    @Test
    public void stringFormatTest2() {
        Assertions.assertEquals("Hello {1}", Strings.format("Hello {1}", "World"));
        Assertions.assertEquals("Hello {1}", Strings.format("Hello {1}", 1));
        Assertions.assertEquals("Hello {1}", Strings.format("Hello {1}", 1.1));
    }

    /**
     * {}内指定字符无效
     */
    @Test
    public void stringFormatTest3() {
        Assertions.assertEquals("Hello {w}", Strings.format("Hello {w}", "World"));
        Assertions.assertEquals("Hello {w}", Strings.format("Hello {w}", 1));
        Assertions.assertEquals("Hello {w}", Strings.format("Hello {w}", 1.1));
    }
}
