package demo.minifly.com.url_config;

/**
 * author ：minifly
 * date: 2017/6/1
 * time: 16:43
 * desc:
 */
public class BuildConfig2 {
    public static String getBase() {
        if (MobileApplication.sp.getValue("1").equals("dt1")) {
            return "dt1_base";
        }else{
            return "dt2_base";
        }

    }

    public static String getWareHouse() {
        if (MobileApplication.sp.getValue("1").equals("ware_house")) {
            return "dt1_ware_house";
        }
        else if ("".equals("dt2")) {
            return "dt2_ware_house";
        }

        return null;
    }


}
