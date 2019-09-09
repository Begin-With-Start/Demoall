package demo.minifly.com.fuction_demo.gilde_display_error;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leoao.sdk.common.utils.LogUtils;
import com.leoao.sdk.common.utils.StringUtils;

import demo.minifly.com.R;

public class GlideErrorDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_error_display);


        ImageView imageView = findViewById(R.id.glide_error_display_img);

//        Glide.with(this).load("https://img.leoao.com/%E6%99%AE%E9%80%9A%E7%94%B7new.png").diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        Glide.with(this).load("https://img.leoao.com/FjlA8LIZQu1vXFlAjwnV7ijdPfWv?imageView2/2/w/640").diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);


        String ANDROID_ID = getAndroidId(this);
        if (StringUtils.isNotBlank(ANDROID_ID) && !"9774d56d682e549c".equalsIgnoreCase(ANDROID_ID)) {
            LogUtils.e("GlideErrorDisplayActivity" , "" + ANDROID_ID);
        }

    }

    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
}
