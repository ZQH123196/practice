package com.example.rbac0withgrouphierarchy.service.vo;

import com.example.rbac0withgrouphierarchy.bff.vo.UsergroupTreeNodeVoList;
import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class TreeNodeVoList {

    @JsonProperty("userGroupId")
    private Long userGroupId;

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNodeVoList> children;

    public TreeNodeVoList(UserGroupHierarchy treeNode) {
        this.userGroupId = treeNode.getUserGroupId();
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getPathEnum();
    }

    public TreeNodeVoList(Long userGroupId, String nodeName, String nodePath, List<TreeNodeVoList> children) {
        this.userGroupId = userGroupId;
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }

    public void setChildren(List<TreeNodeVoList> children) {
        this.children = children;
    }

    public List<TreeNodeVoList> getChildren() {
        return children;
    }

    public void addChildren(UserGroupHierarchy treeNode) {
        this.children.add(new TreeNodeVoList(treeNode));
    }

    public static TreeNodeVoBuilder builder() {
        return new TreeNodeVoBuilder();
    }




    public static class TreeNodeVoBuilder <T extends TreeNodeVoList> {
        private Long userGroupId;
        private String nodeName;
        private String nodePath;
        private List<TreeNodeVoList> children;

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

        public TreeNodeVoBuilder children(List<TreeNodeVoList> children) {
            this.children = children;
            return this;
        }

        public TreeNodeVoList build() {
            return new TreeNodeVoList(userGroupId, nodeName, nodePath, children);
        }

        public String toString() {
            return "TreeNodeVo.TreeNodeVoBuilder(nodeName=" + this.nodeName + ", nodePath=" + this.nodePath + ", children=" + this.children + ")";
        }
    }
}
