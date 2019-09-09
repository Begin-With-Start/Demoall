package demo.minifly.com.fuction_demo.project_all_demo.photo_select_upload.media;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Jackie on 2017/6/21.
 * 生成缩略图
 */

public class ThumbnailUtils {

    /**
     *  通过url去获取视频的第一帧
     *  Android 原生给我们提供了一个MediaMetadataRetriever类
     *  提供了一个统一的接口用于从一个输入媒体文件中取得帧和元数据
     *  @param url 网络视频的路径
     *  @return    缩略图
     */
    public static Bitmap createNetVideoThumbnail(String url) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        if (TextUtils.isEmpty(url)) {
            return null;
        }

        try {
            //根据url获取缩略图
            retriever.setDataSource(url, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (Exception e) {

            }
        }

        if (bitmap == null) {
            return null;
        }

        return bitmap;
    }

    /**
     *  通过url去获取视频的第一帧
     *  Android 原生给我们提供了一个MediaMetadataRetriever类
     *  提供了一个统一的接口用于从一个输入媒体文件中取得帧和元数据
     *  @param url    网络视频的路径
     *  @param width  缩略图的宽
     *  @param height 缩略图的高
     *  @return       缩略图
     */
    public static Bitmap createNetVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        if (TextUtils.isEmpty(url)) {
            return null;
        }

        try {
            //根据url获取缩略图
            retriever.setDataSource(url, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (Exception e) {

            }
        }

        if (bitmap == null) {
            return null;
        }

        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

        return bitmap;
    }

    /**
     * 通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了一个统一的接口用于从一个输入媒体文件中取得帧和元数据
     * @param path 视频的路径
     * @return     缩略图
     */
    public static Bitmap createLocalVideoThumbnail(String path) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        if (TextUtils.isEmpty(path)) {
            return null;
        }

        File file = new File(path);

        if (!file.exists()) {
            return null;
        }

        try {
            retriever.setDataSource(path);
            bitmap = retriever.getFrameAtTime(-1); //取得指定时间的Bitmap，即可以实现抓图（缩略图）功能
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }

        if (bitmap == null) {
            return null;
        }

        return bitmap;
    }

    /**
     * 通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了一个统一的接口用于从一个输入媒体文件中取得帧和元数据
     * @param path   视频的路径
     * @param width  缩略图的宽
     * @param height 缩略图的高
     * @return       缩略图
     */
    public static Bitmap createLocalVideoThumbnail(String path, int width, int height) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        if (TextUtils.isEmpty(path)) {
            return null;
        }

        File file = new File(path);

        if (!file.exists()) {
            return null;
        }

        try {
            retriever.setDataSource(path);
            bitmap = retriever.getFrameAtTime(-1); //取得指定时间的Bitmap，即可以实现抓图（缩略图）功能
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }

        if (bitmap == null) {
            return null;
        }

        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        return bitmap;
    }
}
