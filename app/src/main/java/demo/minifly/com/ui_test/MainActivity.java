package demo.minifly.com.ui_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import demo.minifly.com.R;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTitleView title = (MyTitleView) findViewById(R.id.tv_title);
//        title.setTitle("测试设置标题");

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
