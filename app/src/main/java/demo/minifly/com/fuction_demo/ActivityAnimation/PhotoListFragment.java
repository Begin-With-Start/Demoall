package demo.minifly.com.fuction_demo.ActivityAnimation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import demo.minifly.com.R;


public class PhotoListFragment extends Fragment {

    RecyclerView mRvRecycler;
    private ArrayList<Pair<Integer, Integer>> mData;

    public PhotoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_list, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
        mRvRecycler = (RecyclerView) view.findViewById(R.id.grid_rv_recycler);
        mRvRecycler.setAdapter(new MyGridAdapter(mData, mListener));
        mRvRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 一行两个
    }
    // 初始化数据
    private void initData() {
        mData = new ArrayList<>();

        mData.add(Pair.create(R.string.taeyeon, R.drawable.taeyeon));
        mData.add(Pair.create(R.string.jessica, R.drawable.jessica));
        mData.add(Pair.create(R.string.sunny, R.drawable.sunny));
        mData.add(Pair.create(R.string.tiffany, R.drawable.tiffany));
        mData.add(Pair.create(R.string.yuri, R.drawable.yuri));
        mData.add(Pair.create(R.string.yoona, R.drawable.yoona));
    }
    /**
     * 点击事件, 转换元素的动画,
     * 关键addSharedElement(holder.getImageView(), getResources().getString(R.string.image_transition))
     * 绑定ViewHolder的图片和DetailFragment的跳转.
     */
    private MyViewOnClickListener mListener = new MyViewOnClickListener() {
        @Override
        public void onClickedView(MyGridAdapter.MyGridViewHolder holder, int position) {
            PhotoDetailFragment detailFragment = PhotoDetailFragment.newInstance(position);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                detailFragment.setSharedElementEnterTransition(new DetailTransition());
                setExitTransition(new Fade());
                detailFragment.setEnterTransition(new Fade());
                detailFragment.setSharedElementReturnTransition(new DetailTransition());
            }

            getActivity().getSupportFragmentManager().beginTransaction()
                    .addSharedElement(holder.getImageView(), getResources().getString(R.string.image_transition))
                    .replace(R.id.fragment_id_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
