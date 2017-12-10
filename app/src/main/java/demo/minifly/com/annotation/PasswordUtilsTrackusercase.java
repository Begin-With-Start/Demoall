package demo.minifly.com.annotation;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 * <pre>
 *     author : xiaofei.he
 *     time   : 2017/12/10
 *     desc   : 一个注解类的处理类
 *     version: 1.0
 * </pre>
 */
public class PasswordUtilsTrackusercase {
	public static void trackUsercase(List<Integer> usercases,Class<?> cl){
		for(Method m: cl.getDeclaredMethods()){
			AnnotationUsercaseClass mUsercase = m.getAnnotation(AnnotationUsercaseClass.class);
			if(mUsercase!= null){
				System.out.println("发现测试用例：  " + mUsercase.id() + " 描述：  " + mUsercase.description());
				usercases.remove(new Integer(mUsercase.id()));
			}
		}
		for(int i : usercases){
			System.out.println("警告，没有发现用例：" + i + "这个是不是没写啊，偷懒了吧");
		}
	}
	
	public static void main(String [] args){
		List<Integer> userCases = new LinkedList<Integer>();
		Collections.addAll(userCases, 47,48,49,50);
		trackUsercase(userCases,AnnotationUsercaseClassUtils.class);
	}
}
