package demo.minifly.com.fuction_demo.ActivityAnimation;

import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.minifly.com.R;


/**
 * Fragment的网格适配器
 * <p/>
 * Created by minifly on 17/03/10.
 */
public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.MyGridViewHolder> {

    private ArrayList<Pair<Integer, Integer>> mData; // 名字和图片
    private MyViewOnClickListener mListener; // 点击事件

    public MyGridAdapter(ArrayList<Pair<Integer, Integer>> data,
                         MyViewOnClickListener listener) {
        mData = data;
        mListener = listener;
    }

    @Override
    public MyGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_grid_item, parent, false);
        return new MyGridViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyGridViewHolder holder, final int position) {
        holder.getTextView().setText(mData.get(position).first);
        holder.getImageView().setImageResource(mData.get(position).second);

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.getImageView(), String.valueOf(position) + "_image");

        holder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickedView(holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyGridViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public MyGridViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.grid_image);
            mTextView = (TextView) itemView.findViewById(R.id.grid_text);
        }

        public ImageView getImageView() {
            return mImageView;
        }

        public TextView getTextView() {
            return mTextView;
        }
    }
}
