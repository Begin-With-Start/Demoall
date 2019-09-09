package demo.minifly.com.fuction_demo.image_compress_demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.bither.util.NativeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;

public class ImageCompressActivity extends AppCompatActivity {

    private ImageView oldImageview,newImageview;
    private TextView oldTxt,newTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_compress);

        oldImageview = (ImageView) findViewById(R.id.old_image_id);
        newImageview = (ImageView) findViewById(R.id.new_image_id);
        oldTxt = (TextView) findViewById(R.id.oldimage_txt);
        newTxt = (TextView) findViewById(R.id.newimage_txt);

    }

    public static final int REQUEST_PICK_IMAGE = 10011;
    public static final int REQUEST_KITKAT_PICK_IMAGE = 10012;

    public void pickFromGallery(View v) {
        Log.e("======", "========打开图片======");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_KITKAT_PICK_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.e("======", "========返回到图片获取处======");
            switch (requestCode) {

                case REQUEST_PICK_IMAGE:
                    if (data != null) {
                        Uri uri = data.getData();
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            oldImageview.setImageBitmap(bitmap);
                            oldTxt.setText(bitmap.getByteCount()+"b");
                        }catch(Exception e){
                            Log.e(" ====== ",""+e.getMessage());
                        }

                        compressImage(uri);
                    } else {
                        Log.e("======", "========图片为空======");
                    }
                    break;
                case REQUEST_KITKAT_PICK_IMAGE:
                    if (data != null) {
                        Uri uri = ensureUriPermission(this, data);
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            oldImageview.setImageBitmap(bitmap);
                            oldTxt.setText(bitmap.getByteCount()+"b");
                        }catch(Exception e){
                            Log.e(" ====== ",""+e.getMessage());
                        }
                        compressImage(uri);
                    } else {
                        Log.e("======", "====-----==图片为空======");
                    }
                    break;
            }
        }
    }

    @SuppressWarnings("ResourceType")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Uri ensureUriPermission(Context context, Intent intent) {
        Uri uri = intent.getData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int takeFlags = intent.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
            context.getContentResolver().takePersistableUriPermission(uri, takeFlags);
        }
        return uri;
    }


    //把图片的uri进行压缩处理
    public void compressImage(Uri uri) {
        Log.e("===compressImage===", "====开始====uri==" + uri.getPath());
        long currentBefore = System.currentTimeMillis();

        try {
            File saveFile = new File(Environment.getExternalStorageDirectory()+"/", "compress_" + System.currentTimeMillis() + ".jpg");
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            Log.e("===compressImage===", "====开始==压缩==saveFile==" + saveFile.getAbsolutePath());
            NativeUtil.compressBitmap(bitmap, saveFile.getAbsolutePath());


            try{
                FileInputStream fis = new FileInputStream(saveFile.getAbsolutePath());
                newTxt.setText(fis.available()+"b");
                Bitmap bitmap2  = BitmapFactory.decodeStream(fis);
                newImageview.setImageBitmap(bitmap2);
            }catch(Exception e){

            }

            Log.e("===compressImage===", "====完成==压缩==saveFile==" + saveFile.getAbsolutePath());

            LogUtils.showErrLog("用时间： " + (System.currentTimeMillis() - currentBefore));

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("===compressImage===","==压缩=="+e.getMessage());
        }
    }

}
