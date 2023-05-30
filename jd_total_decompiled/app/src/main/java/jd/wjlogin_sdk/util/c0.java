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
    */
    public static void a(byte b2, int i2) {
        String str;
        try {
            int currentTimeMillis = (int) (System.currentTimeMillis() - f19918c);
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            short s = 2;
            if (i2 != 1 && i2 == 2) {
                if (p.b) {
                    p.b(a, "reportNewLoginResult type " + i2);
                }
                bVar.a(jd.wjlogin_sdk.d.d.a(d.x, s, g.d(), b));
                jd.wjlogin_sdk.d.d.a(bVar, g.e());
                jd.wjlogin_sdk.d.d.a(bVar, g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
                String str2 = "";
                jd.wjlogin_sdk.d.d.a(bVar, d != null ? "" : new String(ByteUtil.parseHexStr2Byte(d)));
                jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, b2);
                str = f19919e;
                if (str == null) {
                    str2 = str;
                }
                jd.wjlogin_sdk.d.d.d(bVar, str2);
                jd.wjlogin_sdk.net.d.a().a(new b(jd.wjlogin_sdk.util.b.a(bVar.a())));
            }
            s = 1;
            if (p.b) {
            }
            bVar.a(jd.wjlogin_sdk.d.d.a(d.x, s, g.d(), b));
            jd.wjlogin_sdk.d.d.a(bVar, g.e());
            jd.wjlogin_sdk.d.d.a(bVar, g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            String str22 = "";
            jd.wjlogin_sdk.d.d.a(bVar, d != null ? "" : new String(ByteUtil.parseHexStr2Byte(d)));
            jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, b2);
            str = f19919e;
            if (str == null) {
            }
            jd.wjlogin_sdk.d.d.d(bVar, str22);
            jd.wjlogin_sdk.net.d.a().a(new b(jd.wjlogin_sdk.util.b.a(bVar.a())));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
