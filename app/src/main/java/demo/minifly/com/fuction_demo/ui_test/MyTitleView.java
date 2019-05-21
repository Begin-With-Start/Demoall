package demo.minifly.com.fuction_demo.ui_test;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.minifly.com.R;


/**
 * 自定义title布局
 * Created by devil on 16/12/12.
 */

public class MyTitleView extends RelativeLayout {

    private TextView title;

    public MyTitleView(Context context) {
        super(context);
        initView(context);
    }

    public MyTitleView(Context context,AttributeSet attrs){
        super(context,attrs);
        initView(context);

    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_title_view, null);
        title = (TextView) view.findViewById(R.id.tv_setting_public_title);

        TypedArray array = context.obtainStyledAttributes(R.styleable.MyTitleView);
        String str = array.getString(R.styleable.MyTitleView_title);
        setTitle(str);
        array.recycle();

    }

    public void setTitle(String public_title) {
        title.setText(public_title);
    }

    public MyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }


}
