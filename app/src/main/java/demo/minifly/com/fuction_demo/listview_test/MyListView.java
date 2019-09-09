package demo.minifly.com.fuction_demo.listview_test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者：minifly on 2016/11/24 14:20
 */
public class MyListView extends ListView{
    private int mMaxOverDistance;

    public MyListView(Context context) {
        super(context);
        mMaxOverDistance = ConvertUtils.dip2px(context,100f);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMaxOverDistance = ConvertUtils.dip2px(context,100f);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMaxOverDistance = ConvertUtils.dip2px(context,100f);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //mMaxOverDistance该属性就是限定listview的弹性的，google默认没有给这个的值，只会出现半月型的一个滑动到底的提示。
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
        //这种方式的超过界限，会出现一个问题：当滑动的距离没有到最大的距离的时候，会发生弹不回去的问题。
        //这种实现方式并不是很好。

    }
}
