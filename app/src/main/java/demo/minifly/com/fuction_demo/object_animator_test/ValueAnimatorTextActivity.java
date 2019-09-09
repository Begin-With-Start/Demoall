package demo.minifly.com.fuction_demo.object_animator_test;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demo.minifly.com.R;


/**
 * author ：minifly
 * date: 2017-02-07
 * time: 19:28
 * desc: valueanimator 值动画
 */
public class ValueAnimatorTextActivity extends Activity implements View.OnClickListener{
    private Context context;
    private TextView numberText,numberText2;
    private Button clickBtn;
    private ValueAnimator animator,animator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_valueanimator);
        initView();
    }

    private void initView() {
        numberText = (TextView)findViewById(R.id.text_id);
        numberText2 = (TextView)findViewById(R.id.text_id2);
        clickBtn = (Button)findViewById(R.id.button_id);

        clickBtn.setOnClickListener(this);

        numberText.setOnClickListener(this);

        int [] data = getInt2ByFloat(50001.24f);

        animator = ValueAnimator.ofInt(0,data[0]);
        animator2 = ValueAnimator.ofInt(0,data[1]);

        /**
         * 监听属性动画的改变值
         * 类似口袋钱包的钱数额增加的，还得有其他的处理方式才行。
         *
         * 2017-
         */
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                numberText.setText(""+ (valueAnimator.getAnimatedValue()));
            }
        });
        animator.setDuration(2000);
        /**
         * 监听属性动画的改变值
         * 类似口袋钱包的钱数额增加的，还得有其他的处理方式才行。
         */
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                numberText2.setText(""+ (valueAnimator.getAnimatedValue()));
            }
        });
        animator2.setDuration(2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_id:
                /**
                 *调起动画.
                 */
                animator.start();
                animator2.start();
                break;
        }
    }
    public int[] getInt2ByFloat(float number){
        if(number == 0f){
            return new int[]{0,0};
        }
        String numberStr = number+"";

        String [] dest = numberStr.split("\\.");
        if(dest.length == 2){

            return new int[]{parseIntByStr(dest[0]),parseIntByStr(dest[1])};
        }else if(dest.length==1){
            return new int[]{0,parseIntByStr(dest[0])};
        }else{
            return new int[]{0,0};
        }
    }

    public int parseIntByStr (String dest ){
        if (dest==null  || "".equals(dest)){
            return 0;
        }
        try{
            return Integer.parseInt(dest);
        }catch(Exception e){
            return 0 ;
        }
    }
}
