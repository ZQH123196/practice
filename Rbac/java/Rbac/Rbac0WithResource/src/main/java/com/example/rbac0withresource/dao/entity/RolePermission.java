package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;

import java.io.Serializable;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("role_permission")
@Builder
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("role")
    private String role;

    @TableField("permission_id")
    private Integer permissionId;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
            "role=" + role +
            ", permissionId=" + permissionId +
        "}";
    }
}
