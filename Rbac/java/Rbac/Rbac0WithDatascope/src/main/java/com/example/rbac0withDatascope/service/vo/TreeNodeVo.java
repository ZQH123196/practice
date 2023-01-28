package com.example.rbac0withDatascope.service.vo;

import com.example.rbac0withDatascope.dao.entity.UserGroupHierarchy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * TreeNodeVo
 * 在与多树结构对比是可以视为单树结构
 * 一般作为 MultipleUserGroupTree 的成员
 */
@Getter
@EqualsAndHashCode
public class TreeNodeVo {

    @JsonProperty("userGroupId")
    private Long userGroupId;

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNodeVo> children;

    public TreeNodeVo(UserGroupHierarchy treeNode) {
        this.userGroupId = treeNode.getUserGroupId();
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getPathEnum();
    }

    public TreeNodeVo(Long userGroupId, String nodeName, String nodePath, List<TreeNodeVo> children) {
        this.userGroupId = userGroupId;
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }


    public void setChildren(List<TreeNodeVo> children) {
        this.children = children;
    }

    public List<TreeNodeVo> getChildren() {
        return children;
    }

    public void addChildren(UserGroupHierarchy treeNode) {
        this.children.add(new TreeNodeVo(treeNode));
    }

    public static TreeNodeVoBuilder builder() {
        return new TreeNodeVoBuilder();
    }


    public static class TreeNodeVoBuilder <T extends TreeNodeVo> {
        private Long userGroupId;
        private String nodeName;
        private String nodePath;
        private List<TreeNodeVo> children;

        TreeNodeVoBuilder() {
        }

        public TreeNodeVoBuilder userGroupId(Long userGroupId) {
            this.userGroupId = userGroupId;
            return this;
        }

        public TreeNodeVoBuilder nodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public TreeNodeVoBuilder nodePath(String nodePath) {
            this.nodePath = nodePath;
            return this;
        }

        public TreeNodeVoBuilder children(List<TreeNodeVo> children) {
            this.children = children;
            return this;
        }

        public TreeNodeVo build() {
            return new TreeNodeVo(userGroupId, nodeName, nodePath, children);
        }

        public String toString() {
            return "TreeNodeVo.TreeNodeVoBuilder(nodeName=" + this.nodeName + ", nodePath=" + this.nodePath + ", children=" + this.children + ")";
        }
    }

}
