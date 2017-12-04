package demo.minifly.com.weex_test;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * <pre>
 *     author : xiaofei.he
 *     time   : 2017/12/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class IImageLoaderAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(view==null||view.getLayoutParams()==null){
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                String temp = url;
                if (url.startsWith("//")) {
                    temp = "http:" + url;
                }
                if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                    return;
                }
//                Picasso.with(WXEnvironment.getApplication())
//                        .load(temp)
//                        .into(view);
                Glide.with(WXEnvironment.getApplication()).load(temp).into(view);
            }
        },0);
    }
}
