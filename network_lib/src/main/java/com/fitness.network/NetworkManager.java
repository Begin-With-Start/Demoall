package com.fitness.network;

import android.app.Application;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fitness.network.annotation.NetworkListener;
import com.fitness.network.entry.MethodEntry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.arch.lifecycle.Lifecycle.State.DESTROYED;

/**
 * author ： minifly
 * 网络框架管理类；
 */
public class NetworkManager implements NetChangeObserver {
    private static volatile NetworkManager instance;

    private NetStateReceiver mReceiver;
    private Application mApplication;

    private Map<LifecycleOwner, List<MethodEntry>> methodEntryMap;

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
        if (methodEntryMap.size() != 0 && methodEntryMap.get(object) != null) {
            Log.e("mainactivity" , "注销方法" + object.getClass());
            methodEntryMap.remove(object);
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

    public void registerObserver(LifecycleOwner object) {
        List<MethodEntry> methodEntry = methodEntryMap.get(object.getClass());
        if (methodEntry == null) { //为空：尚未注册；
            if (object.getLifecycle().getCurrentState() == DESTROYED) {
                return;
            }
            LifecycleBoundObserver observer = new LifecycleBoundObserver(object);
            object.getLifecycle().addObserver(observer);
            methodEntry = findAnnotationMethod(object);
            if(methodEntry != null && methodEntry.size() != 0){
                methodEntryMap.put(object, methodEntry);
            }
        }
    }

    /**
     * 获取注解的方法class
     * 检查： 返回值为空 + 方法参数为一个
     * @param object 监听类
     * @return 方法数组 找不到绑定的方法不会添加到map中；
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

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException(method.getName() + "方法的参数必须只有一个");
            }
            MethodEntry methodEntry = new MethodEntry(parameterTypes[0], annotation.type(), method );
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
        Set<LifecycleOwner> methodSets = methodEntryMap.keySet();

        for (Object object : methodSets) {
            List<MethodEntry> methodEntries = methodEntryMap.get(object);
            for (MethodEntry methodEntry : methodEntries) {
                if (methodEntry.getType().isAssignableFrom(type.getClass())) { //当监听传递的监听类型与注解方法的类型相同时
//                    Log.e("mainactivity" ,"反射到这个方法" + methodEntry.getMethod().getName() );
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

                            if (NetTypes.NET_3G == type || NetTypes.NONE == type) {
                                invokeAnnotationMethod(methodEntry, type, object);
                            }
                            break;
                        case NetTypes.NET_4G:

                            if (NetTypes.NET_4G == type || NetTypes.NONE == type) {
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
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    class LifecycleBoundObserver implements GenericLifecycleObserver {
        @NonNull final LifecycleOwner mOwner;

        LifecycleBoundObserver(@NonNull LifecycleOwner owner) {
            mOwner = owner;
        }

        @Override
        public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
            if (mOwner.getLifecycle().getCurrentState() == DESTROYED) {
                unRegisterObserver(mOwner);
                return;
            }

//            if(event == Lifecycle.Event.ON_STOP || event == Lifecycle.Event.ON_PAUSE){
//
//            }else{
//
//            }
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
