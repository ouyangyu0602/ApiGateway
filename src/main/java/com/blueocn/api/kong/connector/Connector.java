package com.blueocn.api.kong.connector;

import com.blueocn.api.kong.KongConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastjsonConverterFactory;

import javax.annotation.PostConstruct;

/**
 * Title: CommonClient
 * Description: Retrofit2 链接基础类, 基于 Kong 的信息定义
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-15 16:04
 */
@Component
public class Connector {

    @Autowired
    private KongConfig config;

    private Retrofit kongAdmin;

    private Retrofit kongFront;

    /**
     * HTTP 请求接口 Retrofit 适配器
     *
     * 定义 Kong 的调用地址, 注意 Retrofit2 之后的地址拼接方式变更,
     * 不再是直接的 URL 拼接, 而是类似 HTTP 的 <a href="..."></a> 的拼接形式
     */
    @PostConstruct
    private void init() { // NOSONAR
        kongAdmin = new Retrofit.Builder()
            .baseUrl(config.getKongAdminUrl())
            .addConverterFactory(FastjsonConverterFactory.create())
            .build();
        kongFront = new Retrofit.Builder()
            .baseUrl(config.getKongHttpsAddress())
            .addConverterFactory(FastjsonConverterFactory.create())
            .build();
    }

    public <T> T admin(final Class<T> service) {
        return kongAdmin.create(service);
    }

    public <T> T front(final Class<T> service) {
        return kongFront.create(service);
    }
}
