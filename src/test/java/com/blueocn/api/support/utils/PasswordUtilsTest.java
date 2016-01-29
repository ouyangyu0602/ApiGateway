package com.blueocn.api.support.utils;

import com.blueocn.api.BaseTest;
import com.blueocn.api.support.utils.password.PasswordUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.*;
import org.junit.Assert;

public class PasswordUtilsTest extends BaseTest {

    @Test
    public void testEncodePassword1() throws Exception {
        String rawPassword = PasswordUtils.generateSalt(19);
        Pair<String, String> pair = PasswordUtils.encodePassword(rawPassword);
        logger.info("Raw {}; E {}; S {}", rawPassword, pair.getLeft(), pair.getRight());

        String encode = PasswordUtils.encodePassword(rawPassword, pair.getRight());
        org.junit.Assert.assertTrue(encode.equals(pair.getLeft()));
    }

    @Test
    public void testIsPasswordValid() throws Exception {
        String rawPassword = "ysqDJM5PvczRZ3JqB02";
        String encodePassword = "f6a8ff986ca2bcbf9c52e697fbdfa5b0f55d891c06f254a7e816b0b13728359ae961f626f9fbbbf9f7e614bcfccc2978883b9e6202371be590d09af8b6212bf9";
        String salt = "QgUoe43fHtPdtJS0macwsrGyogD0y46K";
        Assert.assertTrue(PasswordUtils.isPasswordValid(rawPassword, encodePassword, salt));
    }
}
