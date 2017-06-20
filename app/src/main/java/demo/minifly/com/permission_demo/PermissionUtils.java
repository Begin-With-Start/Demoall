package demo.minifly.com.permission_demo;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * author ：minifly
 * date: 2017/6/5
 * time: 16:22
 * desc:权限管理相关
 */
public class PermissionUtils {
    public static String TAG = "permission";

    //回调监听
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {

        void onPermissionsGranted(int requestCode, List<String> perms);

        void onPermissionsDenied(int requestCode, List<String> perms);

    }

    public static void requestPermissions(
            @NonNull Activity host, @NonNull String[] permissions,
            int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if(!hasPermissions(host,permissions)){
                host.requestPermissions(permissions, 1);
//                Toast.makeText(host,"开始请求权限",Toast.LENGTH_LONG).show();
            }else{
                //全部有权限之后也会回调
                List<String > pers = new LinkedList<>();
                for (String per : permissions) {
                    pers.add(per);
                }
                ((PermissionCallbacks) host).onPermissionsGranted(requestCode, pers);//权限通过
            }
        }else{//版本小于23，不用请求权限 但是全部回调给页面处理
//            Toast.makeText(host,"版本小于23，不用请求权限",Toast.LENGTH_LONG).show();

            List<String > pers = new LinkedList<>();
            for (String per : permissions) {
                pers.add(per);
            }
            ((PermissionCallbacks) host).onPermissionsGranted(requestCode, pers);//权限通过

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

    //检测权限是否已全部拥有
    public static boolean hasPermissions(Context context, @NonNull String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default");
            return true;
        }

        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        }

        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(context, perm)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    //给用户显示该权限的作用
    public static void showPerDetailDialog(final Activity host){
        //用户不同意，向用户展示该权限作用
        if (!ActivityCompat.shouldShowRequestPermissionRationale(host, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AlertDialog dialog = new AlertDialog.Builder(host)
                    .setMessage("该相册需要赋予访问存储的权限，不开启将无法正常工作！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create();
            dialog.show();
            return;
        }
    }


    //到系统权限管理页面设置相应的权限。
    public static void permissionDialog(final Activity host,List<String> perms,@Nullable final AlertDialog.OnClickListener listener){
        StringBuilder perNames = new StringBuilder("");
        for(String perm : perms){
            perNames.append(permissionMap.get(perm) + ",");
        }


        final AlertDialog.Builder builder = new AlertDialog.Builder(host);
        builder.setTitle("提示：");
        builder.setMessage("当前应用没有"+perNames.toString().substring(0,perNames.toString().length()-1)+"权限，去设置界面打开？");
        builder.setNegativeButton("取消",listener == null?new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }:listener);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.fromParts("package", host.getPackageName(), null));
                host.startActivity(intent);
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * CALENDAR  日历
     READ_CALENDAR
     WRITE_CALENDAR

     CAMERA  相机
     CAMERA

     CONTACTS 联系人
     READ_CONTACTS
     WRITE_CONTACTS
     GET_ACCOUNTS

     LOCATION  位置
     ACCESS_FINE_LOCATION
     ACCESS_COARSE_LOCATION

     MICROPHONE  麦克风
     RECORD_AUDIO

     PHONE  手机
     READ_PHONE_STATE
     CALL_PHONE
     READ_CALL_LOG
     WRITE_CALL_LOG
     ADD_VOICEMAIL
     USE_SIP
     PROCESS_OUTGOING_CALLS

     SENSORS  传感器
     BODY_SENSORS

     SMS  短信
     SEND_SMS
     RECEIVE_SMS
     READ_SMS
     RECEIVE_WAP_PUSH
     RECEIVE_MMS

     STORAGE  存储
     READ_EXTERNAL_STORAGE
     WRITE_EXTERNAL_STORAGE
     */
    static Map<String,String > permissionMap= new HashMap<>();
    static{
        permissionMap.put("android.permission.READ_CALENDAR","读取日历");
        permissionMap.put("android.permission.WRITE_CALENDAR","添加日历");

        permissionMap.put("android.permission.CAMERA","相机");

        permissionMap.put("android.permission.READ_CONTACTS","读取联系人");
        permissionMap.put("android.permission.WRITE_CONTACTS","添加联系人");
        permissionMap.put("android.permission.GET_ACCOUNTS","获取联系人");

        permissionMap.put("android.permission.ACCESS_FINE_LOCATION","定位");
        permissionMap.put("android.permission.ACCESS_COARSE_LOCATION","基站定位");

        permissionMap.put("android.permission.RECORD_AUDIO","录制音频");

        permissionMap.put("android.permission.READ_PHONE_STATE","读取电话状态");
        permissionMap.put("android.permission.CALL_PHONE","拨打电话");
        permissionMap.put("android.permission.READ_CALL_LOG","读取拨打日志");
        permissionMap.put("android.permission.WRITE_CALL_LOG","添加拨打日志");
        permissionMap.put("android.permission.USE_SIP","呼叫管理");
        permissionMap.put("android.permission.PROCESS_OUTGOING_CALLS","电话管理");

        permissionMap.put("android.permission.BODY_SENSORS","传感器");

        permissionMap.put("android.permission.SEND_SMS","发送短信");
        permissionMap.put("android.permission.RECEIVE_SMS","接受短信");
        permissionMap.put("android.permission.READ_SMS","读取短信");
        permissionMap.put("android.permission.RECEIVE_WAP_PUSH","接受推送");
        permissionMap.put("android.permission.RECEIVE_MMS","短信拦截");

        permissionMap.put("android.permission.READ_EXTERNAL_STORAGE","读取存储");
        permissionMap.put("android.permission.WRITE_EXTERNAL_STORAGE","写存储");
    }


}
