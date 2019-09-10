package com.demo.hexiaofei.multithread.singleton;

/**
 * create by minifly on 2019-09-09 19:57
 * description:饿汉式的单例实现具体代码
 */
public class HangerSingleton {
    public static HangerSingleton INSTANCE  = new HangerSingleton();

    private HangerSingleton(){

    }

    public static HangerSingleton getInstance(){
        return INSTANCE;
    }
}
