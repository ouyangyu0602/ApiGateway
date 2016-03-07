package com.blueocn.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.kong.client.ConsumerClient;
import com.blueocn.api.kong.model.Consumer;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.ConsumerService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Title: ConsumerServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-02 11:29
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @Autowired
    private ConsumerClient consumerClient;

    @Override
    public Consumer query(String consumerId) {
        try {
            Consumer consumer = consumerClient.queryOne(consumerId);
            LOGGER.debug("Consumer 信息 {}", JSON.toJSONString(consumer));
            return consumer;
        } catch (IOException e) {
            LOGGER.warn("", e);
        }
        return null;
    }

    @Override
    public RestfulResponse save(Consumer consumer) {
        Preconditions.checkNotNull(consumer, "待保存的开发者信息不能为空");
        try {
            Consumer newConsumer;
            if (isBlank(consumer.getId())) {
                newConsumer = consumerClient.add(consumer);
            } else {
                newConsumer = consumerClient.update(consumer);
            }
            if (newConsumer != null && isBlank(newConsumer.getErrorMessage())) {
                return new RestfulResponse();
            }
            return new RestfulResponse(newConsumer == null ? "保存失败" : newConsumer.getErrorMessage());
        } catch (IOException e) {
            LOGGER.info("", e);
            return new RestfulResponse(e.getMessage());
        }
    }

    @Override
    public List<Consumer> queryAll(Consumer consumer) {
        try {
            Consumer queryParam = consumer == null ? new Consumer() : consumer;
            queryParam.setSize(getConsumerAmount());
            return consumerClient.query(queryParam);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return null;
    }

    @Override
    public void delete(String consumerId) {
        try {
            consumerClient.delete(consumerId);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
    }

    private Integer getConsumerAmount() {
        try {
            Consumer c = new Consumer();
            c.setSize(1);
            return consumerClient.totalSize(c);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return null;
    }
}
