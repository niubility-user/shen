package com.jd.verify;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes18.dex */
public class c {
    private static ThreadLocal<DateFormat> a = new a();
    private static ThreadLocal<DateFormat> b = new b();

    /* loaded from: classes18.dex */
    class a extends ThreadLocal<DateFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        }
    }

    /* loaded from: classes18.dex */
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

    public static String a(long j2) {
        return a.get().format(Long.valueOf(j2));
    }

    public static String a(Date date) {
        return b.get().format(date);
    }
}
