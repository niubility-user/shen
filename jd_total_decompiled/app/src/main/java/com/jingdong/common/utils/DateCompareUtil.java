package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes6.dex */
public class DateCompareUtil {
    private static final String TAG = "DateCompareUtil";

    public static int compareTo(String str, String str2, String str3) throws Exception {
        Calendar calendar;
        if (TextUtils.isEmpty(str2)) {
            str2 = "yyyy-MM-dd";
        }
        if (StringUtil.isEmpty(str)) {
            return -4;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date parse = simpleDateFormat.parse(str);
        if (TextUtils.isEmpty(str3)) {
            calendar = Calendar.getInstance();
        } else {
            Date parse2 = simpleDateFormat.parse(str3);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(parse2);
            calendar = calendar2;
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(parse);
        if (calendar.get(1) != calendar3.get(1)) {
            return -3;
        }
        int compareTo = parse.compareTo(formatDate(calendar, simpleDateFormat));
        return compareTo < 0 ? parse.compareTo(formatDate(calendar, simpleDateFormat)) < 0 ? -2 : -1 : compareTo;
    }

    private static Date formatDate(Calendar calendar, SimpleDateFormat simpleDateFormat) throws Exception {
        calendar.add(5, -1);
        return simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
    }

    public static Date parseDate(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Date date = new Date(0L);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e2) {
            OKLog.e(TAG, e2);
            return date;
        }
    }
}
