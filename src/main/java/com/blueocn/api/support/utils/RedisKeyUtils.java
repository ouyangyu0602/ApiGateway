package com.blueocn.api.support.utils;

/**
 * Title: KeyUtils
 * Description: Simple class keeping all the key patterns to avoid the proliferation of
 * Strings through the code.<br />
 * From: https://github.com/spring-projects/spring-data-keyvalue-examples/blob/master/retwisj/src/main/java/org/springframework/data/redis/samples/retwisj/redis/KeyUtils.java
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-25 02:12
 */
public abstract class RedisKeyUtils {

    static final String UID = "uid:";

    static String uid(String uid) {
        return UID + uid;
    }

    public static String validateKey(String validateCode) {
        return UID + "validate-" + validateCode;
    }
}
