package com.blueocn.api.service.impl;

import com.blueocn.api.service.MatrixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Title: MatrixServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-04 17:21
 */
@Service
public class MatrixServiceImpl implements MatrixService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixServiceImpl.class);

    @Value("${matrix.url}")
    private String userSystemBaseUrl;

    @PostConstruct
    public void init() {
    }

    @Override
    public String login(String account, String password) {
        return null;
    }
}
