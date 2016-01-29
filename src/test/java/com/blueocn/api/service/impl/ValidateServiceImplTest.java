package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.service.ValidateService;
import com.blueocn.api.response.ValidateResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ValidateServiceImplTest extends BaseTest {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Test
    public void testGenerateCode() throws Exception {
        Long userId = 12345L;
        String key = validateService.generateCode(userId);
        ValidateResponse response = validateService.isCodeValidate(key);
        Assert.assertTrue(userId.equals(response.getUserId()));
    }

    @Test
    public void testRedis() throws Exception {
        redisTemplate.opsForValue().set("A", 1000L);
        Assert.assertTrue(redisTemplate.opsForValue().get("A").equals(1000L));
        redisTemplate.delete("A");
        Assert.assertTrue(redisTemplate.opsForValue().get("A") == null);
    }
}
