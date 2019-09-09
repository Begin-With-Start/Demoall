package demo.minifly.com.fuction_demo.object_animator_test;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.DensityUtils;


/**
 * 贝塞尔曲线的购物车添加动画
 * 2017-02-28
 */
public class ObjectAnimatorBezierActivity extends Activity {

    private Button click;
    private ImageView foodImg,cartImg;
    private Context mContext;
    private RelativeLayout parentRel;
    private Activity mActivity;

    private Point startPoint = new Point(),endPoint= new Point(),assistPoint= new Point();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_bezier);

        mContext = this;
        mActivity = this;
        click = (Button) findViewById(R.id.bezier_btn_id);
        foodImg = (ImageView) findViewById(R.id.bezier_food_img);
        cartImg = (ImageView) findViewById(R.id.bezier_cart_img);
        parentRel = (RelativeLayout) findViewById(R.id.activity_object_animator_bezier);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ImageView goods = new ImageView(mContext);
                goods.setImageResource(R.drawable.food);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(DensityUtils.dip2px(mContext,16), DensityUtils.dip2px(mContext,16));
                parentRel.addView(goods, params);

                deal(goods);
            }
        });
    }

    /**
     * 当layout的布局绘制完之后对点的信息进行初始化操作.
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int [] foodArry = new int[2];
        foodImg.getLocationInWindow(foodArry);
        if(foodArry.length==2){
            startPoint.set(foodArry[0] ,foodArry[1]);//计算该视图在它所在的widnow的坐标x，y值
        }

        int [] cartArry = new int[2];
        cartImg.getLocationInWindow(cartArry);
        if(cartArry.length==2){
            endPoint.set(cartArry[0]+cartImg.getWidth()/2,cartArry[1] - cartImg.getHeight()/2);//结束点定位在购物车的中间位置
        }


        /**
         * 中间点确定
         */
        int pointX = (startPoint.x + endPoint.x) / 2;
        int pointY = startPoint.y;
        assistPoint = new Point(pointX, pointY);
    }

    /**
     * 动画处理
     * @param view
     */

    Path mPath = new Path();

    public void deal(final View view){
        // 重置路径
        mPath.reset();
        // 起点
        mPath.moveTo(startPoint.x, startPoint.y - DensityUtils.dip2px(mContext,20));
        // 重要的就是这句
        mPath.quadTo(assistPoint.x, assistPoint.y , endPoint.x, endPoint.y);

        ObjectAnimator mAnimator;
        mAnimator = ObjectAnimator.ofFloat(view, View.X, View.Y, mPath);
        mAnimator.setDuration(800);
        mAnimator.start();

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }
            @Override
            public void onAnimationEnd(Animator animator) {
                parentRel.removeView(view);
            }
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });

    }
}
