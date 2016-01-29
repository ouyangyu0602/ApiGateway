package com.blueocn.api.support.redis;

import com.blueocn.api.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class KeyUtilsTest extends BaseTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testOperate() {
        byte[] bytes = redisTemplate.dump("AAAA");
        System.out.println(new String(bytes));
    }
}
