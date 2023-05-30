package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class a7 {

    /* renamed from: c  reason: collision with root package name */
    private static volatile a7 f18459c;
    private final Context a;
    private Map<String, b7> b = new HashMap();

    private a7(Context context) {
        this.a = context;
    }

    public static a7 a(Context context) {
        if (context == null) {
            g.j.a.a.a.c.D("[TinyDataManager]:mContext is null, TinyDataManager.getInstance(Context) failed.");
            return null;
        }
        if (f18459c == null) {
            synchronized (a7.class) {
                if (f18459c == null) {
                    f18459c = new a7(context);
                }
            }
        }
        return f18459c;
    }

    private boolean g(String str, String str2, String str3, String str4, long j2, String str5) {
        g7 g7Var = new g7();
        g7Var.d(str3);
        g7Var.c(str4);
        g7Var.a(j2);
        g7Var.b(str5);
        g7Var.a(true);
        g7Var.a("push_sdk_channel");
        g7Var.e(str2);
        return e(g7Var, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b7 b() {
        b7 b7Var = this.b.get("UPLOADER_PUSH_CHANNEL");
        if (b7Var != null) {
            return b7Var;
        }
        b7 b7Var2 = this.b.get("UPLOADER_HTTP");
        if (b7Var2 != null) {
            return b7Var2;
        }
        return null;
    }

    Map<String, b7> c() {
        return this.b;
    }

    public void d(b7 b7Var, String str) {
        if (b7Var == null) {
            g.j.a.a.a.c.D("[TinyDataManager]: please do not add null mUploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            g.j.a.a.a.c.D("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            c().put(str, b7Var);
        }
    }

    public boolean e(g7 g7Var, String str) {
        if (TextUtils.isEmpty(str)) {
            g.j.a.a.a.c.o("pkgName is null or empty, upload ClientUploadDataItem failed.");
            return false;
        } else if (com.xiaomi.push.service.e1.e(g7Var, false)) {
            return false;
        } else {
            if (TextUtils.isEmpty(g7Var.d())) {
                g7Var.f(com.xiaomi.push.service.e1.b());
            }
            g7Var.g(str);
            com.xiaomi.push.service.f1.a(this.a, g7Var);
            return true;
        }
    }

    public boolean f(String str, String str2, long j2, String str3) {
        return g(this.a.getPackageName(), this.a.getPackageName(), str, str2, j2, str3);
    }
}
