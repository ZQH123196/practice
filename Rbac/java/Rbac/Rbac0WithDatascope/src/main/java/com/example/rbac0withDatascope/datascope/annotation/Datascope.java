package com.example.rbac0withDatascope.datascope.annotation;


import java.lang.annotation.*;

/**
 * 相当于是 Datascope 的意思
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Datascope {
    String Value() default "";
}
