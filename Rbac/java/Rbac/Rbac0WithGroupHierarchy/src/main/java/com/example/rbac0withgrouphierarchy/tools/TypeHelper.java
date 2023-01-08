package com.example.rbac0withgrouphierarchy.tools;

import java.lang.reflect.ParameterizedType;

public class TypeHelper<TYPE> {

    public <TYPE> TYPE newInstnce4Type(){
        TYPE newInstance;
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<TYPE> clazz = (Class<TYPE>) pt.getActualTypeArguments()[0];
            // 通过反射创建model的实例
            newInstance = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newInstance;
    }
}
