package demo.minifly.com.fuction_demo.scrollview_test;

import android.app.Activity;
import android.os.Bundle;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 */
public class ScrollActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_scroll);
        findView();
    }

    public void findView(){

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
