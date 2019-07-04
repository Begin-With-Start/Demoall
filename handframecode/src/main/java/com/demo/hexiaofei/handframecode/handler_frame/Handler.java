package com.demo.hexiaofei.handframecode.handler_frame;

import android.os.Bundle;

public abstract class Handler {

    private Looper looper;
    private MessageQueue queue;

    /**
     * 默认赋值；
     */
    public Handler(){
        this(null);
    }

    public Handler(Looper looper) {
        if(looper != null){
            this.looper = looper;
        }else{
            this.looper = Looper.myLooper();
        }
        this.queue = looper.queue;
    }

    /**
     * 处理消息；
     * @param message 具体处理在实现处
     */
    protected abstract void handleMessage(Message message);

    /**
     * 发送message 到queue中；
     * @param message 发送的消息；
     */
    public void sendMessage(Message message){
        addMessageQueue(message);
    }

    /**
     * 发送空的消息；
     */
    public void sendEmptyMessage(){
        if(queue != null ){
            addMessageQueue(new Message(-1,new Bundle()));
        }
    }

    private void addMessageQueue(Message message){

        if(queue != null){
            queue.enqueueMessage(message);
        }
    }

}
