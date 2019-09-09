package com.demo.hexiaofei.multithread.base_thread_synchronos;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.demo.hexiaofei.handframecode.R;

import java.util.concurrent.Executor;

public class HandThreadSynchronousActivity extends AppCompatActivity {

    private static final String TAG = "HandThreadSynchronous";
    public TextView mHandThreadTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_thread_synchronous);

        this.mHandThreadTxt = (TextView) findViewById(R.id.hand_thread_txt);


//        for (int i = 0; i < 20; i++) {
//            new MyAsycTask().execute(1); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(2); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(3); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(4); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(5); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(6); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(7); //doInBackground 顺序调用，顺序执行；
//            new MyAsycTask().execute(8); //doInBackground 顺序调用，顺序执行；阻塞就不会执行下面的task了；

//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,1);
//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,2);
//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,3);
//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,4);
//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,5);
//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,6);
//            new MyAsycTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,7);
//        }


            new MyThread(0).start();
    }


    /**
     * thread
     */
    static class MyThread extends  Thread{
        private int i ;
        public  MyThread(int i ) {
            this.i = i;
        }

        @Override
        public final void run() {
            super.run();
            Log.e(TAG , "mythread "  + i);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG , "Runnable     1");
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * asyctask 任务一般在执行时间短，当下立即启动情况下使用；
     * 注意：需要注意自定义的内部类asynctask 会持有外部的引用，造成泄露，应该写成静态内部类；
     * 1.默认asynctask是线性调度的，所以，在定义20个task的时候会使得所有的task在一个线性队列中等待，我们可以使用asynctask.executeOnExecutor来制定asynctask使用线程池来进行并发调度任务。AsyncTask.THREAD_POOL_EXRCUTOR 指定执行的线程池 ， 以此减少中间task对于后面队列的阻塞问题；
     * 2.cancle没有真正cancle掉，要在doinbackground中，不断的循环来进行是否cancle的判断；
     */
    static class MyAsycTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            if(objects.length == 1){
                Log.e(TAG, "MyAsycTask.doInBackground" + objects[0]);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(TAG, "MyAsycTask.onCancelled");
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            /**
             * 执行耗时操作，在这里面
             */
            Log.e(TAG, "MyAsycTask.onPostExecute");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "MyAsycTask.onPreExecute");
        }
    }

}
