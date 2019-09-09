package demo.minifly.com.fuction_demo.ear_listener_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

public class LightDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLightCurrentId;
    private Button mLightWholeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_demo);
        initView();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.light_current_id:
                LightUtils.setLight(this,0);
                break;
            case R.id.light_whole_id:
                LightUtils.saveBrightness(this,0);
                break;
        }
    }

    private void initView() {
        mLightCurrentId = (Button) findViewById(R.id.light_current_id);
        mLightWholeId = (Button) findViewById(R.id.light_whole_id);

        mLightCurrentId.setOnClickListener(this);
        mLightWholeId.setOnClickListener(this);
    }



}
