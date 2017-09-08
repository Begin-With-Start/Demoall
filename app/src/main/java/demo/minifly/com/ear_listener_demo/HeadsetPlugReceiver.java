package demo.minifly.com.ear_listener_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import demo.minifly.com.utils.ToastUtils;

/**
 * author ：minifly
 * date: 2017/9/8
 * time: 16:02
 * desc: 耳机监听相关的东西
 */
public class HeadsetPlugReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("state")){
            if (intent.getIntExtra("state", 0) == 0){
                ToastUtils.showToast("拔出耳机！");
                LightUtils.setBrightnessMode(context,1);
            }
            else if (intent.getIntExtra("state", 0) >0 ){
                ToastUtils.showToast("插入耳机！");
                LightUtils.setBrightnessMode(context,0);
            }
        }
    }


}
