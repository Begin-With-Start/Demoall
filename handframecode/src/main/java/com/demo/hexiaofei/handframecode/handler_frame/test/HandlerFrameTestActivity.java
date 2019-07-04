package com.demo.hexiaofei.handframecode.handler_frame.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.hexiaofei.handframecode.R;
import com.demo.hexiaofei.handframecode.handler_frame.Handler;
import com.demo.hexiaofei.handframecode.handler_frame.Looper;
import com.demo.hexiaofei.handframecode.handler_frame.Message;

public class HandlerFrameTestActivity extends AppCompatActivity {

    private String TAG = "HandlerFrameTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_frame_test);
        Log.e(TAG, "" + 100000000 + " 哈哈哈哈哈哈 我进来了；" );

        Looper.prepare();
        Handler myHandler = new Handler() {
            @Override
            protected void handleMessage(Message message) {
                switch (message.what) {
                    case 10001:
                        Log.e(TAG, "" + message.what + " " + message.getData().getString("data"));
                        break;
                    case 10002:
                        Log.e(TAG, "" + message.what + " " + message.getData().getString("data"));
                        break;
                    case 10003:
                        Log.e(TAG, "" + message.what + " " + message.getData().getString("data"));
                        break;
                }
            }
        };

        Looper.loop();

        Bundle bundle = new Bundle();
        bundle.putString("data", "我是数据10001");
        myHandler.sendMessage(new Message(10001, bundle));

        bundle = new Bundle();
        bundle.putString("data", "我是数据10002");
        myHandler.sendMessage(new Message(10002, bundle));

        bundle = new Bundle();
        bundle.putString("data", "我是数据10003");
        myHandler.sendMessage(new Message(10003, bundle));

    }


}
