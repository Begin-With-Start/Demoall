package demo.minifly.com.project_all_demo.photo_select_upload.media;

import android.content.Context;

import java.util.List;

import demo.minifly.com.R;

/**
 * Created by Jackie on 2017/6/19.
 */

public class DirectoryAdapter extends CommonAdapter<ImageFolder> {
    private Context mContext;

    public DirectoryAdapter(Context context, int layoutId, List<ImageFolder> list) {
        super(context, layoutId, list);

        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder holder, ImageFolder imageFolder) {
        holder.loadImage(mContext, R.id.directory_item_image, imageFolder.getFirstImagePath()).setText(R.id.directory_item_name, imageFolder.getName()).setText(R.id.directory_item_count, imageFolder.getCount() + "");
    }
}
