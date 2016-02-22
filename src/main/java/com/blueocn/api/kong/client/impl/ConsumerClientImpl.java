package com.blueocn.api.kong.client.impl;

import com.blueocn.api.kong.client.ConsumerClient;
import com.blueocn.api.kong.connector.Connector;
import com.blueocn.api.kong.connector.ConsumerConnector;
import com.blueocn.api.kong.model.Consumer;
import com.blueocn.api.kong.model.Consumers;
import com.blueocn.api.support.utils.Asserts;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Title: CustomerClientImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-22 11:19
 */
@Component
public class ConsumerClientImpl implements ConsumerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerClientImpl.class);

    @Autowired
    private Connector connector;

    private ConsumerConnector consumerConnector;

    @PostConstruct
    private void init() {
        consumerConnector = connector.create(ConsumerConnector.class);
    }

    @Override
    public Consumer add(Consumer consumer) throws IOException {
        Call<Consumer> call = consumerConnector.add(consumer);
        Response<Consumer> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null; // TODO
    }

    @Override
    public Consumers query(Consumer consumer) throws IOException {
        Call<Consumers> call = consumerConnector.query(consumer == null ? null : consumer.toMap());
        Response<Consumers> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null;
    }

    @Override
    public Consumer queryOne(String consumerId) throws IOException {
        Call<Consumer> call = consumerConnector.queryOne(consumerId);
        Response<Consumer> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null; // TODO
    }

    @Override
    public Consumer update(Consumer consumer) throws IOException {
        Preconditions.checkNotNull(consumer, "用户信息");
        Call<Consumer> call = consumerConnector.update(null, consumer); // TODO
        Response<Consumer> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null; // TODO
    }

    @Override
    public void delete(String consumerId) throws IOException {
        Asserts.checkNotBlank(consumerId, "需要删除的用户 ID");
        Call<String> call = consumerConnector.delete(consumerId);
        call.execute();
    }
}
