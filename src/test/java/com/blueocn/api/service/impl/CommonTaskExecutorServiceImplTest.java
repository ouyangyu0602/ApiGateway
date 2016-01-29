package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.service.CommonTaskExecutorService;
import com.blueocn.api.support.utils.SystemClock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonTaskExecutorServiceImplTest extends BaseTest {

    @Autowired
    private CommonTaskExecutorService commonTaskExecutorService;

    @Test
    public void testExecuteTask() throws Exception {
        commonTaskExecutorService.executeTask(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    logger.info(SystemClock.now() + "");
                }
            }
        });
    }
}
