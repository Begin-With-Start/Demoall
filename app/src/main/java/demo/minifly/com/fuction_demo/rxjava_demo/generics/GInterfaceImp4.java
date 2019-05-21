package demo.minifly.com.fuction_demo.rxjava_demo.generics;

import android.util.Log;

/**
 *
 * @param <G>
 */
public class GInterfaceImp4<G extends GInterface>{
// 泛型实现接口的时候，只指定其中的泛型类；

//    @Override
//    public <T,R>T perform(R r) {
//        R r1  = r;
//        String name = r1.getClass().getName();
//
//        return null;
//    }
    public Object dealPerform(G t){
        Log.e("GInterfaceImp4  ",t.perform("GInterfaceImp4").toString());
        return t.perform("GInterfaceImp4");
    }
}
