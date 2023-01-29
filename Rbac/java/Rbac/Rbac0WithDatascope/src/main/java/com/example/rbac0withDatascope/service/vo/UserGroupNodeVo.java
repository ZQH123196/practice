package com.example.rbac0withDatascope.service.vo;

import com.example.rbac0withDatascope.dao.entity.UserGroupHierarchy;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * UserGroupNodeVo
 * 在与多树结构对比是可以视为单树结构
 * 一般作为 MultipleUserGroupTree 的成员
 */
@Getter
public class UserGroupNodeVo extends TreeNode<UserGroupNodeVo> {

    @JsonProperty("userGroupId")
    private Long userGroupId;


    public UserGroupNodeVo(UserGroupHierarchy groupHierarchy) {
        super(groupHierarchy.getNodeName(), groupHierarchy.getPathEnum());
        this.userGroupId = groupHierarchy.getUserGroupId();
    }

    public UserGroupNodeVo(Long userGroupId, String nodeName, String nodePath) {
        super(nodeName, nodePath);
        this.userGroupId = userGroupId;
    }

    public UserGroupNodeVo(Long userGroupId, String nodeName, String nodePath, List<UserGroupNodeVo> children) {
        super(nodeName, nodePath, children);
        this.userGroupId = userGroupId;
    }


    public void setChildren(List<UserGroupNodeVo> children) {
        super.setChildren(children);
    }

    public List<UserGroupNodeVo> getChildren() {
        return super.getChildren();
    }

    public void addChildren(UserGroupHierarchy userGroupHierarchy) {
        super.getChildren().add(new UserGroupNodeVo(userGroupHierarchy));
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserGroupNodeVo)) return false;
        final UserGroupNodeVo other = (UserGroupNodeVo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userGroupId = this.getUserGroupId();
        final Object other$userGroupId = other.getUserGroupId();
        if (this$userGroupId == null ? other$userGroupId != null : !this$userGroupId.equals(other$userGroupId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserGroupNodeVo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userGroupId = this.getUserGroupId();
        result = result * PRIME + ($userGroupId == null ? 43 : $userGroupId.hashCode());
        return result;
    }
}
