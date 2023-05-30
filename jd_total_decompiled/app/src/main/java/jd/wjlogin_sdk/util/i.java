package jd.wjlogin_sdk.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class i {
    private static ThreadLocal<DateFormat> a = new a();
    private static ThreadLocal<DateFormat> b = new b();

    /* loaded from: classes.dex */
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

    /* loaded from: classes.dex */
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

    public static Date a(String str) {
        try {
            return a.get().parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Date date) {
        try {
            return a.get().format(date);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(Date date) {
        return b.get().format(date);
    }

    public static String a(long j2) {
        return a.get().format(Long.valueOf(j2));
    }
}
