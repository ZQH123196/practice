package com.example.rbac0withgrouphierarchy.service;

import com.example.rbac0withgrouphierarchy.bff.vo.UserTreeList;
import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.example.rbac0withgrouphierarchy.service.vo.MultipleTreeList;


import java.util.List;

public interface ITreeListService {

    UserTreeList getAllUsergroupTree();

    /**
     * 获取所有树
     *
     * @return
     */
    MultipleTreeList getMultipleTree();

    /**
     * 获取目标节点的所有子树，也可用于获取一棵树的全部
     *
     * @return
     */
    MultipleTreeList getTargetNodeChildren(String nodeName);

    /**
     * 根据根节点获取树
     * 是 getTargetNodeChildren 的一个限定版函数
     *
     * @return
     */
    MultipleTreeList getTreeByRoot(String rootName);

    /**
     * 获取一颗树的任意某一层级
     *
     * @return
     */
    List<UserGroupHierarchy> getLayerTree(String rootNode, int layer);


    /**
     * 查找孤立节点，一般用于清理无效节点
     * @param rootNode
     * @param layer
     */
    void findIsolatedNode(String rootNode, int layer);


    /**
     * 查找循环节点，避免死循环
     * @param rootNode
     * @param layer
     */
    void findLoopNode(String rootNode, int layer);


}
