package demo.minifly.com.handler_core_theory.test_for_handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.leoao.sdk.common.utils.LogUtils;
import com.leoao.sdk.common.utils.ToastUtil;

import junit.framework.Test;

import demo.minifly.com.R;

public class TestForHandlerActivity extends AppCompatActivity {
    private Activity mActivity;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_handler);
        this.mActivity = this;

//        myHandler.sendEmptyMessage(1000);
        textView = findViewById(R.id.main_text);


        //看看一个经典的方法，看看是不是子线程延时之后会出问题；
        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("aaaaaaaaa");
            }
        }).start();


        /**
         * android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
         *         at android.view.ViewRootImpl.checkThread(ViewRootImpl.java:7491)
         *         at android.view.ViewRootImpl.requestLayout(ViewRootImpl.java:1195)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.support.constraint.ConstraintLayout.requestLayout(ConstraintLayout.java:3172)
         *         at android.view.View.requestLayout(View.java:21948)
         *         at android.widget.TextView.checkForRelayout(TextView.java:8533)
         *         at android.widget.TextView.setText(TextView.java:5399)
         *         at android.widget.TextView.setText(TextView.java:5255)
         *         at android.widget.TextView.setText(TextView.java:5212)
         *         at demo.minifly.com.handler_core_theory.test_for_handler.TestForHandlerActivity$2.run(TestForHandlerActivity.java:51)
         *         at java.lang.Thread.run(Thread.java:764)
         *
         *
         *
         *
         *
         *
         *
         *         解决方案：  https://blog.csdn.net/goodlixueyong/article/details/50831958
         *         ViewRootImpl是在onResume执行之后创建的。这也就解释了为什么直接在Activity的onCreate中在子线程更新UI不会报错，而在延时一段时间后却出现崩溃。
         *
         *         在oncreate方法中适用一个线程来更新ui是不会造成崩溃的，但是延时之后再更新ui就会发生报错，因为在oncreate方法的时候，依赖于thread检测的viewrootimpl还没有创建这个时候，不会走到impl的一个checkthread方法，所以就不会报错，而在
         *         onresume方法之后viewrootimpl创建之后，会走到checkthread方法中，就会造成报错，Only the original thread that created a view hierarchy can touch its views;
         *         来源于： void checkThread() {
         *         if (mThread != Thread.currentThread()) {
         *             throw new CalledFromWrongThreadException(
         *                     "Only the original thread that created a view hierarchy can touch its views.");
         *         }
         *     }
         *         所以保证没有问题的做法就是在子线程更新ui的时候用handler进行更新；
         *
         *         使用view.postdelay()与new handler().postdelayed() 都会再内部给一个equeue的队列来装这些消息，所以不会造成线程检查问题；
         *         Handler.post(Runnable)
         *         Handler.sendMessage()
         *         View.post(Runnable)
         *         AsyncTask
         *         Activity.runOnUiThread()
         *
         *
         */
        new Thread(() -> {
            LogUtils.e("testforhandleractivity " ,"    name     " +  Thread.currentThread().getName() );
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myHandler.sendEmptyMessage(1000);

        }).start();


    }


    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            LogUtils.e("testforhandleractivity " , "    name     " +  Thread.currentThread().getName() );
            super.handleMessage(msg);
            if(msg.what == 1000){
                textView.setText("cccccccc 5s 之后执行的代码");
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
//        MyThread myThread = new MyThread(textView);
//        myThread.start();
//        new Handler().postDelayed(myThread, 0);

    }

//    MyHandler<TestForHandlerActivity> myHandler = new MyHandler<TestForHandlerActivity>(this) {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1000:
//                    ((TestForHandlerActivity) t.get()).showSome();
//                    break;
//            }
//        }
//    };

    public void showSome() {
        ToastUtil.showLong("显示些东西");
    }

//    static class MyThread extends Thread {
//        private TextView textView;
//        private Handler handler;
//
//        public MyThread(TextView textView) {
//            this.textView = textView;
//        }
//
//        @Override
//        public void run() {
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Message message = Message.obtain();
//            message.what = 100000 ;
//            if(Looper.myLooper() == null){
//                Looper.prepare();
//            }
//            if(handler == null){
//                init();
//            }
//            handler.sendMessage(message);
//            Looper.loop();
//        }
//
//        private void init() {
//            handler = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    if(msg.what == 100000){
//                        textView.setText("哈哈哈哈哈哈");
//                        LogUtils.e("TestForHandlerActivity" , "TestForHandlerActivity   sdfsfsf ");
//                    }
//                }
//            };
//        }
//    }

    /**
     *      Looper.myLooper()
     *      * Return the Looper object associated with the current thread.  Returns
     *      * null if the calling thread is not associated with a Looper.
     *        public static @Nullable Looper myLooper() {
     *          return sThreadLocal.get();
     *        }
      **/
}
