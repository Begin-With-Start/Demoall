package demo.minifly.com.float_titlebar;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import demo.minifly.com.R;
import demo.minifly.com.request_demo.BaseActivity;
import demo.minifly.com.utils.DensityUtils;

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
                setDuration(1000);
        animator.setInterpolator(new AnticipateOvershootInterpolator());//BounceInterpolator在结束的地方弹起
        //AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值

        btnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
    }
}
