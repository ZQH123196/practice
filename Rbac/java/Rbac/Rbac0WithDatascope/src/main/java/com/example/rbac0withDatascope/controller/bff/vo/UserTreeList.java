package com.example.rbac0withDatascope.controller.bff.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 */
public class UserTreeList {


    @JsonProperty("treeNodeVoList")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UsergroupTreeNodeVoList> treeNodeVoList;

    UserTreeList(List<UsergroupTreeNodeVoList> treeNodeVoList) {
        this.treeNodeVoList = treeNodeVoList;
    }

    public static MultipleTreeBuilder builder() {
        return new MultipleTreeBuilder();
    }


    public static class MultipleTreeBuilder {
        private List<UsergroupTreeNodeVoList> treeNodeVoList;

        MultipleTreeBuilder() {
        }

        public MultipleTreeBuilder treeNodeVoList(List<UsergroupTreeNodeVoList> treeNodeVoList) {
            this.treeNodeVoList = treeNodeVoList;
            return this;
        }

        public UserTreeList build() {
            return new UserTreeList(treeNodeVoList);
        }

        public String toString() {
            return "MultipleUserGroupTree.MultipleTreeBuilder(treeNodeVoList=" + this.treeNodeVoList + ")";
        }
    }
}
