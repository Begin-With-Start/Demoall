package demo.minifly.com.fuction_demo.request_demo;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.Binary;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import demo.minifly.com.fuction_demo.url_config.MobileApplication;
import demo.minifly.com.fuction_demo.utils.LogUtils;
import demo.minifly.com.fuction_demo.utils.ThreadUtils;


/**
 * Created by Administrator on 2016/10/7.
 */

public class NoHttpUtils {
    private static String mBaseUrl = "";

    private static final int REQUEST_FAIL = -1;    //请求失败
    private static final int REQUEST_SUCCESS = 1;  //请求成功
    private static final int REQUEST_FINISH = 2;   //请求完成

    // 不同环境的base URL
    public static final String URL_BASE_DEV = "http://192.168.6.24:8080/";
    public static final String URL_BASE_CIT = "http://cit.xxxxx";
    public static final String URL_BASE_SIT = "http://sit.xxxxx";
    public static final String URL_BASE_UAT = "http://uat.xxx";
    public static final String URL_BASE_RELEASE = "http://release.xxxxx";
    // 当前在某个环境的常量
    public static final String ENV_DEV = "env_dev";
    public static final String ENV_CIT = "env_cit";
    public static final String ENV_SIT = "env_sit";
    public static final String ENV_UAT = "env_uat";
    public static final String ENV_RELEASE = "env_release";
    public static final String ENV_DEFAULT = "env_dev"; // 默认环境，更改默认环境只需要改这个就可以了
    public static final String sp_Env = "sp_env"; // 当前是那个环境
    public static final String sp_Token = "sp_token";

    public static Request<byte[]> getByteRequest(String url, Map params, RequestMethod requestMethod) {
        String env = MobileApplication.sp.getValue(sp_Env, ENV_DEFAULT);

        if (ENV_DEV.equals(env)) {
            mBaseUrl = URL_BASE_DEV;
        } else if (ENV_CIT.equals(env)) {
            mBaseUrl = URL_BASE_CIT;
        } else if (ENV_SIT.equals(env)) {
            mBaseUrl = URL_BASE_SIT;
        } else if (ENV_UAT.equals(env)) {
            mBaseUrl = URL_BASE_UAT;
        } else if (ENV_RELEASE.equals(env)) {
            mBaseUrl = URL_BASE_RELEASE;
        }

        String jsonString = JSON.toJSONString(params);

        String urlString = mBaseUrl + url + "?data=" + jsonString;

        String uriString = "";

        try {
            URL urlObject = new URL(urlString);
            URI uriObject = new URI(urlObject.getProtocol(), urlObject.getHost() + ":" + urlObject.getPort(), urlObject.getPath(), urlObject.getQuery(), null);
            uriString = uriObject.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        LogUtils.showLog("请求的地址：" + uriString);

        // 创建请求对象
        Request<byte[]> request = NoHttp.createByteArrayRequest(uriString, requestMethod);

        //添加头信息
        String token = MobileApplication.sp.getValue(sp_Token);

        //auth_token必须传，不能为""，先随便写一个值
        if (!TextUtils.isEmpty(token)) {
            request.addHeader("auth_token", token);
        } else {
            request.addHeader("auth_token", "10000");
        }

        request.addHeader("sign", "");
        request.addHeader("version", "1.0"); //服务端api版本
        request.addHeader("charset", "UTF-8");
        request.addHeader("w_appid", "wh_android_" + MobileApplication.getApplicationInstance().getVersionName());  //客户端版本描述

//        request.setDefineRequestBodyForJson(jsonString); // 添加参数
        request.setConnectTimeout(10 * 1000); // 设置连接超时
        request.setReadTimeout(20 * 1000); // 设置读取超时时间，也就是服务器的响应超时

        return request;
    }

    // 第二个参数取值RequestMethod.POST   RequestMethod.GET
    private static Request<String> getRequest(String url, Map params, RequestMethod requestMethod) {
        Map map = new HashMap();

        String versionName = MobileApplication.getApplicationInstance().getVersionName();

        if (requestMethod == RequestMethod.POST) {
            url = url + "?t=" + System.currentTimeMillis() + "&w_appid=" + "wh_android_" + versionName;

            // 以map的形式添加请求参数
            map.put("data", params);
            map.put("notify_url", "");
            map.put("format", "json");
        } else if (requestMethod == RequestMethod.GET){
            String jsonString = JSON.toJSONString(params);

            // 请求参数直接添加到url后面
            url = url + "?t=" + System.currentTimeMillis() + "&w_appid=" + "wh_android_" + versionName + "&data=" + jsonString;
        }

        String jsonString = JSON.toJSONString(map);

        LogUtils.showLog("请求的地址：" + url);
        LogUtils.showLog("请求的参数：" + jsonString);

        // 创建请求对象
        Request<String> request = NoHttp.createStringRequest(url, requestMethod);

        //添加头信息
        String token = MobileApplication.sp.getValue(sp_Token);

        //auth_token必须传，不能为""，先随便写一个值
        if (!TextUtils.isEmpty(token)) {
            request.addHeader("auth_token", token);
        } else {
            request.addHeader("auth_token", "10000");
        }

        request.addHeader("sign", "");
        request.addHeader("version", "1.0"); //服务端api版本
        request.addHeader("charset", "UTF-8");
        request.addHeader("w_appid", "wh_android_" + MobileApplication.getApplicationInstance().getVersionName());  //客户端版本描述

        request.setDefineRequestBodyForJson(jsonString); // 添加参数
        request.setConnectTimeout(10 * 1000); // 设置连接超时
        request.setReadTimeout(20 * 1000); // 设置读取超时时间，也就是服务器的响应超时

        return request;
    }

    // 普通的Get Post请求的回调监听
    public interface OnResponseListener {
        void onSucceed(CommonClass commonClass, int what);
        void onFailed(String result, int what);
        void onFinish(int what);
    }

    // 上传文件的监听
    public interface OnFileUploadListener {
        void onStart(int position); // 这个文件开始上传。
        void onCancel(int position); // 这个文件的上传被取消时。
        void onProgress(int position, int progress); // 这个文件的上传进度发生边耍
        void onFinish(int position); // 文件上传完成
        void onError(int position, Exception exception); // 文件上传发生错误。
    }

    // 全部文件上传完成后的回调
    public interface OnFileUploadResultListener {
        void onSucceed(int what, Response<String> response);
        void onFailed(int what, Response<String> response);
    }

    public interface OnFileDownloadListener {
        void onStart(int position, boolean isResume, long beforeLength, Headers headers, long allCount);
        void onDownloadError(int position, Exception exception);
        void onProgress(int position, int progress, long fileCount);
        void onFinish(int position, String filePath);
        void onCancel(int position);
    }

    // Get请求
    public static void httpGet(final String url, final Map params, final OnResponseListener responseListener, final int w) {

        String env = MobileApplication.sp.getValue(sp_Env, ENV_DEFAULT);

        if (ENV_DEV.equals(env)) {
            mBaseUrl = URL_BASE_DEV;
        } else if (ENV_CIT.equals(env)) {
            mBaseUrl = URL_BASE_CIT;
        } else if (ENV_SIT.equals(env)) {
            mBaseUrl = URL_BASE_SIT;
        } else if (ENV_UAT.equals(env)) {
            mBaseUrl = URL_BASE_UAT;
        } else if (ENV_RELEASE.equals(env)) {
            mBaseUrl = URL_BASE_RELEASE;
        }

        final String newUrl = mBaseUrl + url;

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;

                if (what == REQUEST_SUCCESS) {
                    // 请求成功
                    if (responseListener != null) {
                        String resultString = (String) msg.obj;

                        CommonClass commonClass = JSON.parseObject(resultString, CommonClass.class);

                        if ("4".equals(commonClass.getCode())) {

                            return;
                        }

                        responseListener.onSucceed(commonClass, w);
                    }
                } else if (what == REQUEST_FINISH) {
                    // 请求完成
                    if (responseListener != null) {
                        responseListener.onFinish(w);
                    }
                } else {
                    if (responseListener != null) {
                        // 请求失败
                        responseListener.onFailed((String) msg.obj, w);
                    }
                }
            }
        };

        if (NetUtils.isHttpConnected(MobileApplication.getApplicationInstance())) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    Request<String> request = getRequest(newUrl, params, RequestMethod.GET);
                    Response<String> response = NoHttp.startRequestSync(request);
                    String resultString = response.get();

                    LogUtils.showLog("请求的结果：" + resultString);

                    handler.sendEmptyMessage(REQUEST_FINISH); // 请求完成

                    if ((response.getHeaders().getResponseCode() + "").startsWith("2")) {
                        // 请求成功
                        Message msg = new Message();
                        msg.what = REQUEST_SUCCESS;
                        msg.obj = resultString;
                        handler.sendMessage(msg);
                    } else {
                        // 请求失败
                        Message msg = new Message();
                        msg.what = REQUEST_FAIL;
                        msg.obj = resultString;
                        handler.sendMessage(msg);
                    }
                }
            });
        } else {
            // 没联网，直接完成
            handler.sendEmptyMessage(REQUEST_FINISH); // 请求完成

            // 请求失败
            Message msg = new Message();
            msg.what = REQUEST_FAIL;
            msg.obj = "no net";
            handler.sendMessage(msg);
        }
    }

    // Get请求
    public static void httpPost(final String url, final Map params, final OnResponseListener responseListener, final int w) {

        String env = MobileApplication.sp.getValue(sp_Env, ENV_DEFAULT);

        if (ENV_DEV.equals(env)) {
            mBaseUrl = URL_BASE_DEV;
        } else if (ENV_CIT.equals(env)) {
            mBaseUrl = URL_BASE_CIT;
        } else if (ENV_SIT.equals(env)) {
            mBaseUrl = URL_BASE_SIT;
        } else if (ENV_UAT.equals(env)) {
            mBaseUrl = URL_BASE_UAT;
        } else if (ENV_RELEASE.equals(env)) {
            mBaseUrl = URL_BASE_RELEASE;
        }

        final String newUrl = mBaseUrl + url;

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;

                if (what == REQUEST_SUCCESS) {
                    // 请求成功
                    if (responseListener != null) {
                        String resultString = (String) msg.obj;

                        CommonClass commonClass = JSON.parseObject(resultString, CommonClass.class);

                        if ("4".equals(commonClass.getCode())) {

                            return;
                        }

                        responseListener.onSucceed(commonClass, w);
                    }
                } else if (what == REQUEST_FINISH) {
                    // 请求完成
                    if (responseListener != null) {
                        responseListener.onFinish(w);
                    }
                } else {
                    // 请求失败
                    if (responseListener != null) {
                        responseListener.onFailed((String) msg.obj, w);
                    }
                }
            }
        };

        if (NetUtils.isHttpConnected(MobileApplication.getApplicationInstance())) {
            ThreadUtils.newThread(new Runnable() {
                @Override
                public void run() {
                    Request<String> request = getRequest(newUrl, params, RequestMethod.POST);
                    Response<String> response = NoHttp.startRequestSync(request);
                    String resultString = response.get();

                    LogUtils.showLog("请求的结果：" + resultString);

                    handler.sendEmptyMessage(REQUEST_FINISH); // 请求完成

                    if ((response.getHeaders().getResponseCode() + "").startsWith("2")) {
                        // 请求成功
                        Message msg = new Message();
                        msg.what = REQUEST_SUCCESS;
                        msg.obj = resultString;
                        handler.sendMessage(msg);
                    } else {
                        // 请求失败
                        Message msg = new Message();
                        msg.what = REQUEST_FAIL;
                        msg.obj = resultString;
                        handler.sendMessage(msg);
                    }
                }
            });
        } else {
            // 没联网，直接完成
            handler.sendEmptyMessage(REQUEST_FINISH); // 请求完成

            // 请求失败
            Message msg = new Message();
            msg.what = REQUEST_FAIL;
            msg.obj = "no net";
            handler.sendMessage(msg);
        }
    }

    // 组拼get参数, 不过没用上
    private static String getUrl(String url, HashMap<String, String> params) {
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();

            StringBuffer stringBuffer = null;

            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);

                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                    stringBuffer.append("?");
                } else {
                    stringBuffer.append("&");
                }

                stringBuffer.append(key);
                stringBuffer.append("=");
                stringBuffer.append(value);
            }

            url += stringBuffer.toString();
        }

        return url;
    }

    // 文件上传
    private static OnFileUploadListener mOnFileUploadListener;

    public static void httpUploadFileRequest(String url, List<String> fileList, OnFileUploadListener onFileUploadListener, final OnFileUploadResultListener onFileUploadResultListener) {
        mOnFileUploadListener = onFileUploadListener;

        String env = MobileApplication.sp.getValue(sp_Env, ENV_DEFAULT);

        if (ENV_DEV.equals(env)) {
            mBaseUrl = URL_BASE_DEV;
        } else if (ENV_CIT.equals(env)) {
            mBaseUrl = URL_BASE_CIT;
        } else if (ENV_SIT.equals(env)) {
            mBaseUrl = URL_BASE_SIT;
        } else if (ENV_UAT.equals(env)) {
            mBaseUrl = URL_BASE_UAT;
        } else if (ENV_RELEASE.equals(env)) {
            mBaseUrl = URL_BASE_RELEASE;
        }
        final String newUrl = mBaseUrl + url;
        LogUtils.showLog("请求的地址：" + newUrl);
        final Request<String> request = NoHttp.createStringRequest(newUrl, RequestMethod.POST);

//            for (int i = 0; i < fileList.size(); i++) {
//                File file = new File(fileList.get(i));
//                BasicBinary binary = new FileBinary(file);
//                binary.setUploadListener(i, mOnUploadListener);
//                request.add("file", binary);
//            }

        List<Binary> binaries = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            File file = new File(fileList.get(i));
            FileBinary binary = new FileBinary(file);
            binary.setUploadListener(i, mOnUploadListener);
            binaries.add(binary);
        }
//            for (int i = 0; i < fileList.size(); i++) {
//                File file = new File(fileList.get(i));
//                Bitmap bmp = BitmapFactory.decodeFile(fileList.get(i));
//                BitmapBinary binary = new BitmapBinary(bmp, file.getName());
//                binary.setUploadListener(i, mOnUploadListener);
//                binaries.add(binary);
//            }
        request.add("file", binaries);

        CallServer.getRequestInstance().add(0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                if ((response.getHeaders().getResponseCode()+"").startsWith("2")) {
                    String resultString = response.get();

                    LogUtils.showLog("请求的结果：" + resultString);

                    CommonClass commonClass = JSON.parseObject(resultString, CommonClass.class);

                    if ("4".equals(commonClass.getCode())) {

                        return;
                    }

                    if (onFileUploadResultListener != null) {
                        onFileUploadResultListener.onSucceed(what, response);
                    }
                }
                else {
                    if (onFileUploadResultListener != null) {
                        onFileUploadResultListener.onFailed(what, response);
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                String resultString = response.get();
                LogUtils.showLog("请求的结果：" + resultString);
                if (onFileUploadResultListener != null) {
                    onFileUploadResultListener.onFailed(what, response);
                }
            }
        });
    }

    private static OnUploadListener mOnUploadListener = new OnUploadListener() {

        @Override
        public void onStart(int position) {    // 这个文件开始上传
            if (mOnFileUploadListener != null) {
                mOnFileUploadListener.onStart(position);
            }
        }

        @Override
        public void onCancel(int position) {    // 这个文件的上传被取消时
            if (mOnFileUploadListener != null) {
                mOnFileUploadListener.onCancel(position);
            }
        }

        @Override
        public void onProgress(int position, int progress) {    // 这个文件的上传进度发生变化
            if (mOnFileUploadListener != null) {
                mOnFileUploadListener.onProgress(position, progress);
            }
        }

        @Override
        public void onFinish(int position) {    // 文件上传完成
            if (mOnFileUploadListener != null) {
                mOnFileUploadListener.onFinish(position);
            }
        }

        @Override
        public void onError(int position, Exception exception) {    // 文件上传发生错误
            if (mOnFileUploadListener != null) {
                mOnFileUploadListener.onError(position, exception);
            }
        }
    };

    // 下载一个文件
    public static void httpDownloadOneFile(String url, String savePath, String fileName, boolean isRange, boolean isDeleteOld, final OnFileDownloadListener onFileDownloadListener) {
        // url 下载地址。
        // fileFolder 保存的文件夹。
        // fileName 文件名, 如果是断点续传就必须指定
        // isRange 是否断点续传下载。
        // isDeleteOld 如果发现存在同名文件，是否删除后重新下载，如果不删除，则直接下载成功。
        DownloadRequest mDownloadRequest = NoHttp.createDownloadRequest(url, savePath, fileName, isRange, isDeleteOld);

        // what 区分下载。
        // downloadRequest 下载请求对象。
        // downloadListener 下载监听。
        CallServer.getDownloadInstance().add(0, mDownloadRequest, new DownloadListener() {

            @Override
            public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {
                if (onFileDownloadListener != null) {
                    onFileDownloadListener.onStart(what, isResume, beforeLength, headers, allCount);
                }
            }

            @Override
            public void onDownloadError(int what, Exception exception) {
                if (onFileDownloadListener != null) {
                    onFileDownloadListener.onDownloadError(what, exception);
                }
            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                if (onFileDownloadListener != null) {
                    onFileDownloadListener.onProgress(what, progress, fileCount);
                }
            }

            @Override
            public void onFinish(int what, String filePath) {
                if (onFileDownloadListener != null) {
                    onFileDownloadListener.onFinish(what, filePath);
                }
            }

            @Override
            public void onCancel(int what) {
                if (onFileDownloadListener != null) {
                    onFileDownloadListener.onCancel(what);
                }
            }
        });
    }
}
