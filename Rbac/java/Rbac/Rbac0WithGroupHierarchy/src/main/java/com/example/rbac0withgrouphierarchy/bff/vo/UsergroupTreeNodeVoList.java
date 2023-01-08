package com.example.rbac0withgrouphierarchy.bff.vo;

import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.example.rbac0withgrouphierarchy.service.vo.TreeNodeVoList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class UsergroupTreeNodeVoList {



    @JsonProperty("userGroupName")
    private String userGroupName;

    @JsonProperty("userGroupId")
    private Long userGroupId;

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UsergroupTreeNodeVoList> children;

    public UsergroupTreeNodeVoList(UserGroupHierarchy treeNode) {
        this.userGroupId = treeNode.getUserGroupId();
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getPathEnum();
    }

    public UsergroupTreeNodeVoList(String userGroupName, Long userGroupId, String nodeName, String nodePath, List<UsergroupTreeNodeVoList> children) {
        this.userGroupName = userGroupName;
        this.userGroupId = userGroupId;
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }
}
