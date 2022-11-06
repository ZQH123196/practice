package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色用户关联表
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("role_user")
public class RoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("role")
    private String role;

    @TableField("username")
    private String username;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
            "role=" + role +
            ", username=" + username +
        "}";
    }
}
