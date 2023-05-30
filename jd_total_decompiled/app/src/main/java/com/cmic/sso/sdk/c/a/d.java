package com.cmic.sso.sdk.c.a;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import com.cmic.sso.sdk.e.n;
import com.cmic.sso.sdk.e.r;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class d implements b {
    private b a;

    @Override // com.cmic.sso.sdk.c.a.b
    public void a(final com.cmic.sso.sdk.c.c.c cVar, final com.cmic.sso.sdk.c.d.c cVar2, final com.cmic.sso.sdk.a aVar) {
        if (!cVar.b()) {
            b(cVar, cVar2, aVar);
            return;
        }
        r a = r.a((Context) null);
        if (Build.VERSION.SDK_INT >= 21) {
            a.a(new r.a() { // from class: com.cmic.sso.sdk.c.a.d.1

                /* renamed from: e  reason: collision with root package name */
                private final AtomicBoolean f988e = new AtomicBoolean(false);

                @Override // com.cmic.sso.sdk.e.r.a
                public void a(final Network network) {
                    if (this.f988e.getAndSet(true)) {
                        return;
                    }
                    n.a(new n.a(null, aVar) { // from class: com.cmic.sso.sdk.c.a.d.1.1
                        @Override // com.cmic.sso.sdk.e.n.a
                        protected void a() {
                            if (network != null) {
                                com.cmic.sso.sdk.e.c.b("WifiChangeInterceptor", "onAvailable");
                                cVar.a(network);
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                d.this.b(cVar, cVar2, aVar);
                                return;
                            }
                            cVar2.a(com.cmic.sso.sdk.c.d.a.a(102508));
                        }
                    });
                }
            });
            return;
        }
        com.cmic.sso.sdk.e.c.a("WifiChangeInterceptor", "\u4f4e\u7248\u672c\u4e0d\u5728\u652f\u6301wifi\u5207\u6362");
        cVar2.a(com.cmic.sso.sdk.c.d.a.a(102508));
    }

    public void b(com.cmic.sso.sdk.c.c.c cVar, final com.cmic.sso.sdk.c.d.c cVar2, com.cmic.sso.sdk.a aVar) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(cVar, new com.cmic.sso.sdk.c.d.c() { // from class: com.cmic.sso.sdk.c.a.d.2
                @Override // com.cmic.sso.sdk.c.d.c
                public void a(com.cmic.sso.sdk.c.d.b bVar2) {
                    cVar2.a(bVar2);
                }

                @Override // com.cmic.sso.sdk.c.d.c
                public void a(com.cmic.sso.sdk.c.d.a aVar2) {
                    cVar2.a(aVar2);
                }
            }, aVar);
        }
    }

    public void a(b bVar) {
        this.a = bVar;
    }
}
