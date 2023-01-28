package com.example.rbac0withDatascope.controller;

import com.example.rbac0withDatascope.bff.vo.UserTreeList;
import com.example.rbac0withDatascope.service.IUserGroupDatascopeService;
import com.example.rbac0withDatascope.service.vo.MultipleUserGroupTree;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user-group-tree")
@CrossOrigin
public class UserGroupTreeController {

    @Resource
    IUserGroupDatascopeService treeListService;

    @RequestMapping("getAllUsergroupTree")
    public UserTreeList getAllUsergroupTree() {
        return treeListService.getAllUsergroupTree4Bff();
    }

    @RequestMapping("getMultipleTree")
    public MultipleUserGroupTree getMultipleTree() {
        return treeListService.getAllMultipleTree();
    }


}
