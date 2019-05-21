package demo.minifly.com.fuction_demo.map;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.minifly.com.R;


/**
 * @author : mundane
 * @time : 2017/6/20 9:15
 * @description :
 * @file : TextInputLayout.java
 */

public class MyTextInputLayout extends LinearLayout {
    //	默认hint的topMargin为10dp
    private float DEF_LABEL_TOP_MARGIN = 10;
    //	默认label的字体颜色
    private final int DEF_LABEL_COLOR = getResources().getColor(R.color.common_color_c1_337cff);
    //  默认的错误提示的颜色
    private final int DEF_WRONG_COLOR = getResources().getColor(R.color.common_color_c11_ff1f1f);
    //	默认hint的字体大小为12sp
    private final int DEF_LABEL_TEXT_SIZE = 12;
    private EditText mEditText;
    private boolean inDrawableStateChanged;
    private TextView mLabelView;
    //	是否处于错误状态
    private boolean isStateWrong;
    // 错误时的提示信息
    private CharSequence mWrongMsg;
    // 是否隐藏下划线
    private boolean mUnderLineHide;
    // 真正显示在label上的文字
    private CharSequence mLabelText;
    // editText的hint文字
    private CharSequence mEditTextHintText;
    // 自定义的label文字, 设置了之后mLabelText将从mEditTextHintText指向mCustomLabelText;
    private CharSequence mCustomLabelText;
    private boolean editTextEnabled = true;

    public void setUnderLineHide(boolean isHide) {
        mUnderLineHide = isHide;
    }

    public MyTextInputLayout(Context context) {
        this(context, null);
    }

    public MyTextInputLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initData(context);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {
            mEditText = (EditText) child;
            if (!mUnderLineHide) {
                mEditText.setBackgroundResource(R.drawable.selector_edittext);
            }
            mEditTextHintText = mEditText.getHint();
            if (TextUtils.isEmpty(mCustomLabelText)) {
                mLabelText = mEditTextHintText;
            } else {
                mLabelText = mCustomLabelText;
            }
            mLabelView.setText(mLabelText);
        }
        super.addView(child, index, params);
    }

    public void updateHint() {
        if (mEditText == null) {
            return;
        }
        mEditTextHintText = mEditText.getHint();
        if (TextUtils.isEmpty(mCustomLabelText)) {
            mLabelText = mEditTextHintText;
        } else {
            mLabelText = mCustomLabelText;
        }
        mLabelView.setText(mLabelText);
        updateLabelState();
    }

    @Override
    protected void drawableStateChanged() {
        if (inDrawableStateChanged) {
            return;
        }
        inDrawableStateChanged = true;
        super.drawableStateChanged();
        if (mEditText != null) {
            if (editTextEnabled) {
                updateLabelState();
            }
        }
        inDrawableStateChanged = false;
    }

    public void setStateWrong(CharSequence msg) {
        isStateWrong = true;
        mWrongMsg = msg;
        mLabelView.setTextColor(DEF_WRONG_COLOR);
        mLabelView.setText(mWrongMsg);
        if (!mUnderLineHide) {
            mEditText.setBackgroundResource(R.drawable.layer_edittext_wrong);
        }
    }

    public String getStateChar() {
        if (mLabelView != null) {
            return mLabelView.getText().toString().trim();
        }
        return "";
    }

    public void setStateNormal() {
        isStateWrong = false;
        mLabelView.setTextColor(DEF_LABEL_COLOR);

        mLabelView.setText(mLabelText);
        if (!mUnderLineHide) {
            mEditText.setBackgroundResource(R.drawable.selector_edittext);
        }
    }

    public void setStateNormal(CharSequence text) {
        isStateWrong = false;
        mLabelView.setTextColor(DEF_LABEL_COLOR);

        mLabelText = text;
        mLabelView.setText(mLabelText);
        if (!mUnderLineHide) {
            mEditText.setBackgroundResource(R.drawable.selector_edittext);
        }
    }

    public void setEnabled(boolean enabled) {
        editTextEnabled = enabled;
        if (enabled) {
            return;
        }
//		if (TextUtils.isEmpty(mEditText.getText())) {
//			mEditText.clearFocus();
////			mEditText.setFocusable(false);
//			mEditText.setEnabled(false);
//			mLabelView.setText(null);
//			if (!mUnderLineHide) {
//				mEditText.setBackgroundResource(R.drawable.selector_edittext);
//			}
//		}
        mEditText.setEnabled(false);
        mLabelView.setVisibility(GONE);
        mLabelView.setText(null);
        if (!mUnderLineHide) {
            mEditText.setBackgroundResource(R.drawable.selector_edittext);
        }
    }

    public void updateLabelState() {
        final boolean isFocused = arrayContains(getDrawableState(), android.R.attr.state_focused);

        // 有焦点的状态
        if (isFocused) {
            mEditText.setHint(null);
            if (isStateWrong) {
                mLabelView.setText(mWrongMsg);
                mLabelView.setTextColor(DEF_WRONG_COLOR);
                if (!mUnderLineHide) {
                    mEditText.setBackgroundResource(R.drawable.layer_edittext_wrong);
                }
            } else {
                mLabelView.setText(mLabelText);
                mLabelView.setTextColor(DEF_LABEL_COLOR);
                if (!mUnderLineHide) {
                    mEditText.setBackgroundResource(R.drawable.selector_edittext);
                }
            }
        } else { // 没有焦点的状态
            mEditText.setHint(mEditTextHintText);
            if (TextUtils.isEmpty(mEditText.getText())) {// EditText中的内容为空
                mLabelView.setText(null);
                if (!mUnderLineHide) {
                    mEditText.setBackgroundResource(R.drawable.selector_edittext);
                }
            } else {// EditText中有内容
                if (isStateWrong) {
                    mLabelView.setText(mWrongMsg);
                    mLabelView.setTextColor(DEF_WRONG_COLOR);
                    if (!mUnderLineHide) {
                        mEditText.setBackgroundResource(R.drawable.layer_edittext_wrong);
                    }
                } else {
                    mLabelView.setText(mLabelText);
                    mLabelView.setTextColor(DEF_LABEL_COLOR);
                    if (!mUnderLineHide) {
                        mEditText.setBackgroundResource(R.drawable.selector_edittext);
                    }
                }
            }

        }
    }

    public void showLableView() {
        if (isStateWrong) {
            mLabelView.setText(mWrongMsg);
            mLabelView.setTextColor(DEF_WRONG_COLOR);
        } else {
            mLabelView.setText(mLabelText);
            mLabelView.setTextColor(DEF_LABEL_COLOR);
        }
    }

    private static boolean arrayContains(int[] array, int value) {
        for (int v : array) {
            if (v == value) {
                return true;
            }
        }
        return false;
    }

    private void initData(Context context) {
        setAddStatesFromChildren(true);
        setOrientation(VERTICAL);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = dp2px(DEF_LABEL_TOP_MARGIN);
        mLabelView = new TextView(context);
        mLabelView.setTextColor(DEF_LABEL_COLOR);
        mLabelView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DEF_LABEL_TEXT_SIZE);
        addView(mLabelView, params);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout);
        mUnderLineHide = a.getBoolean(R.styleable.TextInputLayout_hideUnderLine, false);
        mCustomLabelText = a.getString(R.styleable.TextInputLayout_labelText);
        DEF_LABEL_TOP_MARGIN = a.getDimension(R.styleable.TextInputLayout_hintMarginTop,10);
        a.recycle();
    }

    private int px2dp(float paValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (paValue / scale + 0.5f);
    }

    private int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int px2sp(float pxValue) {
        final float frontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / frontScale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float frontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * frontScale + 0.5f);
    }
}
