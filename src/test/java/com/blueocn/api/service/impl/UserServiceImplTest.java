package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.enums.UserTypeEnum;
import com.blueocn.api.service.UserService;
import com.blueocn.api.vo.UserVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void testRegister() throws Exception {
        UserVo entity = new UserVo();
        entity.setUserPassword("1111");
        entity.setUserIdentity("1111");
        entity.setUserEmail("1111");
        entity.setUserType(UserTypeEnum.NORMAL);
        entity.setUserName("1111");

        userService.register(entity);
        userService.register(entity);
    }

    @Test
    public void testExistUser() throws Exception {
        Assert.assertTrue(userService.checkExistUser("xiaoyu", null));
        Assert.assertTrue(userService.checkExistUser(null, "ssssss"));
        Assert.assertTrue(userService.checkExistUser("xiaoyu", "ssssss"));
    }
}
