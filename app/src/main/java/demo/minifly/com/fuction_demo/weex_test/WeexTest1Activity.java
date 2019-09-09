package demo.minifly.com.fuction_demo.weex_test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.ProgressDialogUtils;
import demo.minifly.com.fuction_demo.utils.ToastUtils;

public class WeexTest1Activity extends AppCompatActivity implements IWXRenderListener {

    private WXSDKInstance mWXSDKInstance;
    private ProgressDialogUtils progressDialogUtils;
    private Runnable myRunnable;
    private Context mContext;
    private boolean isShowSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weex_test1);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        mContext = this;
        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         * width 为-1 默认全屏，可以自己定制。
         * height =-1 默认全屏，可以自己定制。
         */
//        mWXSDKInstance.render("WXSample", WXFileUtils.loadFileContent("click_buble.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);

        String url = "http://7xjiyb.com1.z0.glb.clouddn.com/index.js";
//        String url = "http://7xjiyb.com1.z0.glb.clouddn.com/test.js";

        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, url);
        mWXSDKInstance.renderByUrl("WXSample", url, options, null, -1, -1, WXRenderStrategy.APPEND_ONCE);
        myRunnable = new Runnable() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(1000);
            }
        };
        myHandler.postDelayed(myRunnable,100);
    }

    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(!isShowSuccess){
                progressDialogUtils = ProgressDialogUtils.show(mContext);
            }
        }
    };

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        isShowSuccess = true;
        if(progressDialogUtils!=null && progressDialogUtils.isShowing()){
            progressDialogUtils.dismiss();
        }
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
        isShowSuccess = true;
        if(progressDialogUtils!=null && progressDialogUtils.isShowing()){
            progressDialogUtils.dismiss();
        }
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        ToastUtils.showToast("发生异常." + errCode + " " + msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityResume();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityPause();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityStop();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWXSDKInstance!=null){
            mWXSDKInstance.onActivityDestroy();
        }
    }
}

