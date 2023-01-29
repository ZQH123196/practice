package com.example.rbac0withDatascope.controller;

import com.example.rbac0withDatascope.controller.bff.vo.UserTreeList;
import com.example.rbac0withDatascope.service.IDatascope4UserGroupService;
import com.example.rbac0withDatascope.service.vo.MultipleUserGroupTree;
import com.example.rbac0withDatascope.service.vo.UserGroupNodeVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user-group-tree")
@CrossOrigin
public class UserGroupTreeController {

    @Resource
    IDatascope4UserGroupService treeListService;

    @RequestMapping("getAllUsergroupTree")
    public UserTreeList getAllUsergroupTree() {
        return treeListService.getAllUsergroupTree4Bff();
    }

    @RequestMapping("getMultipleTree")
    public MultipleUserGroupTree getMultipleTree() {
        return treeListService.getAllMultipleTree();
    }

    @RequestMapping("getTargetNodeChildren")
    public List<UserGroupNodeVo> getTargetNodeChildren(@RequestBody String nodeName) {
        return treeListService.getTargetNodeChildren(nodeName);
    }

}
