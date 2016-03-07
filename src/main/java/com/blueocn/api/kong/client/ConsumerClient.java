package com.blueocn.api.kong.client;

import com.blueocn.api.kong.model.Consumer;

import java.io.IOException;
import java.util.List;

/**
 * Title: CustomerClient
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-22 11:19
 */
public interface ConsumerClient {

    /**
     * 增
     *
     * @throws IOException
     */
    Consumer add(Consumer consumer) throws IOException;

    /**
     * 查一堆
     *
     * @throws IOException
     */
    List<Consumer> query(Consumer consumer) throws IOException;

    /**
     * 当前查询条件下的 API 个数
     *
     * @param consumer 查询参数
     * @return API 总数, 对于总数大于100的情况有效
     */
    Integer totalSize(Consumer consumer) throws IOException;

    /**
     * 查一个
     *
     * @param consumerId 用户ID, 其实还可以使用用户名称, 这里废弃用户名称方式
     * @throws IOException
     */
    Consumer queryOne(String consumerId) throws IOException;

    /**
     * 更新一个
     *
     * @throws IOException
     */
    Consumer update(Consumer consumer) throws IOException;

    /**
     * 删除一个
     *
     * @param consumerId 用户ID, 不建议使用用户名
     * @throws IOException
     */
    void delete(String consumerId) throws IOException;
}
