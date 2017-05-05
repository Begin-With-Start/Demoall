package demo.minifly.com.request_demo;

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;

import java.util.Map;

import demo.minifly.com.utils.LogUtils;


/**
 * Created by Administrator on 2016/10/7.
 */

public class NoHttpUtils {
    // 第二个参数取值RequestMethod.POST   RequestMethod.GET
    public static Request<String> getRequest(String url, Map params) {
        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        // 以map的形式添加请求参数
        // request.add(params);

        LogUtils.showInfoLog("请求的地址: " + url);

        if (params != null) {
            //先改回来，冯辉的代码会出问题，跟董杰沟通，需要分开处理
            String jsonStr = JSON.toJSONString(params);
            LogUtils.showInfoLog("提交的参数: " + jsonStr);
            request.setDefineRequestBodyForJson(jsonStr);
        }

        request.setConnectTimeout(10 * 1000); // 设置连接超时。
        request.setReadTimeout(20 * 1000); // 设置读取超时时间，也就是服务器的响应超时。

        // 添加请求头
        request.addHeader("Content-Type", "application/json");
        String m = "ANDROID";
        request.addHeader("m", m);
        request.addHeader("v", "1.0.0");

        return request;
    }

    public static Request<String> getRequestNoHeader(String url, Map params) {
        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        // 添加请求参数
//        request.add(params);

        LogUtils.showLog("请求的地址: " + url);
        if (params != null) {
            request.set(params);
        }
        request.setContentType("application/x-www-form-urlencoded");
        request.setConnectTimeout(10 * 1000); // 设置连接超时。
        request.setReadTimeout(20 * 1000); // 设置读取超时时间，也就是服务器的响应超时。
        return request;
    }


    // 第二个参数取值RequestMethod.POST   RequestMethod.GET
    public static Request<String> getHttpRequest(String url, String params) {
        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        // 添加请求参数
//        request.add(params);
        if (params != null) {
            LogUtils.showLog("提交的参数: " + params);
            request.setDefineRequestBodyForJson(params);
        }

        request.setConnectTimeout(10 * 1000); // 设置连接超时。
        request.setReadTimeout(20 * 1000); // 设置读取超时时间，也就是服务器的响应超时。

        // 添加请求头
        request.addHeader("Content-Type", "application/json");
        String m = "test";
        request.addHeader("m", m);
        request.addHeader("v", "1.0.0");

        return request;
    }
}
