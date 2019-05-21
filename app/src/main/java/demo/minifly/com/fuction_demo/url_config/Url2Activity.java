package demo.minifly.com.fuction_demo.url_config;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import demo.minifly.com.R;

public class Url2Activity extends AppCompatActivity {

    private TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url2);
        initView();

    }

    private void initView() {
        txtId = (TextView) findViewById(R.id.txt_id);
//        txtId.setText(Constant.getSOCKETIO_URL1());
    }
}
