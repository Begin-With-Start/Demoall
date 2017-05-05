package demo.minifly.com.object_animator_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import demo.minifly.com.R;


/**
 * view 本身自带的一个animator的属性，直接进行了动画。
 * 链式的调用
 * 从android 3.0开始有这个属性。
 */
public class ViewWithAnimatorActivity extends Activity {

    private ImageView imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_animator_myown);
        initView();
    }

    private void initView() {
        imageId = (ImageView) findViewById(R.id.image_id);

        imageId.animate().alpha(0).setDuration(1000).withStartAction(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ViewWithAnimatorActivity.this,"begin",Toast.LENGTH_SHORT).show();
            }
        }).withEndAction(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ViewWithAnimatorActivity.this,"end",Toast.LENGTH_LONG).show();
            }
        }).start();
    }



}
