package demo.minifly.com.permission_demo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import demo.minifly.com.R;

public class PermissionDemoActivity extends AppCompatActivity implements PermissionUtils.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_demo);
        PermissionUtils.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        /**
         * 当requestcode相同时候，同时perms.size == 请求的权限数时候，认为权限请求成功。
         */
        if(requestCode==1 && perms.size() == 2){

            Toast.makeText(this,"同意了权限全部权限",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        /**
         * 调用了一次拒绝权限认为失败
         */
        if(requestCode==1){
            Toast.makeText(this,"拒绝了权限"+perms.get(0),Toast.LENGTH_LONG).show();
            PermissionUtils.permissionDialog(this, perms,null);
        }

    }
}
