package demo.minifly.com.project_all_demo.rangebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ProgressBar;

import demo.minifly.com.R;


/**
 * @author : mundane
 * @time : 2017/6/22 14:46
 * @description :
 * @file : RangeBar.java
 */

public class RangeBar extends ProgressBar {

	private final int mBgColor = getResources().getColor(R.color.common_color_c13_ececec);
	private final int mReachColor = getResources().getColor(R.color.common_color_c1_0971ce);
	private final int mWhiteBallColor = getResources().getColor(android.R.color.white);
	private Paint mBgPaint;
	private Paint mReachPaint;
	private Paint mBlueBallPaint;
	private Paint mWhiteBallPaint;
	private Paint mBlueTextPaint;
	// 蓝字14sp
	private int mBlueTextSize = 14;
	private Paint mGreyTextPaint;
	private int mGreyColor = getResources().getColor(R.color.common_color_c8_666666);
	//	灰字12sp
	private int mGreyTextSize = 12;
	//	默认线的高度是3dp
	private final int mLineHeight = dp2px(3);
	// 默认线离左边的距离是40dp
	private float mPaddingLeft = dp2px(44);
	// 默认蓝色大圆的半径是14dp
	private float mBlueRadius = dp2px(14);
	private float mWhiteRadius = dp2px(13);
	private float mTotalLineWidth;
	private float downX;
	private float mOffset = mWhiteRadius - 2;
	private float mReachWidth = mPaddingLeft + mOffset;
	private OnRangeBarChangeListener mOnRangeBarChangeListener;

	public static final int NORMAL = 0;
	public static final int TEXT = 1;
	private int mTextStyle;
	private final String ZERO = "0";

	public RangeBar(Context context) {
		this(context, null);
	}

	public RangeBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RangeBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttrs(context, attrs);
		initData();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		drawRangeBar(canvas);
		canvas.restore();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mTotalLineWidth = w - 2 * mPaddingLeft;
	}

	public void setOnRangeBarChangeListener(OnRangeBarChangeListener onRangeBarChangeListener) {
		mOnRangeBarChangeListener = onRangeBarChangeListener;
	}

	public interface OnRangeBarChangeListener{
		void onProgressChanged(int progress);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float reachWidth;
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = event.getX();
				if (downX >= mPaddingLeft+mOffset && downX <= mPaddingLeft + mTotalLineWidth - mOffset) {
					reachWidth = downX;
				} else if (downX < mPaddingLeft+mOffset) {
					reachWidth = mPaddingLeft+mOffset;
				} else {
					reachWidth = mPaddingLeft + mTotalLineWidth - mOffset;
				}
				setReachWidth(reachWidth);
				if (mOnRangeBarChangeListener != null) {
					mOnRangeBarChangeListener.onProgressChanged(getProgress());
				}
				return true;
			case MotionEvent.ACTION_MOVE:
//				if (event.getY() > getHeight()) {
//					return super.onTouchEvent(event);
//				}
				downX = event.getX();
				if (downX >= mPaddingLeft+mOffset && downX <= mPaddingLeft + mTotalLineWidth - mOffset) {
					reachWidth = downX;
				} else if (downX < mPaddingLeft+mOffset) {
					reachWidth = mPaddingLeft+mOffset;
				} else {
					reachWidth = mPaddingLeft + mTotalLineWidth- mOffset;
				}
				setReachWidth(reachWidth);
				if (mOnRangeBarChangeListener != null) {
					mOnRangeBarChangeListener.onProgressChanged(getProgress());
				}
				break;
			default:
				break;
		}
		return super.onTouchEvent(event);
	}


	private void setReachWidth(float width) {
		mReachWidth = width;
		invalidate();
	}

	@Override
	public synchronized int getProgress() {
		int progress = (int) (((mReachWidth - mPaddingLeft - mOffset) / (mTotalLineWidth - 2* mOffset)) * getMax());
		return progress;
	}

	@Override
	public synchronized void setProgress(int progress) {
		float reachWidth = mPaddingLeft +  mOffset + (mTotalLineWidth - 2 * mOffset) * progress / getMax();
		setReachWidth(reachWidth);
	}

	private void drawRangeBar(Canvas canvas) {
		canvas.translate(0, getHeight() / 2);
		canvas.drawLine(mPaddingLeft, 0, mPaddingLeft + mTotalLineWidth, 0, mBgPaint);
		canvas.drawLine(mPaddingLeft, 0, mReachWidth, 0, mReachPaint);

		String text = String.valueOf(getProgress());
		float textWidth = mBlueTextPaint.measureText(text);
		float textHeight = mBlueTextPaint.descent() + mBlueTextPaint.ascent();
		canvas.drawText(text, mReachWidth - textWidth/2, -mBlueRadius - dp2px(10), mBlueTextPaint);
		switch (mTextStyle) {
			case TEXT:
				String number = ZERO;
				float numberWidth = mGreyTextPaint.measureText(number);
				float numberHeight = mGreyTextPaint.descent() + mGreyTextPaint.ascent();
				canvas.drawText(number, mPaddingLeft - dp2px(5) - numberWidth, 0 - numberHeight / 2, mGreyTextPaint);
				canvas.drawText(getMax()+"", mPaddingLeft + mTotalLineWidth+dp2px(5), 0 - numberHeight/2, mGreyTextPaint);
				break;
			default:
				break;
		}


		canvas.drawCircle(mReachWidth, 0, mBlueRadius, mBlueBallPaint);
		canvas.drawCircle(mReachWidth, 0, mWhiteRadius, mWhiteBallPaint);


	}



	private void initData() {
		mBgPaint = createLinePaint(mBgColor, Paint.Style.FILL, mLineHeight);
		mReachPaint = createLinePaint(mReachColor, Paint.Style.FILL, mLineHeight);
		mBlueBallPaint = createBlueBallPaint(mReachColor, Paint.Style.FILL);
		mWhiteBallPaint = createWhiteBallPaint(mWhiteBallColor, Paint.Style.FILL);
		mBlueTextPaint = createTextPaint(mReachColor, sp2px(mBlueTextSize));
		mGreyTextPaint = createTextPaint(mGreyColor, sp2px(mGreyTextSize));
	}

	private Paint createTextPaint(int paintColor, int textSize) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setColor(paintColor);
		paint.setTextSize(textSize);
		return paint;
	}

	private Paint createBlueBallPaint(int paintColor, Paint.Style style) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setDither(true);
		paint.setColor(paintColor);
		paint.setStyle(style);
		paint.setStrokeJoin(Paint.Join.ROUND);
		return paint;
	}

	private Paint createWhiteBallPaint(int paintColor, Paint.Style style) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setDither(true);
		paint.setColor(paintColor);
		paint.setStyle(style);
		paint.setStrokeJoin(Paint.Join.ROUND);
		return paint;
	}

	private Paint createLinePaint(int paintColor, Paint.Style style, int width) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		paint.setDither(true);
		paint.setColor(paintColor);
		paint.setStyle(style);
		paint.setStrokeWidth(width);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		return paint;
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeBar);
		mTextStyle = a.getInt(R.styleable.RangeBar_textStyle, NORMAL);
		a.recycle();
	}

	public int px2dp(float paValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (paValue / scale + 0.5f);
	}

	public int dp2px(float dpValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public int px2sp(float pxValue) {
		final float frontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / frontScale + 0.5f);
	}

	public int sp2px(float spValue) {
		final float frontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * frontScale + 0.5f);
	}
}
