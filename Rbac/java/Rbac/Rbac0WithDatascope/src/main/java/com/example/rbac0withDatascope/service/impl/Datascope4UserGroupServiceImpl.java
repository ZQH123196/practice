package com.example.rbac0withDatascope.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.rbac0withDatascope.controller.bff.vo.UserTreeList;
import com.example.rbac0withDatascope.dao.entity.UserUsergroup;
import com.example.rbac0withDatascope.dao.service.IUserGroupHierarchyService;
import com.example.rbac0withDatascope.dao.service.IUserGroupService;
import com.example.rbac0withDatascope.dao.service.IUserUsergroupService;
import com.example.rbac0withDatascope.service.IDatascope4UserGroupService;
import com.example.rbac0withDatascope.service.vo.MultipleUserGroupTree;
import com.example.rbac0withDatascope.service.vo.UserGroupNodeVo;
import com.example.rbac0withDatascope.controller.bff.vo.UsergroupTreeNodeVoList;
import com.example.rbac0withDatascope.dao.entity.UserGroup;
import com.example.rbac0withDatascope.dao.entity.UserGroupHierarchy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 路径枚举法实现
 */
@Service
@Slf4j
public class Datascope4UserGroupServiceImpl implements IDatascope4UserGroupService {

    @Resource
    IUserGroupHierarchyService userGroupHierarchyService;
    static String pathEnumLabel = "path_enum";
    static String lenPath = String.format("LENGTH(%s)", pathEnumLabel);

    @Resource
    IUserUsergroupService userUsergroupService;

    @Resource
    IUserGroupService userGroupService;

    @Override
    public UserTreeList getAllUsergroupTree4Bff() {
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
    public MultipleUserGroupTree getAllMultipleTree() {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .eq(lenPath, 1);
        List<UserGroupHierarchy> rootList = userGroupHierarchyService.list(wrapper);


        List<UserGroupNodeVo> userGroupNodeVoMapList = new ArrayList<>();
        for (UserGroupHierarchy treeNode : rootList) {
            UserGroupNodeVo treeNodeList = getTreeNodeVoList(treeNode.getNodeName());
            userGroupNodeVoMapList.add(treeNodeList);
        }
        MultipleUserGroupTree multipleUserGroupTree = MultipleUserGroupTree.builder().treeNodeVoList(userGroupNodeVoMapList).build();
        return multipleUserGroupTree;
    }


    @Override
    public Set<UserGroupNodeVo> getUserGroupHierarchyByUserId(Integer userId) {
        LambdaQueryWrapper<UserUsergroup> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserUsergroup::getUserId, userId);
        List<UserUsergroup> userGroupsRelation = userUsergroupService.list(wrapper);
        Set<UserGroupNodeVo> userGroupNodeVoSet = new HashSet<>();
        for (UserUsergroup relation : userGroupsRelation) {
            userGroupNodeVoSet.addAll(getTargetNodeChildren(relation.getUserGroupId()));
        }
        return userGroupNodeVoSet;
    }

    ;


    @Override
    public List<UserGroupNodeVo> getTargetNodeChildren(String nodeName) {
        List<UserGroupNodeVo> newTreeNodeList = new ArrayList<>();
        newTreeNodeList.add(getTreeNodeVoList(nodeName));
        return newTreeNodeList;
    }


    @Override
    public List<UserGroupNodeVo> getTreeByRoot(String rootName) {
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


    /**
     * 将第一个作为 root
     * 自动根据 root 将剩下 list 中的关联关系排好，跟 root 没有关联的就丢弃
     *
     * @param treeNodeList
     * @return
     */
    private UserGroupNodeVo toTreeNodeVo(List<UserGroupHierarchy> treeNodeList) {

        UserGroupHierarchy rootNode = treeNodeList.get(0);
        treeNodeList.remove(0);
        UserGroupNodeVo rootNodeVo = new UserGroupNodeVo(rootNode);

        List<UserGroupNodeVo> childNode = getChildNode(rootNodeVo, treeNodeList);
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

    /**
     * 根据 targetNode，将 treeNodeList 内的值都挂载为其子树
     *
     * @param targetNode
     * @param treeNodeList
     */
    private void recursionSetChildWithEffect4User(UsergroupTreeNodeVoList targetNode, List<UserGroupHierarchy> treeNodeList) {
        // 给每个 node 都设置 group name
        targetNode.setUserGroupName(getUserGroupName(targetNode));
        List<UsergroupTreeNodeVoList> childNode = getChildNode4User(targetNode, treeNodeList);
        targetNode.setChildren(childNode);
        for (UsergroupTreeNodeVoList treeNodeVoList : childNode) {
            recursionSetChildWithEffect4User(treeNodeVoList, treeNodeList);
        }
    }


    private static List<UserGroupNodeVo> getChildNode(UserGroupNodeVo targetNode, List<UserGroupHierarchy> treeNodeList) {
        // 路径枚举中包含这个值并且这个值是倒数第二位，就说明是有父子关系
        List<UserGroupNodeVo> childNodeList = new ArrayList<>();
        for (UserGroupHierarchy treeNode : treeNodeList) {
            Long userGroupId = treeNode.getUserGroupId();
            String nodeName = treeNode.getNodeName();
            String pathString = treeNode.getPathEnum();
            if (pathString.substring(pathString.length() - 2, pathString.length() - 1).equals(targetNode.getNodeName())) {
                childNodeList.add(new UserGroupNodeVo(userGroupId, nodeName, pathString));
            }
        }
        return childNodeList;
    }

    private void recursionSetChildWithEffect(UserGroupNodeVo targetNode, List<UserGroupHierarchy> treeNodeList) {
        List<UserGroupNodeVo> childNode = getChildNode(targetNode, treeNodeList);
        targetNode.setChildren(childNode);
        for (UserGroupNodeVo userGroupNodeVo : childNode) {
            recursionSetChildWithEffect(userGroupNodeVo, treeNodeList);
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

    private UserGroupNodeVo getTreeNodeVoList(String nodeName) {
        QueryWrapper<UserGroupHierarchy> wrapper = Wrappers.<UserGroupHierarchy>query()
                .like(pathEnumLabel, nodeName)
                .orderByAsc(lenPath);
        List<UserGroupHierarchy> treeNodeList = userGroupHierarchyService.list(wrapper);

        UserGroupNodeVo userGroupNodeVo = toTreeNodeVo(treeNodeList);


        return userGroupNodeVo;
    }
}
