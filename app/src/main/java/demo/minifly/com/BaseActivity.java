package demo.minifly.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;


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

    /**
     * 跳转activity
     */
    public void startActivity(String path){
        if(path == null || "".equals(path)){
            throw new ArouterPathException("路径为空！");
        }
        ARouter.getInstance().build(path).navigation();
    }

    public void startActivity(String path,String key,String value){
        if(path == null || "".equals(path)){
            throw new ArouterPathException("路径为空！");
        }
        ARouter.getInstance().build(path)
                .withString(key, value)
                .navigation();
    }

    public void startActivity(String path,Bundle bundle){
        if(path == null || "".equals(path)){
            throw new ArouterPathException("路径为空！");
        }
        ARouter.getInstance().build(path)
//                .setExtra(bundle)
                .navigation();
    }
}
