package com.cmic.sso.sdk.b;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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
    */
    private void b(Context context) {
        c.b("UMCTelephonyManagement", "readSimInfoDbStart");
        Uri parse = Uri.parse("content://telephony/siminfo");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            try {
                String[] strArr = {"_id", "sim_id"};
                String[] strArr2 = new String[1];
                strArr2[0] = "0";
                cursor = contentResolver.query(parse, strArr, "sim_id>=?", strArr2, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int i2 = cursor.getInt(cursor.getColumnIndex("sim_id"));
                        int i3 = cursor.getInt(cursor.getColumnIndex("_id"));
                        if (this.f983c.a == -1 && this.f983c.b != -1 && this.f983c.b == i3) {
                            this.f983c.a = i2;
                            StringBuilder sb = new StringBuilder();
                            sb.append("\u901a\u8fc7\u8bfb\u53d6sim db\u83b7\u53d6\u6570\u636e\u6d41\u91cf\u5361\u7684\u5361\u69fd\u503c\uff1a");
                            sb.append(i2);
                            c.b("UMCTelephonyManagement", sb.toString());
                        }
                        if (this.f983c.a == i2) {
                            this.f983c.b = i3;
                        }
                    }
                }
            } catch (Exception unused) {
                c.a("UMCTelephonyManagement", "readSimInfoDb error");
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
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
