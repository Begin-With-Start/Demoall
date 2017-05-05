package demo.minifly.com.request_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

import demo.minifly.com.R;


public class BaseActivity extends Activity {

    public RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        requestQueue = NoHttp.newRequestQueue();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();
    }

    // 弹出Toast
    public void toast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 弹出Toast
    public void toast(final int resource) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, resource, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
