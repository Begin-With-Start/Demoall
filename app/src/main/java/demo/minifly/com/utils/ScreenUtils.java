package demo.minifly.com.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 *	三种获取屏幕宽高的方式
 * @author zhy
 */
public class ScreenUtils {
	private ScreenUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}


	/**
	 * 获得屏幕高度
	 */
	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context
			.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}


	/**
	 * 获得屏幕宽度
	 */
	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
			.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}


	/**
	 * 获得状态栏的高度
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
				.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}


	/**
	 * 获取当前屏幕截图，包含状态栏
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
		view.destroyDrawingCache();
		return bp;

	}


	/**
	 * 获取当前屏幕截图，不包含状态栏
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
			- statusBarHeight);
		view.destroyDrawingCache();
		return bp;

	}

	/**
	 * 屏幕宽高三种方式
	 */
	public static void getWidthHeight(Activity activity){
		// 1. 获取屏幕宽高一
		int screenWidth1 = activity.getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：px）
		int screenHeight1 = activity.getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：p）
//		tvScreenHeight.setText(screenHeight1 + " px");
//		tvScreenWidth.setText(screenWidth1 + " px");


		// 2.获取屏幕宽高二
		DisplayMetrics dm2 = activity.getResources().getDisplayMetrics();
//		float density = dm2.density; // 屏幕密度（像素比例：./././.）
//		int densityDPI = dm2.densityDpi; // 屏幕密度（每寸像素：///）
//		float xdpi = dm2.xdpi;
//		float ydpi = dm2.ydpi;
		int screenWidth2 = dm2.widthPixels; // 屏幕宽（像素，如：px）
		int screenHeight2 = dm2.heightPixels; // 屏幕高（像素，如：px）


		//3. 获取屏幕宽高三
		DisplayMetrics dm3 = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm3);
//		density = dm3.density; // 屏幕密度（像素比例：./././.）
//		densityDPI = dm3.densityDpi; // 屏幕密度（每寸像素：///）
//		xdpi = dm3.xdpi;
//		ydpi = dm3.ydpi;
		int screenWidth3 = dm3.widthPixels; // 屏幕宽（px，如：px）
		int screenHeight3 = dm3.heightPixels; // 屏幕高（px，如：px）
	}
}
