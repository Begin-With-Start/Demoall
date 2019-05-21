package demo.minifly.com.fuction_demo.rxjava_demo.generics;

public interface GInterface <T,R>{
    T perform(R r); //提供了个开放的接口定义，允许从使用端，调用端来指定方法的入参和返回参数；
}
