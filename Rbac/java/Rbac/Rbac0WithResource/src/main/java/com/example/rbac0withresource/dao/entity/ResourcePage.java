package com.example.rbac0withresource.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 页面资源表
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@TableName("resource_page")
public class ResourcePage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 归属菜单的 id，为 null 则为 顶级
     */
    @TableField("menu_id")
    private Integer menuId;

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
     * 前端组件的渲染位置
     */
    @TableField("component_path")
    private String componentPath;

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
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResourcePage{" +
            "id=" + id +
            ", menuId=" + menuId +
            ", order=" + order +
            ", routerPath=" + routerPath +
            ", componentPath=" + componentPath +
            ", description=" + description +
        "}";
    }
}
