package demo.minifly.com.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 作者：minifly on 2016/12/5 09:25
 * view 与 surfaceview
 * view是在主ui thread中进行绘制和刷新的，如果是绘图会消耗掉16ms以上的时间，或者是需要频繁的进行重绘操作的话，应该使用surfaceview的操作
 * surfaceview 实现了双缓存机制，同时是在子线程中进行的绘图操作。
 * 在使用上一般的surfaceview的使用会麻烦点，但是有一个可以满足大多数绘制的模板，直接套用模板即可，相反会更加的简单一些。
 *
 *  模板surfaceview
 */
public class CanvasSurfaceViewSin extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private SurfaceHolder surfaceHolder;
    private boolean mIsDrawing = false;  //绘图是否结束
    private Canvas mCanvas;
    private Path mPath;
    private int x,y;
    private Paint mPaint;

    public CanvasSurfaceViewSin(Context context) {
        super(context);
        initView();
    }

    public CanvasSurfaceViewSin(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CanvasSurfaceViewSin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public CanvasSurfaceViewSin(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public void initView(){
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        //设置画笔宽度
        mPaint.setStrokeWidth(1);
        //消除锯齿
        mPaint.setAntiAlias(true);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.getKeepScreenOn();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;

        mPath.moveTo(x,(int)(100*Math.sin(x*2*Math.PI/180) + 400));//开始点移动到起始点。

        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {

        while (mIsDrawing && x < getWidth()){
            x += 1;
            y = (int)(100*Math.sin(x*2*Math.PI/180) + 400);
            mPath.lineTo(x,y);
        }
        draw();
    }

    private void draw(){
        try{
            mCanvas = surfaceHolder.lockCanvas(); //获取当前的绘图对象。
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
//            mCanvas.drawPoint(x,y,mPaint);
        }catch (Exception  e){

        }finally {
            if(mCanvas!=null)
                surfaceHolder.unlockCanvasAndPost(mCanvas);//异常之后也应该每次都将绘图的内容进行提交
        }
    }
}
