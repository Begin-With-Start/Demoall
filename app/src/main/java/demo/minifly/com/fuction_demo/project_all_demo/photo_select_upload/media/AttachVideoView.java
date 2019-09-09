package demo.minifly.com.fuction_demo.project_all_demo.photo_select_upload.media;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import demo.minifly.com.R;


/**
 * Created by Jackie on 2017/6/26.
 * 添加视频
 */

public class AttachVideoView extends LinearLayout {
    private OnAttachVideoListener mOnAttachVideoListener;

    public AttachVideoView(Context context) {
        this(context, null);
    }

    public AttachVideoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttachVideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    public interface OnAttachVideoListener {
        void onAttachVideo();
    }

    public void setOnAttachVideoListener(OnAttachVideoListener onAttachVideoListener) {
        this.mOnAttachVideoListener = onAttachVideoListener;
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.attach_video_view, null);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = context.getResources().getDimensionPixelSize(R.dimen.common_dimen_dp90);
        params.height = context.getResources().getDimensionPixelSize(R.dimen.common_dimen_dp90);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.attach_video_view);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAttachVideoListener != null) {
                    mOnAttachVideoListener.onAttachVideo();
                }
            }
        });

        addView(view, params);
    }
}
