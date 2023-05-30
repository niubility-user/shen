package com.jdpay.bury;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jdpay.bury.proguard.APIKeep;
import java.util.Map;
import jpbury.a0;
import jpbury.b0;
import jpbury.c0;
import jpbury.d0;
import jpbury.h;
import jpbury.k;

@APIKeep
/* loaded from: classes18.dex */
public final class JPBury {
    public static final int LOG_LEVEL_DEBUG = 1;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 0;
    public static final int LOG_LEVEL_NONE = -1;
    public static final int LOG_LEVEL_WARN = 2;
    private static volatile boolean d;

    /* renamed from: e  reason: collision with root package name */
    private static final Object f7520e = new Object();
    private final k a;
    private c0 b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f7521c;

    @APIKeep
    /* loaded from: classes18.dex */
    public static final class Builder {
        private final k a = new k();

        public Builder addHeader(String str, Class<DynamicValue> cls) {
            this.a.a(str, cls);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.a.a(str, str2);
            return this;
        }

        public Builder addHeader(Map<String, String> map) {
            this.a.a(map);
            return this;
        }

        public JPBury build() {
            return new JPBury(this.a);
        }

        public Builder resetHeader() {
            this.a.b();
            return this;
        }

        public Builder resetHeader(Map<String, String> map) {
            this.a.b();
            return addHeader(map);
        }

        public Builder sdkBuildId(String str) {
            this.a.a("sdkBuild", str);
            return this;
        }

        public Builder sdkName(String str) {
            this.a.a("sdkName", str);
            return this;
        }

        public Builder sdkVersion(String str) {
            this.a.b(str);
            return this;
        }

        public Builder setConfigUrl(String str) {
            this.a.a(str);
            return this;
        }

        public Builder setUrl(String str) {
            this.a.c(str);
            return this;
        }
    }

    private JPBury(k kVar) {
        this.f7521c = new Object();
        this.a = kVar;
    }

    private String a(StackTraceElement stackTraceElement) {
        return stackTraceElement.getClassName() + "|" + stackTraceElement.getMethodName() + "|" + stackTraceElement.getLineNumber();
    }

    private static void b() {
        synchronized (f7520e) {
        }
    }

    private void c(String str, String str2, IdExtension idExtension, Class cls, boolean z) {
        g().a(str, str2, idExtension, null, cls == null ? "" : cls.getName(), z);
    }

    public static Builder createBuilder() {
        b();
        return new Builder();
    }

    private void d(String str, String str2, String str3, String str4, String str5, int i2, boolean z) {
        g().a(str, str2, str3, str4, str5, Thread.currentThread().getStackTrace()[4].getClassName(), i2, z);
    }

    private void e(String str, String str2, Throwable th, boolean z) {
        g().a(str, str2, th, Thread.currentThread().getStackTrace()[4].getClassName(), z);
    }

    private void f(String str, String str2, boolean z) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        if (TextUtils.isEmpty(str)) {
            str = a(stackTraceElement);
        }
        g().a("event", str, null, str2, stackTraceElement.getClassName(), 0, z);
    }

    private c0 g() {
        synchronized (this.f7521c) {
            if (this.b == null) {
                if (!d) {
                    return b0.c();
                }
                this.b = d0.a(this.a);
            }
            return this.b;
        }
    }

    public static void init(@NonNull Context context) {
        synchronized (f7520e) {
            if (!d && context != null) {
                a0.a(context);
                d = true;
            }
        }
    }

    public SessionPack createSessionPack() {
        return g().a();
    }

    public SessionPack createSessionPack(String str) {
        return g().a(str);
    }

    public SessionPack createSessionPack(String str, String str2) {
        return g().b(str, str2);
    }

    public SessionPack createSessionPack(String str, String str2, String str3) {
        return g().a(str, str2, str3);
    }

    public void d(String str, String str2) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 1, false);
    }

    public void d(String str, String str2, boolean z) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 1, z);
    }

    public void e(String str, String str2) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 3, false);
    }

    public void e(String str, String str2, boolean z) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 3, z);
    }

    @Deprecated
    public void flush() {
    }

    public String getUUID() {
        return g().b();
    }

    public void i(String str, String str2) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 0, false);
    }

    public void i(String str, String str2, boolean z) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 0, z);
    }

    public void onClick(String str) {
        c("click", str, null, null, false);
    }

    public void onClick(String str, IdExtension idExtension, Class cls) {
        c("click", str, idExtension, cls, false);
    }

    public void onClick(String str, IdExtension idExtension, Class cls, boolean z) {
        c("click", str, idExtension, cls, z);
    }

    public void onClick(String str, Class cls) {
        c("click", str, null, cls, false);
    }

    public void onClick(String str, Class cls, boolean z) {
        c("click", str, null, cls, z);
    }

    public void onClick(String str, boolean z) {
        c("click", str, null, null, z);
    }

    public void onEvent(String str) {
        f(str, "", false);
    }

    public void onEvent(String str, String str2) {
        f(str, str2, false);
    }

    public void onEvent(String str, String str2, boolean z) {
        f(str, str2, z);
    }

    public void onEvent(String str, boolean z) {
        f(str, "", z);
    }

    public void onException(String str, String str2, Throwable th) {
        e(str, str2, th, false);
    }

    public void onException(String str, String str2, Throwable th, boolean z) {
        e(str, str2, th, z);
    }

    public void onException(String str, Throwable th) {
        e(str, null, th, false);
    }

    public void onException(String str, Throwable th, boolean z) {
        e(str, null, th, z);
    }

    public void onMethodFail(String str, String str2, String str3) {
        d(str, "", "fail", str2, str3, 2, false);
    }

    public void onMethodFail(String str, String str2, String str3, String str4) {
        d(str, str2, "fail", str3, str4, 2, false);
    }

    public void onMethodFail(String str, String str2, String str3, String str4, boolean z) {
        d(str, str2, "fail", str3, str4, 2, z);
    }

    public void onMethodFail(String str, String str2, String str3, boolean z) {
        d(str, "", "fail", str2, str3, 2, z);
    }

    public void onMethodSuccess(String str) {
        d(str, "", h.f20123k, "", "", 0, false);
    }

    public void onMethodSuccess(String str, String str2) {
        d(str, str2, h.f20123k, "", "", 0, false);
    }

    public void onMethodSuccess(String str, String str2, boolean z) {
        d(str, str2, h.f20123k, "", "", 0, z);
    }

    public void onMethodSuccess(String str, boolean z) {
        d(str, "", h.f20123k, "", "", 0, z);
    }

    public void onPage(String str) {
        c("page", str, null, null, false);
    }

    public void onPage(String str, IdExtension idExtension, Class cls) {
        c("page", str, idExtension, cls, false);
    }

    public void onPage(String str, IdExtension idExtension, Class cls, boolean z) {
        c("page", str, idExtension, cls, z);
    }

    public void onPage(String str, Class cls) {
        c("page", str, null, cls, false);
    }

    public void onPage(String str, Class cls, boolean z) {
        c("page", str, null, cls, z);
    }

    public void onPage(String str, boolean z) {
        c("page", str, null, null, z);
    }

    public void startSession(SessionPack sessionPack) {
        sessionPack.pack();
        g().a(this.a.c(), sessionPack);
    }

    @Deprecated
    public void stopSession() {
    }

    public void updateSession(String str, String str2) {
        g().a(str, str2);
    }

    public void w(String str, String str2) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 2, false);
    }

    public void w(String str, String str2, boolean z) {
        g().a("event", str, null, str2, Thread.currentThread().getStackTrace()[3].getClassName(), 2, z);
    }
}
