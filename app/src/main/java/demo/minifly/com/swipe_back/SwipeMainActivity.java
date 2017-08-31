package demo.minifly.com.swipe_back;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

/**
 * 左划退出页面
 * 主页面不能左划退出，其他衍生页面都需要左划退出了
 */
public class SwipeMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSwipeActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_main);
        initView();
    }

    private void initView() {
        mSwipeActivityBtn = (Button) findViewById(R.id.swipe_activity_btn);

        mSwipeActivityBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.swipe_activity_btn:
                Intent intent = new Intent();
                intent.setClass(this,SwipeNextActivity.class);
                startActivity(intent);
                break;
        }
    }
}
