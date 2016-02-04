package com.blueocn.api.kong.client;

/**
 * Title: BaseClient
 * Description: 基础 Kong 操作接口定义, 基于此接口设计 Kong 的实际管理请求接口
 * Q: 请求类型
 * A: 响应类型
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-03 18:39
 */
public interface BaseClient<Q, A> {

    Q add(Q obj);

    A query(Q obj);
}
