package demo.minifly.com.project_all_demo.photo_select_upload.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.minifly.com.R;


/**
 * Created by Jackie on 2017/6/26.
 * 播放视频
 */

public class PlayVideoView extends LinearLayout {
    private ImageView mImageView;

    private OnPlayVideoListener mOnPlayVideoListener;

    public PlayVideoView(Context context) {
        this(context, null);
    }

    public PlayVideoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayVideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    public interface OnPlayVideoListener {
        void onPlayVideo();
    }

    public void setOnPlayVideoListener(OnPlayVideoListener onPlayVideoListener) {
        this.mOnPlayVideoListener = onPlayVideoListener;
    }

    public void setVideoUrl(String videoUrl) {
        Bitmap thumbnailBitmap = ThumbnailUtils.createNetVideoThumbnail(videoUrl);
        mImageView.setImageBitmap(thumbnailBitmap);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.play_video_view, null);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = context.getResources().getDimensionPixelSize(R.dimen.common_dimen_dp90);
        params.height = context.getResources().getDimensionPixelSize(R.dimen.common_dimen_dp90);

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.play_video_view);
        mImageView = (ImageView) view.findViewById(R.id.video_thumbnail);

        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPlayVideoListener != null) {
                    mOnPlayVideoListener.onPlayVideo();
                }
            }
        });

        addView(view, params);
    }
}
