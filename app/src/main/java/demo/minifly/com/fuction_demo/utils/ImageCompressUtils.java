package demo.minifly.com.fuction_demo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import net.bither.util.NativeUtil;

import java.io.File;
import java.io.IOException;

/**
 * author ：minifly
 * date: 2017/6/8
 * time: 15:24
 * desc: 图片压缩相关。
 */
public class ImageCompressUtils {

    /**
     * 把图片的uri进行压缩处理
     * 没有保存图片地址的时候，给出一个默认的存储地址 ： sdcard/wangcang/tempimage/******.jpg
     */
    public boolean compressImage(Context context, Uri uri,String saveFileFullPath) {
        LogUtils.showErrLog("====开始====uri==" + uri.getPath());
        try {
//            File saveFile = new File(Environment.getExternalStorageDirectory()+"/", "compress_" + System.currentTimeMillis() + ".jpg");
            File saveFile = null;
            if(saveFileFullPath==null || "".equals(saveFileFullPath)){
                saveFile = new FileUtils().getImagesTempFile(System.currentTimeMillis()+".jpg");
            }else{
                saveFile = new File(saveFileFullPath);
            }
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

            LogUtils.showErrLog( "====开始==压缩==saveFile==" + saveFile.getAbsolutePath());
            NativeUtil.compressBitmap(bitmap, saveFile.getAbsolutePath());

            LogUtils.showErrLog( "====完成==压缩==saveFile==" + saveFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.showErrLog("==压缩问题=="+e.getMessage());
            return false;
        }
        return true;
    }

    //图片的path压缩处理
    //todo吧



}
