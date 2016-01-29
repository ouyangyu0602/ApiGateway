package com.blueocn.api.repository.mapper;

import com.blueocn.api.BaseTest;
import com.blueocn.api.repository.domain.ValidateInfoEntity;
import com.blueocn.api.support.utils.ValidateCodeUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ValidateInfoRepoTest extends BaseTest {

    @Autowired
    private ValidateInfoRepo validateInfoRepo;

    @Test
    public void testQueryInfoByCode() throws Exception {
        ValidateInfoEntity info = new ValidateInfoEntity();
        info.setUserId(1111111L);
        String validateCode = ValidateCodeUtils.generateCode(30);
        info.setValidateCode(validateCode);
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(30);
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        validateInfoRepo.add(info);

        ValidateInfoEntity entity = validateInfoRepo.queryInfoByCode(validateCode);
        Assert.assertTrue(info.getUserId().equals(entity.getUserId()));
        validateInfoRepo.deleteByUserId(1111111L);

        ValidateInfoEntity entity1 = validateInfoRepo.queryInfoByCode(validateCode);
        Assert.assertTrue(entity1 == null);
    }
}
