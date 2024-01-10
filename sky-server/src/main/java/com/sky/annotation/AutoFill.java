package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名: sky-take-out
 * 文件名: Autofill
 * 创建者: LZS
 * 创建时间:2024/1/10 14:17
 * 描述:自定义注解，用于填充公共字段
 **/
//注解使用位置：方法
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //数据库操作对象
    OperationType value();
}
