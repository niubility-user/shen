package jd.wjlogin_sdk.common;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import java.io.IOException;
import jd.wjlogin_sdk.net.NetworkException;
import jd.wjlogin_sdk.net.b;
import jd.wjlogin_sdk.tlvtype.o0;
import jd.wjlogin_sdk.tlvtype.p0;
import jd.wjlogin_sdk.util.DecryptorJni;
import jd.wjlogin_sdk.util.ReplyCode;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.c0;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.v;
import jd.wjlogin_sdk.util.w;

/* loaded from: classes.dex */
public class c {
    private static final String a = "WJLogin.KeyManager";
    public static final String b = "+XhedidFtFgxKd7AoNH0w1zY13I7cUHuyQoT0CWp2zI22qe3W1jr4KD7QqzbHdtSRnpX5jXz3Cwf4g57JfwsQUoVCgQzZYa+BOJDcMI6czPkU6xBh2ghNBuD59dZotRcr3JMpZosZCDui35JCGFeJWEJDKWGJKvEHGhxjEMrZ+KAILzjMBYr5Gz5GsX/uj9JwKI+bJTvZn89d98JU552uLAWvmp613kjDnrQPuIIaSqoIMh6YQx5vql0oJLEtREFoh3yt3jLR1WbO79AbavECUC1/YLkaRBmKhT9kaRDtNjJUMlgY+RpZ7CQ6+41WbfhDellAnQcDg==";

    /* renamed from: c */
    private static volatile String f19745c;
    private static volatile String d;

    /* renamed from: e */
    private static volatile String f19746e;

    /* renamed from: f */
    private static volatile String f19747f;

    static boolean a(Throwable th) {
        return th.getCause() instanceof IOException;
    }

    public static void b() {
        v.b(jd.wjlogin_sdk.util.e.q, true);
        f19745c = w.a(jd.wjlogin_sdk.util.e.H, "");
        d = w.a(jd.wjlogin_sdk.util.e.I, "");
        f19746e = w.a(jd.wjlogin_sdk.util.e.J, "");
        f19747f = w.a(jd.wjlogin_sdk.util.e.K, "");
        long a2 = w.a(jd.wjlogin_sdk.util.e.M, 0L);
        long a3 = w.a(jd.wjlogin_sdk.util.e.L, 0L);
        v.a(jd.wjlogin_sdk.util.e.H, f19745c);
        v.a(jd.wjlogin_sdk.util.e.I, d);
        v.a(jd.wjlogin_sdk.util.e.J, f19746e);
        v.a(jd.wjlogin_sdk.util.e.K, f19747f);
        v.b(jd.wjlogin_sdk.util.e.M, a2);
        v.b(jd.wjlogin_sdk.util.e.L, a3);
        c();
    }

    private static void c() {
        if (w.a(jd.wjlogin_sdk.util.e.H)) {
            w.b(jd.wjlogin_sdk.util.e.H);
        }
        if (w.a(jd.wjlogin_sdk.util.e.I)) {
            w.b(jd.wjlogin_sdk.util.e.I);
        }
        if (w.a(jd.wjlogin_sdk.util.e.J)) {
            w.b(jd.wjlogin_sdk.util.e.J);
        }
        if (w.a(jd.wjlogin_sdk.util.e.K)) {
            w.b(jd.wjlogin_sdk.util.e.K);
        }
        if (w.a(jd.wjlogin_sdk.util.e.M)) {
            w.b(jd.wjlogin_sdk.util.e.M);
        }
        if (w.a(jd.wjlogin_sdk.util.e.L)) {
            w.b(jd.wjlogin_sdk.util.e.L);
        }
    }

    public static String d() {
        return d;
    }

    public static String e() {
        return f19745c;
    }

    public static void f() {
        boolean z = System.currentTimeMillis() - v.a(jd.wjlogin_sdk.util.e.L, 0L) > v.a(jd.wjlogin_sdk.util.e.M, 0L);
        if (TextUtils.isEmpty(f19745c) || TextUtils.isEmpty(d) || TextUtils.isEmpty(f19746e) || TextUtils.isEmpty(f19747f)) {
            i();
        } else if (z) {
            j();
        }
    }

    public static String g() {
        return f19747f;
    }

    public static void h() {
        if (k()) {
            b();
        }
        f19745c = v.e(jd.wjlogin_sdk.util.e.H);
        d = v.e(jd.wjlogin_sdk.util.e.I);
        f19746e = v.e(jd.wjlogin_sdk.util.e.J);
        f19747f = v.e(jd.wjlogin_sdk.util.e.K);
    }

    public static boolean i() {
        byte b2;
        byte b3;
        byte[] bArr = new byte[8192];
        int[] iArr = new int[2];
        byte[] bArr2 = new byte[8192];
        int[] iArr2 = new int[2];
        int jniFirstExchangeStep1 = DecryptorJni.jniFirstExchangeStep1(bArr, iArr, bArr2, iArr2);
        if (p.b) {
            p.b(a, "isFirstStepSuccess encode " + jniFirstExchangeStep1);
        }
        if (jniFirstExchangeStep1 != 0) {
            c0.a((byte) jniFirstExchangeStep1, 1);
            return false;
        }
        try {
            Pair<Integer, byte[]> b4 = new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.c(jd.wjlogin_sdk.util.e0.c.x)).a(b0.a(bArr, iArr[0]).getBytes()).a().b();
            if (b4 != null && ((Integer) b4.first).intValue() == 200) {
                jd.wjlogin_sdk.d.a aVar = new jd.wjlogin_sdk.d.a(jd.wjlogin_sdk.util.a.a((byte[]) b4.second, 2));
                b2 = aVar.a().n();
                jd.wjlogin_sdk.tlvtype.a b5 = aVar.b();
                try {
                    if (b2 == 0) {
                        o0 K = b5.K();
                        if (K != null && K.a() != null) {
                            byte[] a2 = K.a();
                            byte[] bArr3 = new byte[1024];
                            int[] iArr3 = new int[2];
                            byte[] bArr4 = new byte[256];
                            int[] iArr4 = new int[2];
                            byte[] bArr5 = new byte[512];
                            int[] iArr5 = new int[2];
                            byte[] bArr6 = new byte[512];
                            int[] iArr6 = new int[2];
                            int jniFirstExchangeStep2 = DecryptorJni.jniFirstExchangeStep2(bArr5, iArr5, bArr6, iArr6, bArr3, iArr3, bArr4, iArr4, a2, new int[]{a2.length}[0], bArr2, iArr2[0]);
                            if (jniFirstExchangeStep2 != 0) {
                                c0.a((byte) jniFirstExchangeStep2, 1);
                                return false;
                            }
                            if (b5.M() == null) {
                                c0.a((byte) ReplyCode.reply0xac, 1);
                                return false;
                            }
                            a(bArr5, bArr6, bArr3, bArr4, iArr5[0], iArr6[0], iArr3[0], iArr4[0], System.currentTimeMillis(), r0.a() * 1000);
                            return true;
                        }
                        c0.a((byte) ReplyCode.reply0xaa, 1);
                        return false;
                    } else if (b2 == 30) {
                        a();
                        c0.a(b2, 1);
                    }
                } catch (Throwable th) {
                    th = th;
                    b3 = -1;
                    th.printStackTrace();
                    if (!(th instanceof NetworkException)) {
                    }
                    c0.a((byte) ReplyCode.reply0xa9, b2);
                    return false;
                }
            } else {
                b2 = 1;
                b3 = -1;
                try {
                    c0.a((byte) -1, 1);
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    try {
                        if (!(th instanceof NetworkException) && a(th)) {
                            c0.a(b3, b2);
                        } else {
                            c0.a((byte) ReplyCode.reply0xa9, b2);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return false;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            b2 = 1;
        }
        return false;
    }

    public static boolean j() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                if ((th instanceof NetworkException) && a(th)) {
                    c0.a((byte) -1, 2);
                } else {
                    c0.a((byte) ReplyCode.reply0xa9, 2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(f19746e) && !TextUtils.isEmpty(f19747f)) {
            byte[] bArr = new byte[8192];
            int[] iArr = new int[2];
            byte[] bArr2 = new byte[8192];
            int[] iArr2 = new int[2];
            byte[] decode = Base64.decode(f19746e, 2);
            int length = decode.length;
            byte[] bytes = f19747f.getBytes();
            int jniFollowExchangeStep1 = DecryptorJni.jniFollowExchangeStep1(bArr, iArr, bArr2, iArr2, decode, length, bytes, bytes.length);
            if (jniFollowExchangeStep1 != 0) {
                c0.a((byte) jniFollowExchangeStep1, 2);
                return false;
            }
            Pair<Integer, byte[]> b2 = new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.c(jd.wjlogin_sdk.util.e0.c.y)).a(b0.a(bArr, iArr[0]).getBytes()).a().b();
            if (b2 != null && ((Integer) b2.first).intValue() == 200) {
                jd.wjlogin_sdk.d.a aVar = new jd.wjlogin_sdk.d.a(jd.wjlogin_sdk.util.a.a((byte[]) b2.second, 2));
                jd.wjlogin_sdk.d.c a2 = aVar.a();
                jd.wjlogin_sdk.tlvtype.a b3 = aVar.b();
                byte n2 = a2.n();
                if (n2 == 0) {
                    p0 L = b3.L();
                    if (L != null && L.a() != null) {
                        byte[] a3 = b3.L().a();
                        byte[] bArr3 = new byte[512];
                        int[] iArr3 = new int[2];
                        byte[] bArr4 = new byte[512];
                        int[] iArr4 = new int[2];
                        byte[] bArr5 = new byte[1024];
                        int[] iArr5 = new int[2];
                        byte[] bArr6 = new byte[256];
                        int[] iArr6 = new int[2];
                        int jniFollowExchangeStep2 = DecryptorJni.jniFollowExchangeStep2(bArr3, iArr3, bArr4, iArr4, bArr5, iArr5, bArr6, iArr6, a3, new int[]{a3.length}[0], bArr2, iArr2[0], decode, length);
                        if (jniFollowExchangeStep2 != 0) {
                            c0.a((byte) jniFollowExchangeStep2, 2);
                            return false;
                        }
                        if (b3.M() == null) {
                            c0.a((byte) ReplyCode.reply0xac, 2);
                            return false;
                        }
                        a(bArr3, bArr4, bArr5, bArr6, iArr3[0], iArr4[0], iArr5[0], iArr6[0], System.currentTimeMillis(), r0.a() * 1000);
                        return true;
                    }
                    c0.a((byte) ReplyCode.reply0xaa, 2);
                    return false;
                } else if (n2 == 30) {
                    a();
                    c0.a(n2, 2);
                }
            } else {
                c0.a((byte) -1, 2);
            }
            return false;
        }
        return i();
    }

    private static boolean k() {
        boolean a2 = v.a(jd.wjlogin_sdk.util.e.q, false);
        v.b(jd.wjlogin_sdk.util.e.q, true);
        if (p.b) {
            p.b(a, "isNeedCopy read= " + a2);
            if (jd.wjlogin_sdk.util.g.d() != null) {
                p.b(a, " init APP getLastUpdateTime()=" + jd.wjlogin_sdk.util.g.d().k());
                p.b(a, " init APP getFirstInstallTime()=" + jd.wjlogin_sdk.util.g.d().j());
            }
        }
        if (!a2 && jd.wjlogin_sdk.util.g.d() != null && 0 != jd.wjlogin_sdk.util.g.d().k() && 0 != jd.wjlogin_sdk.util.g.d().j() && jd.wjlogin_sdk.util.g.d().k() != jd.wjlogin_sdk.util.g.d().j()) {
            p.b(a, "isNeedCopy \u662f\u5347\u7ea7\u4e0a\u6765\u7684");
            return true;
        }
        p.b(a, "isNeedCopy \u662f\u65b0\u88c5\u6216\u65b0\u7248\u672csdk");
        return false;
    }

    public static void a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i2, int i3, int i4, int i5, long j2, long j3) {
        f19745c = b0.a(bArr, i2);
        d = b0.a(bArr2, i3);
        f19746e = Base64.encodeToString(bArr3, 0, i4, 2);
        f19747f = b0.a(bArr4, i5);
        p.b(a, "saveKeys mSidV2=" + f19747f);
        v.a(jd.wjlogin_sdk.util.e.H, f19745c);
        v.a(jd.wjlogin_sdk.util.e.I, d);
        v.a(jd.wjlogin_sdk.util.e.J, f19746e);
        v.a(jd.wjlogin_sdk.util.e.K, f19747f);
        v.b(jd.wjlogin_sdk.util.e.M, j3);
        v.b(jd.wjlogin_sdk.util.e.L, j2);
    }

    public static void a() {
        f19745c = "";
        d = "";
        f19746e = "";
        f19747f = "";
        v.a(jd.wjlogin_sdk.util.e.H, "");
        v.a(jd.wjlogin_sdk.util.e.I, "");
        v.a(jd.wjlogin_sdk.util.e.J, "");
        v.a(jd.wjlogin_sdk.util.e.K, "");
        v.b(jd.wjlogin_sdk.util.e.M, 0L);
        v.b(jd.wjlogin_sdk.util.e.L, 0L);
    }
}
