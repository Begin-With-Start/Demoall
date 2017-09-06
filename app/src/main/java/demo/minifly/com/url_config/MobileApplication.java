package demo.minifly.com.url_config;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yolanda.nohttp.NoHttp;

import demo.minifly.com.utils.SharedPreferencesHelper;

/**
 * Created by Administrator on 2016/10/8.
 * application初始化类
 */

public class MobileApplication extends Application {
    public static SharedPreferencesHelper sp = null;
    static MobileApplication instance = null;
    OnBackLinstener onBackLinstener = null;

    //地图相应 starting
    public Vibrator mVibrator;

    //是否是调试模式
    boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        // 避免重复初始化
        instance = this;
        sp = new SharedPreferencesHelper(this);

        //arouter初始化
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

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
