package demo.minifly.com.permission_demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * author ：minifly
 * date: 2017/6/5
 * time: 16:22
 * desc:
 */
public class PermissionUtils {

    //回调监听
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {

        void onPermissionsGranted(int requestCode, List<String> perms);

        void onPermissionsDenied(int requestCode, List<String> perms);

    }

    public static void requestPermissions(
            @NonNull Activity host,@NonNull String[] permissions,
            int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (host.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {//已有权限

            }else {//正请求权限
                host.requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
                Toast.makeText(host,"开始请求权限",Toast.LENGTH_LONG).show();
            }
        }else{//版本小于23，不用请求权限
            Toast.makeText(host,"版本小于23，不用请求权限",Toast.LENGTH_LONG).show();
        }
    }


    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, @NonNull Object... receivers) {
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        for (Object object : receivers) {
            //记录同意的权限
            if (!granted.isEmpty()) {
                if (object instanceof PermissionCallbacks) {
                    ((PermissionCallbacks) object).onPermissionsGranted(requestCode, granted);//权限通过
                }
            }

            // 记录所有拒绝的权限
            if (!denied.isEmpty()) {
                if (object instanceof PermissionCallbacks) {
                    ((PermissionCallbacks) object).onPermissionsDenied(requestCode, denied);//权限拒绝
                }
            }
        }
    }
}
