package com.xkyii.spry.admin.manager;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class ErrorMessageManagerTest {
    @Inject
    ErrorMessageManager emm;

    @Test
    public void test_01() {
        Assertions.assertEquals("成功", emm.getMessage("0"));
    }

}
