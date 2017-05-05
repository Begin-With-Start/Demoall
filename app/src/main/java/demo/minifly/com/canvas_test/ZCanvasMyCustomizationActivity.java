package demo.minifly.com.canvas_test;

import android.app.Activity;
import android.os.Bundle;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 * 可以左右marqueen的图片
 */
public class ZCanvasMyCustomizationActivity extends Activity {

    private MyCutomizationProgress myProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_mycustomizaion);
        initView();
        findView();
    }

    public void findView() {
        myProgressBar = (MyCutomizationProgress)findViewById(R.id.mycircle_progressview);
        myProgressBar.setProgress(100,100);
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
