package demo.minifly.com.fuction_demo.canvas_test;

import android.app.Activity;
import android.os.Bundle;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 * 可以左右marqueen的图片
 */
public class ZCanvasSurfaceSinActivity extends Activity {

//    private ProgressBar myProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_surfaceview_sin);
        initView();
        findView();
    }

    public void findView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
    }
}
