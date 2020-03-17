package demo.minifly.com.fuction_demo.webview;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import demo.minifly.com.R;

public class WebviewActivity extends AppCompatActivity {

    private WebView mWebviewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
    }

    private void initView() {
        mWebviewId = (WebView) findViewById(R.id.webview_id);

        WebSettings webSettings = mWebviewId.getSettings();
        String userAgent = webSettings.getUserAgentString();
        webSettings.setSavePassword(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setSaveFormData(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        /**
         * 支持H5本地缓存数据,一定要打开M站以来
         * */
        webSettings.setDomStorageEnabled(true);

        /**
         * 支持H5错误调试
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mWebviewId.setWebChromeClient(new WebChromeClient());

        mWebviewId.loadUrl("http://172.16.12.24:9800/annual-report-2019.html");
    }
}
