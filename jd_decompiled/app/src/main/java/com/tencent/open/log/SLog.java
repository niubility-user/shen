package com.tencent.open.log;

import android.os.Environment;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.d;
import java.io.File;

/* loaded from: classes9.dex */
public class SLog implements TraceLevel {
    public static final String TAG = "openSDK_LOG";

    /* renamed from: c  reason: collision with root package name */
    private static boolean f17669c;
    public static SLog instance;
    protected a a;
    private Tracer b;

    private SLog() {
    }

    private void d() {
        this.a = new a(new b(a(), c.f17695m, c.f17689g, c.f17690h, c.f17686c, c.f17691i, 10, c.f17687e, c.f17696n));
    }

    public static final void e(String str, String str2) {
        getInstance().a(16, str, str2, null);
    }

    public static void flushLogs() {
        getInstance().c();
    }

    public static SLog getInstance() {
        if (instance == null) {
            synchronized (SLog.class) {
                if (instance == null) {
                    SLog sLog = new SLog();
                    instance = sLog;
                    sLog.d();
                    f17669c = true;
                }
            }
        }
        return instance;
    }

    public static final void i(String str, String str2) {
        getInstance().a(4, str, str2, null);
    }

    public static void release() {
        synchronized (SLog.class) {
            getInstance().b();
            if (instance != null) {
                instance = null;
            }
        }
    }

    public static final void u(String str, String str2) {
        getInstance().a(32, str, str2, null);
    }

    public static final void v(String str, String str2) {
        getInstance().a(1, str, str2, null);
    }

    public static final void w(String str, String str2) {
        getInstance().a(8, str, str2, null);
    }

    protected void a(int i2, String str, String str2, Throwable th) {
        if (f17669c) {
            String b = com.tencent.open.utils.g.b();
            if (!TextUtils.isEmpty(b)) {
                String str3 = b + " SDK_VERSION:" + Constants.SDK_VERSION;
                if (this.a == null) {
                    return;
                }
                e.a.a(32, Thread.currentThread(), System.currentTimeMillis(), TAG, str3, null);
                this.a.a(32, Thread.currentThread(), System.currentTimeMillis(), TAG, str3, null);
                f17669c = false;
            }
        }
        e.a.a(i2, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        if (d.a.a(c.b, i2)) {
            a aVar = this.a;
            if (aVar == null) {
                return;
            }
            aVar.a(i2, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
        }
        Tracer tracer = this.b;
        if (tracer != null) {
            try {
                tracer.a(i2, Thread.currentThread(), System.currentTimeMillis(), str, a(str2), th);
            } catch (Exception unused) {
            }
        }
    }

    protected void b() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.a();
            this.a.b();
            this.a = null;
        }
    }

    protected void c() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void setCustomLogger(Tracer tracer) {
        this.b = tracer;
    }

    public static final void e(String str, String str2, Throwable th) {
        getInstance().a(16, str, str2, th);
    }

    public static final void i(String str, String str2, Throwable th) {
        getInstance().a(4, str, str2, th);
    }

    public static final void u(String str, String str2, Throwable th) {
        getInstance().a(32, str, str2, th);
    }

    public static final void v(String str, String str2, Throwable th) {
        getInstance().a(1, str, str2, th);
    }

    public static final void w(String str, String str2, Throwable th) {
        getInstance().a(8, str, str2, th);
    }

    public static final void d(String str, String str2) {
        getInstance().a(2, str, str2, null);
    }

    public static final void d(String str, String str2, Throwable th) {
        getInstance().a(2, str, str2, th);
    }

    private String a(String str) {
        return TextUtils.isEmpty(str) ? "" : d.a(str) ? "xxxxxx" : str;
    }

    protected static File a() {
        String str = c.d;
        try {
            d.c b = d.b.b();
            if (b != null && b.c() > c.f17688f) {
                return new File(Environment.getExternalStorageDirectory(), str);
            }
            return new File(com.tencent.open.utils.g.c(), str);
        } catch (Throwable th) {
            e(TAG, "getLogFilePath:", th);
            return null;
        }
    }
}
