package com.example.rbac0withgrouphierarchy.datascope.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserGroupDatascope {


}
