package demo.minifly.com.fuction_demo.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : xiaofei.he
 *     time   : 2017/12/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AnnotationUsercaseClassUtils {

    @AnnotationUsercaseClass(id=47,description = "密码最起码包含一个数字")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @AnnotationUsercaseClass(id = 48)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }
    
    @AnnotationUsercaseClass(id=49,description = "新密码不能跟以前密码相同")
    public boolean checkForNewPassword(List<String> prePassword, String password){
        return !prePassword.contains(password);
    }


    public static void main(String [] args){
        System.out.println(new AnnotationUsercaseClassUtils().validatePassword("sdfafadfasd"));
        System.out.println(new AnnotationUsercaseClassUtils().encryptPassword("sdfafadfasd"));
        List<String> list = new ArrayList<String>(){};
        list.add("2321");
        System.out.println(new AnnotationUsercaseClassUtils().checkForNewPassword(list, "2321"));
    }
}
