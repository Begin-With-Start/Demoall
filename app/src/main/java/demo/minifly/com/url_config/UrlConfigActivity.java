package demo.minifly.com.url_config;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

public class UrlConfigActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_config);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn1:
                Constant.BaseUrl = Constant.BaseUrl1;
                reject_utls.reject(new Constant());
                intent.setClass(this,Url2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn2:

                Constant.BaseUrl = Constant.BaseUrl2;
                reject_utls.reject(new Constant());
                intent.setClass(this,Url2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn3:

                Constant.BaseUrl = Constant.BaseUrl3;
                reject_utls.reject(new Constant());
                intent.setClass(this,Url2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
