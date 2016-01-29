package com.blueocn.api.repository.mapper;

import com.blueocn.api.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Title: CommonRepositoryTest
 * Description: 数据库初始化测试用例
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-21 14:26
 */
public class CommonRepositoryTest extends BaseTest {

    @Autowired
    private CommonRepo commonRepository;

    @Test
    public void testExecuteSQL() throws Exception {
        commonRepository.executeSQL("SELECT * FROM `USER`");
    }
}
