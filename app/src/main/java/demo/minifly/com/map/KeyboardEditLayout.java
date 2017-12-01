package demo.minifly.com.map;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiaofei.he
 *     time   : 2017/12/01
 *     desc   : 一个可以跳转到下一个可以输入的edittext的layout
 *     version: 1.0
 * </pre>
 */
public class KeyboardEditLayout extends LinearLayout {
    public KeyboardEditLayout(Context context) {
        super(context);
    }

    public KeyboardEditLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //View v = getCurrentFocus();

    public void next(){
        boolean isThis = false;
        View current = this.findFocus();
        ArrayList<View> views =  this.getFocusables(View.FOCUS_DOWN);
        for(View view : views ){
            if(isThis){
                isThis = false;
                view.requestFocus();
                return ;
            }
            if(current == view){
                isThis = true;
            }
        }

    }

    public void previous(){
        View current = this.findFocus();
        ArrayList<View> views =  this.getFocusables(View.FOCUS_DOWN);
        for(int i = 0 ; i < views.size(); i++){
            if(current == views.get(i)){
                if(i >= 1){
                    views.get(i-1).requestFocus();
                }
            }
        }
    }
}
