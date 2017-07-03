package demo.minifly.com.utils;

import android.widget.Toast;

import demo.minifly.com.url_config.MobileApplication;


/**
 * Created by Administrator on 2016/10/18.
 */

public class ToastUtils {

	public static void showToast(String text) {
		if (text != null) {
			Toast.makeText(MobileApplication.getApplicationInstance(), text, Toast.LENGTH_SHORT).show();
		}
	}


	public static void showToast(int resource) {
		Toast.makeText(MobileApplication.getApplicationInstance(), resource, Toast.LENGTH_SHORT).show();
	}


	public static void toastError(String error) {
		if (error != null) {
			Toast.makeText(MobileApplication.getApplicationInstance(), error, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Creator by xzw on 2017/1/13 14:51
	 * Description: 自定义toast
	 * param icon 默认图标传入0即可，其他自己设置图片
	 * param msg 弹出的提示信息，默认传null
	 * param width height 设置toast的宽高传入的是dp单位
	 */
//	public static void customToastShow(Context context, int icon, String msg, int width, int height){
//		Toast toast = Toast.makeText(context,msg, Toast.LENGTH_SHORT);
//		View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
//		LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.toast_linear);
//		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ConvertUtils.dip2px(context,width), ConvertUtils.dip2px(context,height));
//		linearLayout.setLayoutParams(layoutParams);
//		TextView textView = (TextView)view.findViewById(R.id.tv_toast_message);
//		ImageView imageView= (ImageView) view.findViewById(R.id.tv_toast_icon);
//		if (icon!=0){
//			imageView.setImageResource(icon);
//		}
//		if (!TextUtils.isEmpty(msg)){
//			textView.setText(msg);
//		}
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		toast.setView(view);
//		toast.show();
//	}
}
