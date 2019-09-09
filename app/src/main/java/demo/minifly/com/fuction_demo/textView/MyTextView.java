package demo.minifly.com.fuction_demo.textView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者：Administrator on 2016/11/3 11:19
 * 一个水平的闪烁的textview
 *自定义view写的清楚的:http://blog.csdn.net/htybay/article/details/51444661
 */
public class MyTextView extends TextView {
    private int mViewWidth= 0,mTranslate = 0;
    private Paint mPaint;
    private LinearGradient mLinearGradient = null;
    private Matrix mGradientMatrix = null;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate +=mViewWidth/5;
            if(mTranslate>2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);//100毫秒之后在非ui进程中重新绘制
            /**
             * 一组是invalidate，另一组是postInvalidate，其中前者是在UI线程自身中使用，而后者在非UI线程中
             */
        }
    }

    /**
     * 今天查看了下iew的 回调方法的执行顺序：
     10-12 07:41:21.040: V/(668): onAttachedToWindow
     10-12 07:41:21.040: V/(688): onFinishInflate1
     10-12 07:41:21.100: V/(688): onmeasure2
     10-12 07:41:21.160: V/(688): onSizeChanged3
     10-12 07:41:21.160: V/(688): onlayout4
     10-12 07:41:21.250: V/(688): onmeasure5
     10-12 07:41:21.260: V/(688): onlayout6
     10-12 07:41:21.541: V/(688): onmeasure7
     10-12 07:41:21.541: V/(688): onlayout8
     10-12 07:41:21.559: V/(688): ondraw9
     10-12 07:41:21.599: V/(688): dispatchDraw10
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,new int[]{Color.BLUE,0xfffffff,Color.BLUE},null , Shader.TileMode.CLAMP);

                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }


}
