package demo.minifly.com.designpattern.abstractfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;

public class ZFactoryDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfactory_demo);

        findViewById(R.id.factory_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new CustomerClient()).needAmdComputer();
                (new CustomerClient()).needInterComputer();
                LogUtils.showErrLog("显示一个日志；");
            }
        });
    }
}
