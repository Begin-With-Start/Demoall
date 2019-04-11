package demo.minifly.com.rxjava_demo.generics;

import android.text.TextUtils;

public class GInterfaceImp2 implements GInterface <String , String> {

    @Override
    public String perform(String s) {
        if(TextUtils.isEmpty(s)){
            return "" + s ;
        }else {
            return "指定了string类型返回和 string 入参类型" + s;
        }
    }
}
