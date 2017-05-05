package demo.minifly.com.project_testhouse;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.minifly.com.R;
import demo.minifly.com.utils.Common;

/**
 * Created by jack.dong on 2017/2/21.
 */

public class YiTuotuoEdittext extends RelativeLayout {

    public YiTuotuoEdittext(Context context) {
        super(context);
        init(context);
    }

    public YiTuotuoEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public YiTuotuoEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private OnSearchChangeListener mSearchChangeListener;

    public interface OnSearchChangeListener {
        void SearchChange(String s);
        void RemoveView(int position);
        void SearchListen();
    }

    public void setOnSearchChangeListener(OnSearchChangeListener searchChangeListener) {
        mSearchChangeListener = searchChangeListener;
    }

    Context mContext = null;
    LayoutInflater mInflater = null;
    View v = null;
    LinearLayout mContainer = null;
    EditText editText = null;
    private void init(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        v = mInflater.inflate(R.layout.yi_tuotuo_edittext_layout, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = 15;
        params.rightMargin = 15;
        addView(v, params);
        mContainer = (LinearLayout) v.findViewById(R.id.layout);
        editText = (EditText) v.findViewById(R.id.edittext);
        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (Common.getInstance().isNotFastClick()) {
                        if (editText.getText().toString().length() > 0) {
                            String str = editText.getText().toString();
                            str = str.substring(0, str.length() - 1);
                            editText.setText(str);
                            editText.setSelection(str.length());
                        } else {
                            if (mContainer.getChildCount() > 0) {
                                if (mSearchChangeListener != null) {
                                    mSearchChangeListener.RemoveView(mContainer.getChildCount() - 1);
                                }
                                mContainer.removeViewAt(mContainer.getChildCount() - 1);
                            }
                        }
                    }
                }

                return true;
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //录入了数据，输入框为空，执行搜索功能
                    if (editText.getText().toString().trim().equals("")) {
                        if (mContainer.getChildCount() > 0){
                            if (mSearchChangeListener != null) {
                                mSearchChangeListener.SearchListen();
                            }
                        }
                        return true;
                    }
                    TextView text = new TextView(mContext);
                    text.setText(editText.getText().toString().trim());
                    text.setTextSize(14);
                    text.setTextColor(Color.parseColor("#dfe0e0"));
                    text.setPadding(10, 0, 10, 0);
                        text.setBackgroundResource(R.drawable.shape_edittext_round_bg);
                    text.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.leftMargin = 10;
                    text.setLayoutParams(params);
                    if (mSearchChangeListener != null) {
                        mSearchChangeListener.SearchChange(editText.getText().toString().trim());
                    }
                    editText.setText("");
                    mContainer.addView(text);
                }
                return true;
            }
        });
    }

    public EditText getEditText() {
        return editText;
    }
    public LinearLayout getContainer() {
        return mContainer;
    }
}
