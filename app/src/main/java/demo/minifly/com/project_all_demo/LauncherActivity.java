package demo.minifly.com.project_all_demo;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import demo.minifly.com.BaseActivity;
import demo.minifly.com.R;

public class LauncherActivity extends BaseActivity {
    Handler mHandler = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.launcher_layout);

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                String value = sp.getValue(Constant.sp_Token);
//                if (TextUtils.isEmpty(value)) {
//                    startActivity(LoginActivity.class, null, true);
////                    startActivity(MainActivity.class, null, true);
//                    Intent it = new Intent(LauncherActivity.this, UpdateVersionService.class);
//                    startService(it);
//                } else {
//                    startActivity(MainActivity.class, null, true);
//                    Intent it = new Intent(LauncherActivity.this, UpdateVersionService.class);
//                    startService(it);
//                }
            }
        }, 1000);
    }
}
