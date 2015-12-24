package com.blueocn.api.support.redis;

import com.blueocn.api.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class KeyUtilsTest extends BaseTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testOperate() {
        String someStr = "This is a str";
        redisTemplate.opsForHash().put(KeyUtils.UID, KeyUtils.users(), someStr);
        String getStr = (String) redisTemplate.opsForHash().get(KeyUtils.UID, KeyUtils.users());

        Assert.assertTrue(someStr.equals(getStr));
    }
}
