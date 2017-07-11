package demo.minifly.com.project_all_demo.photo_select_upload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import demo.minifly.com.R;
import demo.minifly.com.project_all_demo.photo_select_upload.media.OnItemPlusListener;
import demo.minifly.com.project_all_demo.photo_select_upload.media.OnItemUploadListener;
import demo.minifly.com.project_all_demo.photo_select_upload.media.UploadAdapter;
import demo.minifly.com.project_all_demo.photo_select_upload.media.UploadType;
import demo.minifly.com.request_demo.NoHttpUtils;

import static demo.minifly.com.project_all_demo.photo_select_upload.media.UploadAdapter.UPLOAD_TYPE_IMAGE;
import static demo.minifly.com.project_all_demo.photo_select_upload.media.UploadAdapter.UPLOAD_TYPE_PLUS;


public class UploadConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mTopReturn;
    private GridView mGridView;
    private UploadAdapter mAdapter;

    private List<UploadType> mSelectedUploadTypeList;

    public static String KEY_UPLOAD_LIST = "key_upload_list";
    /**
     * 上传图片接口
     */
    public static final String URL_UPLOAD_FILE = "gateway/uploads?type=avatar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_confirm);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mTopReturn = (ImageView) findViewById(R.id.top_return);
        mGridView = (GridView) findViewById(R.id.grid_view);

        mSelectedUploadTypeList = new ArrayList<>();
    }

    private void initData() {
        List<String> selectedImageList = (List<String>) getIntent().getSerializableExtra(KEY_UPLOAD_LIST);

        buildUploadType(selectedImageList);

        /**
         * 对GridView进行测量，否则获取到的getHorizontalSpacing为0，getNumColumns为-1
         * 获取测试mode
         */
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        //measure一次才能获取到属性值
        mGridView.measure(width, height);

        int padding = mGridView.getPaddingLeft() + mGridView.getPaddingRight() + (mGridView.getHorizontalSpacing() * (mGridView.getNumColumns() - 1));
        mAdapter = new UploadAdapter(this, padding, mSelectedUploadTypeList);

        mGridView.setAdapter(mAdapter);

        //上传图片
        uploadFile(selectedImageList);
    }

    private void initEvent() {
        mTopReturn.setOnClickListener(this);

        mAdapter.setOnItemUploadListener(new OnItemUploadListener() {
            @Override
            public void onItemUpload(String path) {
                //重新上传
                List<String> selectedImageList = new ArrayList<>();
                selectedImageList.add(path);
                uploadFile(selectedImageList);
            }
        });

        mAdapter.setOnItemPlusListener(new OnItemPlusListener() {
            @Override
            public void onItemPlus() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.top_return) {
            finish();
        }
    }

    private void buildUploadType(List<String> selectedImageList) {
        for (String path : selectedImageList) {
            UploadType uploadType = new UploadType();
            uploadType.setPath(path);
            uploadType.setType(UPLOAD_TYPE_IMAGE);
            uploadType.setProgress(0);
            uploadType.setSuccess(true);   //默认都是能上传成功
            uploadType.setFinish(false);

            mSelectedUploadTypeList.add(uploadType);
        }

        UploadType uploadType = new UploadType();
        uploadType.setPath("");
        uploadType.setType(UPLOAD_TYPE_PLUS);
        mSelectedUploadTypeList.add(uploadType);
    }

    private void uploadFile(List<String> selectedImageList) {
        NoHttpUtils.httpUploadFileRequest(URL_UPLOAD_FILE, selectedImageList, new NoHttpUtils.OnFileUploadListener() {
            @Override
            public void onStart(int position) {

            }

            @Override
            public void onCancel(int position) {

            }

            @Override
            public void onProgress(int position, int progress) {
                UploadType uploadType = mSelectedUploadTypeList.get(position);
                uploadType.setProgress(progress);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFinish(int position) {
                UploadType uploadType = mSelectedUploadTypeList.get(position);
                uploadType.setFinish(true);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int position, Exception exception) {
                UploadType uploadType = mSelectedUploadTypeList.get(position);
                uploadType.setSuccess(false);

                mAdapter.notifyDataSetChanged();
            }
        }, new NoHttpUtils.OnFileUploadResultListener() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                //所有图片上传成功
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }
        });
    }
}
