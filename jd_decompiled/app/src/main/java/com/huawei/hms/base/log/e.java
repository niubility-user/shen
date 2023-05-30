package com.huawei.hms.base.log;

import android.os.Process;
import android.util.Log;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes12.dex */
public class e {
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f1229c;
    private int d;

    /* renamed from: g  reason: collision with root package name */
    private String f1232g;

    /* renamed from: h  reason: collision with root package name */
    private int f1233h;

    /* renamed from: i  reason: collision with root package name */
    private int f1234i;

    /* renamed from: j  reason: collision with root package name */
    private int f1235j;
    private final StringBuilder a = new StringBuilder();

    /* renamed from: e  reason: collision with root package name */
    private long f1230e = 0;

    /* renamed from: f  reason: collision with root package name */
    private long f1231f = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i2, String str, int i3, String str2) {
        this.f1229c = "HMS";
        this.f1235j = i2;
        this.b = str;
        this.d = i3;
        if (str2 != null) {
            this.f1229c = str2;
        }
        b();
    }

    public static String a(int i2) {
        return i2 != 3 ? i2 != 4 ? i2 != 5 ? i2 != 6 ? String.valueOf(i2) : "E" : "W" : "I" : AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE;
    }

    private e b() {
        this.f1230e = System.currentTimeMillis();
        Thread currentThread = Thread.currentThread();
        this.f1231f = currentThread.getId();
        this.f1233h = Process.myPid();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        int length = stackTrace.length;
        int i2 = this.f1235j;
        if (length > i2) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            this.f1232g = stackTraceElement.getFileName();
            this.f1234i = stackTraceElement.getLineNumber();
        }
        return this;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        b(sb);
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        b(sb);
        a(sb);
        return sb.toString();
    }

    public <T> e a(T t) {
        this.a.append(t);
        return this;
    }

    public e a(Throwable th) {
        a((e) '\n').a((e) Log.getStackTraceString(th));
        return this;
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    private StringBuilder a(StringBuilder sb) {
        sb.append(' ');
        sb.append(this.a.toString());
        return sb;
    }

    private StringBuilder b(StringBuilder sb) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        sb.append('[');
        sb.append(simpleDateFormat.format(Long.valueOf(this.f1230e)));
        String a = a(this.d);
        sb.append(' ');
        sb.append(a);
        sb.append('/');
        sb.append(this.f1229c);
        sb.append('/');
        sb.append(this.b);
        sb.append(' ');
        sb.append(this.f1233h);
        sb.append(':');
        sb.append(this.f1231f);
        sb.append(' ');
        sb.append(this.f1232g);
        sb.append(':');
        sb.append(this.f1234i);
        sb.append(']');
        return sb;
    }
}
