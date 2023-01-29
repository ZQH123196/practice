package com.example.rbac0withgrouphierarchy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.example.rbac0withgrouphierarchy.service.ITreeListService;
import com.example.rbac0withgrouphierarchy.service.vo.MultipleTreeList;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class GroupTreeServiceImplTest {

    @Resource
    ITreeListService treeListService;

    @Resource
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMultipleTree() throws JsonProcessingException {
        MultipleTreeList multipleTree = treeListService.getMultipleTree();

        String jsonStr = objectMapper.writeValueAsString(multipleTree);

        log.info(jsonStr);
    }

    @Test
    void getTargetNodeChildren() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"A"})
    void getTreeByRoot(String rootName) throws JsonProcessingException {
        MultipleTreeList multipleTree = treeListService.getTreeByRoot(rootName);
        String jsonStr = objectMapper.writeValueAsString(multipleTree);

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