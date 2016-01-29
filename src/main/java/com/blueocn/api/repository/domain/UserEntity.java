package com.blueocn.api.repository.domain;

import com.blueocn.api.enums.UserStatusEnum;
import com.blueocn.api.enums.UserTypeEnum;
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
public class
UserEntity extends IdEntity {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public UserStatusEnum getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusEnum userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }
}
