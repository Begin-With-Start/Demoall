package demo.minifly.com.asm;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class MyAop {

    /**
     * 在执行这个方法之前进行aop的面向切面编程哦；
     */
    public String aopMethod(){

        LogUtils.showErrLog("aopMethod 方法执行了。");
        return "\"aopMethod 方法执行了。\"";
    }
}
