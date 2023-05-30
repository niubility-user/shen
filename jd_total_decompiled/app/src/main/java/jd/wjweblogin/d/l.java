package jd.wjweblogin.d;

import android.net.Uri;
import android.text.TextUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes11.dex */
public class l {
    private static final String a = "WJWebLogin.FormatUtil";
    private static ThreadLocal<DateFormat> b = new a();

    /* renamed from: c  reason: collision with root package name */
    private static ThreadLocal<DateFormat> f20068c = new b();
    private static ThreadLocal<DateFormat> d = new c();

    /* loaded from: classes11.dex */
    class a extends ThreadLocal<DateFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    }

    /* loaded from: classes11.dex */
    class b extends ThreadLocal<DateFormat> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    }

    /* loaded from: classes11.dex */
    class c extends ThreadLocal<DateFormat> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            return new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz", Locale.US);
        }
    }

    public static Date a(String str) {
        try {
            return b.get().parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Date date) {
        try {
            return b.get().format(date);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(Date date, int i2) {
        try {
            g.b(a, "formatCookieDateToStr time= " + i2 + " date.gettime=" + date.getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String format = simpleDateFormat.format(Long.valueOf(date.getTime() + (new Long(i2).longValue() * 1000)));
            if (g.b) {
                g.b(a, "formatCookieDateToStr nowTime= " + simpleDateFormat.format(Long.valueOf(date.getTime())));
            }
            g.b(a, "formatCookieDateToStr cookieTime= " + format);
            return format;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean b(String str) {
        g.b(a, "\u6821\u9a8curl\u7684\u5408\u6cd5\u6027 url = " + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        String scheme = parse.getScheme();
        g.b(a, "\u6821\u9a8curl\u7684\u5408\u6cd5\u6027 url host= " + host + " scheme=" + scheme);
        return (TextUtils.isEmpty(host) || TextUtils.isEmpty(scheme)) ? false : true;
    }

    public static String a(int i2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.format(Long.valueOf(System.currentTimeMillis() + (new Long(i2).longValue() * 1000)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, -30);
            return simpleDateFormat.format(calendar.getTime());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(Date date) {
        return f20068c.get().format(date);
    }

    public static String a(long j2) {
        return b.get().format(Long.valueOf(j2));
    }
}
