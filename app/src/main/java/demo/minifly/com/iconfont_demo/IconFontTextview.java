package demo.minifly.com.iconfont_demo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * minifly
 * Description:
 */

public class IconFontTextview extends android.support.v7.widget.AppCompatTextView {
    public IconFontTextview(Context context) {
        super(context);
        init(context);
    }

    public IconFontTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IconFontTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "mainfonts/iconfont.ttf");
        setTypeface(iconfont);
    }
}
