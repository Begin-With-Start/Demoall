package demo.minifly.com.leoao.dialog.dialog2.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

public class CustomeBaseDialogUpdateAll extends CustomBaseDialog {

    private View view;
    private LinearLayout contentAllLin,progressAllLin;

    @SuppressLint("ResourceType")
    public CustomeBaseDialogUpdateAll(@NonNull Context context) {
        super(context, 0);
    }

    @SuppressLint("ResourceType")
    public CustomeBaseDialogUpdateAll(@NonNull Context context, int styleId) {
        //alpha的动画效果
        super(context, 0 );
    }

    @SuppressLint("ResourceType")
    public CustomeBaseDialogUpdateAll(@NonNull Context context , String title, String content) {
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
        view = inflater.inflate(R.layout.dialog_nps_content_layout_all,mDialogBaseContentRel);

    }

    private String title;
    private String content;
    private ProgressBar progressBar;
    private TextView progress;
    private StateButton progressConfimBtn;
    private TextView progressTitle;

    @Override
    public void showSuspend() {
        this.show();

//        setContentMarginLeftRight(DisplayUtil.dip2px(28),DisplayUtil.dip2px(28));

        //content 视图
        TextView title = view.findViewById(R.id.tv_update_dialog_content_title);
        TextView content = view.findViewById(R.id.tv_update_dialog_content_content);
        StateButton confirm = view.findViewById(R.id.btn_update_dialog_content_update);

        //两种视图的切换
        contentAllLin = view.findViewById(R.id.lin_update_dialog_content_all_content);
        progressAllLin = view.findViewById(R.id.lin_update_dialog_content_all_progress);

        LinearLayout rootView = view.findViewById(R.id.dialog_nps_rootview);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rootView.getLayoutParams();
        layoutParams.leftMargin = DisplayUtil.dip2px(18);
        layoutParams.rightMargin = DisplayUtil.dip2px(18);
        rootView.setLayoutParams(layoutParams);

        title.setText("" + this.title);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
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


        //progress 视图
        progressTitle = view.findViewById(R.id.tv_update_dialog_progress_title);
        progressBar = view.findViewById(R.id.pb_update_dialog_progress_pb);
        progress = view.findViewById(R.id.tv_update_dialog_progress);
        StateButton cancleBtn = view.findViewById(R.id.btn_update_dialog_progress_cancle);
        progressConfimBtn = view.findViewById(R.id.btn_update_dialog_progress_confirm);


        progressTitle.setText("" + this.title);

        progressConfimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.confirmClick();
                }
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.closeClick();
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

    /**
     * 显示contentview
     */
    public void showContentview(){
        if(contentAllLin == null){
            return;
        }
        contentAllLin.setVisibility(View.VISIBLE);
        progressAllLin.setVisibility(View.GONE);
    }
    /**
     * 显示progressview
     */
    public void showProgressview(){
        if(progressAllLin == null){
            return;
        }
        progressAllLin.setVisibility(View.VISIBLE);
        contentAllLin.setVisibility(View.GONE);
    }

    @Override
    public void dismissSuspend() {
        this.dismiss();
    }

    MyCustomBaseDialogListener listener;
    public void setMyCustomDialogListener(MyCustomBaseDialogListener listener){
        this.listener = listener;
    }
    public abstract static class MyCustomBaseDialogListener implements CustomBaseDialogListener{
        public abstract void confirmClick();
    }

    /**
     * @return
     */
    public ProgressBar getProgressBar(){
        return this.progressBar;
    }

    public void setProgress(int progress){
        if(progressBar != null){
            this.progressBar.setProgress(progress);
        }
    }

    public void setConfirmBtnAble(boolean confirmBtnAble){
        if(progressConfimBtn != null){
            this.progressConfimBtn.setEnabled(confirmBtnAble);
        }
    }

    public void setProgressTitle(String title){
        if(progressTitle != null){
            progressTitle.setText(title);
        }
    }

    public void setProgressText(String text){
        if(progress != null){
            progress.setText(text);
        }
    }

    public void setProgressDrawable(Drawable drawable){
        if(progressBar != null){
            progressBar.setProgressDrawable(drawable);
        }
    }
}
