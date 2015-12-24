/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.redis;

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
public abstract class KeyUtils {

    static final String UID = "uid:";

    static String following(String uid) {
        return UID + uid + ":following";
    }

    static String followers(String uid) {
        return UID + uid + ":followers";
    }

    static String timeline(String uid) {
        return UID + uid + ":timeline";
    }

    static String mentions(String uid) {
        return UID + uid + ":mentions";
    }

    static String posts(String uid) {
        return UID + uid + ":posts";
    }

    static String auth(String uid) {
        return UID + uid + ":auth";
    }

    static String uid(String uid) {
        return UID + uid;
    }

    static String post(String pid) {
        return "pid:" + pid;
    }

    static String authKey(String auth) {
        return "auth:" + auth;
    }

    public static String user(String name) {
        return "user:" + name + ":uid";
    }

    static String users() {
        return "users";
    }

    static String timeline() {
        return "timeline";
    }

    static String globalUid() {
        return "global:uid";
    }

    static String globalPid() {
        return "global:pid";
    }

    static String alsoFollowed(String uid, String targetUid) {
        return UID + uid + ":also:uid:" + targetUid;
    }

    static String commonFollowers(String uid, String targetUid) {
        return UID + uid + ":common:uid:" + targetUid;
    }
}
