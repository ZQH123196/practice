package com.example.rbac0withgrouphierarchy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.rbac0withgrouphierarchy.bff.vo.UserTreeList;
import com.example.rbac0withgrouphierarchy.bff.vo.UsergroupTreeNodeVoList;
import com.example.rbac0withgrouphierarchy.dao.entity.UserGroup;
import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.example.rbac0withgrouphierarchy.dao.service.IUserGroupHierarchyService;
import com.example.rbac0withgrouphierarchy.dao.service.IUserGroupService;
import com.example.rbac0withgrouphierarchy.service.ITreeListService;
import com.example.rbac0withgrouphierarchy.service.vo.MultipleTreeList;
import com.example.rbac0withgrouphierarchy.service.vo.TreeNodeVoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupTreeServiceImpl implements ITreeListService {

    @Resource
    IUserGroupHierarchyService userGroupHierarchyService;
    static String pathEnumLabel = "path_enum";
    static String lenPath = String.format("LENGTH(%s)", pathEnumLabel);

    @Resource
    IUserGroupService userGroupService;


    @Override
    public UserTreeList getAllUsergroupTree() {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .eq(lenPath, 1);
        List<UserGroupHierarchy> rootList = userGroupHierarchyService.list(wrapper);


        List<UsergroupTreeNodeVoList> treeNodeVoMapList = new ArrayList<>();
        for (UserGroupHierarchy treeNode : rootList) {
            UsergroupTreeNodeVoList treeNodeList = getUsergroupTreeNodeVoList(treeNode.getNodeName());
            treeNodeVoMapList.add(treeNodeList);
        }
        UserTreeList userTreeList = UserTreeList.builder().treeNodeVoList(treeNodeVoMapList).build();

        return userTreeList;
    }

    @Override
    public MultipleTreeList getMultipleTree() {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .eq(lenPath, 1);
        List<UserGroupHierarchy> rootList = userGroupHierarchyService.list(wrapper);


        List<TreeNodeVoList> treeNodeVoMapList = new ArrayList<>();
        for (UserGroupHierarchy treeNode : rootList) {
            TreeNodeVoList treeNodeList = getTreeNodeVoList(treeNode.getNodeName());
            treeNodeVoMapList.add(treeNodeList);
        }
        MultipleTreeList multipleTreeList = MultipleTreeList.builder().treeNodeVoList(treeNodeVoMapList).build();
        return multipleTreeList;
    }

    /**
     * 获取 目标的所有子树
     * 当节点路径为 1 时，视为一棵树的根节点
     *
     * @return
     */
    @Override
    public MultipleTreeList getTargetNodeChildren(String nodeName) {
        List<TreeNodeVoList> newTreeNodeList = new ArrayList<>();
        newTreeNodeList.add(getTreeNodeVoList(nodeName));

        MultipleTreeList multipleTreeList = MultipleTreeList.builder().treeNodeVoList(newTreeNodeList).build();

        return multipleTreeList;
    }


    @Override
    public MultipleTreeList getTreeByRoot(String rootName) {
        return getTargetNodeChildren(rootName);
    }

    @Override
    public List<UserGroupHierarchy> getLayerTree(String rootNode, int layer) {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .like(pathEnumLabel, rootNode)
                .eq(lenPath, layer);
        List<UserGroupHierarchy> curLayerNodes = userGroupHierarchyService.list(wrapper);

        return curLayerNodes;
    }

    @Override
    public void findIsolatedNode(String rootNode, int layer) {

    }

    @Override
    public void findLoopNode(String rootNode, int layer) {

    }


    private TreeNodeVoList toTreeNodeVo(List<UserGroupHierarchy> treeNodeList) {

        UserGroupHierarchy rootNode = treeNodeList.get(0);
        treeNodeList.remove(0);
        TreeNodeVoList rootNodeVo = new TreeNodeVoList(rootNode);

        List<TreeNodeVoList> childNode = getChildNode(rootNodeVo, treeNodeList);
        rootNodeVo.setChildren(childNode);
        recursionSetChildWithEffect(rootNodeVo, treeNodeList);

        return rootNodeVo;
    }

    private UsergroupTreeNodeVoList toTreeNodeVo4User(List<UserGroupHierarchy> treeNodeList) {

        UserGroupHierarchy rootNode = treeNodeList.get(0);
        treeNodeList.remove(0);
        UsergroupTreeNodeVoList rootNodeVo = new UsergroupTreeNodeVoList(rootNode);

        String userGroupName = getUserGroupName(rootNodeVo);
        rootNodeVo.setUserGroupName(userGroupName);

        List<UsergroupTreeNodeVoList> childNode = getChildNode4User(rootNodeVo, treeNodeList);
        rootNodeVo.setChildren(childNode);
        recursionSetChildWithEffect4User(rootNodeVo, treeNodeList);

        return rootNodeVo;
    }

    private String getUserGroupName(UsergroupTreeNodeVoList rootNodeVo) {
        LambdaQueryWrapper<UserGroup> userGroupQuery = Wrappers.<UserGroup>lambdaQuery();
        userGroupQuery.eq(UserGroup::getId, rootNodeVo.getUserGroupId());
        String usergroupName = userGroupService.getOne(userGroupQuery).getText();
        return usergroupName;
    }

    private static List<UsergroupTreeNodeVoList> getChildNode4User(UsergroupTreeNodeVoList targetNode, List<UserGroupHierarchy> treeNodeList) {
        // 路径枚举中包含这个值并且这个值是倒数第二位，就说明是有父子关系
        List<UsergroupTreeNodeVoList> childNodeList = new ArrayList<>();
        for (UserGroupHierarchy treeNode : treeNodeList) {
            Long userGroupId = treeNode.getUserGroupId();
            String nodeName = treeNode.getNodeName();
            String pathString = treeNode.getPathEnum();
            if (pathString.substring(pathString.length() - 2, pathString.length() - 1).equals(targetNode.getNodeName())) {
                childNodeList.add(UsergroupTreeNodeVoList
                        .builder()
                        .userGroupId(userGroupId)
                        .nodeName(nodeName)
                        .nodePath(pathString)
                        .build()
                );
            }
        }
        return childNodeList;
    }

    private void recursionSetChildWithEffect4User(UsergroupTreeNodeVoList targetNode, List<UserGroupHierarchy> treeNodeList) {
        // 给每个 node 都设置 group name
        targetNode.setUserGroupName(getUserGroupName(targetNode));
        List<UsergroupTreeNodeVoList> childNode = getChildNode4User(targetNode, treeNodeList);
        targetNode.setChildren(childNode);
        for (UsergroupTreeNodeVoList treeNodeVoList : childNode) {
            recursionSetChildWithEffect4User(treeNodeVoList, treeNodeList);
        }
    }


    private static List<TreeNodeVoList> getChildNode(TreeNodeVoList targetNode, List<UserGroupHierarchy> treeNodeList) {
        // 路径枚举中包含这个值并且这个值是倒数第二位，就说明是有父子关系
        List<TreeNodeVoList> childNodeList = new ArrayList<>();
        for (UserGroupHierarchy treeNode : treeNodeList) {
            Long userGroupId = treeNode.getUserGroupId();
            String nodeName = treeNode.getNodeName();
            String pathString = treeNode.getPathEnum();
            if (pathString.substring(pathString.length() - 2, pathString.length() - 1).equals(targetNode.getNodeName())) {
                childNodeList.add(TreeNodeVoList
                        .builder()
                        .userGroupId(userGroupId)
                        .nodeName(nodeName)
                        .nodePath(pathString)
                        .build()
                );
            }
        }
        return childNodeList;
    }

    private void recursionSetChildWithEffect(TreeNodeVoList targetNode, List<UserGroupHierarchy> treeNodeList) {
        List<TreeNodeVoList> childNode = getChildNode(targetNode, treeNodeList);
        targetNode.setChildren(childNode);
        for (TreeNodeVoList treeNodeVoList : childNode) {
            recursionSetChildWithEffect(treeNodeVoList, treeNodeList);
        }
    }


    private UsergroupTreeNodeVoList getUsergroupTreeNodeVoList(String nodeName) {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .like(pathEnumLabel, nodeName)
                .orderByAsc(lenPath);
        List<UserGroupHierarchy> treeNodeList = userGroupHierarchyService.list(wrapper);

        UsergroupTreeNodeVoList treeNodeVoList = toTreeNodeVo4User(treeNodeList);


        return treeNodeVoList;
    }

    private TreeNodeVoList getTreeNodeVoList(String nodeName) {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .like(pathEnumLabel, nodeName)
                .orderByAsc(lenPath);
        List<UserGroupHierarchy> treeNodeList = userGroupHierarchyService.list(wrapper);

        TreeNodeVoList treeNodeVoList = toTreeNodeVo(treeNodeList);


        return treeNodeVoList;
    }
}
