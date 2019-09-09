package demo.minifly.com.fuction_demo.request_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

import java.util.HashMap;

import demo.minifly.com.BaseActivity;
import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;

/**
 * author ：minifly
 * date: 2017/3/10
 * time: 19:04
 * desc:
 */
public class RequestTestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_request_demo);
        request();
    }


    /**
     * 请求试试
     */
    public void request(){
        String url = null;
        HashMap<String, Object> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "1000");
        map.put("sort", "createTime&asc");
//        Request<String> request = NoHttpUtils.getRequest(url, map);
//        requestQueue.add(0, request, onResponseListener);
    }

    /**
     * 请求监听
     */
    OnResponseListener<String> onResponseListener = new OnResponseListener<String>() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response<String> response) {
            LogUtils.showLog(response.get());
            try {
                if (what == 0) {

                }
                else if (what == 1) {

                }
            } catch (Exception e) {

            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {
        }

        @Override
        public void onFinish(int what) {
            try {
            } catch (Exception e) {

            }
        }
    };
}
