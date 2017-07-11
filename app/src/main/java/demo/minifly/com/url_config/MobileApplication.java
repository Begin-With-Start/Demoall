package demo.minifly.com.url_config;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;

import com.yolanda.nohttp.NoHttp;

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

        // 初始化NoHttp
        NoHttp.initialize(getApplicationContext());
    }


    public static MobileApplication getApplicationInstance() {
        return instance;
    }


    public interface OnBackLinstener{
        void onBack();
        void onError();
    }
    // 获取版本名
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
