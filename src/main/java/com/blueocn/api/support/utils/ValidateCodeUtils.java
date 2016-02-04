package com.blueocn.api.support.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Title: ValidateCodeUtils
 * Description: 验证码生成工具类, 可于用户邮箱验证链接验证码等.
 * 包含验证码过期时间, 便于判断从 Redis 一类的缓存中无法读取验证码信息时, 是否要从数据库中读取.
 * <p/>
 * <strong>验证码可用字符有:</strong>
 * 26 个小写字母, 26 个大写字母, 10 个数字, 共计62个字符.
 * <p/>
 * <strong>验证码生成逻辑:</strong>
 * 将验证码过期时间变为13位的timeMiles数字.
 * 对于 timeMiles 的 0~9 每个数字, 随机对应 6~7 个字符, 生成特定字典.
 * 十三位数字变为一个随机性比较高的字符串, 前面和后面拼接上相同长度随机字符串, 十三位数字的编码中间插入单随机字符.
 * <p/>
 * <strong>本工具类使用的随机字典:</strong>
 * '0' -> {'0', 't', 'x', 'p', 'y', 'I'}
 * '1' -> {'o', '9', 'j', 'Q', 'P', '5'}
 * '2' -> {'K', 'f', 'z', 'J', 'B', 'M'}
 * '3' -> {'i', 'H', 'R', 'h', 'w', '8'}
 * '4' -> {'1', 'k', 'C', 'T', 'g', '4'}
 * '5' -> {'U', '3', 'N', 'l', '7', 'V'}
 * '6' -> {'F', 's', 'n', 'Y', '2', 'D'}
 * '7' -> {'G', 'S', 'q', 'd', 'A', 'u'}
 * '8' -> {'e', 'v', 'c', 'E', '6', 'X'}
 * '9' -> {'Z', 'L', 'O', 'W', 'b', 'm'}
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 22:01
 */
public final class ValidateCodeUtils {
    private static final String DICTIONARY =
        "0oKi1UFGeZt9fHk3sSvLxjzRCNnqcOpQJhTlYdEWyPBwg72A6bI5M84VDuXm";

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateCodeUtils.class);

    private ValidateCodeUtils() {
        // No Construct Function
    }

    /**
     * 生成包含失效时间的定长随机字符串
     *
     * @param expiredTime 从当前系统时间计算, 多久后失效. 默认单位, 分钟.
     *                    如果为空, 默认30分钟后失效.
     * @return 随机字符串
     */
    public static String generateCode(Integer expiredTime) {
        LocalDateTime dateTime =
            LocalDateTime.now().plusMinutes(expiredTime == null ? 30 : expiredTime);
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        String encryptTimeMiles = encodeTimeMiles(date.getTime());
        return RandomStringUtils.randomAlphanumeric(3) + encryptTimeMiles + RandomStringUtils.randomAlphanumeric(3);
    }

    /**
     * 验证 validateCode 是否超时失效
     *
     * @param validateCode 验证码
     * @return 是否失效
     */
    public static boolean isCodeNotExpired(String validateCode) {
        return decodeTimeMiles(validateCode) > SystemClock.now();
    }

    /**
     * 将时间戳翻译为乱序字符串
     *
     * @param timeMiles 时间戳
     * @return 乱序字符串
     */
    private static String encodeTimeMiles(Long timeMiles) {
        Preconditions.checkNotNull(timeMiles, "时间参数错误");
        return encode(new StringBuilder(), timeMiles).toString();
    }

    /**
     * 将编码的乱序字符串解码为时间戳
     *
     * @param encodeStr 编码的字符串
     * @return 时间戳
     */
    private static Long decodeTimeMiles(String encodeStr) {
        Long timeMines = 0L;
        for (int i = 0; i < encodeStr.length(); i += 2) {
            timeMines = timeMines * 10 + (DICTIONARY.indexOf(encodeStr.charAt(i)) % 10);
        }
        return timeMines;
    }

    /**
     * 内部方法, 递归生成实际的验证码
     *
     * @param builder   {@code StringBuilder}
     * @param timeMiles 实际的时间戳
     * @return 包含时间的乱序编码的验证码
     */
    private static StringBuilder encode(StringBuilder builder, Long timeMiles) {
        builder.append(RandomStringUtils.randomAlphanumeric(1));
        if (timeMiles < 10) {
            builder.append(getRandomChar(timeMiles.intValue()));
            return builder.reverse();
        } else {
            Long thisIndex = timeMiles % 10;
            builder.append(getRandomChar(thisIndex.intValue()));
            return encode(builder, timeMiles / 10);
        }
    }

    /**
     * 从字典字符串中获取所需的字符
     *
     * @param numeric 实际的数字
     * @return 参数数字对应的编码字符
     */
    private static Character getRandomChar(int numeric) {
        if (numeric > 9) {
            throw new IndexOutOfBoundsException("随机字符串越界");
        }
        return DICTIONARY.charAt(((int) (Math.random() * 6)) * 10 + numeric);
    }

    // Test Code
    public static void main(String[] args) {
        Long now = SystemClock.now();
        String encode = encodeTimeMiles(now);
        Long decode = decodeTimeMiles(encode);
        LOGGER.info("Now: " + now + " Encode: " + encode + " Decode: " + decode);

        String validateCode = generateCode(30);
        Long timeMiles = decodeTimeMiles(validateCode.substring(3, 29));
        Date date = new Date(timeMiles);
        LOGGER.info("Code Not Expired? " + isCodeNotExpired(validateCode));
        LOGGER.info("Validate Code: " + validateCode + " TimeMiles: " + timeMiles + " Date: " + date);
    }
}
