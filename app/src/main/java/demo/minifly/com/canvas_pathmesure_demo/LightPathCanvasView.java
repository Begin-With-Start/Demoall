package demo.minifly.com.canvas_pathmesure_demo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.listview_test.ConvertUtils;
import demo.minifly.com.utils.LogUtils;

/**
 * author ：minifly
 * date: 2017/9/7
 * time: 10:29
 * desc:
 */
public class LightPathCanvasView extends View {
    private Context mContext;

    private Paint linePaint;
    private int width;
    private int height;


    private Path drawPath;//用于截取路径的 Path
    private Path outerPath;//外层圆的路径保存
    private Path inPath; //内层圆的路径保存

    private  Path ractaglePath1; // 第一个三角形的路径
    private Path ractaglePath2; // 第二个三角形路径

    private PathMeasure pathMeasure;//path的一个包装类 主要是可以取得一些特点的path
    private float distance;
    private ValueAnimator valueAnimator;
    private RectF outerRect;
    private RectF inRect;


    public LightPathCanvasView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public LightPathCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public LightPathCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init(){
        linePaint = new Paint();
        linePaint.setStrokeWidth(ConvertUtils.dip2px(mContext,1));
        linePaint.setColor(getResources().getColor(R.color.canvas_own_color));
        linePaint.setAntiAlias(true);//消除锯齿
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStrokeJoin(Paint.Join.BEVEL);
        linePaint.setShadowLayer(15, 0, 0, Color.WHITE);//白色光影效果

        outerPath = new Path();
        inPath = new Path();
        drawPath = new Path();
        ractaglePath1 = new Path();
        ractaglePath2 = new Path();
        pathMeasure = new PathMeasure();

        width = ConvertUtils.dip2px(mContext,200);
        height = ConvertUtils.dip2px(mContext,200);

        int outCircleRadius = width - ConvertUtils.dip2px(mContext,4);
        int inCircleRadius = width - ConvertUtils.dip2px(mContext,(24));

        if(outerRect == null || inRect == null ){
            /**
             * 外圆 起点往下移动一个dp，为了给宽的线留一个距离出来
             * 第二种的填充方式， 空心
             */
            outerRect = new RectF(ConvertUtils.dip2px(mContext,2),ConvertUtils.dip2px(mContext,2),outCircleRadius,outCircleRadius);
            /**
             * 内圆应该往下去点，同时留一个距离
             */
            inRect = new RectF(ConvertUtils.dip2px(mContext,21),ConvertUtils.dip2px(mContext,21),inCircleRadius,inCircleRadius);
        }

        outerPath.addArc(outerRect,150,-359.9F);
        inPath.addArc(inRect,90,-359.9F);
        pathMeasure.setPath(inPath,false);

        float [] pos = new float[2];
        ractaglePath1.reset();
        pathMeasure.getPosTan(0,pos,null);
        ractaglePath1.moveTo(pos[0],pos[1]);
        pathMeasure.getPosTan((1f/3f)*pathMeasure.getLength(),pos,null);//获取三分之一的时候的点位置
        ractaglePath1.lineTo(pos[0],pos[1]);
        pathMeasure.getPosTan((2f/3f)*pathMeasure.getLength(),pos,null);//获取三分之二的时候的点位置
        ractaglePath1.lineTo(pos[0],pos[1]);
        pathMeasure.getPosTan(0,pos,null);
        ractaglePath1.lineTo(pos[0],pos[1]);
        ractaglePath1.close();

        pathMeasure.getPosTan((2f / 3f) * pathMeasure.getLength(), pos, null);
        Matrix matrix = new Matrix();
        matrix.postRotate(-180);
        ractaglePath1.transform(matrix,ractaglePath2);

        LogUtils.showErrLog("" + ractaglePath2.toString());

        initValueAnimator();
        startAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



//        drawPath.reset();//先重置一下
//        pathMeasure.setPath(outerPath,false);
//        pathMeasure.getSegment(0, distance * pathMeasure.getLength(), drawPath, true);
//        canvas.drawPath(drawPath, linePaint);
//
//
//        drawPath.reset();//先重置一下
//        pathMeasure.setPath(inPath,false);
//        pathMeasure.getSegment(0, distance * pathMeasure.getLength(), drawPath, true);
//        canvas.drawPath(drawPath, linePaint);


//        drawPath.reset();//先重置一下
//        pathMeasure.setPath(ractaglePath1,false);
        float stopD = distance * pathMeasure.getLength();
        float startD =stopD - (0.5f - Math.abs(0.5f - distance))*ConvertUtils.dip2px(mContext,100);//
//        pathMeasure.getSegment(startD, stopD, drawPath, true);
//        canvas.drawPath(drawPath, linePaint);

        drawPath.reset();//先重置一下
        pathMeasure.setPath(ractaglePath2,false);
        pathMeasure.getSegment(startD, stopD, drawPath, true);
        canvas.drawPath(drawPath, linePaint);


    }


    public void initValueAnimator(){
        valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(1500);
    }

    public void startAnimator(){
        if(valueAnimator!=null){
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    distance = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.start();
        }
    }
}




