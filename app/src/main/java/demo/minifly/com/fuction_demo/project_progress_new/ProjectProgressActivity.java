package demo.minifly.com.fuction_demo.project_progress_new;

import android.app.Activity;
import android.os.Bundle;

import demo.minifly.com.R;


/**
 *
 */
public class ProjectProgressActivity extends Activity {

    private ProgressLinearlayout progressLinearlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_project_progress);
        initView();
        findView();
    }

    public void findView() {
        progressLinearlayout = (ProgressLinearlayout) findViewById(R.id.progress_lin_id);
        progressLinearlayout.setData(30,40,80,60);
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
