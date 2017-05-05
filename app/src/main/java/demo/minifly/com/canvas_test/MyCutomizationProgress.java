package demo.minifly.com.canvas_test;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;


/**
 * author ：minifly
 * date: 2017/2/17
 * time: 14:31
 * desc: 为工程定制一个进度条，带有渐变的效果。
 */
public class MyCutomizationProgress extends View{
    private Context mContext;
    private int width;
    private int height;
    private Paint mPaint;
    private int total  = 100;//默认一百
    private int current = 50;
    private Paint textPaint;

    public MyCutomizationProgress(Context context) {
        super(context);
        init(context);
    }

    public MyCutomizationProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCutomizationProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth() - dip2px(mContext,60);
        height = getHeight();

        int marginHeight = height/2;

        mPaint.setStrokeWidth(height);


        float unitLength = (float) width/total;

        int nowWidth = (int)(current*unitLength);

        mPaint.setShader(new LinearGradient(0,marginHeight,nowWidth,marginHeight,getResources().getColor(R.color.start_color),getResources().getColor(R.color.end_color), Shader.TileMode.REPEAT));//从（0,0）到（0,height）的色彩渐变
        canvas.drawLine(marginHeight,marginHeight,nowWidth-marginHeight,marginHeight,mPaint);



        String testString = "" + current;
        Rect bounds = new Rect();
        textPaint.getTextBounds(testString, 0, testString.length(), bounds);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(testString,0,testString.length(),nowWidth + dip2px(mContext,10), height-2 , textPaint);

    }

    private void initAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, temp);
        animator.setDuration(700);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int count = (int)animation.getAnimatedValue();
                current = count;
                invalidate();
            }
        });
    }

    private void init(Context context){
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(mContext.getResources().getColor(R.color.start_color));
        mPaint.setAntiAlias(true);//去锯齿

        textPaint = new Paint();
        textPaint.setStrokeWidth(dip2px(mContext,1));
        textPaint.setTextSize(dip2px(mContext,14));
        textPaint.setColor(mContext.getResources().getColor(R.color.gray));
        textPaint.setTextAlign(Paint.Align.LEFT);


    }
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    int temp = 0;
    public void setProgress(int total,int current){
        this.total = total;
        this.temp = current;
//        invalidate();//重绘
        initAnimator();
    }
}
