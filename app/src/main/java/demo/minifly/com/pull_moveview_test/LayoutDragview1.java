package demo.minifly.com.pull_moveview_test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：minifly on 2016/11/25 16:05
 * view 的随手指进行滑动
 * 第一种实现方式 view的滑动
 * 通过layout onlayout 来进行。
 */
public class LayoutDragview1 extends View implements View.OnTouchListener{

    private int lastX;
    private int lastY;
    private int offsetX;
    private int offsetY;

    public LayoutDragview1(Context context) {
        super(context);
    }

    public LayoutDragview1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutDragview1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int)event.getRawX(); //获取基于view的坐标原点的x轴坐标
        int rawY = (int)event.getRawY(); //获取基于view的坐标原点的y轴坐标

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = rawX - lastX;
                offsetY = rawY - lastY;

                /**
                 * 第一种方式
                 */
                //左上右下分别进行x轴与y轴的平移操作。
//                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

                /**
                 * 第二种方式 从根本上和第一种一样。
                 */
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                /**
                 * 第三种方式 通过layouparam进行
                 * 使用marginlayoutparams 可以不用考虑父布局的类型，因为这是他们的公共父类。
                 */
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//
//                layoutParams.leftMargin = getLeft()+offsetX;  //布局的参数来进行
//                layoutParams.topMargin = getTop()+ offsetY;
//
//                setLayoutParams(layoutParams);
                /**
                 * 第三种方式 属性动画的方式。
                 */
                // 属性动画移动
                ObjectAnimator y = ObjectAnimator.ofFloat(this, "y",  getY(), getY()+ offsetY);//开始点，结束点。 百度好坑。
                ObjectAnimator x = ObjectAnimator.ofFloat(this, "x",  getX(), getX()+ offsetX);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(x, y);
                animatorSet.setDuration(0);
                animatorSet.start();
                /**第四，五中方法。
                 * scrollto , scrollby , scroller 三种方式也可以。
                 */

                /**
                 * 第六种方法
                 * qq的侧滑相关的类，viewdragerhelper,来实现view的滑动。
                 */

                lastX = rawX;
                lastY = rawY;

                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
        //事件拦截机制
        // 如果返回true，处理了，不用下流程。
        //false 事件不拦截，继续流程，那么这个事件会给到activity(父级viewgroup)的进行处理。
        //在父级发生的事件还是在父级中进行处理的。

    }

    /**
     * 可以在activity中来测算布局的宽高，不让控件滑到控件外面去。
     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        // 这里来获取容器的宽和高
//        if (hasFocus) {
//            int containerHeight = this.getHeight();
//            int containerWidth = this.getWidth();
//        }
//    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
