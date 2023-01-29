package com.example.rbac0withDatascope.controller.bff.serviec;

import cn.hutool.core.lang.copier.Copier;
import com.example.rbac0withDatascope.controller.bff.vo.UserTreeList;
import com.example.rbac0withDatascope.controller.bff.vo.UsergroupTreeNodeVoList;
import com.example.rbac0withDatascope.service.IDatascope4UserGroupService;
import com.example.rbac0withDatascope.service.vo.UserGroupNodeVo;
import com.google.common.base.Supplier;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 为了返回前端的一次整形
 */
public class BffService {

    @Resource
    IDatascope4UserGroupService datascopeService;

    public UserTreeList getTargetNodeChildren(String nodeName) {
        List<UserGroupNodeVo> targetNodeChildren = datascopeService.getTargetNodeChildren(nodeName);
        List<UsergroupTreeNodeVoList> treeNodeVoLists = walkUserGroupNodeVoList2BffVo(targetNodeChildren);
        return UserTreeList.builder().treeNodeVoList(treeNodeVoLists).build();
    }

    private List<UsergroupTreeNodeVoList> walkUserGroupNodeVoList2BffVo(List<UserGroupNodeVo> targetNodeChildren) {

        
        return null;
    }


}
