package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 常规资源表（即不需要额外处理的资源）,该资源只用于表达权限，使用起来相当于写死权限，将与代码深度耦合
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("resource_permel")
public class ResourcePermel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 纯粹的权限表达式，比如 模块:资源:权限 system:role:create
     */
    @TableField("permission_el")
    private String permissionEl;

    /**
     * 资源描述
     */
    @TableField("description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getPermissionEl() {
        return permissionEl;
    }

    public void setPermissionEl(String permissionEl) {
        this.permissionEl = permissionEl;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResourcePermel{" +
            "id=" + id +
            ", permissionEl=" + permissionEl +
            ", description=" + description +
        "}";
    }
}
