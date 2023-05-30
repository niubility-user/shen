package com.cmic.sso.sdk.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.cmic.sso.sdk.a.b;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.cmic.sso.sdk.e.k;

/* loaded from: classes.dex */
public class c implements b.a {
    @SuppressLint({"StaticFieldLeak"})
    private static c a;
    private a b;

    /* renamed from: c  reason: collision with root package name */
    private a f964c;
    private b d;

    /* renamed from: e  reason: collision with root package name */
    private Context f965e;

    private c(Context context) {
        this.f965e = context;
        b();
    }

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new c(context);
                }
            }
        }
        return a;
    }

    private void b() {
        String b = k.b("sdk_config_version", "");
        if (!TextUtils.isEmpty(b) && AuthnHelper.SDK_VERSION.equals(b)) {
            b a2 = b.a(false);
            this.d = a2;
            this.b = a2.b();
        } else {
            b a3 = b.a(true);
            this.d = a3;
            this.b = a3.a();
            if (!TextUtils.isEmpty(b)) {
                c();
            }
        }
        this.d.a(this);
        this.f964c = this.d.a();
    }

    private void c() {
        com.cmic.sso.sdk.e.c.b("UmcConfigManager", "delete localConfig");
        this.d.c();
    }

    public a a() {
        try {
            return this.b.clone();
        } catch (CloneNotSupportedException unused) {
            return this.f964c;
        }
    }

    @Override // com.cmic.sso.sdk.a.b.a
    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(com.cmic.sso.sdk.a aVar) {
        this.d.a(aVar);
    }
}
