package demo.minifly.com.fuction_demo.notification_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

public class NotificationStartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnNotificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_start);
        initView();
    }

    private void initView() {
        mBtnNotificationId = (Button) findViewById(R.id.btn_notification_id);

        mBtnNotificationId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notification_id:
                NotificationTools.getInstance(this).sendCustomNotification("我的通知",NotificationViewActivity.class);
                break;
        }
    }
}
