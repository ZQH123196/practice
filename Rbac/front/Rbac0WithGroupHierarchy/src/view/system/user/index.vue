<script setup lang="ts" >
import { ElTree } from "element-plus";
import axios from "axios";
import type {
    TreeNode4Front,
    TreeNode4Map,
    TreeNode4Array,
} from "./treeHelper.js";
import { walkTree4Map, walkTree4Array } from "./treeHelper.js";

const handleNodeClick = (data: TreeNode4Front) => {
    console.log(data);
};

const defaultProps = {
    children: "children",
    label: "label",
};

let tree4FrontData: TreeNode4Front[] = [];

// const srcJson = {
//     TreeNodeVoList: [
//         {
//             nodeName: "A",
//             nodePath: "A",
//             children: [
//                 {
//                     nodeName: "B",
//                     nodePath: "AB",
//                 },
//                 {
//                     nodeName: "C",
//                     nodePath: "AC",
//                     children: [
//                         {
//                             nodeName: "D",
//                             nodePath: "ACD",
//                         },
//                         {
//                             nodeName: "E",
//                             nodePath: "ACE",
//                         },
//                         {
//                             nodeName: "F",
//                             nodePath: "ACF",
//                             children: [
//                                 {
//                                     nodeName: "G",
//                                     nodePath: "ACFG",
//                                 },
//                             ],
//                         },
//                     ],
//                 },
//             ],
//         },
//     ],
// };

const url = "http://localhost:8080/user-group-tree/getAllUsergroupTree";
const srcJson: {
    treeNodeVoList: TreeNode4Array[];
} = (await axios.get(url)).data;

const multipleTree: TreeNode4Array[] = srcJson.treeNodeVoList;


walkTree4Array(multipleTree, tree4FrontData);

console.log(multipleTree);



</script>

<template>
    <el-tree
        :data="tree4FrontData"
        :props="defaultProps"
        @node-click="handleNodeClick"
    ></el-tree>
</template>

<style scoped></style>
