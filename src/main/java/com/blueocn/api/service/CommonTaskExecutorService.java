package com.blueocn.api.service;

/**
 * Title: CommonTaskExecutorService
 * Description: 通用并发任务处理服务
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 13:15
 */
@FunctionalInterface
public interface CommonTaskExecutorService {

    /**
     * 任务执行接口
     *
     * @param task {@code Runnable}
     */
    void executeTask(Runnable task);
}
