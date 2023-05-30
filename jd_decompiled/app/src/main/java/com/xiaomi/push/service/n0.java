package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.xiaomi.push.a3;
import com.xiaomi.push.a5;
import com.xiaomi.push.c3;
import com.xiaomi.push.c5;
import com.xiaomi.push.f1;
import com.xiaomi.push.o5;
import com.xiaomi.push.r4;
import com.xiaomi.push.r6;
import com.xiaomi.push.r9;
import com.xiaomi.push.service.z0;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes11.dex */
public class n0 extends z0.a implements f1.a {
    private XMPushService a;
    private long b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class a implements f1.b {
        a() {
        }

        @Override // com.xiaomi.push.f1.b
        public String a(String str) {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(48));
            buildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
            buildUpon.appendQueryParameter("os", r6.b(BaseInfo.getDeviceModel() + ":" + Build.VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(r9.a()));
            String builder = buildUpon.toString();
            g.j.a.a.a.c.B("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String h2 = com.xiaomi.push.j0.h(r9.b(), url);
                c5.g(url.getHost() + ":" + port, (int) (System.currentTimeMillis() - currentTimeMillis), null);
                return h2;
            } catch (IOException e2) {
                c5.g(url.getHost() + ":" + port, -1, e2);
                throw e2;
            }
        }
    }

    /* loaded from: classes11.dex */
    static class b extends com.xiaomi.push.f1 {
        protected b(Context context, com.xiaomi.push.e1 e1Var, f1.b bVar, String str) {
            super(context, e1Var, bVar, str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaomi.push.f1
        public String f(ArrayList<String> arrayList, String str, String str2, boolean z) {
            try {
                if (a5.f().k()) {
                    str2 = z0.g();
                }
                return super.f(arrayList, str, str2, z);
            } catch (IOException e2) {
                c5.d(0, r4.GSLB_ERR.a(), 1, null, com.xiaomi.push.j0.q(com.xiaomi.push.f1.f18601h) ? 1 : 0);
                throw e2;
            }
        }
    }

    n0(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public static void d(XMPushService xMPushService) {
        n0 n0Var = new n0(xMPushService);
        z0.f().k(n0Var);
        synchronized (com.xiaomi.push.f1.class) {
            com.xiaomi.push.f1.k(n0Var);
            com.xiaomi.push.f1.j(xMPushService, null, new a(), "0", "push", "2.2");
        }
    }

    @Override // com.xiaomi.push.f1.a
    public com.xiaomi.push.f1 a(Context context, com.xiaomi.push.e1 e1Var, f1.b bVar, String str) {
        return new b(context, e1Var, bVar, str);
    }

    @Override // com.xiaomi.push.service.z0.a
    public void b(a3 a3Var) {
    }

    @Override // com.xiaomi.push.service.z0.a
    public void c(c3 c3Var) {
        com.xiaomi.push.b1 q;
        if (c3Var.p() && c3Var.n() && System.currentTimeMillis() - this.b > 3600000) {
            g.j.a.a.a.c.o("fetch bucket :" + c3Var.n());
            this.b = System.currentTimeMillis();
            com.xiaomi.push.f1 c2 = com.xiaomi.push.f1.c();
            c2.i();
            c2.s();
            o5 m151a = this.a.m151a();
            if (m151a == null || (q = c2.q(m151a.c().j())) == null) {
                return;
            }
            ArrayList<String> c3 = q.c();
            boolean z = true;
            Iterator<String> it = c3.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().equals(m151a.d())) {
                    z = false;
                    break;
                }
            }
            if (!z || c3.isEmpty()) {
                return;
            }
            g.j.a.a.a.c.o("bucket changed, force reconnect");
            this.a.a(0, (Exception) null);
            this.a.a(false);
        }
    }
}
