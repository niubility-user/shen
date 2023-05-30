package com.jingdong.sdk.talos.inner;

import com.jingdong.sdk.talos.a;
import com.jingdong.sdk.talos.inner.e;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
class CProtocol implements d {
    private static CProtocol d;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f15479e;
    private boolean a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private Set<Integer> f15480c = Collections.synchronizedSet(new HashSet());

    static {
        try {
            if (!e.f.b("logx", CProtocol.class)) {
                System.loadLibrary("logx");
            }
            f15479e = true;
        } catch (Throwable th) {
            th.printStackTrace();
            f15479e = false;
        }
    }

    CProtocol() {
    }

    private native void clogx_debug(boolean z);

    private native void clogx_flush();

    private native int clogx_init(String str, String str2, int i2, String str3, String str4);

    private native int clogx_open(String str);

    private static native String clogx_secret();

    private native int clogx_write(int i2, String str, long j2, String str2, long j3, int i3, int i4);

    private void d(String str, int i2) {
        if (i2 >= 0 || !"clogx_write".endsWith(str) || i2 == -4060 || this.f15480c.contains(Integer.valueOf(i2))) {
            return;
        }
        this.f15480c.add(Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e() {
        return f15479e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CProtocol f() {
        if (d == null) {
            synchronized (CProtocol.class) {
                if (d == null) {
                    d = new CProtocol();
                }
            }
        }
        return d;
    }

    public static String g() {
        if (f15479e) {
            try {
                return clogx_secret();
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
                return "";
            }
        }
        return "";
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void a(int i2, String str, long j2, String str2, long j3, boolean z, int i3) {
        if (this.b && f15479e) {
            try {
                int clogx_write = clogx_write(i2, str, j2, str2, j3, z ? 1 : 0, i3);
                if (clogx_write != -4010) {
                    d("clogx_write", clogx_write);
                }
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
                d("clogx_write", -4060);
            }
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void a(String str) {
        if (this.a && f15479e) {
            try {
                int clogx_open = clogx_open(str);
                this.b = true;
                d("clogx_open", clogx_open);
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
                d("clogx_open", -2070);
            }
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void a(boolean z) {
        if (this.a && f15479e) {
            try {
                clogx_debug(z);
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void b(h hVar) {
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void c() {
        if (this.b && f15479e) {
            try {
                clogx_flush();
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void c(String str, String str2, int i2, String str3, String str4) {
        if (this.a) {
            return;
        }
        if (!f15479e) {
            d("logx_loadso", -5020);
            return;
        }
        try {
            a.g();
            if (e.C0748e.b()) {
                d("clogx_init", clogx_init(str, str2, i2, str3, str4));
            }
            this.a = true;
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            d("clogx_init", -1060);
        }
    }
}
