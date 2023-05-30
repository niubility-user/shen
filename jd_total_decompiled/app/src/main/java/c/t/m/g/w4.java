package c.t.m.g;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class w4 {
    public static HashMap<String, ThreadLocal<SimpleDateFormat>> a = new HashMap<>();

    public static String a(String str) {
        return b(str, System.currentTimeMillis());
    }

    public static String b(String str, long j2) {
        return c(str).format(new Date(j2));
    }

    public static synchronized SimpleDateFormat c(String str) {
        SimpleDateFormat simpleDateFormat;
        synchronized (w4.class) {
            ThreadLocal<SimpleDateFormat> threadLocal = a.get(str);
            if (threadLocal == null) {
                threadLocal = new ThreadLocal<>();
                a.put(str, threadLocal);
            }
            simpleDateFormat = threadLocal.get();
            if (simpleDateFormat == null) {
                simpleDateFormat = new SimpleDateFormat(str, Locale.ENGLISH);
                threadLocal.set(simpleDateFormat);
            }
        }
        return simpleDateFormat;
    }
}
