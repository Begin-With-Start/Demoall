package demo.minifly.com.fuction_demo.map;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import demo.minifly.com.R;


/**
 * 带有清除键的EditText
 * Created by ShineF on 2017/6/26 0026.
 */

public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;
    private Drawable mStick;
    private boolean hasFoucs;
    private onMyFocusChangeListener mListener;

    public void setOnMyFocusChangeListener(onMyFocusChangeListener listener) {
        this.mListener = listener;
    }

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mClearDrawable = getCompoundDrawables()[2];//left, top, right, bottom, 第三个是drawableRight
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(
                    R.drawable.common_search_delete_selector);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
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
                //	判断按下的点是否按到了那个清空按钮上面
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());//x在图标的最左边和最右边之间
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，
     * 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            if (mListener != null) {
                mListener.onFocusChange(v, true);
            }
            setClearIconVisible(getText().length() > 0);
        } else {
            if (mListener != null) {
                mListener.onFocusChange(v, false);
            }
            setClearIconVisible(false);
        }
    }

    public void setClearIconVisible(boolean visible) {
        Drawable clear = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], clear, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface onMyFocusChangeListener {
        void onFocusChange(View view, boolean hasFocus);
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
