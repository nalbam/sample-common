package com.nalbam.common.util;

import java.util.Calendar;

public class TimeAgo {

    public static String ago(Calendar c) {
        Calendar n = Calendar.getInstance();

        long nt = n.getTimeInMillis() / 1000;
        long ct = c.getTimeInMillis() / 1000;

        long diff = nt - ct;

        if (ct > nt) {
            return "방금";
        } else {
            if (diff < 3) {
                return "방금";
            } else if (diff < 60) {
                return diff + "초 전";
            } else if (diff < 3600) {
                return diff / 60 + "분 전";
            } else if (diff < 3600 * 24) {
                return diff / 60 / 60 + "시간 전";
            } else if (diff < 3600 * 24 * 30) {
                long diffDay = diff / 3600 / 24;
                if (diffDay == 1) {
                    return "하루 전";
                } else {
                    return diffDay + "일 전";
                }
            } else if (diff < 3600 * 24 * 365) {
                long diffMonth = diff / 3600 / 24 / 30;
                if (diffMonth == 1) {
                    return "한달 전";
                } else {
                    return diffMonth + "달 전";
                }
            } else {
                long diffYear = diff / 3600 / 24 / 365;
                if (diffYear == 1) {
                    return "작년";
                } else {
                    return diffYear + "년 전";
                }
            }
        }
    }

}
