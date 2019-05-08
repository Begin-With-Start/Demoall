package com.fitness.network.annotation;

import com.fitness.network.NetTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //针对方法级
@Retention(RetentionPolicy.RUNTIME) //在运行时依旧可获取
public @interface NetworkListener {
    @NetTypes String type() default NetTypes.AUTO;//默认参数 auto时候直接返回所有的网络变化
}
