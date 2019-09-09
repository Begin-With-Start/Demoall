package demo.minifly.com.fuction_demo.request_demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import java.io.IOException;

public class NetUtils {
	// 判断网络是否连接
	public static boolean isHttpConnected(Context context) {
		try {
			NetworkInfo info = null;
			ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

			info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			State mobile = null;
			if (info != null) {
				mobile = info.getState();
			}

			State wifi = null;
			info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (info != null) {
				wifi = info.getState();   //如果3G、wifi、2G等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
			}

			if(mobile == State.CONNECTED || wifi == State.CONNECTED) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isWifiConnected(Context context) {
		try {
			NetworkInfo info = null;
			ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			State wifi = null;
			info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (info != null) {
				wifi = info.getState();
			}

			if(wifi == State.CONNECTED) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean is3GConnected(Context context) {
		try {
			NetworkInfo info = null;
			ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

			info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			State mobile = null;
			if (info != null) {
				mobile = info.getState();
			}
			
			if(mobile == State.CONNECTED) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean ping(){ 
    	boolean success=false;  
    	Process p =null;
    	String ip = "http://www.baidu.com";
    	try {   
    		p = Runtime.getRuntime().exec("ping -c 1 -W 1" +ip);
    		int status = p.waitFor();   
    		if (status == 0) {   
    			success=true;   
    		} else {   
    			success=false;    
    		}   
    	} catch (IOException e) {
    		success=false;     
    	} catch (InterruptedException e) {
    		success=false;     
    	}finally{   
    		p.destroy();  
    	}  
    	return success;  
	}  
}
