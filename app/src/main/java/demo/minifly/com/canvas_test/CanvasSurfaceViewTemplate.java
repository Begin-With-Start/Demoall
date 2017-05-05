package demo.minifly.com.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
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
public class CanvasSurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private SurfaceHolder surfaceHolder;
    private boolean mIsDrawing = false;  //绘图是否结束
    private Canvas mCanvas;

    public CanvasSurfaceViewTemplate(Context context) {
        super(context);
        initView();
    }

    public CanvasSurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CanvasSurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public CanvasSurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public void initView(){
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.getKeepScreenOn();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
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

        while (mIsDrawing){
            draw();
        }
    }

    private void draw(){
        try{
            mCanvas = surfaceHolder.lockCanvas(); //获取当前的绘图对象
        }catch (Exception  e){

        }finally {
            if(mCanvas!=null)
                surfaceHolder.unlockCanvasAndPost(mCanvas);//异常之后也应该每次都将绘图的内容进行提交
        }
    }
}
