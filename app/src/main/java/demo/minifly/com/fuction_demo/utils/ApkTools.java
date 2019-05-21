package demo.minifly.com.fuction_demo.utils;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class ApkTools {
	static Context mContext;
	static ApkTools mInstance;

	private ApkTools(Context context) {
		mContext = context;
	}

	public static ApkTools getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new ApkTools(context);
		}
		return mInstance;
	}

	public boolean isInstallOnSDCard(String packageName) {
		try {
			PackageManager pm = mContext.getPackageManager();
			ApplicationInfo appInfo;
			appInfo = pm.getApplicationInfo(packageName, 0);

			if ((appInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 判断微信是否安装
	public static boolean isWeixinInstall(Context context) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mm")) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断qq是否可用
	 *
	 * @param context
	 * @return
	 */
	public static boolean isQQInstall(Context context) {
		final PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mobileqq")) {
					return true;
				}
			}
		}
		return false;
	}

}
