package demo.minifly.com.fuction_demo.url_config;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fitness.network.NetworkManager;
import com.leoao.sdk.common.config.SdkConfig;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.yolanda.nohttp.NoHttp;

import demo.minifly.com.fuction_demo.utils.SharedPreferencesHelper;
import demo.minifly.com.fuction_demo.utils.ToastUtils;
import demo.minifly.com.fuction_demo.weex_test.IImageLoaderAdapter;

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

    //acitivity 数量统计 来判断是否实在前端
    int appCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        // 避免重复初始化
        instance = this;
        sp = new SharedPreferencesHelper(this);


        NetworkManager.getDefault().init(this);

        SdkConfig.getSdkConfig().init(this);
        //arouter初始化
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        // 初始化NoHttp
        NoHttp.initialize(getApplicationContext());

        /** weex的初始化操作 **/
        InitConfig config = new InitConfig.Builder().setImgAdapter(new IImageLoaderAdapter()).build();
        WXSDKEngine.initialize(this, config);


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                                               @Override
                                               public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                                               }

                                               @Override
                                               public void onActivityStarted(Activity activity) {
                                                   appCount++;
                                               }

                                               @Override
                                               public void onActivityResumed(Activity activity) {
                                               }

                                               @Override
                                               public void onActivityPaused(Activity activity) {
                                               }

                                               @Override
                                               public void onActivityStopped(Activity activity) {

                                                   appCount--;
                                                   if (appCount == 0) {
                                                       ToastUtils.showToast("到后台了");
                                                   }
                                               }

                                               @Override
                                               public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                                               }

                                               @Override
                                               public void onActivityDestroyed(Activity activity) {

                                               }
                                           }
        );
    }

    public int getAppCount() {
        return appCount;
    }

    public void setAppCount(int appCount) {
        this.appCount = appCount;
    }


    public static MobileApplication getApplicationInstance() {
        return instance;
    }


    public interface OnBackLinstener {
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
