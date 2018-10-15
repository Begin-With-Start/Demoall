package demo.minifly.com.leoao.dialog.dialog2.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.leoao.sdk.common.utils.DisplayUtil;

import demo.minifly.com.R;
import demo.minifly.com.leoao.dialog.dialog2.CustomBaseDialog;
import demo.minifly.com.leoao.dialog.dialog2.CustomBaseDialogListener;
import demo.minifly.com.project_all_demo.button.StateButton;

/**
 * Created by ${minifly} on 2018/5/18.
 * desc: 实现了基础弹框类的例子
 */

public class CustomeBaseDialogUpdate extends CustomBaseDialog {

    private View view;
    private String title;
    private String content;

    @SuppressLint("ResourceType")
    public CustomeBaseDialogUpdate(@NonNull Context context) {
        super(context, 0);
    }

    @SuppressLint("ResourceType")
    public CustomeBaseDialogUpdate(@NonNull Context context, int styleId) {
        //alpha的动画效果
        super(context, 0 );
    }

    @SuppressLint("ResourceType")
    public CustomeBaseDialogUpdate(@NonNull Context context , String title, String content) {
        //alpha的动画效果
        super(context, 0 );
        this.title = title;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        //直接添加到basecontent中
        view = inflater.inflate(R.layout.leoao_business_dialog_nps_content_layout,mDialogBaseContentRel);

    }

    @Override
    public void showSuspend() {
        this.show();

        setContentMarginLeftRight(DisplayUtil.dip2px(38), DisplayUtil.dip2px(38));

        TextView title = view.findViewById(R.id.tv_update_dialog_content_title);
        TextView content = view.findViewById(R.id.tv_update_dialog_content_content);
        StateButton confirm = view.findViewById(R.id.btn_update_dialog_content_update);

        title.setText("" + this.title);
        content.setText("" + this.content);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.confirmClick();
                }
            }
        });

        setDialogListener(new CustomBaseDialogListener() {
            @Override
            public void closeClick() {
                if(listener != null){
                    listener.closeClick();
                }
            }
        });
    }

    @Override
    public void dismissSuspend() {
        this.dismiss();
    }

    MyCustomBaseDialogListener listener;
    public void setDialogListener(MyCustomBaseDialogListener listener){
        this.listener = listener;
    }
    public abstract static class MyCustomBaseDialogListener implements CustomBaseDialogListener{
        public abstract void confirmClick();
    }
}
