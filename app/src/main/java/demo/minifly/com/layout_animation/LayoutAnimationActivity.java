package demo.minifly.com.layout_animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2017-02-07 11:43
 */
public class LayoutAnimationActivity extends Activity {
    /**
     *
     * 1.在布局中添加这个设置之后添加view会有逐渐显示的过渡效果 android:animateLayoutChanges="true" 效果不是特别明显
     * 2.获取到viewgroup之后，给他指定一个进入的动画，如下。
     * @param savedInstanceState
     */
    private LinearLayout llLayoutAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_layout_animation);
        llLayoutAnimation = (LinearLayout)findViewById(R.id.ll_layoutanimation);

        ScaleAnimation sa = new ScaleAnimation(-1,1,1,1);
//        TranslateAnimation sa = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF);
        sa.setDuration(300);
        LayoutAnimationController lac = new LayoutAnimationController(sa,0.5F);//delay延迟
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        /**
         * normal : 顺序
         * random ：随机
         * reverse：反序
         */

        llLayoutAnimation.setLayoutAnimation(lac);//设置viewgroup的进入动画效果。

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
