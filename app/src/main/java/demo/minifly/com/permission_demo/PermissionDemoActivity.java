package demo.minifly.com.permission_demo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import demo.minifly.com.R;

public class PermissionDemoActivity extends AppCompatActivity implements PermissionUtils.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_demo);
    }


    @Override
    protected void onResume() {
        super.onResume();
        PermissionUtils.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

        Toast.makeText(this,"同意了权限",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this,"拒绝了权限",Toast.LENGTH_LONG).show();

    }
}
