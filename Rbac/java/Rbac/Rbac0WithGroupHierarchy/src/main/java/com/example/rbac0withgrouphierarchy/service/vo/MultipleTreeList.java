package com.example.rbac0withgrouphierarchy.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * 结构形如：
 * [
 *     {
 *         "nodeName": "A",
 *         "nodePath": "A",
 *         "children": [
 *             {
 *                 "nodeName": "B",
 *                 "nodePath": "AB"
 *             },
 *             {
 *                 "nodeName": "C",
 *                 "nodePath": "AC",
 *                 "children": [
 *                     {
 *                         "nodeName": "D",
 *                         "nodePath": "ACD"
 *                     },
 *                     {
 *                         "nodeName": "E",
 *                         "nodePath": "ACE"
 *                     },
 *                     {
 *                         "nodeName": "F",
 *                         "nodePath": "ACF",
 *                         "children": [
 *                             {
 *                                 "nodeName": "G",
 *                                 "nodePath": "ACFG"
 *                             }
 *                         ]
 *                     }
 *                 ]
 *             }
 *         ]
 *     }
 * ]
 */
public class MultipleTreeList {


    @JsonProperty("TreeNodeVoList")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNodeVoList> treeNodeVoList;

    MultipleTreeList(List<TreeNodeVoList> treeNodeVoList) {
        this.treeNodeVoList = treeNodeVoList;
    }

    public static MultipleTreeBuilder builder() {
        return new MultipleTreeBuilder();
    }


    public static class MultipleTreeBuilder {
        private List<TreeNodeVoList> treeNodeVoList;

        MultipleTreeBuilder() {
        }

        public MultipleTreeBuilder treeNodeVoList(List<TreeNodeVoList> treeNodeVoList) {
            this.treeNodeVoList = treeNodeVoList;
            return this;
        }

        public MultipleTreeList build() {
            return new MultipleTreeList(treeNodeVoList);
        }

        public String toString() {
            return "MultipleTree.MultipleTreeBuilder(treeNodeVoList=" + this.treeNodeVoList + ")";
        }
    }
}
