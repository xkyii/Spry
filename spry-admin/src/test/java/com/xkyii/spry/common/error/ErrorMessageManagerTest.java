package com.xkyii.spry.common.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorMessageManagerTest {

    @Test
    public void testReadFromCommon() {
        ErrorMessageManager emm = new ErrorMessageManager();
        Assertions.assertEquals("成功", emm.getMessage(0));
    }
}