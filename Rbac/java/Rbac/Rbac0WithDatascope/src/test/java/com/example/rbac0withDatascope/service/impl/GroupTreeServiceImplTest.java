package com.example.rbac0withDatascope.service.impl;

import com.example.rbac0withDatascope.Initial.GlobalUserGroupHierarchy;
import com.example.rbac0withDatascope.dao.entity.UserGroupHierarchy;
import com.example.rbac0withDatascope.service.IDatascope4UserGroupService;
import com.example.rbac0withDatascope.service.vo.MultipleUserGroupTree;
import com.example.rbac0withDatascope.service.vo.UserGroupNodeVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class GroupTreeServiceImplTest {

    @Resource
    IDatascope4UserGroupService treeListService;

    @Resource
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Resource
    GlobalUserGroupHierarchy globalUserGroupHierarchy;

    @Test
    void testGlobal() throws JsonProcessingException {
        String jsonStr = objectMapper.writeValueAsString(globalUserGroupHierarchy.getMultipleUserGroupTree());
        log.info(jsonStr);
    }




    @Test
    void getAllMultipleTree() throws JsonProcessingException {
        MultipleUserGroupTree multipleUserGroupTree = treeListService.getAllMultipleTree();
        String jsonStr = objectMapper.writeValueAsString(multipleUserGroupTree);
        log.info(jsonStr);
    }

    /**
     * 前端点击树的某个节点时用到
     */
    @ParameterizedTest
    @ValueSource(strings = { "A", "B", "C" })
    void getTargetNodeChildren(String nodeName) throws JsonProcessingException {
        List<UserGroupNodeVo> targetNodeChildren = treeListService.getTargetNodeChildren(nodeName);
        String jsonStr = objectMapper.writeValueAsString(targetNodeChildren);
        log.info(jsonStr);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A"})
    void getTreeByRoot(String rootName) throws JsonProcessingException {
        List<UserGroupNodeVo> newTreeNodeList = treeListService.getTreeByRoot(rootName);
        MultipleUserGroupTree multipleUserGroupTree = MultipleUserGroupTree.builder().treeNodeVoList(newTreeNodeList).build();
        String jsonStr = objectMapper.writeValueAsString(multipleUserGroupTree);

        log.info(jsonStr);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, 1", "A, 2", "A, 3"})
    void getLayerTree(String rootNode, int layer) {
        List<UserGroupHierarchy> curLayerNodes = treeListService.getLayerTree(rootNode, layer);

        for (UserGroupHierarchy curLayerNode : curLayerNodes) {
            log.info("nodeName = {}, path = {}", curLayerNode.getNodeName(), curLayerNode.getPathEnum());
        }
    }

    @Test
    void findIsolatedNode() {
    }

    @Test
    void findLoopNode() {
    }
}