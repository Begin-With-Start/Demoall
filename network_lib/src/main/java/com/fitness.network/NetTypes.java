package com.fitness.network;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络类型：
 * 代替枚举的google建议的用法；
 * 感兴趣可以了解下：https://www.liaohuqiu.net/cn/posts/android-enum-memory-usage/
 * 而在Android的开发文档里，google建议使用@IntDef/@StringDef注解替代枚举
 */
@Retention(RetentionPolicy.SOURCE) //源代码即可；
@StringDef({NetTypes.AUTO, NetTypes.WIFI, NetTypes.NET_4G, NetTypes.NET_3G , NetTypes.NET_2G, NetTypes.NONE})
public @interface NetTypes {
    //有网络，包括Wifi/gprs
    public static final String AUTO = "AUTO";

    //wifi
    public static final String WIFI = "WIFI";

    //4G 网络
    public static final String  NET_4G= "4G";

    //3G 网络
    public static final String  NET_3G= "3G";

    //2G 网络
    public static final String  NET_2G= "2G";

    //没有网络
    public static final String NONE = "NONE";
}
