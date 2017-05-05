package demo.minifly.com.gallary_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2017/02/21 11:43
 * 画廊的做法，来生成一个工程的组件
 */
public class GalleryTestActivity extends Activity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getLayoutInflater().inflate(R.layout.activity_gallery_demo, null);
        setContentView(view);
        initView();
        findView();
    }

    public void findView() {

    }

    private void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            this.hideNavigationBar();
        }
        return false;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if( hasFocus ) {
            hideNavigationBar();
        }
    }

    /**
     * 隐藏掉navigationbar
     */
//    public void hideNavigationBar(){
//
////        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        int i = view.getSystemUiVisibility();
//        if (i == View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) {//2
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        } else if (i == View.SYSTEM_UI_FLAG_VISIBLE) {//0
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
//        } else if (i == View.SYSTEM_UI_FLAG_LOW_PROFILE) {//1
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        }
//    }
    public void hideNavigationBar() {
        int uiFlags = //View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                //View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // hide nav bar

        if( android.os.Build.VERSION.SDK_INT >= 19 ){
            uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }

        getWindow().getDecorView().setSystemUiVisibility(uiFlags);
    }
}
