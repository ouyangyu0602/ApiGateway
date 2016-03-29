package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.service.MatrixService;
import com.blueocn.api.vo.UserVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MatrixServiceImplTest extends BaseTest {

    @Autowired
    private MatrixService matrixService;

    @Test
    public void login() throws Exception {
        UserVo userVo = matrixService.login("shengyufan@163.com", "yufan25ba");
        Assert.assertNotNull(userVo);
    }
}
