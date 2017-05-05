package demo.minifly.com.androidl_animation;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import demo.minifly.com.R;
import demo.minifly.com.utils.DensityUtils;

/**
 * Android L新增的动画效果
 * 图形揭示
 * 哇，华为的这个坑，竟然不行
 * 到我的魅族一下就好了，坑死了，我还找了那么久的问题。
 */
public class AndroidLCircularRevealActivity extends Activity {

    private Context mContext;
    private View myView,myView2;
    private Animator animator1;
    private Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_lcircular_reveal);
        mContext = this;
        deal();

    }

    public void deal(){
        myView = findViewById(R.id.circular_reveal_view_id);
        myView2 = findViewById(R.id.circular_reveal_view_id2);


        //view加载完成时回调
        myView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {





        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator1 = ViewAnimationUtils.createCircularReveal(
                    myView,
                    DensityUtils.dip2px(mContext,100)/2,
                    DensityUtils.dip2px(mContext,100)/2,
                    DensityUtils.dip2px(mContext,100),
                    0);
                animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                animator1.setDuration(2000);
                animator1.start();
            }
        });

        myView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animator = ViewAnimationUtils.createCircularReveal(
                        myView2,
                        0,
                        0,
                        0,
                        (float) Math.hypot(DensityUtils.dip2px(mContext,100), DensityUtils.dip2px(mContext,100)));
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
        super.onWindowFocusChanged(hasFocus);
    }
}
