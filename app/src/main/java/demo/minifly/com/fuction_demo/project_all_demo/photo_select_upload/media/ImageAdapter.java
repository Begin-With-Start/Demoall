package demo.minifly.com.fuction_demo.project_all_demo.photo_select_upload.media;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.ScreenUtils;

public class ImageAdapter extends CommonAdapter<String> {
    private Context mContext;

    //保存选中的图片的绝对路径
    final static Set<String> mSelectedImageSet = new HashSet<>();

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public ImageAdapter(Context context, List<String> imagePathList){
        super(context, R.layout.item_image, imagePathList);

        this.mContext = context;
    }

    @Override
    public void convert(final ViewHolder holder, final String path) {
        final ImageView imageView = holder.getView(R.id.item_image);

        //重置状态
        holder.setImageResource(R.id.item_image, R.drawable.picture_no).setImageResource(R.id.item_selected, R.drawable.picture_unselected);
        imageView.setColorFilter(null);

        //宽度设置为屏幕的1/3(GridView有3列)
        ViewGroup.LayoutParams params =  imageView.getLayoutParams();
        params.width = params.height = (ScreenUtils.getScreenWidth(mContext)) / 3;
        imageView.setLayoutParams(params);

        holder.loadImage(mContext, R.id.item_image, path);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //已经选中
                if (mSelectedImageSet.contains(path)) {
                    //清除选中状态
                    mSelectedImageSet.remove(path);
                    imageView.setColorFilter(null);
                    holder.setImageResource(R.id.item_selected, R.drawable.picture_unselected);
                } else {
                    mSelectedImageSet.add(path);
                    imageView.setColorFilter(mContext.getResources().getColor(R.color.common_color_upload_fail_99000000));
                    holder.setImageResource(R.id.item_selected, R.drawable.picture_selected);
                }

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mSelectedImageSet);
                }
            }
        });

        if (mSelectedImageSet.contains(path)) {
            imageView.setColorFilter(mContext.getResources().getColor(R.color.common_color_upload_fail_99000000));
            holder.setImageResource(R.id.item_selected, R.drawable.picture_selected);
        }
    }
}