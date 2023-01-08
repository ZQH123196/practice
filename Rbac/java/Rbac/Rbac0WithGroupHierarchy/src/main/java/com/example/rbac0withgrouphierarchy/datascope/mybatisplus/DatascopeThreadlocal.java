package com.example.rbac0withgrouphierarchy.datascope.mybatisplus;

import lombok.Data;

@Data
public class DatascopeThreadlocal {

    private ThreadLocal<DatascopeShared> sharedThreadLocal = new ThreadLocal<>();



}
