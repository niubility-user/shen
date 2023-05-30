package cn.com.union.fido.util;

import android.text.format.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class DateTools {
    public static String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public static String getFileName() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
    }

    public static String getNow() {
        Time time = new Time();
        time.setToNow();
        return time.format("%Y_%m_%d %T");
    }

    public static String getTimeStamp() {
        Time time = new Time();
        time.setToNow();
        return time.format("%Y_%m_%d_%H_%M_%S");
    }
}
