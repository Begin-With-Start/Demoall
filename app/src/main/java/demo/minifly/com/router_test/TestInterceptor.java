package demo.minifly.com.router_test;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import demo.minifly.com.utils.LogUtils;
import demo.minifly.com.utils.ToastUtils;

/**
 * author ：minifly
 * date: 2017/9/5
 * time: 19:01
 * desc:比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
 * 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
 */
@Interceptor(priority = 8)
public class TestInterceptor implements IInterceptor {

    Context mContext;

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if ("/swipnext/next".equals(postcard.getPath())) {

            MainLooper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.showErrLog("进入了哦");
                    ToastUtils.showToast("哈哈哈哈哈我还是拦截了一下的。");
//                    callback.onInterrupt(null);//直接截断了
                    callback.onContinue(postcard);//继续吧，放你一马

                }
            });
        } else {
            callback.onContinue(postcard);
        }
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        mContext = context;
        MainLooper.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogUtils.showErrLog("第一次进入了哦");
            }
        });
    }
}
