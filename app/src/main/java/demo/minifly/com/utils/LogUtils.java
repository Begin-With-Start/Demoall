package demo.minifly.com.utils;

import android.text.TextUtils;

import demo.minifly.com.logger.Logger;

public class LogUtils {
	private static boolean debug = true;
	public static void setDebug(boolean isDebug) {
		debug = isDebug;
	}

	public static void showLog(String text) {
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
//						Log.d("wangcang",text.substring(i, i+3000));
						d("wangcang",text.substring(i, i+3000));
					else
//						Log.d("wangcang",text.substring(i, text.length()));
						d("wangcang",text.substring(i, text.length()));
				}
			} else
//				Log.d("wangcang",text);
				d("wangcang",text);
		}
	}

	public static void showErrLog(String text) {
		if (debug) {
			e("wangcang", text);
		}
	}

	public static void showInfoLog(String text){
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
//						Log.i("wangcang",text.substring(i, i+3000));
						d("wangcang",text.substring(i, i+3000));
					else
//						Log.i("wangcang",text.substring(i, text.length()));
						d("wangcang",text.substring(i, text.length()));
				}
			} else
//				Log.i("wangcang",text);
				i("wangcang",text);
		}
	}

	static void d(String tag,String msg){
//		Log.d(tag,msg);
		Logger.d(msg);
	}

	static void i(String tag,String msg){
//		Log.i(tag,msg);
		Logger.i(msg);
	}

	static void e(String tag,String msg){
//		Log.e(tag,msg);
		Logger.e(msg);
	}


}
