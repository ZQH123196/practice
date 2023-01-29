package com.example.rbac0withDatascope.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class TreeNode<T extends TreeNode> {
    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> children;

    public TreeNode() {
    }

    public TreeNode(T treeNode) {
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getNodePath();
        this.children = treeNode.getChildren();
    }

    public TreeNode(String nodeName, String nodePath) {
        this.nodeName = nodeName;
        this.nodePath = nodePath;
    }

    public TreeNode(String nodeName, String nodePath, List<T> children) {
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public String getNodePath() {
        return this.nodePath;
    }

    public List<T> getChildren() {
        return this.children;
    }

    @JsonProperty("nodeName")
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @JsonProperty("nodePath")
    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    @JsonProperty("children")
    public void setChildren(List<T> children) {
        this.children = children;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TreeNode)) return false;
        final TreeNode<?> other = (TreeNode<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$nodeName = this.getNodeName();
        final Object other$nodeName = other.getNodeName();
        if (this$nodeName == null ? other$nodeName != null : !this$nodeName.equals(other$nodeName)) return false;
        final Object this$nodePath = this.getNodePath();
        final Object other$nodePath = other.getNodePath();
        if (this$nodePath == null ? other$nodePath != null : !this$nodePath.equals(other$nodePath)) return false;
        final Object this$children = this.getChildren();
        final Object other$children = other.getChildren();
        if (this$children == null ? other$children != null : !this$children.equals(other$children)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TreeNode;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nodeName = this.getNodeName();
        result = result * PRIME + ($nodeName == null ? 43 : $nodeName.hashCode());
        final Object $nodePath = this.getNodePath();
        result = result * PRIME + ($nodePath == null ? 43 : $nodePath.hashCode());
        final Object $children = this.getChildren();
        result = result * PRIME + ($children == null ? 43 : $children.hashCode());
        return result;
    }

    public String toString() {
        return "TreeNode(nodeName=" + this.getNodeName() + ", nodePath=" + this.getNodePath() + ", children=" + this.getChildren() + ")";
    }
}
