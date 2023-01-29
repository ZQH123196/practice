package com.example.rbac0withDatascope.service;

import com.example.rbac0withDatascope.datascope.annotation.Datascope;
import com.example.rbac0withDatascope.service.vo.MultipleUserGroupTree;
import com.example.rbac0withDatascope.controller.bff.vo.UserTreeList;
import com.example.rbac0withDatascope.dao.entity.UserGroupHierarchy;
import com.example.rbac0withDatascope.service.vo.UserGroupNodeVo;


import java.util.List;
import java.util.Set;

public interface IDatascope4UserGroupService {

    /**
     * 获取所有树，跟 getMultipleTree 区别在于返回的类型是 BffService.vo 下的
     * 也就是只为了返回前端进行了再次整形，
     * TODO：待修改，与实现高度耦合，应当分离，并移动到 bffservice
     * @return
     */
    @Datascope
    UserTreeList getAllUsergroupTree4Bff();


    /**
     * 获取所有树
     *
     * @return
     */
    @Datascope
    MultipleUserGroupTree getAllMultipleTree();



    /**
     * 根据用户返回一个基于此用户用户组 treeNode 为 root 的 treeNode，自动将其上下继承关系填充完毕
     * 相当于 getTargetNodeChildren 的简便版本，省略了部分转换
     * 因为一个用户可能被添加到多个组，因此返回的是一个 list
     *
     * @return
     */
    @Datascope
    Set<UserGroupNodeVo> getUserGroupHierarchyByUserId(Integer userId);

    /**
     * 获取目标节点的所有子树，也可用于获取一棵树的全部
     * @param nodeName 在这里就是 userGroupId
     * @return
     */
    @Datascope
    List<UserGroupNodeVo> getTargetNodeChildren(String nodeName);

    /**
     * 根据根节点获取树
     * 是 getTargetNodeChildren 的一个限定版函数
     *
     * @return
     */
    @Datascope
    List<UserGroupNodeVo> getTreeByRoot(String rootName);

    /**
     * 获取一颗树的任意某一层级
     *
     * @return
     */
    @Datascope
    List<UserGroupHierarchy> getLayerTree(String rootNode, int layer);


    /**
     * 查找孤立节点，一般用于清理无效节点
     * @param rootNode
     * @param layer
     */
    @Datascope
    void findIsolatedNode(String rootNode, int layer);


    /**
     * 查找循环节点，避免死循环
     * @param rootNode
     * @param layer
     */
    @Datascope
    void findLoopNode(String rootNode, int layer);


}
