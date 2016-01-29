package com.blueocn.api.repository.mapper;

import com.blueocn.api.repository.domain.ValidateInfoEntity;
import org.springframework.stereotype.Repository;

/**
 * Title: ValidateInfoRepo
 * Description: 验证码数据库存储操作接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-11 23:15
 */
@Repository
public interface ValidateInfoRepo {

    void add(ValidateInfoEntity info);

    ValidateInfoEntity queryInfoByCode(String validateCode);

    void deleteByUserId(Long userId);
}
