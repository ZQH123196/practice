package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("permission")
public class Permission extends Model<Permission> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * CRUD，表明可以对资源做什么操作，比如 RU 就说明可读可改
     */
    @TableField("expression")
    private String expression;

    /**
     * 资源的类型，permel、menu、page 等，其中类型为 permel
     */
    @TableField("resource_type")
    private String resourceType;

    /**
     * 资源 id
     */
    @TableField("resource_id")
    private Integer resourceId;

    /**
     * 资源 id
     */
    @TableField("resource_table")
    private Integer resourceTable;

    /**
     * 这将决定 handler，填写 handler 的类全限定名
     */
    @TableField("resource_handler")
    private String resourceHandler;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    public Integer getResourceTable() {
        return resourceTable;
    }

    public void setResourceTable(Integer resourceTable) {
        this.resourceTable = resourceTable;
    }
    public String getResourceHandler() {
        return resourceHandler;
    }

    public void setResourceHandler(String resourceHandler) {
        this.resourceHandler = resourceHandler;
    }

    @Override
    public String toString() {
        return "Permission{" +
            "id=" + id +
            ", expression=" + expression +
            ", resourceType=" + resourceType +
            ", resourceId=" + resourceId +
            ", resourceTable=" + resourceTable +
            ", resourceHandler=" + resourceHandler +
        "}";
    }
}
