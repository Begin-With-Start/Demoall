package com.demo.hexiaofei.multithread.singleton;

/**
 * create by minifly on 2019-09-09 20:02
 * description: double check 写法
 * 那么会出现一种情况：当变量已经初始化了 地址指向了内存了， 但是没调用到构造方法；
 * 这个时候，b 来使用单例的时候，恰好拿到的是一个不为空未构造的对象。。。那么就出现问题了；
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton INSTANCE ; //volatile 轻量级同步块，volatile关键字来修饰singleton，其最关键的作用是防止指令重排
    private DoubleCheckSingleton(){ }

    public DoubleCheckSingleton getInstance(){
        if(INSTANCE == null){
            synchronized (DoubleCheckSingleton.class){ //加锁保证只有一个线程进入到第二个判断中去；
                if(INSTANCE == null){
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
