package com.example.rbac0withgrouphierarchy.controller;

import com.example.rbac0withgrouphierarchy.bff.vo.UserTreeList;
import com.example.rbac0withgrouphierarchy.service.ITreeListService;
import com.example.rbac0withgrouphierarchy.service.vo.MultipleTreeList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user-group-tree")
@CrossOrigin
public class UserGroupTreeController {

    @Resource
    ITreeListService treeListService;

    @RequestMapping("getAllUsergroupTree")
    public UserTreeList getAllUsergroupTree() {
        return treeListService.getAllUsergroupTree();
    }

    @RequestMapping("getMultipleTree")
    public MultipleTreeList getMultipleTree() {
        return treeListService.getMultipleTree();
    }


}
