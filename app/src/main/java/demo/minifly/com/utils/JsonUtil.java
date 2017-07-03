package demo.minifly.com.utils;

import org.json.JSONObject;

/**
 * Created by ZFY on 2016/10/20 0020.
 */

public class JsonUtil {
	public static String getCode(String responseBody) {
		JSONObject json = null;
		String code = null;
		try {
			json = new JSONObject(responseBody);
			code = json.optString("code");//如果没有这个字段, 返回"" (这个可以查看源码)
//			code = json.getString("") 如果没有这个字段, 会抛出错误
		} catch (Exception e) {
			ToastUtils.showToast("JsonUtil: " + e.getMessage());
			e.printStackTrace();
		}
		return code;
	}


	public static String getMsg(String responseBody) {
		JSONObject json = null;
		String msg = "后端没有返回msg";
		try {
			json = new JSONObject(responseBody);
			msg = json.optString("msg");
			return msg;
		} catch (Exception e) {
			ToastUtils.showToast("JsonUtil: " + e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}


	public static String getData(String responseBody) {
		JSONObject json = null;
		String data = null;
		try {
			json = new JSONObject(responseBody);
			data = json.optString("data");
		} catch (Exception e) {
			ToastUtils.showToast("JsonUtil: " + e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
}
