package demo.minifly.com.fuction_demo.intent_test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.bean.BeanIn;

public class Intent2Activity extends AppCompatActivity {

    private TextView intentActivity2Txt;
    private BeanIn bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        if(bundle.containsKey("bean")){
            bean = (BeanIn) bundle.get("bean");

        }
        Context context = this;
        intentActivity2Txt = (TextView) findViewById(R.id.intent_activity2_txt);

        intentActivity2Txt.setText(bean.toString());
    }
}
