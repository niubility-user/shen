package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.shell.events.ReportEvent;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class k1 {
    private static volatile k1 b;
    private boolean a = false;

    /* loaded from: classes9.dex */
    public class a extends ba.i<Boolean> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            try {
                r3 r3Var = (r3) l2.a(r3.class);
                if (TextUtils.isEmpty(b7.z())) {
                    b7.z();
                }
                NetResponse launchStat = ((d3) r3Var.d()).launchStat(f7.b(), URLEncoder.encode(f7.a(), "utf-8"), b7.A(), b7.z(), b7.I(), b7.M(), b7.E(), b7.O(), b7.G());
                if (launchStat != null) {
                    ma.c("LaunchStat data with response:" + new String(launchStat.data, launchStat.charset));
                }
            } catch (Exception e2) {
                ma.b("err:" + e2.getMessage());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("cm_model", f7.a());
            u.f().a(new ReportEvent("cm_stat", hashMap));
            return Boolean.TRUE;
        }
    }

    private k1() {
    }

    public static k1 a() {
        if (b == null) {
            synchronized (k1.class) {
                if (b == null) {
                    b = new k1();
                }
            }
        }
        return b;
    }

    public synchronized void a(o1 o1Var) {
        if (this.a) {
            return;
        }
        if (o1Var != null && !o1Var.v().b()) {
            ba.a((ba.i) new a()).b((ba.d.b) Boolean.FALSE);
            this.a = true;
        }
    }
}
