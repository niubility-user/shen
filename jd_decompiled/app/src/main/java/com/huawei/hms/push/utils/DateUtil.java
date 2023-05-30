package com.huawei.hms.push.utils;

import com.huawei.hms.support.log.HMSLog;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes12.dex */
public class DateUtil {
    private DateUtil() {
    }

    public static String parseMilliSecondToString(Long l2) {
        if (l2 == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(l2);
        } catch (Exception e2) {
            HMSLog.e("DateUtil", "parseMilliSecondToString Exception.", e2);
            return null;
        }
    }

    public static long parseUtcToMillisecond(String str) throws ParseException, StringIndexOutOfBoundsException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.parse(str.substring(0, str.indexOf(OrderISVUtil.MONEY_DECIMAL)) + (str.substring(str.indexOf(OrderISVUtil.MONEY_DECIMAL)).substring(0, 4) + "Z")).getTime();
    }
}
