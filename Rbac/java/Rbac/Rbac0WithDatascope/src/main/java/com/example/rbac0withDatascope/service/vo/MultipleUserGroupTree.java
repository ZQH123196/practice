package com.example.rbac0withDatascope.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

/**
 * 多树结构
 * 可以视最顶层的每一个 treeNode 都是一个 root，
 * 这个 root 不一定是整棵树的 root，也可以是子树的 root
 */
@Data
public class MultipleUserGroupTree {


    @JsonProperty("UserGroupNodeVo")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserGroupNodeVo> userGroupNodeVo;

    MultipleUserGroupTree(List<UserGroupNodeVo> userGroupNodeVo) {
        this.userGroupNodeVo = userGroupNodeVo;
    }

    public static MultipleTreeBuilder builder() {
        return new MultipleTreeBuilder();
    }


    public static class MultipleTreeBuilder {
        private List<UserGroupNodeVo> userGroupNodeVo;

        MultipleTreeBuilder() {
        }

        public MultipleTreeBuilder treeNodeVoList(List<UserGroupNodeVo> userGroupNodeVo) {
            this.userGroupNodeVo = userGroupNodeVo;
            return this;
        }

        public MultipleUserGroupTree build() {
            return new MultipleUserGroupTree(userGroupNodeVo);
        }

        public String toString() {
            return "MultipleUserGroupTree.MultipleTreeBuilder(userGroupNodeVo=" + this.userGroupNodeVo + ")";
        }
    }
}
