package demo.minifly.com.project_imagedeal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author ：minifly
 * date: 2017/5/5
 * time: 14:35
 * desc:图片处理类
 */
public class ImageDealView extends View {
    private Context mContext;

    public ImageDealView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ImageDealView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ImageDealView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public ImageDealView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        initView();
    }

    public void initView(){

    }



}
