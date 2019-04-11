package demo.minifly.com.rxjava_demo.generics;

import android.text.TextUtils;

public class GInterfaceImp implements GInterface <Integer , String> {
//    指定泛型的实现类，指定泛型的类型；
    @Override
    public Integer perform(String s) {
        if(TextUtils.isEmpty(s)){
            return 0 ;
        }else {
            return Integer.parseInt(s);
        }
    }
}
