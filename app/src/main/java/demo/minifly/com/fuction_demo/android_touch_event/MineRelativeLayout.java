package demo.minifly.com.fuction_demo.android_touch_event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import demo.minifly.com.fuction_demo.utils.LogUtils;

/**
 * create by minifly on 2019-10-10 15:34
 * description:
 */
public class MineRelativeLayout extends RelativeLayout {

    boolean isIntercept = false;
    public void setIntercept(boolean isIntercept){
        this.isIntercept = isIntercept;
    }

    public MineRelativeLayout(Context context) {
        super(context);
    }

    public MineRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MineRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.showErrLog("MineRelativeLayout   relativelayout ---- dispatchTouchEvent ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isIntercept){
            LogUtils.showErrLog("MineRelativeLayout   relativelayout ---- onInterceptTouchEvent 拦截 ");
            return isIntercept;
        }
        LogUtils.showErrLog("MineRelativeLayout   relativelayout ---- onInterceptTouchEvent 未拦截");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.showErrLog("MineRelativeLayout   relativelayout---- MotionEvent.ACTION_DOWN ");
                return true;
            case MotionEvent.ACTION_UP:
                LogUtils.showErrLog("MineRelativeLayout   relativelayout---- MotionEvent.ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.showErrLog("MineRelativeLayout   relativelayout---- MotionEvent.ACTION_MOVE ");
                break;
        }
        if(isIntercept){
            return true;
        }
        return super.onTouchEvent(event);
    }
}
