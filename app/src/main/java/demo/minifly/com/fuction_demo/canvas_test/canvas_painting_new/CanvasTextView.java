package demo.minifly.com.fuction_demo.canvas_test.canvas_painting_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.fuction_demo.utils.ConvertUtils;
import demo.minifly.com.fuction_demo.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/4
 * time: 19:19
 * desc: 画个表盘
 */
public class CanvasTextView extends View {
    private Context mContext;
    private Paint rotatePaint;
    private int viewWidth,viewHeight;

    public CanvasTextView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CanvasTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public void init(){
        rotatePaint = new Paint();
        rotatePaint.setStyle(Paint.Style.STROKE);
        rotatePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        rotatePaint.setAntiAlias(true);
        rotatePaint.setStyle(Paint.Style.FILL);
        rotatePaint.setColor(0xff000000);
        rotatePaint.setTextSize(ConvertUtils.dip2px(mContext,20));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(0 , -getBaseLineTop(rotatePaint)); //返回的top 是负的
        canvas.drawText("饿和日俄 hi hi  " , 0 , 0 ,rotatePaint);
//        canvas.save();
        canvas.translate(0, ConvertUtils.dip2px(mContext , 20) + getBaseLineBottom(rotatePaint));//现在返回的是正的了，所以可以累加了
        canvas.drawText("饿和日俄 hi hi  " , 0 , 0 ,rotatePaint);
//        canvas.restore();
    }

    /**
     * 基于基线进行文字绘制 top
     */
    public int getBaseLineTop(Paint paint){
        return paint.getFontMetricsInt().top;
    }
    /**
     * 基于基线进行文字绘制 top
     */
    public int getBaseLineBottom(Paint paint){
        return paint.getFontMetricsInt().bottom;
    }
}







