package demo.minifly.com.asm;

public class MyAop {

    /**
     * 在执行这个方法之前进行aop的面向切面编程哦；
     */
    public String aopMethod(){

        System.out.println("aopMethod 方法执行了。");
        return "\"aopMethod 方法执行了。\"";
    }
}
