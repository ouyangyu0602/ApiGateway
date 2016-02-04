package com.blueocn.api.repository.domain;

import com.blueocn.api.enums.UserStatusEnum;
import com.blueocn.api.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Title: UserEntity
 * Description: 用户表领域对象
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:27
 */
@Alias("UserEntity")
@Setter
@Getter
public class UserEntity extends IdEntity {
    private static final long serialVersionUID = 6044707143718263105L;

    /*
     * 用户昵称
     */
    private String userName;

    /*
     * 用户唯一标识
     */
    private String userIdentity;

    /*
     * 用户密码
     */
    private String userPassword;

    /*
     * 用户类型,
     */
    private UserTypeEnum userType;

    /*
     * 用户邮箱
     */
    private String userEmail;

    /*
     * 上一次登录时间
     */
    private Date lastLoginDate;

    /*
     * 用户状态
     */
    private UserStatusEnum userStatus;

    private String userSalt;
}
