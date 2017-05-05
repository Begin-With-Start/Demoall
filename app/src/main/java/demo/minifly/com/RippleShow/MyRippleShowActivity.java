package demo.minifly.com.RippleShow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.minifly.com.R;


/**
 *  可以将波纹的效果放到任何你想放的view上面
 *  android:background="?android:selectableItemBackground"
 *  android:background="?android:selectableItemBackground-borderless"
 *  即可
 *  在其中的效果，一个是有边框的，一个是无边框的效果
 *
 *  emui的3.1版本版本有问题
 *  华为定制系统
 *
 */
public class MyRippleShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ripple_show);
    }
}
