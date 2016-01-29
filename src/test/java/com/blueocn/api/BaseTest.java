package com.blueocn.api;

import com.blueocn.api.support.config.ApiSystemConfig;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Title: BaseTest
 * Description: Spring Test 配置基类, 查看
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html
 * 继承此类编写自己的测试用例, 会自动初始化 Spring 等相关的环境配置
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-21 15:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApiSystemConfig.class})
public abstract class BaseTest {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
