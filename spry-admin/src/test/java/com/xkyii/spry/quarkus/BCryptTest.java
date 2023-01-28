package com.xkyii.spry.quarkus;

import com.xkyss.mocky.unit.text.Strings;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class BCryptTest {

    @Test
    public void test_01() {
        String text = "123456";
        String s1 = BcryptUtil.bcryptHash(text);
        String s2 = BcryptUtil.bcryptHash(text);

        Assertions.assertNotNull(s1);
        Assertions.assertNotNull(s2);
        // 每次加密都用的随机盐,结果都是不一样的
        Assertions.assertNotEquals(s1, s2);

        // 但是都能匹配上
        Assertions.assertTrue(BcryptUtil.matches(text, s1));
        Assertions.assertTrue(BcryptUtil.matches(text, s2));
    }

    @Test
    public void test_02() {
        String text = "123456";

        Strings strings = new Strings(ThreadLocalRandom.current());
        String salt = strings.size(16).get();

        String encoded = BcryptUtil.bcryptHash(text, 10, salt.getBytes());
        Assertions.assertNotNull(encoded);

        boolean matches = BcryptUtil.matches(text, encoded);
        Assertions.assertTrue(matches);
    }
}
