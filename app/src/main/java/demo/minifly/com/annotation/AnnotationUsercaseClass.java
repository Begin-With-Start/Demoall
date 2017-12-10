package demo.minifly.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author : xiaofei.he
 *     time   : 2017/12/10
 *     desc   : 注解在测试用例的使用，通过便利已经实现了这个注解的类方法就可以知道项目的进度。
 *
 *     version: 1.0
 * </pre>
     ElementType.TYPE:用于描述类、接口或enum声明
     ElementType.FIELD:用于描述实例变量
     ElementType.METHOD
     ElementType.PARAMETER
     ElementType.CONSTRUCTOR
     ElementType.LOCAL_VARIABLE
     ElementType.ANNOTATION_TYPE 另一个注释
     ElementType.PACKAGE 用于记录java文件的package信息

     @Retention– 定义该注解的生命周期。
     RetentionPolicy.SOURCE – 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。
     RetentionPolicy.CLASS – 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式。
     RetentionPolicy.RUNTIME– 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationUsercaseClass {
    int id();
    String description() default "没有description！";
    
    /**
     * 在注解中没有null的表达，不能以null来作为其值
     * 可以自己定义一个特殊值来表达空的概念  int -- -1  string -- ""
     */
    
}
