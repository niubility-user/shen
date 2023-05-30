package jd.wjlogin_sdk.util;

import android.text.TextUtils;
import jd.wjlogin_sdk.net.b;

/* loaded from: classes.dex */
public class c0 {
    private static final String a = "WJLogin.WJMonitor";
    private static int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static long f19918c = 0;
    private static String d = null;

    /* renamed from: e  reason: collision with root package name */
    private static String f19919e = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.d()).a(this.a.getBytes()).a().b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.d()).a(this.a.getBytes()).a().b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(short s, String str) {
        try {
            p.b(a, "WJExceptionMonitor reportTlvExcOrHttpReq commandType = " + ((int) s) + " exception = " + str);
            int currentTimeMillis = (int) (System.currentTimeMillis() - f19918c);
            b = b + 1;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 256, s, g.d(), b));
            jd.wjlogin_sdk.d.d.a(bVar, g.e());
            jd.wjlogin_sdk.d.d.a(bVar, g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            jd.wjlogin_sdk.d.d.a(bVar, TextUtils.isEmpty(d) ? "" : new String(ByteUtil.parseHexStr2Byte(d)));
            jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, (byte) 0);
            jd.wjlogin_sdk.d.d.d(bVar, f19919e + "@@@" + str);
            f19918c = System.currentTimeMillis();
            jd.wjlogin_sdk.net.d.a().a(new a(jd.wjlogin_sdk.util.b.a(bVar.a())));
        } catch (Exception unused) {
        }
    }

    public static void b(String str) {
        d = str;
    }

    public static void a(String str) {
        f19919e = str;
    }

    public static void a() {
        d = null;
        f19919e = "";
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005e A[Catch: all -> 0x008d, TRY_ENTER, TryCatch #0 {all -> 0x008d, blocks: (B:2:0x0000, B:8:0x0016, B:11:0x001c, B:12:0x0031, B:17:0x0069, B:21:0x0075, B:16:0x005e), top: B:26:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(byte r6, int r7) {
        /*
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L8d
            long r2 = jd.wjlogin_sdk.util.c0.f19918c     // Catch: java.lang.Throwable -> L8d
            long r0 = r0 - r2
            int r1 = (int) r0     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.d.b r0 = new jd.wjlogin_sdk.d.b     // Catch: java.lang.Throwable -> L8d
            r0.<init>()     // Catch: java.lang.Throwable -> L8d
            r2 = 2
            r3 = 1
            if (r7 != r3) goto L12
            goto L15
        L12:
            if (r7 != r2) goto L15
            goto L16
        L15:
            r2 = 1
        L16:
            boolean r3 = jd.wjlogin_sdk.util.p.b     // Catch: java.lang.Throwable -> L8d
            if (r3 == 0) goto L31
            java.lang.String r3 = "WJLogin.WJMonitor"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8d
            r4.<init>()     // Catch: java.lang.Throwable -> L8d
            java.lang.String r5 = "reportNewLoginResult type "
            r4.append(r5)     // Catch: java.lang.Throwable -> L8d
            r4.append(r7)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.util.p.b(r3, r7)     // Catch: java.lang.Throwable -> L8d
        L31:
            r7 = 28673(0x7001, float:4.018E-41)
            jd.wjlogin_sdk.common.e r3 = jd.wjlogin_sdk.util.g.d()     // Catch: java.lang.Throwable -> L8d
            int r4 = jd.wjlogin_sdk.util.c0.b     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.d.c r7 = jd.wjlogin_sdk.d.d.a(r7, r2, r3, r4)     // Catch: java.lang.Throwable -> L8d
            r0.a(r7)     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.tlvtype.tlv_0x4 r7 = jd.wjlogin_sdk.util.g.e()     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.d.d.a(r0, r7)     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.common.e r7 = jd.wjlogin_sdk.util.g.d()     // Catch: java.lang.Throwable -> L8d
            android.content.Context r2 = jd.wjlogin_sdk.common.b.a()     // Catch: java.lang.Throwable -> L8d
            java.lang.String r2 = jd.wjlogin_sdk.util.r.b(r2)     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.d.d.a(r0, r7, r2)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r7 = jd.wjlogin_sdk.util.c0.d     // Catch: java.lang.Throwable -> L8d
            java.lang.String r2 = ""
            if (r7 != 0) goto L5e
            r7 = r2
            goto L69
        L5e:
            java.lang.String r7 = new java.lang.String     // Catch: java.lang.Throwable -> L8d
            java.lang.String r3 = jd.wjlogin_sdk.util.c0.d     // Catch: java.lang.Throwable -> L8d
            byte[] r3 = jd.wjlogin_sdk.util.ByteUtil.parseHexStr2Byte(r3)     // Catch: java.lang.Throwable -> L8d
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L8d
        L69:
            jd.wjlogin_sdk.d.d.a(r0, r7)     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.d.d.a(r0, r1, r6)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r6 = jd.wjlogin_sdk.util.c0.f19919e     // Catch: java.lang.Throwable -> L8d
            if (r6 != 0) goto L74
            goto L75
        L74:
            r2 = r6
        L75:
            jd.wjlogin_sdk.d.d.d(r0, r2)     // Catch: java.lang.Throwable -> L8d
            byte[] r6 = r0.a()     // Catch: java.lang.Throwable -> L8d
            java.lang.String r6 = jd.wjlogin_sdk.util.b.a(r6)     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.net.d r7 = jd.wjlogin_sdk.net.d.a()     // Catch: java.lang.Throwable -> L8d
            jd.wjlogin_sdk.util.c0$b r0 = new jd.wjlogin_sdk.util.c0$b     // Catch: java.lang.Throwable -> L8d
            r0.<init>(r6)     // Catch: java.lang.Throwable -> L8d
            r7.a(r0)     // Catch: java.lang.Throwable -> L8d
            goto L91
        L8d:
            r6 = move-exception
            r6.printStackTrace()
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.util.c0.a(byte, int):void");
    }
}
