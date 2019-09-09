package demo.minifly.com.fuction_demo.rxjava_demo.generics;

import java.util.ArrayList;
import java.util.List;

public class GInterfaceImp3<T,R> implements GInterface  <T,R>{
// 泛型实现接口的时候，只指定其中的泛型类；

//    @Override
//    public <T,R>T perform(R r) {
//        R r1  = r;
//        String name = r1.getClass().getName();
//
//        return null;
//    }

    @Override
    public T perform(R r) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        T t = null;
        return t;
    }

//    方法中定义自己的泛型；
    public<U,R> R action(U u){
        R result = null;
        return result;
    }
}
