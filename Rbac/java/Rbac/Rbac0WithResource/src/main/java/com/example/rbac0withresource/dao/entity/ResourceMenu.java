package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 菜单资源表
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("resource_menu")
public class ResourceMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级 id，为 null 则为 顶级
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 显示的排列顺序，递增数值表自上而下的显示
     */
    @TableField("order")
    private Integer order;

    /**
     * 路由匹配的地址
     */
    @TableField("router_path")
    private String routerPath;

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
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    public String getRouterPath() {
        return routerPath;
    }

    public void setRouterPath(String routerPath) {
        this.routerPath = routerPath;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResourceMenu{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", order=" + order +
            ", routerPath=" + routerPath +
            ", description=" + description +
        "}";
    }
}
