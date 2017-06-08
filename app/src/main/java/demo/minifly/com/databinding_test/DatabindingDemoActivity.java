package demo.minifly.com.databinding_test;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.databinding.ActivityDatabindingDemoBinding;

public class DatabindingDemoActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *
         public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId,
             DataBindingComponent bindingComponent) {
             activity.setContentView(layoutId);
             View decorView = activity.getWindow().getDecorView();
             ViewGroup contentView = (ViewGroup) decorView.findViewById(android.R.id.content);
             return bindToAddedViews(bindingComponent, contentView, 0, layoutId);
         }
         databindingutil 中已经有了一个setcontentview的操作，所以可以省略掉activity的该操作。
         */
        ActivityDatabindingDemoBinding demoBinding = DataBindingUtil.setContentView(this,R.layout.activity_databinding_demo);
        user = new User("password","user");
        demoBinding.setUser(user);

        /**
         * 在databinding的布局中，如果有id的view那么会自动生成一个final的控件属性，直接可用
         */
        demoBinding.btnId1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPassword("密码啊");
                user.setUsername("用户名啊");
            }
        });

    }
}
