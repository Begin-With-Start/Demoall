package com.demo.hexiaofei.handframecode.handler_frame;

import android.os.Bundle;

/**
 * <p>
 * simple message for handler deal ;
 * </p>
 * <pre>
 *     演示整个handler流程简单例子；
 *     消息体构建
 * </pre>
 * @author  minfily
 */
public class Message {

    public Handler target;
    public int what; // 消息的id；
    private Bundle data;


    public Message(int what, Bundle data) {
        this.what = what;
        this.data = data;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }
}
