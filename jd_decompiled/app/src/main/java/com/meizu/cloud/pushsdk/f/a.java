package com.meizu.cloud.pushsdk.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.f.c.a;
import com.meizu.cloud.pushsdk.f.c.f;
import com.meizu.cloud.pushsdk.f.f.a;
import com.meizu.cloud.pushsdk.f.f.c;
import com.meizu.cloud.pushsdk.f.g.b;
import com.meizu.cloud.pushsdk.f.g.c;
import com.meizu.cloud.pushsdk.f.g.e;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;

/* loaded from: classes14.dex */
public class a {
    private static com.meizu.cloud.pushsdk.f.f.a a;
    private static BroadcastReceiver b;

    /* renamed from: com.meizu.cloud.pushsdk.f.a$a */
    /* loaded from: classes14.dex */
    public class C0760a extends BroadcastReceiver {
        final /* synthetic */ com.meizu.cloud.pushsdk.f.f.a a;

        C0760a(com.meizu.cloud.pushsdk.f.f.a aVar) {
            this.a = aVar;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (e.j(context)) {
                c.f("QuickTracker", "restart track event: %s", "online true");
                this.a.f();
            }
        }
    }

    public static com.meizu.cloud.pushsdk.f.f.a a(Context context, com.meizu.cloud.pushsdk.e.d.a aVar, f fVar) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    com.meizu.cloud.pushsdk.f.f.a c2 = c(g(context, aVar, fVar), null, context);
                    a = c2;
                    f(context, c2);
                }
            }
        }
        return a;
    }

    public static com.meizu.cloud.pushsdk.f.f.a b(Context context, boolean z) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = c(g(context, null, null), null, context);
                }
            }
        }
        DebugLogger.i("PushAndroidTracker", "can upload subject " + z);
        if (z) {
            a.e(d(context));
        }
        return a;
    }

    private static com.meizu.cloud.pushsdk.f.f.a c(com.meizu.cloud.pushsdk.f.c.a aVar, com.meizu.cloud.pushsdk.f.f.c cVar, Context context) {
        a.C0765a c0765a = new a.C0765a(aVar, "PushAndroidTracker", context.getPackageCodePath(), context, com.meizu.cloud.pushsdk.f.f.d.a.class);
        c0765a.c(b.VERBOSE);
        c0765a.d(Boolean.FALSE);
        c0765a.b(cVar);
        c0765a.a(4);
        return new com.meizu.cloud.pushsdk.f.f.d.a(c0765a);
    }

    private static com.meizu.cloud.pushsdk.f.f.c d(Context context) {
        c.b bVar = new c.b();
        bVar.b(context);
        return bVar.c();
    }

    private static String e() {
        String str = MzSystemUtils.isOverseas() ? PushConstants.URL_ABROAD_STATICS_DOMAIN : PushConstants.URL_STATICS_DOMAIN;
        DebugLogger.e("QuickTracker", "current statics domain is " + str);
        return str;
    }

    private static void f(Context context, com.meizu.cloud.pushsdk.f.f.a aVar) {
        if (b != null) {
            return;
        }
        b = new C0760a(aVar);
        context.registerReceiver(b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    private static com.meizu.cloud.pushsdk.f.c.a g(Context context, com.meizu.cloud.pushsdk.e.d.a aVar, f fVar) {
        a.C0761a c0761a = new a.C0761a(e(), context, com.meizu.cloud.pushsdk.f.c.h.a.class);
        c0761a.d(fVar);
        c0761a.b(aVar);
        c0761a.f(1);
        com.meizu.cloud.pushsdk.f.c.b bVar = com.meizu.cloud.pushsdk.f.c.b.DefaultGroup;
        c0761a.c(bVar);
        c0761a.e(bVar.a());
        c0761a.a(2);
        return new com.meizu.cloud.pushsdk.f.c.h.a(c0761a);
    }
}
