export interface TreeNode4Front extends Node {
    label: string;
    children?: TreeNode4Front[] | [];
}

export interface TreeNode4Map {
    [propname: string]: {
        nodeName: string,
        nodePath: string,
        children?: TreeNode4Map
    }
}

export interface TreeNode4Array extends Node {
    "userGroupName": string,
    "userGroupId": number,
    children?: TreeNode4Array[]
}

export interface Node {
    nodeName: string,
    nodePath: string,
}

export function walkTree4Map(node: TreeNode4Map, tree: TreeNode4Front[]) {
    const keys = Object.keys(node)
    keys.forEach(k => {
        let item = node[k]
        let newValue: TreeNode4Front = {
            label: item.nodePath,
            nodeName: item.nodeName,
            nodePath: item.nodePath
        }

        tree.push(newValue)
        if (item.children) {
            newValue.children = []
            walkTree4Map(item.children, newValue.children)
        }
    })
};

/**
 * 将 node 的值转为 tree
 * @param node 
 * @param tree 
 */
export function walkTree4Array(node: TreeNode4Array[], tree: TreeNode4Front[]) {
    node.forEach(item => {
        let newValue: TreeNode4Front = {
            label: item.userGroupName,
            nodeName: item.nodeName,
            nodePath: item.nodePath
        }
        tree.push(newValue)
        if (item.children) {
            newValue.children = []
            walkTree4Array(item.children, newValue.children)
        }
    })
};


export function walkTree(node: TreeNode4Array[], cb: (args0: TreeNode4Array) => void) {
    node.forEach(item => {
        cb(item)
        if (item.children) {
            walkTree(item.children, cb)
        }
    })
};
