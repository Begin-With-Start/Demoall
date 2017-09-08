package demo.minifly.com.canvas_pathmesure_demo;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.listview_test.ConvertUtils;

/**
 * author ：minifly
 * date: 2017/9/8
 * time: 17:13
 * desc:
 */
public class BlurMaskFilterView extends View {
    private Context mContext;
    private Paint mPaint;


    public BlurMaskFilterView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public BlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.canvas_own_green_5_color));
        mPaint.setStrokeWidth(ConvertUtils.dip2px(mContext,1));
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
        //内发光
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));

    }

    public void setMaskFilter(BlurMaskFilter.Blur blur){
        mPaint.setMaskFilter(new BlurMaskFilter(50, blur));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(ConvertUtils.dip2px(mContext,50),ConvertUtils.dip2px(mContext,50),ConvertUtils.dip2px(mContext,40),mPaint);
    }
}
