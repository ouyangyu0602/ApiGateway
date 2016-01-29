package com.blueocn.api.service;

import com.blueocn.api.response.UserResponse;
import com.blueocn.api.vo.UserVo;

/**
 * Title: UserService
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 22:50
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 密码校验结果
     */
    UserResponse login(String username, String password);

    /**
     * 用户注册
     *
     * @param userVo 用户页面实体
     */
    UserResponse register(UserVo userVo);

    /**
     * 用户激活
     */
    boolean activeUser(Long userId);

    /**
     * 查询用户是否存在
     *
     * @param userIdentity 登录名
     * @param userEmail    邮箱
     * @return 是否存在
     */
    boolean checkExistUser(String userIdentity, String userEmail);

    /**
     * 通过用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return {@code UserVo}
     */
    UserVo queryUserById(Long userId);
}
