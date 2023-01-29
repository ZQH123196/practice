package com.example.rbac0withgrouphierarchy.bff.vo;

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
            return "MultipleTree.MultipleTreeBuilder(treeNodeVoList=" + this.treeNodeVoList + ")";
        }
    }
}
