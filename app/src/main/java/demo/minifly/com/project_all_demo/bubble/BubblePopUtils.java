package demo.minifly.com.project_all_demo.bubble;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.minifly.com.R;
import demo.minifly.com.project_all_demo.popupwindow.EasyPopupWindow;


/**
 * 泡泡弹框工具类
 * Created by ShineF on 2017/6/28 0028.
 */

public class BubblePopUtils {

    /**
     * 箭头朝上
     *
     * @param view    相对的控件
     * @param context 上下文
     */
    public static void showBubbleUp(View view, Context context, String text) {
        View bubbleView = View.inflate(context, R.layout.view_bubble_pop_top, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tv_content);
        tvContent.setText(text);
        EasyPopupWindow easyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(bubbleView)
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchHide(true)
                .create();
        easyPopupWindow.showAsDropDown(view);
    }

    /**
     * 箭头朝左
     *
     * @param view    相对的控件
     * @param context 上下文
     */
    public static void showBubbleLeft(View view, Context context, String text) {
        View bubbleView = View.inflate(context, R.layout.view_bubble_pop_left, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tv_content);
        tvContent.setText(text);
        EasyPopupWindow easyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(bubbleView)
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchHide(true)
                .create();
        int[] location = new int[2];
        view.getLocationInWindow(location);
        easyPopupWindow.showAtLocation(view, Gravity.LEFT, location[0] + view.getWidth(), 0);
    }

    /**
     * 箭头朝右
     *
     * @param view    相对的控件
     * @param context 上下文
     */
    public static void showBubbleRight(View view, Context context, String text) {
        View bubbleView = View.inflate(context, R.layout.view_bubble_pop_right, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tv_content);
        tvContent.setText(text);
        EasyPopupWindow easyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(bubbleView)
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchHide(true)
                .create();
        easyPopupWindow.showAtLocation(view, Gravity.RIGHT, view.getWidth(), 0);

    }

    /**
     * 箭头朝下
     *
     * @param view    相对的控件
     * @param context 上下文
     * @param text    内容
     */
    public static void showBubbleBottom(View view, Context context, String text) {
        View bubbleView = View.inflate(context, R.layout.view_bubble_pop_bottom, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tv_content);
        tvContent.setText(text);
        EasyPopupWindow easyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(bubbleView)
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchHide(true)
                .create();
        int[] location = new int[2];
        view.getLocationInWindow(location);
        easyPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, view.getHeight());
    }

    /**
     * 自定义箭头朝下
     *
     * @param view    相对的控件
     * @param context 上下文
     * @param gravity 针对父布局的位置
     * @param x       距离X轴距离
     * @param y       距离Y轴距离
     */
    public static void showBubbleBottom(View view, Context context, int gravity, int x, int y, String text) {
        View bubbleView = View.inflate(context, R.layout.view_bubble_pop_bottom, null);
        TextView tvContent = (TextView) bubbleView.findViewById(R.id.tv_content);
        tvContent.setText(text);
        EasyPopupWindow easyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(bubbleView)
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchHide(true)
                .create();
        int[] location = new int[2];
        view.getLocationInWindow(location);
        easyPopupWindow.showAtLocation(view, gravity, x, y);
    }
}
