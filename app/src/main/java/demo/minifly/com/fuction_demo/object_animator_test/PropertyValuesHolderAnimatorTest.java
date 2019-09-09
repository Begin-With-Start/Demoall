package demo.minifly.com.fuction_demo.object_animator_test;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import demo.minifly.com.R;


/**
 * author ：minifly
 * date: 2016/12/15
 * time: 19:28
 * desc: 属性动画中，针对于一个对象的多个属性，同时进行各种属性的变化操作时候，使用PropertyValuesHolder来进行
 * 平移过程中，还进行缩放操作。
 */
public class PropertyValuesHolderAnimatorTest extends Activity implements View.OnClickListener{
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
         * scaleX scaleY
         */
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("translationX",300f);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("scaleX",1f,0f,1f); //从1倍到0到1倍
        PropertyValuesHolder propertyValuesHolder3 = PropertyValuesHolder.ofFloat("scaleY",1f,0f,1f); //从1倍到0到1倍
        ObjectAnimator.ofPropertyValuesHolder(objectanimatorId,propertyValuesHolder1,propertyValuesHolder2,propertyValuesHolder3).setDuration(1000).start();

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
