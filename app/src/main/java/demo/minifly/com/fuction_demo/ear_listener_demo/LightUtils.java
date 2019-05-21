package demo.minifly.com.fuction_demo.ear_listener_demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * author ：minifly
 * date: 2017/9/8
 * time: 16:45
 * desc:
 */
public class LightUtils {
    /**
     * 设置当前页面的亮度
     * @param context
     * @param brightness
     */
    public static void setLight(Context context, int brightness) {
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        ((Activity)context).getWindow().setAttributes(lp);
    }

    /**
     * 设置成系统的亮度
     * @param activity
     * @param brightness
     */
    public static void saveBrightness(Context activity, int brightness) {
        //先需要改变成手动的模式才能进行亮度设置
        setBrightnessMode((Activity) activity, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

        ContentResolver contentResolver = activity.getContentResolver();
        Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
        activity.getContentResolver().notifyChange(uri, null);
    }

    /**
     * 设置当前屏幕亮度的模式
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     */
    public static void setBrightnessMode(Context activity, int brightMode) {
        Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, brightMode);
    }
}
