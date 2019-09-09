package demo.minifly.com.fuction_demo.project_progress_new;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/5/3
 * time: 13:27
 * desc:
 */
public class ProgressView extends View {
    private Context mContext;
    private int width;//控件的宽度
    private int height; //控件的高度
    private int progress = 50;//默认的是50%的刻度
    private Paint progressPaint;
    private int progressLeft;
    private float progressTop;
    private int progressRight;
    private int progressBottom;
    private int current;

    public ProgressView(Context context) {
        super(context);
        mContext = context;
        init();

    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }


    private Paint bgPaint;

    public void init(){
        bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.gray_progress));
//        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setAntiAlias(true);//去锯齿
        bgPaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触

        progressPaint = new Paint();
        progressPaint.setColor(getResources().getColor(R.color.task_blue));
//        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setAntiAlias(true);//去锯齿
        progressPaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = DensityUtils.dip2px(mContext,300);

        //设置自定义view的宽度   LinearGradient(x1,y1,x2,y2)四个参数代表的是线性渲染的基准线
//        progressPaint.setShader(new LinearGradient(0,progressBottom,0,progressTop, new int[]{R.color.task_blue, Color.WHITE,R.color.task_blue},null, Shader.TileMode.REPEAT));//从（0,0）到（0,height）的色彩渐变
        canvas.drawRect(0,0,width,height,bgPaint);

        getProgressLocation();//获取进度相关的坐标

        canvas.drawRect(progressLeft,progressTop,progressRight,progressBottom,progressPaint);


    }

    public void getProgressLocation(){

        float unit= height/(float)100;


        progressLeft = 0;
        progressTop = unit*(100-current);
        progressRight = width;
        progressBottom = height;

//        LogUtils.showErrLog("progressLeft " +  progressLeft + " progressTop " + progressTop + " progressRight " + progressRight + " progressBottom " + progressBottom);

        //打印一下中间的划线点的坐标
//        int[] position = new int[2];
//        this.getLocationOnScreen(position);
//        LogUtils.showErrLog("0:" + (position[0] + width/2) + ",1:" + ( position[1]+ progressTop));

    }

    public void setProgressint(int progressint){
        progress = progressint;
        //得到进度相关的一个坐标
        initAnimator();
    }

    private void initAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, progress);
        animator.setDuration(900);
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

}
