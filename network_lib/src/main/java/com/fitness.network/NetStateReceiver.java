package com.fitness.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fitness.network.utils.NetWorkUtils;

public class NetStateReceiver extends BroadcastReceiver {
    private final static String TAG = "BroadcastReceiver";
    public final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    NetChangeObserver mNetChangeObserver;
    private @NetTypes String type;

    public  NetStateReceiver(){
        this.type = NetTypes.NONE;
    }

    public void setListener(NetChangeObserver observer){
        mNetChangeObserver = observer;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getAction() == null){
            Log.e(TAG,"广播异常");
            return;
        }
        if(intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)){
            Log.e( TAG,"网络状态变化了");
            type = NetWorkUtils.getNetworkType(context);
            if(NetWorkUtils.isNetWorkAvailable()){
                Log.e( TAG,"网络连上了");
                mNetChangeObserver.onConnected(type);
            }else{
                Log.e( TAG,"网络断开了");
                mNetChangeObserver.onDisConnected();
            }
        }
    }
}
