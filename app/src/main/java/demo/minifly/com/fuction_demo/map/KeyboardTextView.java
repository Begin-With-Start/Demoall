package demo.minifly.com.fuction_demo.map;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import demo.minifly.com.R;


/**
 * 带有清除键的EditText
 * Created by ShineF on 2017/6/26 0026.
 */

public class KeyboardTextView extends TextView {

    private Drawable mKeyboardDrawable;
    private Drawable mKeyboardDeepDrawable;
    private KeyBoardTvBackListener onKeyBoardTvBackListener;
    private boolean isShow;

        public void setOnKeyBoardTvBackListener(KeyBoardTvBackListener onKeyBoardTvBackListener) {
        this.onKeyBoardTvBackListener = onKeyBoardTvBackListener;
    }

    public KeyboardTextView(Context context) {
        this(context, null);
    }

    public KeyboardTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public KeyboardTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mKeyboardDrawable = getCompoundDrawables()[2];//left, top, right, bottom, 第三个是drawableRight
        if (mKeyboardDrawable == null) {
            mKeyboardDrawable = getResources().getDrawable(
                    R.drawable.keyboard);
        }

        mKeyboardDrawable.setBounds(0, 0, mKeyboardDrawable.getIntrinsicWidth(),
                mKeyboardDrawable.getIntrinsicHeight());
        setKeyboardDrawableVisible(false);

        mKeyboardDeepDrawable = getCompoundDrawables()[2];//left, top, right, bottom, 第三个是drawableRight
        if (mKeyboardDeepDrawable == null) {
            mKeyboardDeepDrawable = getResources().getDrawable(
                    R.drawable.keyboard_deep);
        }

        mKeyboardDeepDrawable.setBounds(0, 0, mKeyboardDeepDrawable.getIntrinsicWidth(),
                mKeyboardDeepDrawable.getIntrinsicHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                //	判断按下的点是否按到了那个键盘按钮上面
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());//x在图标的最左边和最右边之间
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    isShow = !isShow;
                    if (onKeyBoardTvBackListener != null)
                        onKeyBoardTvBackListener.onBack(isShow);
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    if (isShow){
                        setKeyboardDrawableVisible(true);
                    }else{
                        setKeyboardDrawableVisible(false);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }


    public void setKeyboardDrawableVisible(boolean visible) {
        Drawable clear = visible ? mKeyboardDeepDrawable : mKeyboardDrawable;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], clear, getCompoundDrawables()[3]);
    }


    public interface KeyBoardTvBackListener {
        void onBack(boolean isShow);
    }

    /**
     * Creator by zhenwang.xiang on 2017/8/16 15:54
     * Description: 新增Edittext限制回调接口，如有限制只需要回调setOnRegExpListener方法，
     * 设置真确的正则表达式
     */

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new MyInputConnecttion(super.onCreateInputConnection(outAttrs), false);
    }

    class MyInputConnecttion extends InputConnectionWrapper implements InputConnection {

        public MyInputConnecttion(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            if (mOnRegExpListener != null) {
                if (!mOnRegExpListener.onRegExp(text)) {
                    return false;
                }
            }
            return super.commitText(text, newCursorPosition);
        }
    }

    public interface OnRegExpListener {
        boolean onRegExp(CharSequence text);
    }

    private OnRegExpListener mOnRegExpListener;

    public void setOnRegExpListener(OnRegExpListener regexplistener) {
        mOnRegExpListener = regexplistener;
    }

}
