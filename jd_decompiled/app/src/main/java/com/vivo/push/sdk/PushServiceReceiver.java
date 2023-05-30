package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import com.jd.dynamic.DYConstants;
import com.vivo.push.PushClient;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.e;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.VivoPushException;
import com.vivo.push.util.p;
import com.vivo.push.util.r;

/* loaded from: classes11.dex */
public class PushServiceReceiver extends BroadcastReceiver {
    private static HandlerThread a;
    private static Handler b;

    /* renamed from: c  reason: collision with root package name */
    private static a f18291c = new a();

    /* loaded from: classes11.dex */
    static class a implements Runnable {
        private Context a;
        private String b;

        a() {
        }

        static /* synthetic */ void a(a aVar, Context context, String str) {
            aVar.a = ContextDelegate.getContext(context);
            aVar.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NetworkInfo a = r.a(this.a);
            if (!(a != null ? a.isConnectedOrConnecting() : false)) {
                p.d("PushServiceReceiver", this.a.getPackageName() + ": \u65e0\u7f51\u7edc  by " + this.b);
                p.a(this.a, "\u89e6\u53d1\u9759\u6001\u5e7f\u64ad:\u65e0\u7f51\u7edc(" + this.b + DYConstants.DY_REGEX_COMMA + this.a.getPackageName() + ")");
                return;
            }
            p.d("PushServiceReceiver", this.a.getPackageName() + ": \u6267\u884c\u5f00\u59cb\u51fa\u53d1\u52a8\u4f5c: " + this.b);
            p.a(this.a, "\u89e6\u53d1\u9759\u6001\u5e7f\u64ad(" + this.b + DYConstants.DY_REGEX_COMMA + this.a.getPackageName() + ")");
            e.a().a(this.a);
            if (ClientConfigManagerImpl.getInstance(this.a).isCancleBroadcastReceiver()) {
                return;
            }
            try {
                PushClient.getInstance(this.a).initialize();
            } catch (VivoPushException e2) {
                e2.printStackTrace();
                p.a(this.a, " \u521d\u59cb\u5316\u5f02\u5e38 error= " + e2.getMessage());
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context context2 = ContextDelegate.getContext(context);
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action) || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
            if (a == null) {
                HandlerThread handlerThread = new HandlerThread("PushServiceReceiver");
                a = handlerThread;
                handlerThread.start();
                b = new Handler(a.getLooper());
            }
            p.d("PushServiceReceiver", context2.getPackageName() + ": start PushSerevice for by " + action + "  ; handler : " + b);
            a.a(f18291c, context2, action);
            b.removeCallbacks(f18291c);
            b.postDelayed(f18291c, 2000L);
        }
    }
}
