package com.cmic.sso.sdk.b;

import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionManager;
import com.cmic.sso.sdk.e.c;
import com.cmic.sso.sdk.e.m;
import com.jingdong.common.jdmiaosha.utils.cache.Final;

/* loaded from: classes.dex */
public class a {
    private static a a;
    private static long b;

    /* renamed from: c  reason: collision with root package name */
    private C0017a f983c = null;

    /* renamed from: com.cmic.sso.sdk.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0017a {
        private int a = -1;
        private int b = -1;

        public int a() {
            return this.b;
        }
    }

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public C0017a b() {
        C0017a c0017a = this.f983c;
        return c0017a == null ? new C0017a() : c0017a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x008b, code lost:
        if (r11 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0096, code lost:
        if (r11 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0098, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009b, code lost:
        com.cmic.sso.sdk.e.c.b("UMCTelephonyManagement", "readSimInfoDbEnd");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a1, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "sim_id"
            java.lang.String r1 = "_id"
            java.lang.String r2 = "UMCTelephonyManagement"
            java.lang.String r3 = "readSimInfoDbStart"
            com.cmic.sso.sdk.e.c.b(r2, r3)
            java.lang.String r3 = "content://telephony/siminfo"
            android.net.Uri r5 = android.net.Uri.parse(r3)
            android.content.ContentResolver r4 = r11.getContentResolver()
            r11 = 0
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r3 = 0
            r6[r3] = r1     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r7 = 1
            r6[r7] = r0     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r8 = "sim_id>=?"
            java.lang.String[] r9 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r7 = "0"
            r9[r3] = r7     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r3 = 0
            r7 = r8
            r8 = r9
            r9 = r3
            android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r11 == 0) goto L8b
        L34:
            boolean r3 = r11.moveToNext()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r3 == 0) goto L8b
            int r3 = r11.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r3 = r11.getInt(r3)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r4 = r11.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r4 = r11.getInt(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            com.cmic.sso.sdk.b.a$a r5 = r10.f983c     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r5 = com.cmic.sso.sdk.b.a.C0017a.b(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r6 = -1
            if (r5 != r6) goto L7d
            com.cmic.sso.sdk.b.a$a r5 = r10.f983c     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r5 = com.cmic.sso.sdk.b.a.C0017a.a(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r5 == r6) goto L7d
            com.cmic.sso.sdk.b.a$a r5 = r10.f983c     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r5 = com.cmic.sso.sdk.b.a.C0017a.a(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r5 != r4) goto L7d
            com.cmic.sso.sdk.b.a$a r5 = r10.f983c     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            com.cmic.sso.sdk.b.a.C0017a.a(r5, r3)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r5.<init>()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r6 = "\u901a\u8fc7\u8bfb\u53d6sim db\u83b7\u53d6\u6570\u636e\u6d41\u91cf\u5361\u7684\u5361\u69fd\u503c\uff1a"
            r5.append(r6)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r5.append(r3)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            com.cmic.sso.sdk.e.c.b(r2, r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
        L7d:
            com.cmic.sso.sdk.b.a$a r5 = r10.f983c     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            int r5 = com.cmic.sso.sdk.b.a.C0017a.b(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r5 != r3) goto L34
            com.cmic.sso.sdk.b.a$a r3 = r10.f983c     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            com.cmic.sso.sdk.b.a.C0017a.b(r3, r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            goto L34
        L8b:
            if (r11 == 0) goto L9b
            goto L98
        L8e:
            r0 = move-exception
            goto La2
        L90:
            java.lang.String r0 = "readSimInfoDb error"
            com.cmic.sso.sdk.e.c.a(r2, r0)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L9b
        L98:
            r11.close()
        L9b:
            java.lang.String r11 = "readSimInfoDbEnd"
            com.cmic.sso.sdk.e.c.b(r2, r11)
            return
        La2:
            if (r11 == 0) goto La7
            r11.close()
        La7:
            goto La9
        La8:
            throw r0
        La9:
            goto La8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.b.a.b(android.content.Context):void");
    }

    public void a(Context context, boolean z) {
        long currentTimeMillis = System.currentTimeMillis() - b;
        if (currentTimeMillis >= Final.FIVE_SECOND || currentTimeMillis <= 0) {
            this.f983c = new C0017a();
            if (z) {
                a(context);
                if (m.e() && m.d()) {
                    c.b("UMCTelephonyManagement", "\u534e\u4e3a\u624b\u673a\u517c\u5bb9\u6027\u5904\u7406");
                    if (this.f983c.b == 0 || this.f983c.b == 1) {
                        if (this.f983c.a == -1) {
                            C0017a c0017a = this.f983c;
                            c0017a.a = c0017a.b;
                        }
                        this.f983c.b = -1;
                    }
                    if ((this.f983c.a != -1 || this.f983c.b != -1) && Build.VERSION.SDK_INT >= 21) {
                        b(context);
                    }
                }
                b = System.currentTimeMillis();
            }
        }
    }

    private void a(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 22) {
            this.f983c.a = -1;
            return;
        }
        SubscriptionManager from = SubscriptionManager.from(context.getApplicationContext());
        if (from != null) {
            try {
                if (this.f983c.a == -1 && i2 >= 24) {
                    this.f983c.b = SubscriptionManager.getDefaultDataSubscriptionId();
                    StringBuilder sb = new StringBuilder();
                    sb.append("android 7.0\u53ca\u4ee5\u4e0a\u624b\u673agetDefaultDataSubscriptionId\u9002\u914d\u6210\u529f: dataSubId = ");
                    sb.append(this.f983c.b);
                    c.b("UMCTelephonyManagement", sb.toString());
                    return;
                }
            } catch (Exception unused) {
                c.a("UMCTelephonyManagement", "android 7.0\u53ca\u4ee5\u4e0a\u624b\u673agetDefaultDataSubscriptionId\u9002\u914d\u5931\u8d25");
            }
            try {
                Object invoke = from.getClass().getMethod("getDefaultDataSubId", new Class[0]).invoke(from, new Object[0]);
                if ((invoke instanceof Integer) || (invoke instanceof Long)) {
                    this.f983c.b = ((Integer) invoke).intValue();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("android 7.0\u4ee5\u4e0b\u624b\u673agetDefaultDataSubId\u9002\u914d\u6210\u529f: dataSubId = ");
                    sb2.append(this.f983c.b);
                    c.b("UMCTelephonyManagement", sb2.toString());
                    return;
                }
            } catch (Exception unused2) {
                c.a("UMCTelephonyManagement", "readDefaultDataSubId-->getDefaultDataSubId \u53cd\u5c04\u51fa\u9519");
            }
            try {
                Object invoke2 = from.getClass().getMethod("getDefaultDataSubscriptionId", new Class[0]).invoke(from, new Object[0]);
                if ((invoke2 instanceof Integer) || (invoke2 instanceof Long)) {
                    this.f983c.b = ((Integer) invoke2).intValue();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\u53cd\u5c04getDefaultDataSubscriptionId\u9002\u914d\u6210\u529f: dataSubId = ");
                    sb3.append(this.f983c.b);
                    c.b("UMCTelephonyManagement", sb3.toString());
                }
            } catch (Exception unused3) {
                c.a("UMCTelephonyManagement", "getDefaultDataSubscriptionId-->getDefaultDataSubscriptionId \u53cd\u5c04\u51fa\u9519");
            }
        }
    }
}
