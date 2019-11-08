package demo.minifly.com.fuction_demo.android_touch_event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.minifly.com.fuction_demo.utils.LogUtils;

/**
 * create by minifly on 2019-10-11 14:14
 * description:
 */
public class MineView extends View {
    public MineView(Context context) {
        super(context);
    }

    public MineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.showErrLog("   view  MineView---- dispatchTouchEvent ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.showErrLog("   view  MineView---- onTouchEvent ");
        return super.onTouchEvent(event);
    }
}
