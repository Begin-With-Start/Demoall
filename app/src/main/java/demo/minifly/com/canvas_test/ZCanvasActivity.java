package demo.minifly.com.canvas_test;

import android.app.Activity;
import android.os.Bundle;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 * 可以左右marqueen的图片
 */
public class ZCanvasActivity extends Activity {

    private MarqueeImageView marqueeId;
    private MyCircleProgressView myprogressView;
//    private ProgressBar myProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_canvas);
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
//        marqueeId = (MarqueeImageView) findViewById(R.id.marquee_id);
//        marqueeId.Start(getWindow());
        myprogressView = (MyCircleProgressView)findViewById(R.id.mycircle_progressview);
        myprogressView.setData(100,90,70,0);
//        myProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
//        myProgressBar.setMax(100);
//        myProgressBar.setProgress(50);
    }
}
