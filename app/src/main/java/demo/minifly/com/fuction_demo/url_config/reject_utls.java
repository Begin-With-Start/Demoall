package demo.minifly.com.fuction_demo.url_config;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
public class reject_utls {

    /**
     * 扫描所有的static中的属性，每次在选择之后，因为系统的static会先执行，那么只能通过反射来进行刷新，强制刷新。
     * @param model
     */
    public static void reject(Object model){
        String baseUrl = "BaseUrl";
        List <String > allBase = new LinkedList<>();

        Field[] fields = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        try {
            for (int j = 0; j < fields.length; j++) { // 遍历所有属性
                String name = fields[j].getName(); // 获取属性的名字
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String type = fields[j].getGenericType().toString(); // 获取属性的类型
                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名

                    if(judgeContain(name)){//不处理
                        Field field = fields[j];
                        String value  = field.get(null).toString();
                        allBase.add(value);
                    }else{
                        Log.e("text",""+ name);
                        Field field = fields[j];
                        //将字段的访问权限设为true：即去除private修饰符的影响
                        field.setAccessible(true);
//                        /*去除final修饰符的影响，将字段设为可修改的*/
//                        Field modifiersField = Field.class.getDeclaredField("modifiers");
//                        modifiersField.setAccessible(true);
//                        modifiersField.set(field, field.getModifiers() & ~Modifier.FINAL);
                        String value  = field.get(null).toString();
                        //把字段值设置为自己的值
//                        field.set(null, Constant.BaseUrl + value.replace(baseUrl,""));
                    }

                }

            }
        } catch (SecurityException e) {
            e.printStackTrace();
            Log.e("text",""+ e.getStackTrace());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("text",""+ e.getStackTrace());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("text",""+ e.getStackTrace());
        }
    }
    //加入自己例外处理的base的那么
    public static boolean judgeContain(String name){
        String [] array = new String[]{"SOCKETIO_URL","UPLOAD_PIC","SALES","BASE","TRANSPORTION","WAREHOUSE","LABOR","SUPPLY","DATASYNC","H5"};
        for(String dest: array){
            if(name.contains(dest)){
                return true;
            }
        }
        return false;
    }
}
