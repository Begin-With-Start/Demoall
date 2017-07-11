package demo.minifly.com.project_all_demo.photo_select_upload;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import demo.minifly.com.R;
import demo.minifly.com.permission_demo.PermissionUtils;
import demo.minifly.com.project_all_demo.photo_select_upload.media.DirectoryAdapter;
import demo.minifly.com.project_all_demo.photo_select_upload.media.ImageAdapter;
import demo.minifly.com.project_all_demo.photo_select_upload.media.ImageFolder;
import demo.minifly.com.project_all_demo.photo_select_upload.media.OnItemClickListener;
import demo.minifly.com.project_all_demo.popupwindow.EasyPopupWindow;
import demo.minifly.com.utils.ThreadUtils;

public class UploadImageActivity extends AppCompatActivity implements PermissionUtils.PermissionCallbacks, View.OnClickListener, EasyPopupWindow.ChildClickListener {
    private TextView mTopSwitch;
    private TextView mTopCancel;

    private GridView mGridView;
    private TextView mSelectCount;
    private TextView mSelectConfirm;
    private ImageAdapter mImageAdapter;

    private ProgressDialog mProgressDialog;
    private EasyPopupWindow mEasyPopupWindow;

    private List<String> mImagePathList;
    private List<ImageFolder> mImageFolderList;
    private Set<String> mSelectedImageSet;
    private List<String> mSelectedImageList;

    private static final int MESSAGE_SCAN_SUCCEED = 0x110;
    public static final String KEY_UPLOAD_LIST = "key_upload_list";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgressDialog.dismiss();

            if (mImagePathList.size() == 0) {
                Toast.makeText(UploadImageActivity.this, R.string.scan_no_photo, Toast.LENGTH_SHORT).show();
                return;
            }

            mImageAdapter = new ImageAdapter(UploadImageActivity.this, mImagePathList);
            mGridView.setAdapter(mImageAdapter);

            mImageAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(Set<String> selectedImageSet) {
                    mSelectedImageSet = selectedImageSet;

                    mSelectCount.setText(String.format(getResources().getString(R.string.select_photo_count), selectedImageSet.size()));
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        initView();
        initEvent();
    }

    private void initView() {
        mTopSwitch = (TextView) findViewById(R.id.top_switch);
        mTopCancel = (TextView) findViewById(R.id.top_cancel);

        mGridView = (GridView) findViewById(R.id.grid_view);
        mSelectCount= (TextView) findViewById(R.id.select_count);
        mSelectCount.setText(String.format(getResources().getString(R.string.select_photo_count), 0));

        mSelectConfirm = (TextView) findViewById(R.id.select_confirm);

        mImagePathList = new ArrayList<>();
        mImageFolderList = new ArrayList<>();
        mSelectedImageSet = new HashSet<>();
        mSelectedImageList = new ArrayList<>();

        PermissionUtils.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> permissionList) {
        //当requestCode相同时候，permissions.size == 请求的权限数时候，认为权限请求成功
        if(requestCode == 1 && permissionList.size() == 1) {
            initData();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> permissionList) {
        //调用了一次拒绝权限认为失败
        if(requestCode == 1){
            Toast.makeText(this,"拒绝了权限" + permissionList.get(0), Toast.LENGTH_LONG).show();
            PermissionUtils.permissionDialog(this, permissionList, null);
        }
    }

    /**
     * 利用ContentProvider扫描手机中的所有图片
     */
    private void initData() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, R.string.external_storage_is_disable, Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.loading));

        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Cursor cursor = getContentResolver().query(uri, null, MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{ "image/jpeg", "image/png" }, MediaStore.Images.Media.DATE_MODIFIED);

                Set<String> directorySet = new HashSet<>();

                while(cursor.moveToNext()) {
                    //获取图片的路径
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    mImagePathList.add(path);

                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }

                    String parentPath = parentFile.getAbsolutePath();

                    ImageFolder imageFolder;
                    if (directorySet.contains(parentPath)) {
                        continue;
                    } else {
                        directorySet.add(parentPath);

                        imageFolder = new ImageFolder();
                        imageFolder.setDirectory(parentPath);
                        imageFolder.setFirstImagePath(path);
                    }

                    if (parentFile.list() == null) {
                        continue;
                    } else {
                        int imageCount = parentFile.list(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String name) {
                                if (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg")) {
                                    return true;
                                }

                                return false;
                            }
                        }).length;

                        imageFolder.setCount(imageCount);
                        mImageFolderList.add(imageFolder);
                    }
                }

                cursor.close();

                mHandler.sendEmptyMessage(MESSAGE_SCAN_SUCCEED);
            }
        });
    }

    private void initEvent() {
        mTopSwitch.setOnClickListener(this);
        mTopCancel.setOnClickListener(this);
        mSelectConfirm.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSelectedImageSet.clear();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.top_switch) {
            Drawable drawable = getResources().getDrawable(R.drawable.directory_arrow_up);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTopSwitch.setCompoundDrawables(null, null, drawable, null);

            mEasyPopupWindow = new EasyPopupWindow.Builder(this)
                    .setView(R.layout.upload_image_popupwindow)
                    .setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setAnim(R.style.anim_menu_top_bar)
                    .setOutsideTouchHide(true)
                    .setOnChildClickListener(this)
                    .create();

            mEasyPopupWindow.showAsDropDown(mTopSwitch, 0,  0);

            mEasyPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Drawable drawable = getResources().getDrawable(R.drawable.directory_arrow_down);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTopSwitch.setCompoundDrawables(null, null, drawable, null);
                }
            });
        } else if (id == R.id.top_cancel) {
            finish();
        } else if (id == R.id.select_confirm) {
            if (mSelectedImageSet == null || mSelectedImageSet.size() == 0) {
                Toast.makeText(this, R.string.pls_select_photo, Toast.LENGTH_SHORT).show();
                return;
            } else {
                mSelectedImageList.clear();

                for (String selectedImagePath : mSelectedImageSet) {
                    mSelectedImageList.add(selectedImagePath);
                }
            }

            Intent intent = new Intent(this, UploadConfirmActivity.class);
            intent.putExtra(KEY_UPLOAD_LIST, (Serializable) mSelectedImageList);
            startActivity(intent);
        }
    }
    @Override
    public void getChildView(int layoutId, View view) {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        DirectoryAdapter adapter = new DirectoryAdapter(this, R.layout.item_directory, mImageFolderList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mEasyPopupWindow != null && mEasyPopupWindow.isShowing()) {
                    mEasyPopupWindow.dismiss();
                }

                ImageFolder imageFolder = mImageFolderList.get(position);

                mTopSwitch.setText(imageFolder.getName());

                File parentFile = new File(imageFolder.getFirstImagePath()).getParentFile();

                if (parentFile == null) {
                    return;
                }

                File[] listFile = parentFile.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        if (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg")) {
                            return true;
                        }

                        return false;
                    }
                });

                mImagePathList.clear();

                for (File file : listFile) {
                    mImagePathList.add(file.getAbsolutePath());
                }

                mImageAdapter.notifyDataSetChanged();
            }
        });
    }
}
