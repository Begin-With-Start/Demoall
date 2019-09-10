package com.demo.hexiaofei.multithread.singleton;

import org.apache.commons.lang.SerializationUtils;

/**
 * create by minifly on 2019-09-10 20:45
 * description:
 */
public class TestForSingleton {


    SerializationUtils utils;

    public boolean isEqual(){
        EnumSingleton instance = EnumSingleton.INSTANCE;
        byte[] serialize = SerializationUtils.serialize(instance);
        EnumSingleton newInstance = (EnumSingleton) SerializationUtils.deserialize(serialize);
        return instance == newInstance;
    }
}
