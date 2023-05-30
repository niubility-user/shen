package com.huawei.hms.push;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes12.dex */
public class s {

    /* renamed from: c */
    private static final String[] f1477c = {"url", "app", "cosa", "rp"};
    private Context a;
    private m b;

    public s(Context context, m mVar) {
        this.a = context;
        this.b = mVar;
    }

    public static boolean a(String str) {
        for (String str2 : f1477c) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x007a, code lost:
        if (r3 != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00ba, code lost:
        if (com.huawei.hms.push.d.a(r6.a, r6.b.d(), r2).booleanValue() != false) goto L79;
     */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00bf A[Catch: Exception -> 0x00e1, TryCatch #1 {Exception -> 0x00e1, blocks: (B:67:0x0007, B:81:0x00bf, B:83:0x00c5, B:85:0x00d0, B:87:0x00db, B:86:0x00d6, B:75:0x0097, B:77:0x009f, B:74:0x007e, B:70:0x0044), top: B:94:0x0007, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00c5 A[Catch: Exception -> 0x00e1, TryCatch #1 {Exception -> 0x00e1, blocks: (B:67:0x0007, B:81:0x00bf, B:83:0x00c5, B:85:0x00d0, B:87:0x00db, B:86:0x00d6, B:75:0x0097, B:77:0x009f, B:74:0x007e, B:70:0x0044), top: B:94:0x0007, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void b() {
        Intent parseUri;
        HMSLog.i("PushSelfShowLog", "run into launchCosaApp");
        try {
            HMSLog.i("PushSelfShowLog", "enter launchExistApp cosa, appPackageName =" + this.b.d() + ",and msg.intentUri is " + this.b.n());
            Intent b = d.b(this.a, this.b.d());
            boolean z = false;
            if (this.b.n() != null) {
                try {
                    parseUri = Intent.parseUri(this.b.n(), 0);
                    parseUri.setSelector(null);
                    HMSLog.i("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0), action:" + parseUri.getAction());
                    z = d.a(this.a, this.b.d(), parseUri).booleanValue();
                } catch (Exception e2) {
                    HMSLog.w("PushSelfShowLog", "intentUri error." + e2.toString());
                }
            } else {
                if (this.b.a() != null) {
                    parseUri = new Intent(this.b.a());
                }
                if (b != null) {
                    HMSLog.i("PushSelfShowLog", "launchCosaApp,intent == null");
                    return;
                }
                b.setPackage(this.b.d());
                if (z) {
                    b.addFlags(268435456);
                } else {
                    b.setFlags(805437440);
                }
                this.a.startActivity(b);
                return;
            }
            b = parseUri;
            if (b != null) {
            }
        } catch (Exception e3) {
            HMSLog.e("PushSelfShowLog", "launch Cosa App exception." + e3.toString());
        }
    }

    public void c() {
        m mVar;
        HMSLog.d("PushSelfShowLog", "enter launchNotify()");
        if (this.a != null && (mVar = this.b) != null) {
            if ("app".equals(mVar.i())) {
                a();
                return;
            } else if ("cosa".equals(this.b.i())) {
                b();
                return;
            } else if ("rp".equals(this.b.i())) {
                HMSLog.w("PushSelfShowLog", this.b.i() + " not support rich message.");
                return;
            } else if ("url".equals(this.b.i())) {
                HMSLog.w("PushSelfShowLog", this.b.i() + " not support URL.");
                return;
            } else {
                HMSLog.d("PushSelfShowLog", this.b.i() + " is not exist in hShowType");
                return;
            }
        }
        HMSLog.d("PushSelfShowLog", "launchNotify  context or msg is null");
    }

    private void a() {
        try {
            HMSLog.i("PushSelfShowLog", "enter launchApp, appPackageName =" + this.b.d());
            if (d.c(this.a, this.b.d())) {
                b();
            }
        } catch (Exception e2) {
            HMSLog.e("PushSelfShowLog", "launchApp error:" + e2.toString());
        }
    }
}
