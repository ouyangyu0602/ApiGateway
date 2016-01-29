package com.blueocn.api.support.utils.password;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

/**
 * Title: PasswordUtils
 * Description: Simple Convenient Function For Handle Password Encoding & Salt Generate.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-06 21:54
 */
public final class PasswordUtils {

    private PasswordUtils() {
        // No Construct function
    }

    /**
     * 密码加密工具类, 用给定的未密码生成一个不可逆的加密字符串和盐值
     *
     * @param rawPassword 未加密的密码
     * @return 加密后的密码
     */
    public static Pair<String, String> encodePassword(String rawPassword) {
        String salt = generateSalt(null);
        return ImmutablePair.of(encodePassword(rawPassword, salt), salt);
    }

    /**
     * 密码加密工具类, 用给定的未密码和盐值生成一个不可逆的加密字符串
     *
     * @param rawPassword 未加密的代码
     * @param salt        盐值
     * @return 加密后的密码
     */
    public static String encodePassword(String rawPassword, String salt) {
        ShaEncoder encode = new ShaEncoder(512);
        return encode.encodePassword(checkNotNull(emptyToNull(rawPassword), "待加密的密码"), checkNotNull(
            emptyToNull(salt), "加密盐值"));
    }

    /**
     * 按照给定的长度生成指定的随机字符串作为盐值
     *
     * @param length 随机字符串的长度, 如果为空, 则为32位
     * @return 随机字符串
     */
    public static String generateSalt(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length == null ? 32 : length);
    }

    /**
     * 按照给定的密码和盐值进行加密, 判断是否与期待的加密密码一致
     *
     * @param rawPassword       未加密的密码
     * @param encryptedPassword 已经加密了的密码
     * @param salt              盐值
     * @return 密码校验是否成功
     */
    public static boolean isPasswordValid(String rawPassword, String encryptedPassword, String salt) {
        return encodePassword(rawPassword, salt).equals(encryptedPassword);
    }
}
