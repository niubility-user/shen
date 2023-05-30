package com.huawei.hms.hatool;

import android.os.Build;
import android.text.TextUtils;

/* loaded from: classes12.dex */
public class o0 {

    /* renamed from: c  reason: collision with root package name */
    private static o0 f1399c;
    private String a;
    private String b;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
        if (f() != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String a(String str) {
        String d = f() ? com.huawei.secure.android.common.encrypt.c.a.a.d("analytics_keystore", str) : "";
        if (TextUtils.isEmpty(d)) {
            v.c("hmsSdk", "deCrypt work key first");
            d = n.a(str, e());
            if (TextUtils.isEmpty(d)) {
                d = com.huawei.secure.android.common.encrypt.d.b.d(16);
                c(b(d));
            } else if (f()) {
                c(b(d));
                x.c();
            }
        }
        return d;
    }

    private String b(String str) {
        return f() ? com.huawei.secure.android.common.encrypt.c.a.a.g("analytics_keystore", str) : n.b(str, e());
    }

    private String c() {
        String a = d.a(q0.i(), "Privacy_MY", "PrivacyData", "");
        if (TextUtils.isEmpty(a)) {
            String d = com.huawei.secure.android.common.encrypt.d.b.d(16);
            c(b(d));
            return d;
        }
        return a(a);
    }

    private boolean c(String str) {
        v.c("hmsSdk", "refresh sp aes key");
        if (TextUtils.isEmpty(str)) {
            v.c("hmsSdk", "refreshLocalKey(): encrypted key is empty");
            return false;
        }
        d.b(q0.i(), "Privacy_MY", "PrivacyData", str);
        d.b(q0.i(), "Privacy_MY", "flashKeyTime", System.currentTimeMillis());
        return true;
    }

    public static o0 d() {
        if (f1399c == null) {
            g();
        }
        return f1399c;
    }

    private String e() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = new x().a();
        }
        return this.b;
    }

    private boolean f() {
        return Build.VERSION.SDK_INT >= 23;
    }

    private static synchronized void g() {
        synchronized (o0.class) {
            if (f1399c == null) {
                f1399c = new o0();
            }
        }
    }

    public String a() {
        if (TextUtils.isEmpty(this.a)) {
            this.a = c();
        }
        return this.a;
    }

    public void b() {
        String d = com.huawei.secure.android.common.encrypt.d.b.d(16);
        if (c(b(d))) {
            this.a = d;
        }
    }
}
