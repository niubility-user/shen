package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.c8;
import com.xiaomi.push.f7;
import com.xiaomi.push.g7;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.r9;
import com.xiaomi.push.u9;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes11.dex */
public class e1 {
    private static AtomicLong a = new AtomicLong(0);
    private static SimpleDateFormat b;

    /* renamed from: c */
    private static String f19080c;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        b = simpleDateFormat;
        f19080c = simpleDateFormat.format(Long.valueOf(System.currentTimeMillis()));
    }

    private static c8 a(String str, String str2, f7 f7Var) {
        return new c8("-1", false).d(str).b(str2).a(u9.h(m8.f(f7Var))).c(m7.UploadTinyData.f179a);
    }

    public static synchronized String b() {
        String str;
        synchronized (e1.class) {
            String format = b.format(Long.valueOf(System.currentTimeMillis()));
            if (!TextUtils.equals(f19080c, format)) {
                a.set(0L);
                f19080c = format;
            }
            str = format + "-" + a.incrementAndGet();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.xiaomi.push.c8> c(java.util.List<com.xiaomi.push.g7> r11, java.lang.String r12, java.lang.String r13, int r14) {
        /*
            r0 = 0
            if (r11 != 0) goto L9
            java.lang.String r11 = "requests can not be null in TinyDataHelper.transToThriftObj()."
        L5:
            g.j.a.a.a.c.D(r11)
            return r0
        L9:
            int r1 = r11.size()
            if (r1 != 0) goto L12
            java.lang.String r11 = "requests.length is 0 in TinyDataHelper.transToThriftObj()."
            goto L5
        L12:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.xiaomi.push.f7 r2 = new com.xiaomi.push.f7
            r2.<init>()
            r3 = 0
            r4 = 0
            r5 = 0
        L1f:
            int r6 = r11.size()
            if (r4 >= r6) goto Laa
            java.lang.Object r6 = r11.get(r4)
            com.xiaomi.push.g7 r6 = (com.xiaomi.push.g7) r6
            if (r6 != 0) goto L2f
            goto La6
        L2f:
            java.util.Map r7 = r6.m61a()
            if (r7 == 0) goto L6e
            java.util.Map r7 = r6.m61a()
            java.lang.String r8 = "item_size"
            boolean r7 = r7.containsKey(r8)
            if (r7 == 0) goto L6e
            java.util.Map r7 = r6.m61a()
            java.lang.Object r7 = r7.get(r8)
            java.lang.String r7 = (java.lang.String) r7
            boolean r9 = android.text.TextUtils.isEmpty(r7)
            if (r9 != 0) goto L56
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> L56
            goto L57
        L56:
            r7 = 0
        L57:
            java.util.Map r9 = r6.m61a()
            int r9 = r9.size()
            r10 = 1
            if (r9 != r10) goto L66
            r6.a(r0)
            goto L6f
        L66:
            java.util.Map r9 = r6.m61a()
            r9.remove(r8)
            goto L6f
        L6e:
            r7 = 0
        L6f:
            if (r7 > 0) goto L76
            byte[] r7 = com.xiaomi.push.m8.f(r6)
            int r7 = r7.length
        L76:
            if (r7 <= r14) goto L91
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "TinyData is too big, ignore upload request item:"
            r7.append(r8)
            java.lang.String r6 = r6.d()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            g.j.a.a.a.c.D(r6)
            goto La6
        L91:
            int r8 = r5 + r7
            if (r8 <= r14) goto La2
            com.xiaomi.push.c8 r2 = a(r12, r13, r2)
            r1.add(r2)
            com.xiaomi.push.f7 r2 = new com.xiaomi.push.f7
            r2.<init>()
            r5 = 0
        La2:
            r2.a(r6)
            int r5 = r5 + r7
        La6:
            int r4 = r4 + 1
            goto L1f
        Laa:
            int r11 = r2.a()
            if (r11 == 0) goto Lb7
            com.xiaomi.push.c8 r11 = a(r12, r13, r2)
            r1.add(r11)
        Lb7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.e1.c(java.util.List, java.lang.String, java.lang.String, int):java.util.ArrayList");
    }

    public static void d(Context context, String str, String str2, long j2, String str3) {
        g7 g7Var = new g7();
        g7Var.d(str);
        g7Var.c(str2);
        g7Var.a(j2);
        g7Var.b(str3);
        g7Var.a("push_sdk_channel");
        g7Var.g(context.getPackageName());
        g7Var.e(context.getPackageName());
        g7Var.a(true);
        g7Var.b(System.currentTimeMillis());
        g7Var.f(b());
        f1.a(context, g7Var);
    }

    public static boolean e(g7 g7Var, boolean z) {
        String str;
        if (g7Var == null) {
            str = "item is null, verfiy ClientUploadDataItem failed.";
        } else if (!z && TextUtils.isEmpty(g7Var.f142a)) {
            str = "item.channel is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(g7Var.d)) {
            str = "item.category is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (TextUtils.isEmpty(g7Var.f18666c)) {
            str = "item.name is null or empty, verfiy ClientUploadDataItem failed.";
        } else if (!com.xiaomi.push.p0.i(g7Var.d)) {
            str = "item.category can only contain ascii char, verfiy ClientUploadDataItem failed.";
        } else if (com.xiaomi.push.p0.i(g7Var.f18666c)) {
            String str2 = g7Var.f146b;
            if (str2 == null || str2.length() <= 10240) {
                return false;
            }
            str = "item.data is too large(" + g7Var.f146b.length() + "), max size for data is 10240 , verfiy ClientUploadDataItem failed.";
        } else {
            str = "item.name can only contain ascii char, verfiy ClientUploadDataItem failed.";
        }
        g.j.a.a.a.c.o(str);
        return true;
    }

    public static boolean f(String str) {
        return !r9.i() || "com.miui.hybrid".equals(str);
    }
}
