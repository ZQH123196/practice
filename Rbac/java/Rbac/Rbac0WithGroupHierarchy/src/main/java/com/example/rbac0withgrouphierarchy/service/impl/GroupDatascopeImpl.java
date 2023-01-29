package com.example.rbac0withgrouphierarchy.service.impl;

import com.example.rbac0withgrouphierarchy.datascope.annotation.UserGroupDatascope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GroupDatascopeImpl {

    @UserGroupDatascope
    public void testDatascope() {
        log.info("testDatascope");
    }
}
