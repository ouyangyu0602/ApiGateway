package com.blueocn.api.kong.client.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.client.ConsumerClient;
import com.blueocn.api.kong.model.Consumer;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConsumerClientImplTest extends BaseTest {

    @Autowired
    private ConsumerClient consumerClient;

    @Test
    public void testAll() throws Exception {
        Consumer consumer = consumerClient.add(randomConsumer());
        Consumer queryConsumer = consumerClient.queryOne(consumer.getId());
        Assert.assertNotNull(consumer);
        Assert.assertNotNull(queryConsumer);
        Assert.assertEquals(consumer.getUsername(), consumer.getUsername());

        Consumer queryInfo = new Consumer();
        queryInfo.setUsername(queryConsumer.getUsername());
        List<Consumer> consumers = consumerClient.query(queryInfo);
        Assert.assertTrue(consumers.size() == 1);
        Assert.assertEquals(consumers.get(0).getId(), consumer.getId());

        String newName = RandomStringUtils.randomAlphabetic(10);
        queryConsumer.setUsername(newName);
        consumerClient.update(queryConsumer);
        Consumer updateResult = consumerClient.queryOne(queryConsumer.getId());
        Assert.assertEquals(updateResult.getUsername(), newName);
    }

    private Consumer randomConsumer() {
        Consumer consumer = new Consumer();
        consumer.setUsername(RandomStringUtils.randomAlphabetic(10));
        consumer.setCustom_id(RandomStringUtils.randomNumeric(10));
        return consumer;
    }
}
