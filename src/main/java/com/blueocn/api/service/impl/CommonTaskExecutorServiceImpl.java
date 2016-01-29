package com.blueocn.api.service.impl;

import com.blueocn.api.service.CommonTaskExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title: CommonTaskExecutorServiceImpl
 * Description: 多线程并发任务执行服务
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 13:19
 */
@Service
public class CommonTaskExecutorServiceImpl implements CommonTaskExecutorService {

    @Value("${task.corePoolSize}")
    private Integer corePoolSize;

    @Value("${task.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${task.keepAliveTime}")
    private Integer keepAliveTime;

    private Executor executor;

    @PostConstruct
    public void init() {
        executor =
            new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(maxPoolSize), new BackendThreadFactory(),
            new TaskDiscardPolicy());
    }

    @Override
    public void executeTask(Runnable task) {
        executor.execute(task);
    }

    private static class BackendThreadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String threadNamePrefix;

        BackendThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            threadNamePrefix = "ApiSystemWorker-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread =
                new Thread(group, r, threadNamePrefix + threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.MAX_PRIORITY) {
                thread.setPriority(Thread.MAX_PRIORITY);
            }
            return thread;
        }
    }


    private static class TaskDiscardPolicy implements RejectedExecutionHandler {
        private static final Logger LOGGER = LoggerFactory.getLogger(TaskDiscardPolicy.class);
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof Thread) {
                LOGGER.error("Backend task worker {} is discard", ((Thread) r).getName());
            }
        }
    }
}
