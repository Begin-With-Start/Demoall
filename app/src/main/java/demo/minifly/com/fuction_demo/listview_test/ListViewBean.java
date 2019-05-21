package demo.minifly.com.fuction_demo.listview_test;

import java.io.Serializable;

/**
 * 作者：minifly on 2016/11/24 11:48
 */
public class ListViewBean implements Serializable{

    private String name,age,content;

    @Override
    public String toString() {
        return "ListViewBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
