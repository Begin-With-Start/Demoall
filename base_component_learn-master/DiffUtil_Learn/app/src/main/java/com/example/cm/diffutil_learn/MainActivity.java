package com.example.cm.diffutil_learn;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<TestBean> mDatas;
    private List<TestBean> mNewDatas;//增加一个变量暂存newList
    private RecyclerView mRv;
    private DiffAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DiffAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(new TestBean("1", "111"));
        mDatas.add(new TestBean("2", "222"));
        mDatas.add(new TestBean("3", "333"));
        mDatas.add(new TestBean("4", "444"));
        mDatas.add(new TestBean("5", "555"));
    }

    /**
     * 模拟刷新操作
     *
     * @param view
     */
    public void onRefresh(View view) {
        try {
            mNewDatas = new ArrayList<>();
            for (TestBean bean : mDatas) {
                mNewDatas.add(bean.clone());//clone一遍旧数据 ，模拟刷新操作
            }
            mNewDatas.add(new TestBean("赵子龙", "帅", R.drawable.pic6));//模拟新增数据
            mNewDatas.get(0).setDesc("Android+");
            mNewDatas.get(0).setPic(R.drawable.pic7); //模拟修改数据
            TestBean testBean = mNewDatas.get(1); //模拟数据位移
            mNewDatas.remove(testBean);
            mNewDatas.add(testBean);


            /**
             *  新宠
             *  利用DiffUtil.calculateDiff()方法，传入一个规则DiffUtil.Callback对象，和是否检测移动item的 boolean变量，得到DiffUtil.DiffResult 的对象
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //放在子线程中计算DiffResult
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
                    Message message = mHandler.obtainMessage(H_CODE_UPDATE);
                    message.obj = diffResult;//obj存放DiffResult
                    message.sendToTarget();
                }
            }).start();

            /**
             *  以前普通青年的我们只能这样，现在我们是文艺青年了，有新宠了
             */
//            mAdapter.setDatas(mNewDatas);
            mAdapter.notifyItemChanged(0);
//            mAdapter.notifyItemChanged(0, "Android+");
            mAdapter.notifyDataSetChanged();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static final int H_CODE_UPDATE = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case H_CODE_UPDATE:
                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo()方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(mAdapter);

                    //这种方法可以fix add 0 不滑动
                    /*diffResult.dispatchUpdatesTo(new ListUpdateCallback() {
                        @Override
                        public void onInserted(int position, int count) {
                            mAdapter.notifyItemRangeInserted(position, count);
                            if (position==0){
                                mRv.scrollToPosition(0);
                            }
                        }

                        @Override
                        public void onRemoved(int position, int count) {
                            mAdapter.notifyItemRangeRemoved(position, count);
                        }

                        @Override
                        public void onMoved(int fromPosition, int toPosition) {
                            mAdapter.notifyItemMoved(fromPosition, toPosition);
                        }

                        @Override
                        public void onChanged(int position, int count, Object payload) {
                            mAdapter.notifyItemRangeChanged(position, count, payload);
                        }
                    });*/

                    //别忘了将新数据给Adapter
                    mDatas = mNewDatas;
                    mAdapter.setDatas(mDatas);
                    break;
            }
        }
    };

}
