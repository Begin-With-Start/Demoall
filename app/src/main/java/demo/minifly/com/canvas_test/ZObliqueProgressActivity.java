package demo.minifly.com.canvas_test;

import android.app.Activity;
import android.os.Bundle;

import demo.minifly.com.R;


/**
 * author ：minifly
 * date: 2017/2/28
 * time: 16:11
 * desc:斜线进度条
 */
public class ZObliqueProgressActivity extends Activity {
    private ObliqueProgressbar myProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoblique_progress);
        myProgressbar = (ObliqueProgressbar) findViewById(R.id.canvas_oblique_id);
        myProgressbar.setProgress(80);
    }
}
