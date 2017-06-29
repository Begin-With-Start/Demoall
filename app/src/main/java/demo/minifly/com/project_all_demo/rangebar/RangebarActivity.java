package demo.minifly.com.project_all_demo.rangebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import demo.minifly.com.R;


public class RangebarActivity extends AppCompatActivity {

	private RangeBar mRangeBar;
	private TextView mTvShowProgress;
	private RangeBar mRangeBar2;
	private Gallery mGallery;
	private String[] data = {"不设置", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

	private List<String> mData;
	private RangebarGalleryAdapter mGalleryAdapter;
	private TextView mTvTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_bar);
		mRangeBar = (RangeBar) findViewById(R.id.rangebar);
		mGallery = (Gallery) findViewById(R.id.gallery);
		mRangeBar2 = (RangeBar) findViewById(R.id.rangebar2);
		mTvShowProgress = (TextView) findViewById(R.id.tv_rangebar_progress);
		mTvTime = (TextView) findViewById(R.id.tv_time);

		mRangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
			@Override
			public void onProgressChanged(int progress) {
				mTvShowProgress.setText(progress + "");
				mRangeBar2.setProgress(progress);
			}
		});

		mData = new ArrayList<>();
		Collections.addAll(mData, data);
		mGalleryAdapter = new RangebarGalleryAdapter(2, mData);
		mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				mGalleryAdapter.setSelectPosition(position);
				if (position == 0) {
					mTvTime.setText(data[position]);
				} else {
					mTvTime.setText("≤" + data[position]+"小时");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		mGallery.setAdapter(mGalleryAdapter);
	}
}
