package com.example.rbac0withDatascope.controller.bff.vo;

import com.example.rbac0withDatascope.dao.entity.UserGroupHierarchy;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Data
public class UsergroupTreeNodeVoList {

    @JsonProperty("userGroupName")
    private String userGroupName;

    @JsonProperty("userGroupId")
    private Long userGroupId;

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UsergroupTreeNodeVoList> children;

    public UsergroupTreeNodeVoList(UserGroupHierarchy treeNode) {
        this.userGroupId = treeNode.getUserGroupId();
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getPathEnum();
    }

    public UsergroupTreeNodeVoList(String userGroupName, Long userGroupId, String nodeName, String nodePath, List<UsergroupTreeNodeVoList> children) {
        this.userGroupName = userGroupName;
        this.userGroupId = userGroupId;
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }

    public static UsergroupTreeNodeVoListBuilder builder() {
        return new UsergroupTreeNodeVoListBuilder();
    }

    public static class UsergroupTreeNodeVoListBuilder {
        private String userGroupName;
        private Long userGroupId;
        private String nodeName;
        private String nodePath;
        private List<UsergroupTreeNodeVoList> children;

        UsergroupTreeNodeVoListBuilder() {
        }

        public UsergroupTreeNodeVoListBuilder userGroupName(String userGroupName) {
            this.userGroupName = userGroupName;
            return this;
        }

        public UsergroupTreeNodeVoListBuilder userGroupId(Long userGroupId) {
            this.userGroupId = userGroupId;
            return this;
        }

        public UsergroupTreeNodeVoListBuilder nodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public UsergroupTreeNodeVoListBuilder nodeName(Supplier<String> supplierNodeName) {
            this.nodeName = supplierNodeName.get();
            return this;
        }

        public UsergroupTreeNodeVoListBuilder nodePath(String nodePath) {
            this.nodePath = nodePath;
            return this;
        }

        public UsergroupTreeNodeVoListBuilder children(List<UsergroupTreeNodeVoList> children) {
            this.children = children;
            return this;
        }

        public UsergroupTreeNodeVoList build() {
            return new UsergroupTreeNodeVoList(userGroupName, userGroupId, nodeName, nodePath, children);
        }

        public String toString() {
            return "UsergroupTreeNodeVoList.UsergroupTreeNodeVoListBuilder(userGroupName=" + this.userGroupName + ", userGroupId=" + this.userGroupId + ", nodeName=" + this.nodeName + ", nodePath=" + this.nodePath + ", children=" + this.children + ")";
        }
    }
}
