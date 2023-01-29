<script setup lang="ts">
import { ElTree } from "element-plus";
import axios from "axios";
import type {
    TreeNode4Front,
    TreeNode4Map,
    TreeNode4Array,
} from "./treeHelper.js";
import { walkTree4Map, walkTree4Array, walkTree } from "./treeHelper.js";

const url_getAllUsergroupTree = "http://localhost:8080/user-group-tree/getAllUsergroupTree";

/**
 * el-tree 所需
 */
const defaultProps = {
    label: "label",
    children: "children",
};

let tree4FrontData: TreeNode4Front[] = [];

const srcJson: {
    treeNodeVoList: TreeNode4Array[];
} = (
    await axios.post(
        url_getAllUsergroupTree,
        {},
        { headers: { username: "eor", password: 1008610086 } }
    )
).data;

const multipleTree: TreeNode4Array[] = srcJson.treeNodeVoList;

walkTree4Array(multipleTree, tree4FrontData);

console.log("tree4FrontData", tree4FrontData);


const url_getTargetNodeChildren = "http://localhost:8080/user-group-tree/getTargetNodeChildren";
async function handleNodeClick(node: TreeNode4Front) {
    console.log("handleNodeClick, node", node);

    const srcJson: {
        treeNodeVoList: TreeNode4Array[];
    } = (
        await axios.post(
            url_getTargetNodeChildren,
            {
                nodeName: node.nodeName
            },
            { headers: { username: "eor", password: 1008610086 } }
        )
    ).data;
    const multipleTree: TreeNode4Array[] = srcJson.treeNodeVoList;
    walkTree4Array(multipleTree, tree4FrontData);
    console.log("tree4FrontData", tree4FrontData);
}
</script>

<template>
    <el-tree
        :data="tree4FrontData"
        :props="defaultProps"
        @node-click="handleNodeClick"
        :expand-on-click-node="false"
    ></el-tree>
</template>

<style scoped></style>
