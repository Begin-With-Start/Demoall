package com.demo.hexiaofei.multithread.singleton;

/**
 * create by minifly on 2019-09-09 20:00
 * description:懒汉式单例
 */
public class LazySingleton {
    private static LazySingleton INSTANCE ;
    private LazySingleton(){}

    public LazySingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LazySingleton();
            return INSTANCE;
        }else{
            return INSTANCE;
        }
    }
}
