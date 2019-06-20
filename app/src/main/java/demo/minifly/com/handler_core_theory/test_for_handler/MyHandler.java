package demo.minifly.com.handler_core_theory.test_for_handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class MyHandler<T extends Activity> extends Handler {
    WeakReference<T> t;

    public MyHandler(T t) {
        this.t =new WeakReference(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
}
