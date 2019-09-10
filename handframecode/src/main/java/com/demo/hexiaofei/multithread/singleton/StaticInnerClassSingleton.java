package com.demo.hexiaofei.multithread.singleton;

/**
 * create by minifly on 2019-09-10 20:29
 * description: 静态内部类的方式来写单例；
 * 如果不是有序列化反序列化和反射的话，这个方式是比较好的；
 */
public class StaticInnerClassSingleton {
    private static class StaticInnerClassSingletonInstance{
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }
    private StaticInnerClassSingleton(){
    }
    public static StaticInnerClassSingleton getInstance(){
        return StaticInnerClassSingletonInstance.instance;
    }
}
