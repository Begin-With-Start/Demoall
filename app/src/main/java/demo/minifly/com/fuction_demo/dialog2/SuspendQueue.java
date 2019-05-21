package demo.minifly.com.fuction_demo.dialog2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.PopupWindow;


import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ${minifly} on 2018/5/16.
 * 悬浮窗管理类
 * desc: 弹框的多个弹出的控制，遍历其中的数据
 * 1.适配dialog ， popuwindow
 */

public class SuspendQueue {
    boolean isShowing;
    private ConcurrentLinkedQueue<SuspendInterface> dialogQueue;
    //是否打断循环
    private boolean isInteruptShow;
    //是否暂停队列的显示 用于 当前的队列优先级不高的时候 队列数据依旧在
    private boolean isStopQueue = false;

    private static class SingletonClassInstance {
        private static final SuspendQueue instance = new SuspendQueue();
    }

    public static SuspendQueue getInstance() {
        return SingletonClassInstance.instance;
    }

    private SuspendQueue() {
        dialogQueue = new ConcurrentLinkedQueue();
        isInteruptShow = false;
        isShowing = false;
    }

    /**
     * 添加元素到队列尾
     */
    public void add(SuspendInterface object) {
        dialogQueue.add(object);
    }

    /**
     * 取出队列头的元素
     */
    private SuspendInterface peek() {
        return dialogQueue.poll();//取队列头，同时删除该元素
    }

    private SuspendInterface peekNoDelete(){
        return dialogQueue.peek();
    }
    /**
     * 清空队列
     */
    public void clear(){
        dialogQueue.clear();
    }

    /**
     * 显示悬浮框
     */
    public void showNext(){
        if(isShowing){
            return;//正在显示，需要处理掉，dimiss之后再跳转到下一个之中
        }

        if(isStopQueue){
            return;//是否暂停队列的显示 用于
        }

        final SuspendInterface object = peek();
        if(object == null){
            return;
        }

        //支持两种方式的弹框加载
        if(object instanceof Dialog){
            Dialog dialog = (Dialog) object;
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    isShowing = false;
                    if(!isInteruptShow){
                        showNext();
                    }
                }
            });
            isShowing = true;
            object.showSuspend();

        }else if(object instanceof PopupWindow){
            PopupWindow popupWindow = (PopupWindow) object;
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    isShowing = false;
                    if(!isInteruptShow) {
                        showNext();
                    }
                }
            });
            isShowing = true;
            object.showSuspend();
        }
    }

    /**
     * 设置是否打断循环
     * @param isInteruptShow
     */
    public void interuptShow(boolean isInteruptShow){
        this.isInteruptShow = isInteruptShow;
    }
    /**
     * 设置禁止循环开始，用于该队列优先级低的情况下
     * @param stopQueue
     */
    public void setStopQueue(boolean stopQueue){
        this.isStopQueue = stopQueue;
    }
}
