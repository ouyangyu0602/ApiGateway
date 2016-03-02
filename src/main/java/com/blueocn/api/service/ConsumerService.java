package com.blueocn.api.service;

import com.blueocn.api.kong.model.Consumer;
import com.blueocn.api.kong.model.Consumers;
import com.blueocn.api.response.RestfulResponse;

/**
 * Title: ConsumerService
 * Description: 开发者服务
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-02 11:28
 */
public interface ConsumerService {

    /**
     * 查询开发者信息
     *
     * @param consumerId 开发者ID
     */
    Consumer query(String consumerId);

    /**
     * 新建或者更新开发者
     *
     * @param consumer 开发者信息
     */
    RestfulResponse<String> save(Consumer consumer);

    /**
     * 查询全部的开发者信息
     *
     * @param consumer 可选查询参数
     */
    Consumers queryAll(Consumer consumer);

    /**
     * 删除某个开发者
     *
     * @param consumerId 开发者ID
     */
    void delete(String consumerId);
}
