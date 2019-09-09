package demo.minifly.com.fuction_demo.user_defined_animator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demo.minifly.com.R;


/**
 * 自定义的animation动画，模拟电视的关机的动画。
 */
public class OwnAnimatorActivity2 extends Activity {

    private ImageView imageId;
    private int mWidth;
    private int mHeight;
    private ViewTreeObserver vto;
    private RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animator_myown);
        initView();
    }


    private void initView() {
        imageId = (ImageView) findViewById(R.id.image_id);
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);

//        mWidth = imageId.getWidth();
//         通过这种方式获取到的高度都是0 ， 因为在oncreate 与 onresume中的imageview是还没有渲染进去的，
//        只有对imageview进行监听才能在iamgeview 加载好之后拿到值
//        mHeight = imageId.getHeight();

        vto = imageId.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener vt = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                mHeight = imageId.getMeasuredHeight();
                mWidth = imageId.getMeasuredWidth();
                return true;
            }
        };
        vto.addOnPreDrawListener(vt);

        imageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OwnAnimation ownAnimation = new OwnAnimation(0, 0);
                ownAnimation.setDuration(1000);

//                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//                alphaAnimation.setDuration(1000);

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(ownAnimation);
//                animationSet.addAnimation(alphaAnimation);
                //动画结束之后保留到结束的状态 , 但是animation 的动画并不是真正的将view 移动或者是旋转了，
                // 所以设置了该值之后，照样可以点击这个view，一样播放动画
                animationSet.setFillAfter(true);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        imageId.setVisibility(View.GONE);//电视的一个关闭效果模仿，缩小消失掉
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                imageId.startAnimation(animationSet);
            }
        });
    }

    /**
     * 电视的一个关闭动画的模仿
     */
    class OwnAnimation extends RotateAnimation {
        public OwnAnimation(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public OwnAnimation(float fromDegrees, float toDegrees) {
            super(fromDegrees, toDegrees);
        }

        public OwnAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
            super(fromDegrees, toDegrees, pivotX, pivotY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            final Matrix matrix = t.getMatrix();
            matrix.preScale(1, 1 - interpolatedTime, mWidth / 2, mHeight / 2);
        }
    }


}
