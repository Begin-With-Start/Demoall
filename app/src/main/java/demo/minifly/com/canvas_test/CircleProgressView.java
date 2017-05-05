package demo.minifly.com.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/01/12 13:35
 * 一个有两种颜色的圆形进度条
 * 多种颜色吧，互相之间进行覆盖，产生更多的效果
 */
public class CircleProgressView extends View {

	Context mContext = null;
	Paint mPaintJinji = null; // 紧急笔头
	Paint mPaintNormal; // 普通笔头
	Paint mPaintTimeout = null; // 超时笔头
	int dp4 = 0;
	public CircleProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CircleProgressView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context c) {
		mContext = c;
		
		//紧急笔头设置
		mPaintJinji = new Paint();
		mPaintJinji.setStyle(Paint.Style.STROKE);
		mPaintJinji.setColor(mContext.getResources().getColor(R.color.task_red));
		mPaintJinji.setStrokeWidth(dip2px(mContext, 8));
		mPaintJinji.setAntiAlias(true);
		
		// 普通笔头设置
		mPaintNormal = new Paint();
		mPaintNormal.setStyle(Paint.Style.STROKE);
		mPaintNormal.setColor(mContext.getResources().getColor(R.color.task_blue));
		mPaintNormal.setStrokeWidth(dip2px(mContext, 8));
		mPaintNormal.setAntiAlias(true);
		
		// 超时笔头设置
		mPaintTimeout = new Paint();
		mPaintTimeout.setStyle(Paint.Style.STROKE);
		mPaintTimeout.setColor(mContext.getResources().getColor(R.color.task_yellow));
		mPaintTimeout.setStrokeWidth(dip2px(mContext, 8));
		mPaintTimeout.setAntiAlias(true);

		dp4 = dip2px(mContext, 4);
	}
	
	int radius; // 半径
	int countNormal = 0; // normal 数据个数
	int countJinji = 0; // 紧急数据个数
	int countTimeOut = 0; // 超时数据个数
	int countTotal = 0; // 总个数
	int startAngle = -90; // 画圆的开始角度
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		radius = getWidth() / 2 - dp4;
		
		// 画正常的圆圈
		canvas.drawCircle(x, y, radius, mPaintNormal);

		if (countTotal != 0) { // 避免除0错
			// 画紧急的任务圆弧
			RectF rect = new RectF(getWidth()/2 - radius, dp4, getWidth()/2 + radius, getHeight() - dp4);
			canvas.drawArc(rect, startAngle, 360 * countJinji / countTotal, false, mPaintJinji);

			// 画超时任务圆弧
			startAngle += 360 * countJinji / countTotal;
			rect = new RectF(getWidth()/2 - radius, dp4, getWidth()/2 + radius, getHeight() - dp4);
			canvas.drawArc(rect, startAngle, 360 * countTimeOut / countTotal, false, mPaintTimeout);
		}
	}
	
	public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }

	// 设置数据
	public void setData(int total, int normal, int jinji, int timeout) {
		countTotal = total;
		countNormal = normal;
		countJinji = jinji;
		countTimeOut = timeout;
		invalidate();//重绘操作
	}
}
