package com.example.rbac0withgrouphierarchy.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupDatascopeImplTest {

    @Resource
    GroupDatascopeImpl groupDatascope;

    @Test
    void testDatascope() {
        groupDatascope.testDatascope();
    }
}