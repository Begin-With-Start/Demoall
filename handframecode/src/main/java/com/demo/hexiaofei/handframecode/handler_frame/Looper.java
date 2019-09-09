package com.demo.hexiaofei.handframecode.handler_frame;

/**
 * <pre>
 *     构建looper的功能；提供一个循环从queue中拿message的功能；
 * </pre>
 * @author  minifly
 */
public class Looper {
    /**
     * treadlocal : 一个tread对应一个looper；
     */
    private static ThreadLocal<Looper> mThreadLoacel = new ThreadLocal<>();
    public static MessageQueue queue;

    public static void prepare(){
        if(mThreadLoacel.get() != null){
            throw  new RuntimeException("one thread can have one looper");
        }
        mThreadLoacel.set(new Looper());
    }

    private Looper() {
        this.queue = new MessageQueue();
    }

    public static Looper myLooper(){
        return mThreadLoacel.get();
    }

    /**
     * 没有调用的时候，相当于没有进行发动机发动一样；没有启动发动机一样
     */
    public static void loop(){
        Looper looper = myLooper();
        if(looper == null){
            throw new RuntimeException("you should call prepare() at first !   ");
        }
        /**
         * 不断在queue里拿message出来；不断的进行循环；
         */
        for(;;){
            Message message = queue.next();
            if(message != null ){
                message.target.handleMessage(message);
            }
        }
    }
}
