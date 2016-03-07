package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.service.MatrixService;
import com.blueocn.user.entity.ResultMessage;
import com.blueocn.user.entity.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MatrixServiceImplTest extends BaseTest {

    @Autowired
    private MatrixService matrixService;

    @Test
    public void testLogin() throws Exception {
        ResultMessage<String> resultMessage = matrixService.login("shengyufan@163.com", "yufan25ba");
        Assert.assertTrue(resultMessage.success);

        ResultMessage<UserInfo> userInfo = matrixService.getLoginUserInfo(resultMessage.result);
        Assert.assertNotNull(userInfo.result.getUserId());
    }
}
