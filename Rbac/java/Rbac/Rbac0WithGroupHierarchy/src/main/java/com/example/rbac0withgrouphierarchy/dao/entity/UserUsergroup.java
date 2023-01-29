package com.example.rbac0withgrouphierarchy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@TableName("user_usergroup")
public class UserUsergroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userGroupId;

    private String userId;

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserUsergroup{" +
            "userGroupId=" + userGroupId +
            ", userId=" + userId +
        "}";
    }
}
