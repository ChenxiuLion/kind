package com.youqd.kind.skind.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by youqd on 2016/5/31.
 */
public class WeekUtils {

    public static String getWeek(int num){
        StringBuffer stringBuffer = new StringBuffer("");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for(int i = 0 ; i < num ;i++){
            stringBuffer.append(getYear(calendar.getTime()) + "年" + getMonth(calendar.getTime()) + "月第" + getDateWeek(calendar.getTime()) + "周,");
            calendar.add(Calendar.WEEK_OF_YEAR,-1);
        }

        String result = stringBuffer.toString();
        return (result.equals("") ? result : result.substring(0,result.length() - 1));
    }


    /**
     * 得到传值日期相对于当前年来说是多少周
     * @return
     * @throws Exception
     */
    public static int getDateWeek(Date currDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(currDate);


        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 得到年
     * @param currDate
     * @return
     */
    public static int getYear(Date currDate){
        return currDate.getYear() + 1900;
    }

    /**
     * 得到月份
     * @param currDate
     * @return
     */
    public static int getMonth(Date currDate){
        return currDate.getMonth() + 1;
    }
}
