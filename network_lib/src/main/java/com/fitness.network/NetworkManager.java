package com.fitness.network;

import android.app.Application;
import android.content.IntentFilter;
import android.util.Log;

import com.fitness.network.annotation.NetworkListener;
import com.fitness.network.entry.MethodEntry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author ： minifly
 * 网络框架管理类；
 */
public class NetworkManager implements NetChangeObserver {
    private static volatile NetworkManager instance;

    private NetStateReceiver mReceiver;
    private Application mApplication;

    private Map<Object, List<MethodEntry>> methodEntryMap;

    public NetworkManager() {
        mReceiver = new NetStateReceiver();
        methodEntryMap = new HashMap<>();
    }

    public static NetworkManager getDefault() {
        if (instance == null) {
            synchronized (NetworkManager.class) {
                if (instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public Application getApplication() {
        if (mApplication == null) {
            throw new RuntimeException("NetworkManager.getDefault().init()没有初始化");
        }
        return mApplication;
    }

    public void init(Application application) {
        this.mApplication = application;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetStateReceiver.ANDROID_NET_CHANGE_ACTION);
        mApplication.registerReceiver(mReceiver, intentFilter);
        mReceiver.setListener(this);
    }

    /**
     * 解绑
     */
    public void unRegisterObserver(Object object) {
        if (methodEntryMap.size() != 0 && methodEntryMap.get(object.getClass()) != null) {
            methodEntryMap.remove(object.getClass());
        }
    }

    /**
     * 解绑
     */
    public void unRegisterAllObserver() {
        if (methodEntryMap.size() > 0) {
            methodEntryMap.clear();
        }
        NetworkManager.getDefault().logout();
    }

    private void logout() {
        getApplication().unregisterReceiver(mReceiver);
    }

    public void registerObserver(Object object) {
        List<MethodEntry> methodEntry = methodEntryMap.get(object.getClass());
        if (methodEntry == null) { //为空：尚未注册；
            methodEntry = findAnnotationMethod(object);
            methodEntryMap.put(object, methodEntry);
        }
    }

    /**
     * 获取注解的方法class
     *
     * @param object 监听类
     * @return 方法数组
     */
    public List<MethodEntry> findAnnotationMethod(Object object) {
        List<MethodEntry> methodEntryList = new ArrayList<>();
        Class<?> clazz = object.getClass();
        /**
         * getMethods : 获取类的方法 包括父类的方法
         * getDeclaredMethods ： 获取当前类的方法；
         */
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            NetworkListener annotation = method.getAnnotation(NetworkListener.class);
            if (annotation == null) {
                continue;
            }
            if (!"void".equals(method.getReturnType().toString())) {
                throw new RuntimeException(method.getName() + "方法的返回值需要为空!");
            }

//            if(Modifier.PUBLIC != method.getModifiers()){
//                throw new RuntimeException(method.getName() + "方法的返回值需要为空!");
//            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException(method.getName() + "方法的参数必须只有一个");
            }
            MethodEntry methodEntry = new MethodEntry(parameterTypes[0], annotation.type(), method);
            methodEntryList.add(methodEntry);
        }
        return methodEntryList;
    }

    /**
     * 分发网络变化监听；
     *
     * @param type 网络类型
     */
    private void engin(@NetTypes String type) {
        Set<Object> methodSets = methodEntryMap.keySet();
        for (Object object : methodSets) {
            List<MethodEntry> methodEntries = methodEntryMap.get(object);
            for (MethodEntry methodEntry : methodEntries) {
                if (methodEntry.getType().isAssignableFrom(type.getClass())) { //当监听传递的监听类型与注解方法的类型相同时
                    Log.e("mainactivity", "类型相同了啊1 " + methodEntry.getType().toString() + "     " + type.getClass().toString() + " method name  " + methodEntry.getMethod().getName() + "  type" + type);
                    switch (methodEntry.getNetType()) {
                        case NetTypes.AUTO:
                            invokeAnnotationMethod(methodEntry, type, object);
                            break;
                        case NetTypes.NET_2G:
                            if (NetTypes.NET_2G == type || NetTypes.NONE == type) {
                                invokeAnnotationMethod(methodEntry, type, object);
                            }
                            break;
                        case NetTypes.NET_3G:

                            if (NetTypes.NET_2G == type || NetTypes.NONE == type) {
                                invokeAnnotationMethod(methodEntry, type, object);
                            }
                            break;
                        case NetTypes.NET_4G:

                            if (NetTypes.NET_2G == type || NetTypes.NONE == type) {
                                invokeAnnotationMethod(methodEntry, type, object);
                            }
                            break;
                        case NetTypes.WIFI:
                            if (NetTypes.WIFI == type || NetTypes.NONE == type) {
                                invokeAnnotationMethod(methodEntry, type, object);
                            }
                            break;
                        case NetTypes.NONE:
                            invokeAnnotationMethod(methodEntry, type, object);
                            break;
                    }
                }

            }
        }
    }

    /**
     * 反射调用所有使用到了注解的方法；
     */
    private void invokeAnnotationMethod(MethodEntry methodEntry, @NetTypes String nettype, Object object) {
        Method excuteMethod = methodEntry.getMethod();
        try {
            //method modifier is not public have to set this ;
            excuteMethod.setAccessible(true);
            excuteMethod.invoke(object, nettype);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("mainactivity", "反射错误1 ");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.e("mainactivity", "反射错误2 ");
        }
    }

    @Override
    public void onConnected(@NetTypes String type) {
        engin(type); //分发
    }

    @Override
    public void onDisConnected() {
        engin(NetTypes.NONE); //没有网络
    }


}
