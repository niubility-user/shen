package com.cmic.sso.sdk.c.c;

import com.cmic.sso.sdk.c.b.e;
import com.cmic.sso.sdk.e.p;

/* loaded from: classes.dex */
public class b extends c {
    private final e b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1012c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, e eVar, String str2, String str3) {
        super(str, eVar, str2, str3);
        this.f1012c = false;
        this.b = eVar;
    }

    public void a(com.cmic.sso.sdk.a aVar) {
        com.cmic.sso.sdk.c.b.a c2 = this.b.c();
        c2.u(aVar.b("socketip"));
        com.cmic.sso.sdk.e.c.b("GetPrePhonescripParam", "socket socketip = " + aVar.b("socketip"));
        if (!this.f1012c) {
            String[] strArr = null;
            if (!aVar.b("isCloseIpv4", false)) {
                strArr = p.a(true);
                c2.q(strArr[0]);
            }
            if (!aVar.b("isCloseIpv6", false)) {
                if (strArr == null) {
                    strArr = p.a(true);
                }
                c2.r(strArr[1]);
            }
            this.f1012c = true;
        }
        c2.n(c2.v(aVar.b("appkey")));
        this.b.a(c2);
        this.b.a(true);
        this.a = this.b.b().toString();
    }
}
