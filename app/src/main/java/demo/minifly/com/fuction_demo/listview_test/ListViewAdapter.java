package demo.minifly.com.fuction_demo.listview_test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:46
 */
public class ListViewAdapter extends BaseAdapter {
    private List<ListViewBean> list;
    private Context context;
    private LayoutInflater  mInflater;

    public ListViewAdapter(List<ListViewBean> list, Context context){
        this.list =list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ListViewBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ListViewBean bean = getItem(position);
        //观察convertView随ListView滚动情况
        Log.v("MyListViewBase", "getView " + position + " " + convertView);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_listview_layout,null);
            holder = new ViewHolder();
            /**得到各个控件的对象*/
            holder.name = (TextView) convertView.findViewById(R.id.list_name_txt);
            holder.age = (TextView) convertView.findViewById(R.id.list_age_txt);
            holder.content = (TextView) convertView.findViewById(R.id.list_content_txt);
            holder.image= (ImageView)convertView.findViewById(R.id.list_image_img);
            convertView.setTag(holder);//绑定ViewHolder对象
        }
        else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        /**设置TextView显示的内容，即我们存放在动态数组中的数据*/
        holder.name.setText(bean.getName());
        holder.age.setText(bean.getAge());
        holder.content.setText(bean.getContent());

//        holder.image
//        http://img3.duitang.com/uploads/blog/201409/20/20140920153536_8WsvT.jpeg

        return convertView;

    }

    class ViewHolder{
        TextView name,age,content;
        ImageView image;
    }
}
