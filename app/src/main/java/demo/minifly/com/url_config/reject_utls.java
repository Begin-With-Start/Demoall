package demo.minifly.com.url_config;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * author ：minifly
 * date: 2017/6/1
 * time: 17:17
 * desc:
 */
public class reject_utls {

    public static void reject(Object model){
        String baseUrl = "BaseUrl";

        Field[] fields = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        try {
            for (int j = 0; j < fields.length; j++) { // 遍历所有属性
                String name = fields[j].getName(); // 获取属性的名字
                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String type = fields[j].getGenericType().toString(); // 获取属性的类型
                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名

                    Log.e("text",""+ name);
                    if(name.contains(baseUrl)){//不处理

                    }else{
                        Field field = fields[j];
                        //将字段的访问权限设为true：即去除private修饰符的影响

                        field.setAccessible(true);
                        /*去除final修饰符的影响，将字段设为可修改的*/
                        Field modifiersField = Field.class.getDeclaredField("modifiers");
                        modifiersField.setAccessible(true);
                        modifiersField.set(field, field.getModifiers() & ~Modifier.FINAL);

                        String value  = field.get(null).toString();

                        //把字段值设为200
                        field.set(null, Constant.BaseUrl + value);
                    }

                }

            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
