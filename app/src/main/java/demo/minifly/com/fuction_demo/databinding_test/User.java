package demo.minifly.com.fuction_demo.databinding_test;

//import demo.minifly.com.BR;

/**
 * author ：minifly
 * date: 2017/6/7
 * time: 13:31
 * desc:在databinding中还没有实现双绑定的问题，现在是用 android.databinding.Observable来进行的监听数据的改变通知ui进行替换数据。
 */
public class User
{
//        extends BaseObservable{

//    private String password,username;
//
//    public User(String password, String username) {
//        this.password = password;
//        this.username = username;
//    }
//
//    @Bindable
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
////        notifyPropertyChanged(BR.password);
//        /**
//         * BR 是编译阶段生成的一个类，功能与 R.java 类似，用 @Bindable 标记过 getter 方法会在 BR 中生成一个 entry，当我们
//         * 通过代码可以看出，当数据发生变化时还是需要手动发出通知。
//         * 通过调用notifyPropertyChanged(BR.firstName)来通知系统 BR.firstName 这个 entry 的数据已经发生变化，需要更新 UI。
//         */
//    }
//
//    @Bindable
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
////        notifyPropertyChanged(BR.username);
//    }
}
