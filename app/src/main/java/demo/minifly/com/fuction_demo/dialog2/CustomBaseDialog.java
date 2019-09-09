package demo.minifly.com.fuction_demo.dialog2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leoao.sdk.common.utils.LogUtils;

import demo.minifly.com.R;


/**
 * Created by ${minifly} on 2018/5/18.
 * desc: 对话框的基类
 */

public abstract class CustomBaseDialog extends Dialog implements SuspendInterface {
    protected Context mContext;
    //内容布局
    protected LinearLayout mDialogBaseContentRel;
    //根布局
    protected LinearLayout mDialogBaseCustomRoot;
    //关闭图标
    protected ImageView closeImg;

    public CustomBaseDialog(@NonNull Context context) {
        super( context,0 );
        this.mContext = context;
    }

    public LinearLayout getContentRel(){
        return mDialogBaseContentRel;
    }
    /**
     * 传type 过来
     * @param context 上下文
     * @param styleId    弹窗动画类型
     */
    @SuppressLint("ResourceType")
    public CustomBaseDialog(@NonNull Context context, @StyleableRes int styleId) {
        super(context, R.style.commonui_dialog);
        this.mContext = context;
        if (!(context instanceof Activity)) {
            LogUtils.i("AAA>>>", "context 不是activity 有可能在 返回 991 时候弹窗部分功能失效,如关闭当前界面,请知晓!");
        }
        if(styleId > 0){
            getWindow().setWindowAnimations(styleId);
        }else {
            getWindow().setWindowAnimations(R.style.commonbns_anim_inout_dialog_new);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.dialog_base_custom);
        //设置透明度
        setBgAlpha(0.7f);
        mDialogBaseContentRel = findViewById(R.id.dialog_base_content_rel);
        mDialogBaseCustomRoot = findViewById(R.id.dialog_base_custom_root);
        closeImg = findViewById(R.id.img_dialog_base_close);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomBaseDialog.this.dismiss();
                if(listener != null){
                    listener.closeClick();
                }
            }
        });

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


    public void setCloseImgVisible(int visible){
        if(closeImg != null){
            closeImg.setVisibility(visible);
        }
    }

    /**
     * 是否可取消
     *
     * @param cancleble 布尔类型 ， 是否可以取消对话框
     */
    public void setCancleble(Boolean cancleble) {
        this.setCancelable(cancleble);
    }

    /**
     * @param var 透明度
     */
    public void setBgAlpha(float var) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = var;
        getWindow().setAttributes(lp);
    }

    /**
     * @param color 背景颜色
     */
    public void setBgColor(String color) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 1.0f;
        getWindow().setAttributes(lp);
        findViewById(R.id.fl_bg).setBackgroundColor(Color.parseColor(color));
    }

    protected CustomBaseDialogListener listener;
    /**
     * 设置对话框点击事件监听
     * @param listener
     */
    public void setDialogListener(CustomBaseDialogListener listener){
        this.listener = listener;
    }

    /**
     * 设置内容区域的左右间隔
     */
    public void setContentMarginLeftRight(int leftMargin , int rightMargin){
        if(mDialogBaseContentRel == null){
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mDialogBaseContentRel.getLayoutParams();
        layoutParams.leftMargin = leftMargin;
        layoutParams.rightMargin = rightMargin;
        mDialogBaseContentRel.setLayoutParams(layoutParams);
    }

    /**
     * 添加view到content中
     * @param resourseId
     */
    public void setContentView(@LayoutRes int resourseId){
        if(mDialogBaseContentRel != null && mContext != null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            //直接添加到basecontent中
            inflater.inflate(resourseId,mDialogBaseContentRel);
        }
    }

    public void setContentView(View view){
        if(mDialogBaseContentRel != null){
            mDialogBaseContentRel.removeAllViews();
            mDialogBaseContentRel.addView(view);
        }
    }
}
