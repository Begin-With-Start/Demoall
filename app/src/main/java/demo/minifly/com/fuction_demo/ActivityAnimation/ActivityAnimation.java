package demo.minifly.com.fuction_demo.ActivityAnimation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;


/**
 * 共享元素的界面跳转
 * minifly
 * 哇，华为的这个坑，竟然不行
 * 到我的魅族一下就好了，坑死了，我还找了那么久的问题。
 * emui的3.1版本
 */
public class ActivityAnimation extends Activity {
    private ImageView imageView;
    private Context context;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = (ImageView)findViewById(R.id.my_image_id);

        context = this;
        mActivity = this;
        ViewCompat.setTransitionName(imageView, "_XXX");

        getWindow().setEnterTransition(new Explode());
//        getWindow().setEnterTransition(new Slide());
//        getWindow().setEnterTransition(new Fade());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context,ActivityAnimation2.class);
                Pair pair = new Pair<View, String>(imageView,"XXX");
                if (android.os.Build.VERSION.SDK_INT > 20) {//手机版本是20以上的时候。
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity,pair).toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });
        LogUtils.showErrLog("错误");
    }


}
