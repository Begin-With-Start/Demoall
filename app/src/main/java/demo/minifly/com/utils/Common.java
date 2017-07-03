package demo.minifly.com.utils;

/**
 * author ：minifly
 * date: 2017/3/6
 * time: 16:51
 * desc:
 */
public class Common {
    private static Common instance;


    public static Common getInstance() {
        synchronized (Common.class) {
            if (instance == null) {
                instance = new Common();
            }
        }

        return instance;
    }
    long lastClickTime = 0;

    public boolean isNotFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime >= 300) {
            lastClickTime = time;
            return true;
        } else {
            return false;
        }
    }


    public int addSum(int a ,int b){
        return a+b;
    }
}
