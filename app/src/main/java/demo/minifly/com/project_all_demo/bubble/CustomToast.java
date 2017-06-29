package demo.minifly.com.project_all_demo.bubble;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import demo.minifly.com.R;


/**
 *  自定义Toatst
 * Created by ShineF on 2017/6/22 0022.
 */

public class CustomToast {
    private Toast mToast;

    private CustomToast(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_custom_toast_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_content);
        textView.setText(text);
        if(mToast == null){
            mToast = new Toast(context);
            mToast.setDuration(duration);
            mToast.setGravity(Gravity.TOP, 0, 220);
            mToast.setView(v);
        }else{
            mToast.cancel();
            mToast.setDuration(duration);
        }
    }

    public static CustomToast makeText(Context context, CharSequence text, int duration) {
        return new CustomToast(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public CustomToast setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
        return this;
    }
}
