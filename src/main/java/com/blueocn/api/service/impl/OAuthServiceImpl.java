package com.blueocn.api.service.impl;

import com.blueocn.api.service.OAuthService;
import org.springframework.stereotype.Service;

/**
 * Title: OAuthServiceImpl
 * Description: OAuth 服务实现
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:33
 */
@Service
public class OAuthServiceImpl implements OAuthService {



    @Override
    public boolean validAppKey(String appKey) {
        return false;
    }
}
