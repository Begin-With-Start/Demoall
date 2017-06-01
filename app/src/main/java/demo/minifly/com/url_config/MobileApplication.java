package demo.minifly.com.url_config;

import android.app.Application;
import android.os.Vibrator;

import demo.minifly.com.utils.SharedPreferencesHelper;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MobileApplication extends Application {
    public static SharedPreferencesHelper sp = null;
    static MobileApplication instance = null;
    OnBackLinstener onBackLinstener = null;

    //地图相应 starting
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        // 避免重复初始化
        instance = this;
        sp = new SharedPreferencesHelper(this);

    }


    public static MobileApplication getApplicationInstance() {
        return instance;
    }


    public interface OnBackLinstener{
        void onBack();
        void onError();
    }
}
