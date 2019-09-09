package com.fitness.network.entry;

import com.fitness.network.NetTypes;

import java.lang.reflect.Method;

/**
 * 方法包装类；
 */
public class MethodEntry {
    private Class<?> type;
    private @NetTypes String netType ;
    private Method method;

    public MethodEntry(Class<?> type, String netType, Method method) {
        this.type = type;
        this.netType = netType;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
