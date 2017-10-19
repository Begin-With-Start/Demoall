package demo.minifly.com.project_all_demo.weekdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import demo.minifly.com.utils.LogUtils;

/**
 * author ：minifly
 * date: 2017/10/13
 * time: 10:06
 * desc:
 */
public class CalendarUtils {
    static final String[] weeks = new String[] { "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", };

    public static String  getWeekPerYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cale3 = Calendar.getInstance(Locale.CHINA);
        LogUtils.showErrLog("现在是" + sdf.format(cale3.getTime()));

        cale3.setFirstDayOfWeek(Calendar.MONDAY); // 星期一
        cale3.setMinimalDaysInFirstWeek(7); // 7天为一周

        cale3.setTimeInMillis(System.currentTimeMillis());

        cale3.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        LogUtils.showErrLog("周一时间：  " + sdf.format(cale3.getTime()));
        cale3.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        LogUtils.showErrLog("周日时间：  " + sdf.format(cale3.getTime()));


        LogUtils.showErrLog("getFirstDayOfWeek值-->" + weeks[cale3.getFirstDayOfWeek() - 1]);
        LogUtils.showErrLog("" + sdf.format(cale3.getTime()));

        cale3.roll(Calendar.DATE, 6);//日期回滚7天
        LogUtils.showErrLog("" + sdf.format(cale3.getTime()));
        return sdf.format(cale3.getTime());

    }



}
