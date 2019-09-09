package com.fitness.network;

public interface NetChangeObserver {
    /**
     * 网络连接成功
     */
    void onConnected(@NetTypes String type);
    /**
     * 网络断开
     */
    void onDisConnected();
}
