package demo.minifly.com.fuction_demo.float_titlebar;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import demo.minifly.com.BaseActivity;
import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.DensityUtils;

public class FloatTitleBarActivity extends BaseActivity {

    private RelativeLayout mFloatTitlebarRelId;
    private Button btnid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_title_bar);
        initView();
    }

    private void initView() {
        mFloatTitlebarRelId = (RelativeLayout) findViewById(R.id.float_titlebar_rel_id);
        btnid = (Button) findViewById(R.id.float_titlebar_btn_id);

        int delta = DensityUtils.dip2px(this,20);
//        int height = DensityUtils.dip2px(this,45);

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.66f, delta),
                Keyframe.ofFloat(1f, 0f)
        );


        final ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mFloatTitlebarRelId, pvhTranslateX).
                setDuration(800);
        /**
         AccelerateDecelerateInterpolator 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
         AccelerateInterpolator  在动画开始的地方速率改变比较慢，然后开始加速
         AnticipateInterpolator 开始的时候向后然后向前甩
         AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
         BounceInterpolator   动画结束的时候弹起
         CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
         DecelerateInterpolator 在动画开始的地方快然后慢
         LinearInterpolator   以常量速率改变
         OvershootInterpolator    向前甩一定值后再回到原来位置
         */
        animator.setInterpolator(new AccelerateDecelerateInterpolator());//BounceInterpolator在结束的地方弹起
        btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
    }
}
