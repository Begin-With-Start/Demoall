package demo.minifly.com.fuction_demo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import demo.minifly.com.R;


/**
 * author ：minifly
 * date: 2017/9/13
 * time: 15:34
 * desc:
 */
public class ProgressDialogUtils extends AlertDialog {


    private static ImageView imageView;
    private static TextView textView;

    public ProgressDialogUtils(Context context) {
        super(context);
    }

    public ProgressDialogUtils(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ProgressDialogUtils(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static ProgressDialogUtils show(Context context, String title,boolean cancelable, OnCancelListener cancelListener) {
        ProgressDialogUtils dialog = new ProgressDialogUtils(context);
        View view = View.inflate(context, R.layout.progress_layout, null);


        imageView = (ImageView) view.findViewById(R.id.progress_layout_loading_iv);
        textView = (TextView) view.findViewById(R.id.progress_layout_loading_tv);

        if(title==null || "".equals(title)){
            textView.setText(context.getString(R.string.loading));
        }else
            textView.setText(title);


        if (!dialog.isShowing()) {
            dialog.show();
        }

        dialog.getWindow().setDimAmount(0.2f);
        dialog.setCanceledOnTouchOutside(cancelable);

        if(cancelListener!=null){
            dialog.setOnCancelListener(cancelListener);
        }

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = ConvertUtils.dip2px(context,158);
        dialog.getWindow().setAttributes(params);
        dialog.setContentView(view);

        //添加动画
        if (imageView != null) {
            Animation circle_anim = AnimationUtils.loadAnimation(context, R.anim.anim_round_rotate);
            LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
            circle_anim.setInterpolator(interpolator);
            if (circle_anim != null) {
                imageView.startAnimation(circle_anim);  //开始动画
            }
        }


        return dialog;
    }

    public static ProgressDialogUtils show(Context context, String title) {
        return show(context,title,false,null);
    }


    public static ProgressDialogUtils show(Context context) {
        return show(context,null);
    }



    public  void setTextHint(String text){
        if(textView==null){
            return;
        }
        if(text==null || "".equals(text)){
            textView.setText("");
        }
        textView.setText(text);
    }
}
