package com.nalbam.common.util;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ChineseCalendar;
import org.apache.commons.lang3.StringUtils;

public class LunarUtil {

    /**
     * 양력(yyyy-MM-dd) -> 음력(yyyy-MM-dd)
     */
    public static String toLunar(String ymd) {
        if (StringUtils.isEmpty(ymd)) {
            return "";
        }

        ymd = ymd.trim().replace("-", "");

        if (ymd.length() != 8) {
            return "";
        }

        Calendar cal = Calendar.getInstance();
        ChineseCalendar cc = new ChineseCalendar();

        cal.set(Calendar.YEAR, Integer.parseInt(ymd.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(ymd.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ymd.substring(6)));

        cc.setTimeInMillis(cal.getTimeInMillis());

        // ChineseCalendar.YEAR 는 1~60 까지의 값만 가지고 ,
        // ChineseCalendar.EXTENDED_YEAR 는 Calendar.YEAR 값과 2637 만큼의 차이를 가집니다.
        int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
        int m = cc.get(ChineseCalendar.MONTH) + 1;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH);

        return StringUtils.leftPad(y + "", 4, "0") + "-" +
                StringUtils.leftPad(m + "", 2, "0") + "-" +
                StringUtils.leftPad(d + "", 2, "0");
    }

    /**
     * 음력(yyyy-MM-dd) -> 양력(yyyy-MM-dd)
     */
      static String toSolar(String ymd) {
        if (StringUtils.isEmpty(ymd)) {
            return "";
        }

        ymd = ymd.trim().replace("-", "");

        if (ymd.length() != 8) {
            return "";
        }

        Calendar cal = Calendar.getInstance();
        ChineseCalendar cc = new ChineseCalendar();

        cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(ymd.substring(0, 4)) + 2637);
        cc.set(ChineseCalendar.MONTH, Integer.parseInt(ymd.substring(4, 6)) - 1);
        cc.set(ChineseCalendar.DAY_OF_MONTH, Integer.parseInt(ymd.substring(6)));

        cal.setTimeInMillis(cc.getTimeInMillis());

        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DAY_OF_MONTH);

        return StringUtils.leftPad(y + "", 4, "0") + "-" +
                StringUtils.leftPad(m + "", 2, "0") + "-" +
                StringUtils.leftPad(d + "", 2, "0");
    }

}
