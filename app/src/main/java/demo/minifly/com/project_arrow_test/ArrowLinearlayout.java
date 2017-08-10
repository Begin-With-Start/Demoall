package demo.minifly.com.project_arrow_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import demo.minifly.com.R;
import demo.minifly.com.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/8/9
 * time: 14:47
 * desc:
 */
public class ArrowLinearlayout extends LinearLayout {

    public int [] point1,point2;
    public Paint linePaint;
    public Context mContext;

    public ArrowLinearlayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public ArrowLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public ArrowLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init(){

        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.white));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);//去锯齿
        linePaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触
        linePaint.setStrokeWidth(DensityUtils.dip2px(mContext,2));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

//        if(tempPoint3!=null&& point4 !=null  && tempPoint3.getX() == point4.getX()){//把第四个点画上
//            canvas.drawCircle(centerX4,centerY4,dotSize/2, dotPaint);
//            canvas.drawCircle(centerX3,centerY3,dotSize/2, dotPaint);
//            canvas.drawLine(centerX3,centerY3,tempPoint3.getX(),tempPoint3.getY(),linePaint);
//        }

        if(point1 !=null && point2!=null ){
            canvas.drawLine(point1[0],point1[1],point2[0],point2[1],linePaint);
        }

    }

    public void setData(int [] point1,int [] point2){

        this.point1 = point1;
        this.point2 = point2;

        invalidate();//重绘

    }
}
