package demo.minifly.com.fuction_demo.listview_test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.minifly.com.R;

public class HeaderViewLin extends LinearLayout {

    private TextView textView;

    public HeaderViewLin(Context context) {
        super(context);
        initView(context);
    }

    public HeaderViewLin(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HeaderViewLin(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_main_heardview_listview, null);
        textView = view.findViewById(R.id.minifly_listviewheader_txt);
        super.addView(view);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
