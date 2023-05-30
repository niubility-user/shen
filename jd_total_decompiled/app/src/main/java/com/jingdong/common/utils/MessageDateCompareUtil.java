package com.jingdong.common.utils;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes6.dex */
public class MessageDateCompareUtil {
    private static final long ONE_DAY_TIMES = 86400000;
    static String[] dayNames = {"\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"};

    private static Date formatDate(Calendar calendar) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(5, 1);
        return simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
    }

    private static String getHourAndMin(long j2) throws ParseException {
        return new SimpleDateFormat("HH:mm").format(new Date(j2));
    }

    public static String getMessageTime(String str, String str2) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        if (!TextUtils.isEmpty(str2)) {
            calendar.setTimeInMillis(Long.parseLong(str2));
        }
        calendar2.setTimeInMillis(Long.parseLong(str));
        Date formatDate = formatDate(calendar);
        switch ((formatDate.getTime() - calendar2.getTimeInMillis()) - 1 < 0 ? -1 : (int) (((formatDate.getTime() - calendar2.getTimeInMillis()) - 1) / 86400000)) {
            case 0:
                return getHourAndMin(Long.parseLong(str));
            case 1:
                return "\u6628\u5929";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return dayNames[calendar2.get(7) - 1];
            default:
                return getTime(Long.parseLong(str), "yyyy/MM/dd");
        }
    }

    public static String getMessageTimeWithHM(String str, String str2) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        if (!TextUtils.isEmpty(str2)) {
            calendar.setTimeInMillis(Long.parseLong(str2));
        }
        calendar2.setTimeInMillis(Long.parseLong(str));
        Date formatDate = formatDate(calendar);
        int time = (formatDate.getTime() - calendar2.getTimeInMillis()) - 1 < 0 ? -1 : (int) (((formatDate.getTime() - calendar2.getTimeInMillis()) - 1) / 86400000);
        String hourAndMin = getHourAndMin(Long.parseLong(str));
        switch (time) {
            case 0:
                return hourAndMin;
            case 1:
                return "\u6628\u5929 " + hourAndMin;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return dayNames[calendar2.get(7) - 1] + LangUtils.SINGLE_SPACE + hourAndMin;
            default:
                return getTime(Long.parseLong(str), "yyyy\u5e74MM\u6708dd\u65e5 ") + hourAndMin;
        }
    }

    private static String getTime(long j2, String str) {
        return new SimpleDateFormat(str).format(new Date(j2));
    }
}
