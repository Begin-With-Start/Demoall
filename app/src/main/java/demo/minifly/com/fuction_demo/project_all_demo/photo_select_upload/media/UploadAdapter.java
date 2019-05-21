package demo.minifly.com.fuction_demo.project_all_demo.photo_select_upload.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.ScreenUtils;

public class UploadAdapter extends BaseAdapter {
    private Context mContext;
    private int mPadding;

    private List<UploadType> mSelectedUploadTypeList;
    private LayoutInflater mInflater;

    private OnItemUploadListener mOnItemUploadListener;
    private OnItemPlusListener mOnItemPlusListener;

    public static final int UPLOAD_TYPE_IMAGE = 1;
    public static final int UPLOAD_TYPE_PLUS = 2;

    public void setOnItemUploadListener(OnItemUploadListener onItemUploadListener) {
        this.mOnItemUploadListener = onItemUploadListener;
    }

    public void setOnItemPlusListener(OnItemPlusListener onItemPlusListener) {
        this.mOnItemPlusListener = onItemPlusListener;
    }

    public UploadAdapter(Context context, int padding, List<UploadType> selectedUploadTypeList){
        this.mContext = context;
        this.mPadding = padding;

        this.mSelectedUploadTypeList = selectedUploadTypeList;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return mSelectedUploadTypeList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return mSelectedUploadTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSelectedUploadTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        final UploadType uploadType = mSelectedUploadTypeList.get(position);

        if (convertView == null) {
            holder = new ViewHolder();

            switch (getItemViewType(position)) {
                case UPLOAD_TYPE_IMAGE:
                    convertView = mInflater.inflate(R.layout.item_upload_image_view, null);

                    holder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
                    holder.progressBar = (ProgressBar) convertView.findViewById(R.id.item_progress);
                    holder.fail = (TextView) convertView.findViewById(R.id.item_fail);

                    break;
                case UPLOAD_TYPE_PLUS:
                    convertView = mInflater.inflate(R.layout.item_upload_plus_view, null);

                    holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.layout_upload_plus);
                    break;
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (holder.imageView != null) {
            Glide.with(mContext)
                    .load(new File(uploadType.getPath()))
                    .placeholder(R.drawable.picture_no)
                    .into(holder.imageView);
        }

        if (holder.progressBar != null) {
            if (uploadType.isFinish()) {
                holder.progressBar.setVisibility(View.GONE);
            } else {
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.progressBar.setProgress(uploadType.getProgress());
            }
        }

        if (holder.fail != null && holder.imageView != null) {
            if (uploadType.isSuccess()) {
                //上传成功
                holder.fail.setVisibility(View.GONE);
                holder.imageView.setColorFilter(null);
            } else {
                //上传失败
                holder.fail.setVisibility(View.VISIBLE);
                holder.imageView.setColorFilter(mContext.getResources().getColor(R.color.common_color_upload_fail_99000000));
            }
        }

        if (holder.fail != null) {
            holder.fail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemUploadListener != null) {
                        mOnItemUploadListener.onItemUpload(uploadType.getPath());
                    }
                }
            });
        }

        if (holder.relativeLayout != null) {
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemPlusListener != null) {
                        mOnItemPlusListener.onItemPlus();
                    }
                }
            });
        }

        AbsListView.LayoutParams params =  new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = params.height = (ScreenUtils.getScreenWidth(mContext) - mPadding) / 3;
        convertView.setLayoutParams(params);

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        TextView fail;

        RelativeLayout relativeLayout;
    }
}