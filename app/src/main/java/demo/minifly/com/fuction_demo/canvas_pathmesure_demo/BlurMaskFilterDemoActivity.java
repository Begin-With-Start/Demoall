package demo.minifly.com.fuction_demo.canvas_pathmesure_demo;

import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.minifly.com.R;

public class BlurMaskFilterDemoActivity extends AppCompatActivity {

    private BlurMaskFilterView mMaskFilterId1;
    private BlurMaskFilterView mMaskFilterId2;
    private BlurMaskFilterView mMaskFilterId3;
    private BlurMaskFilterView mMaskFilterId4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_mask_filter_demo);
        initView();
    }

    private void initView() {
        mMaskFilterId1 = (BlurMaskFilterView) findViewById(R.id.mask_filter_id1);
        mMaskFilterId2 = (BlurMaskFilterView) findViewById(R.id.mask_filter_id2);
        mMaskFilterId3 = (BlurMaskFilterView) findViewById(R.id.mask_filter_id3);
        mMaskFilterId4 = (BlurMaskFilterView) findViewById(R.id.mask_filter_id4);

        mMaskFilterId1.setMaskFilter(BlurMaskFilter.Blur.OUTER);
        mMaskFilterId2.setMaskFilter(BlurMaskFilter.Blur.NORMAL);
        mMaskFilterId3.setMaskFilter(BlurMaskFilter.Blur.INNER);
        mMaskFilterId4.setMaskFilter(BlurMaskFilter.Blur.SOLID);

    }
}
