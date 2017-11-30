package demo.minifly.com.map;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;


import java.lang.reflect.Method;

import demo.minifly.com.R;
import demo.minifly.com.listview_test.ConvertUtils;


/**
 * 带键盘的editText
 * Created by clannad
 */

public class KeyBoardEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mKeyBoardDrawable;
    private boolean hasFoucs;
    private onMyFocusChangeListener mListener;
    private Context mContext;

    public void setOnMyFocusChangeListener(onMyFocusChangeListener listener) {
        this.mListener = listener;
    }

    public KeyBoardEditText(Context context) {
        this(context, null);
    }

    public KeyBoardEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public KeyBoardEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init() {
        //left, top, right, bottom, 第三个是drawableRight
        mKeyBoardDrawable = getCompoundDrawables()[2];
        if (mKeyBoardDrawable == null) {
            mKeyBoardDrawable = getResources().getDrawable(
                    R.drawable.common_edittext_keyboard_selector);
        }

        mKeyBoardDrawable.setBounds(0, 0, ConvertUtils.dip2px(mContext,20),
                ConvertUtils.dip2px(mContext,20));
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        setClearIconVisible(true);
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
                //x在图标的最左边和最右边之间
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    if(onKeyboardclick != null){
                        onKeyboardclick.onKeyboardClick();
                    }
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

        } else {
            if (mListener != null) {
                mListener.onFocusChange(v, false);
            }
            setEnabled(true);
        }
    }

    public void setClearIconVisible(boolean visible) {
        Drawable clear = visible ? mKeyBoardDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], clear, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
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
    private OnKeyboardClick onKeyboardclick;
    public interface  OnKeyboardClick{
        void onKeyboardClick();
    }

    public void setOnKeyboardClick(OnKeyboardClick onKeyboardclick){
        this.onKeyboardclick = onKeyboardclick;
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public void disableShowSoftInput() {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            this.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(this, false);
            } catch (Exception e) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(this, false);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 打开Edittext弹出软件盘，光标依然正常显示。
     */
    public void openShowSoftInput() {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            this.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(this, true);
            } catch (Exception e) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(this, true);
            } catch (Exception e) {
            }
        }
    }

}
