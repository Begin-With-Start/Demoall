package demo.minifly.com.project_all_demo.weekdemo;

import android.os.Bundle;
import android.widget.TextView;

import demo.minifly.com.BaseActivity;
import demo.minifly.com.R;

public class WeekDemoActivity extends BaseActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_demo);
        textView = (TextView) findViewById(R.id.week_txt_id);

        textView.setText("" + CalendarUtils.getWeekPerYear());
    }
}
