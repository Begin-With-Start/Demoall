package demo.minifly.com.fuction_demo.object_animator_test;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;


/**
 * author ：minifly
 * date: 2016/12/15
 * time: 19:28
 * desc: 属性动画
 */
public class ObjectAnimatorOwnColorTest extends Activity implements View.OnClickListener{
    private Context context;
    private View objectanimatorId;
    private ObjectAnimator objectAnimator;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_color_objectanimator);
        initView();
    }

    private void initView() {
        context = this;
        objectanimatorId = findViewById(R.id.objectanimator_id);
        objectanimatorId.setOnClickListener(this);

        /**
         * translationX translationY  平移
         * rotation rotationX,rotationY  旋转2
         * pivotX  pivotY  支点旋转
         * x y  终点的位置
         * alpha  view的透明度 0~1
         * 颜色的渐变
         */
//        objectAnimator = new ObjectAnimator().ofArgb(new ColorBean(objectanimatorId),"color",0x03a9f4,0x00bcd4);
//        objectAnimator.setDuration(4000);


        int startColor = 0xffff0000;
        //终止颜色为绿色
        int endColor = 0xff00ff00;
        valueAnimator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                //从初始的int类型的颜色值中解析出Alpha、Red、Green、Blue四个分量
                int startInt = (Integer) startValue;
                int startA = (startInt >> 24) & 0xff;
                int startR = (startInt >> 16) & 0xff;
                int startG = (startInt >> 8) & 0xff;
                int startB = startInt & 0xff;

                //从终止的int类型的颜色值中解析出Alpha、Red、Green、Blue四个分量
                int endInt = (Integer) endValue;
                int endA = (endInt >> 24) & 0xff;
                int endR = (endInt >> 16) & 0xff;
                int endG = (endInt >> 8) & 0xff;
                int endB = endInt & 0xff;

                LogUtils.showErrLog("fraction" + fraction);

                //分别对Alpha、Red、Green、Blue四个分量进行计算，
                //最终合成一个完整的int型的颜色值
                return (int)((startA + (int)(fraction * (endA - startA))) << 24) |
                        (int)((startR + (int)(fraction * (endR - startR))) << 16) |
                        (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                        (int)((startB + (int)(fraction * (endB - startB))));
            }
        }, startColor, endColor);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int)animation.getAnimatedValue();
                objectanimatorId.setBackgroundColor(color);
            }
        });
        valueAnimator.setTarget(objectanimatorId);

//        objectAnimator.setRepeatCount(20);
//        objectAnimator.setInterpolator(new AccelerateInterpolator()); //差值器添加

//        objectAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Toast.makeText(context,"动画结束！",Toast.LENGTH_LONG).show();//监听
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });

//        objectAnimator.addListener(new AnimatorListenerAdapter() {//这个监听只需要实现自己关心的相应的监听即可，
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Toast.makeText(context,"动画结束！",Toast.LENGTH_LONG).show();//监听
//            }
//        });
        /**
         * ObjectAnimator.ofFloat(imageView, "translationX", 0F, 360F).setDuration(1000).start();   //X轴平移旋转
         * ObjectAnimator.ofFloat(imageView, "translationY", 0F, 360F).setDuration(1000).start();   //Y轴平移旋转
         * ObjectAnimator.ofFloat(imageView, "rotation", 0F, 360F).setDuration(1000).start();       //360度旋转
         */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.objectanimator_id:
                valueAnimator.start();
                break;
        }
    }
}
