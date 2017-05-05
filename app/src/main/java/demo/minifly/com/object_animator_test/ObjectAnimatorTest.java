package demo.minifly.com.object_animator_test;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import demo.minifly.com.R;


/**
 * author ：minifly
 * date: 2016/12/15
 * time: 19:28
 * desc: 属性动画
 */
public class ObjectAnimatorTest extends Activity implements View.OnClickListener{
    private Context context;
    private ImageView objectanimatorId;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_objectanimator);
        initView();
    }

    private void initView() {
        context = this;
        objectanimatorId = (ImageView) findViewById(R.id.objectanimator_id);
        objectanimatorId.setOnClickListener(this);

        /**
         * translationX translationY  平移
         * rotation rotationX,rotationY  旋转2
         * pivotX  pivotY  支点旋转
         * x y  终点的位置
         * alpha  view的透明度 0~1
         */
        objectAnimator = new ObjectAnimator().ofFloat(objectanimatorId,"rotation",360);
        objectAnimator.setDuration(300);
        objectAnimator.setRepeatCount(20);
        objectAnimator.setInterpolator(new AccelerateInterpolator()); //差值器添加
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(context,"动画结束！",Toast.LENGTH_LONG).show();//监听
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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
                objectAnimator.start();
                break;
        }
    }
}
