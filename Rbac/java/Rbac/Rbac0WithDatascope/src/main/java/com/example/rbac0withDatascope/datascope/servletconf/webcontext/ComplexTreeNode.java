package com.example.rbac0withDatascope.datascope.servletconf.webcontext;

import com.example.rbac0withDatascope.service.vo.TreeNode;
import lombok.Data;

@Data
public class ComplexTreeNode extends TreeNode {

    private Long userGroupId;


    ComplexTreeNode() {
        super();
    }


}
