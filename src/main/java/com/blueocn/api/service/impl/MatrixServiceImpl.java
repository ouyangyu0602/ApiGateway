package com.blueocn.api.service.impl;

import com.blueocn.api.service.MatrixService;
import com.blueocn.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import javax.annotation.PostConstruct;
import java.io.IOException;

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

    private Retrofit retrofit;

    interface MatrixConnector {
        @FormUrlEncoded
        @POST("/open/api/v2/user/login")
        Call<UserVo> login(@Field("username") String username, @Field("password") String password);
    }

    @PostConstruct
    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(userSystemBaseUrl).addConverterFactory(
            JacksonConverterFactory.create()).build();
    }

    @Override
    public UserVo login(String account, String password) {
        MatrixConnector connector = retrofit.create(MatrixConnector.class);
        try {
            Response<UserVo> response = connector.login(account, password).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return null;
    }
}
