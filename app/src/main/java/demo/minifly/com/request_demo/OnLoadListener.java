package demo.minifly.com.request_demo;

import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Response;

/**
 * NoHttp回调封装
 * Created by Administrator on 2016/10/18.
 */

public abstract class OnLoadListener<T> implements OnResponseListener<T> {



    public abstract void onSuccess(int what, Response<T> response);

    public abstract  void onError(int what, Response<T> response);


    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        onSuccess(what, response);
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        onError(what, response);
    }

    @Override
    public void onFinish(int what) {

    }
}
