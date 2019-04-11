package demo.minifly.com.rxjava_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;
import demo.minifly.com.rxjava_demo.generics.GInterface;
import demo.minifly.com.rxjava_demo.generics.GInterfaceImp;
import demo.minifly.com.rxjava_demo.generics.GInterfaceImp2;
import demo.minifly.com.rxjava_demo.generics.GInterfaceImp3;
import demo.minifly.com.rxjava_demo.generics.GInterfaceImp4;

public class GenericDemoActivity extends AppCompatActivity {
    private String TAG = "GenericDemoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_demo);


        List<String> stringArrayList = new ArrayList();
        List<Integer> integerArrayList = new ArrayList();
        Class class1 = stringArrayList.getClass();
        Class class2 = integerArrayList.getClass();

        //泛型接口，其中的方法的入参和返回值类型都是在调用的时候进行指定；
        ((TextView)findViewById(R.id.generic_txt_1)).setText("泛型接口方法的入参和返回值都为泛型 在实现的时候指定类型：  \n " + new GInterfaceImp().perform("123")
                + "\n" + "" + new GInterfaceImp2().perform("123") + "\n String 的list 与 integer的list" + class1.equals(class2) + "   " + class1.toString() + "   " + class2.toString()
                + "\n " + "   " + (new GInterfaceImp4().dealPerform(new GInterfaceImp3()))
        );

        /**
         * ?  通配符的使用 只能用来填充通配符泛型变量，表示通配任何类型；
         * 注意：利用<? extends Number>定义的变量，只可取其中的值，不可修改
         */
        GInterface<?,?> gInterfaceimp1 = new GInterfaceImp3<String , String>();
//        gInterfaceimp1.perform("");
//        gInterfaceimp1.perform();
        Log.e("" + TAG , "");
//
        /**
         * 如果你想从一个数据类型里获取数据，使用 ? extends 通配符（能取不能存）
         * 通配符的使用
         * 注意：利用<? extends Number>定义的变量，只可取其中的值，不可修改
         */
        List<? extends  Integer> extendsGeneric;
        extendsGeneric = new LinkedList<Integer>();
//        extendsGeneric.add(1); //报错


        /**
         * 如果你想把对象写入一个数据结构里，使用 ? super 通配符（能存不能取）
         */
        List<? super String> superGeneric = new LinkedList<String>();
        superGeneric.add("");
        String s = (String) superGeneric.get(0);

        /**
         * 如果你既想存，又想取，那就别用通配符。
         */
    }
}
