package com.example.rbac0withgrouphierarchy.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 用户组继承树
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@TableName("user_group_hierarchy")
public class UserGroupHierarchy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("user_group_id")
    private Long userGroupId;

    @TableId("node_name")
    private String nodeName;

    @TableField("path_enum")
    private String pathEnum;

    public UserGroupHierarchy() {
    }

    public Long getUserGroupId() {
        return this.userGroupId;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public String getPathEnum() {
        return this.pathEnum;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setPathEnum(String pathEnum) {
        this.pathEnum = pathEnum;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserGroupHierarchy)) return false;
        final UserGroupHierarchy other = (UserGroupHierarchy) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userGroupId = this.getUserGroupId();
        final Object other$userGroupId = other.getUserGroupId();
        if (this$userGroupId == null ? other$userGroupId != null : !this$userGroupId.equals(other$userGroupId))
            return false;
        final Object this$nodeName = this.getNodeName();
        final Object other$nodeName = other.getNodeName();
        if (this$nodeName == null ? other$nodeName != null : !this$nodeName.equals(other$nodeName)) return false;
        final Object this$pathEnum = this.getPathEnum();
        final Object other$pathEnum = other.getPathEnum();
        if (this$pathEnum == null ? other$pathEnum != null : !this$pathEnum.equals(other$pathEnum)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserGroupHierarchy;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userGroupId = this.getUserGroupId();
        result = result * PRIME + ($userGroupId == null ? 43 : $userGroupId.hashCode());
        final Object $nodeName = this.getNodeName();
        result = result * PRIME + ($nodeName == null ? 43 : $nodeName.hashCode());
        final Object $pathEnum = this.getPathEnum();
        result = result * PRIME + ($pathEnum == null ? 43 : $pathEnum.hashCode());
        return result;
    }

    public String toString() {
        return "UserGroupHierarchy(userGroupId=" + this.getUserGroupId() + ", nodeName=" + this.getNodeName() + ", pathEnum=" + this.getPathEnum() + ")";
    }
}
