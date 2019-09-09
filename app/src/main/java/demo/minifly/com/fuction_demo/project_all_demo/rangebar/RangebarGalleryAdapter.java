package demo.minifly.com.fuction_demo.project_all_demo.rangebar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.minifly.com.R;

/**
 * @author : mundane
 * @time : 2017/6/23 9:56
 * @description :
 * @file : GalleryAdapter.java
 */

public class RangebarGalleryAdapter extends BaseAdapter {
	private int selectPosition;
	private List<String> mData;


	public RangebarGalleryAdapter(int selectPosition, List<String> data) {
		this.selectPosition = selectPosition;
		mData = data;
	}

	public void setSelectPosition(int selectPosition) {
		this.selectPosition = selectPosition;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_line_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.textView.setText(mData.get(position));
		if (selectPosition == position) {
//			holder.textView.setActivated(true);
//			holder.centerLine.setBackgroundColor(parent.getResources().getColor(R.color.common_color_c1_0971ce));
//			holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		} else {
//			holder.textView.setActivated(false);
//			holder.centerLine.setBackgroundColor(parent.getResources().getColor(R.color.common_color_c9_999999));
//			holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		}

		return convertView;
	}


	static class ViewHolder {
		TextView textView ;
		View centerLine;

		public ViewHolder(View convertView) {
			textView = (TextView) convertView.findViewById(R.id.tv_numbers);
			centerLine = convertView.findViewById(R.id.tv_line);
		}
	}
}
