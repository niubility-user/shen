package com.jd.security.jdguard.core;

import android.text.TextUtils;
import com.jd.security.jdguard.d.a;
import com.jd.security.jdguard.f.f;
import java.math.BigInteger;
import java.util.Map;

/* loaded from: classes17.dex */
public class e extends c {

    /* renamed from: l */
    private static e f6900l;

    /* renamed from: h */
    private com.jd.security.jdguard.e.c f6901h;

    /* renamed from: i */
    private String f6902i;

    /* renamed from: j */
    private String f6903j;

    /* renamed from: k */
    private long f6904k;

    /* loaded from: classes17.dex */
    class a implements com.jd.security.jdguard.d.b {
        a() {
            e.this = r1;
        }

        @Override // com.jd.security.jdguard.d.b
        public void a(a.d dVar, long j2, boolean z) {
            int i2 = b.a[dVar.ordinal()];
            if (i2 == 1) {
                if (z) {
                    if (e.this.f6901h != null) {
                        e.this.f6901h.c(0, j2);
                    }
                } else if (e.this.f6901h != null) {
                    e.this.f6901h.c(-6101, j2);
                }
            } else if (i2 != 2) {
            } else {
                if (z) {
                    if (e.this.f6901h != null) {
                        e.this.f6901h.b(0, j2);
                    }
                } else if (e.this.f6901h != null) {
                    e.this.f6901h.b(-6101, j2);
                }
            }
        }

        @Override // com.jd.security.jdguard.d.b
        public void b(a.d dVar, long j2) {
            if (b.a[dVar.ordinal()] == 1 && e.this.f6901h != null) {
                e.this.f6901h.c(-6102, j2);
            }
        }
    }

    /* loaded from: classes17.dex */
    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[a.d.values().length];
            a = iArr;
            try {
                iArr[a.d.STATIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.d.ENV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private e(com.jd.security.jdguard.c cVar) {
        super(cVar);
        this.f6902i = null;
        this.f6903j = null;
    }

    private boolean t() {
        com.jd.security.jdguard.c e2 = e();
        if (e2 == null) {
            return false;
        }
        return e2.j();
    }

    public static e x(com.jd.security.jdguard.c cVar) {
        if (f6900l == null) {
            synchronized (e.class) {
                if (f6900l == null) {
                    f6900l = new e(cVar);
                }
            }
        }
        return f6900l;
    }

    public String A(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (l()) {
            Object[] main = Bridge.main(101, new Object[]{bArr, u(), s(), w(), q()});
            if (main == null) {
                return null;
            }
            if (main[0] instanceof Integer) {
                return main[0] + "";
            }
            return (String) main[0];
        }
        return "-3107";
    }

    @Override // com.jd.security.jdguard.core.c
    protected void a() {
        com.jd.security.jdguard.d.a f2 = com.jd.security.jdguard.d.a.f();
        f2.b(this.a);
        f2.e(e().e());
        f2.j(i());
        f2.c(s());
        f2.i(new a());
        f2.g();
        com.jd.security.jdguard.e.c cVar = this.f6901h;
        if (cVar != null) {
            cVar.d(0, System.currentTimeMillis() - this.f6904k);
        }
    }

    @Override // com.jd.security.jdguard.core.c
    protected void b() {
        this.f6904k = System.currentTimeMillis();
    }

    @Override // com.jd.security.jdguard.core.c
    protected boolean c() {
        Object[] main = Bridge.main(103, new Object[1]);
        if (main == null) {
            return false;
        }
        int intValue = ((Integer) main[0]).intValue();
        if (intValue == 0) {
            return true;
        }
        com.jd.security.jdguard.e.c cVar = this.f6901h;
        if (cVar != null) {
            cVar.d(intValue, System.currentTimeMillis() - this.f6904k);
        }
        com.jd.security.jdguard.f.d.e(new RuntimeException("JDGuard native init failed: errno " + intValue));
        return false;
    }

    @Override // com.jd.security.jdguard.core.c
    public boolean j() {
        if (t()) {
            return f.e();
        }
        return true;
    }

    public String q() {
        Map<String, String> jDGConfigs;
        if (e() == null || e().e() == null || (jDGConfigs = e().e().getJDGConfigs()) == null) {
            return "83";
        }
        String str = jDGConfigs.get("ano");
        return com.jd.security.jdguard.f.a.d(str) ? new BigInteger(str, 16).toString() : "83";
    }

    public byte[] r(byte[] bArr, int i2) throws Exception {
        if (bArr == null) {
            return null;
        }
        if (l()) {
            Object[] main = Bridge.main(104, new Object[]{bArr, Integer.valueOf(i2)});
            if (main != null) {
                if (main[0] instanceof byte[]) {
                    return (byte[]) main[0];
                }
                return null;
            }
            throw new Exception("JDGuard crypto internal error.");
        }
        throw new Exception("JDGuard not inited yet.");
    }

    public String s() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f6903j == null) {
            if (e() != null && e().e() != null) {
                this.f6903j = e().e().getDfpEid();
            }
            String str = this.f6903j;
            if (str == null) {
                com.jd.security.jdguard.e.c cVar = this.f6901h;
                if (cVar != null) {
                    cVar.a(-5101, System.currentTimeMillis() - currentTimeMillis);
                }
            } else if (str.isEmpty()) {
                com.jd.security.jdguard.e.c cVar2 = this.f6901h;
                if (cVar2 != null) {
                    cVar2.a(-5102, System.currentTimeMillis() - currentTimeMillis);
                }
            } else {
                com.jd.security.jdguard.e.c cVar3 = this.f6901h;
                if (cVar3 != null) {
                    cVar3.a(0, System.currentTimeMillis() - currentTimeMillis);
                }
            }
        }
        String str2 = this.f6903j;
        return str2 == null ? "" : str2;
    }

    public String u() {
        long currentTimeMillis = System.currentTimeMillis();
        String k2 = com.jd.security.jdguard.d.a.f().k();
        this.f6902i = k2;
        if (TextUtils.isEmpty(k2)) {
            com.jd.security.jdguard.e.c cVar = this.f6901h;
            if (cVar != null) {
                cVar.c(-6101, System.currentTimeMillis() - currentTimeMillis);
            }
        } else {
            com.jd.security.jdguard.e.c cVar2 = this.f6901h;
            if (cVar2 != null) {
                cVar2.c(-1104, System.currentTimeMillis() - currentTimeMillis);
            }
        }
        return this.f6902i;
    }

    public String v() {
        long currentTimeMillis = System.currentTimeMillis();
        String d = com.jd.security.jdguard.d.a.f().d();
        if (TextUtils.isEmpty(d)) {
            com.jd.security.jdguard.e.c cVar = this.f6901h;
            if (cVar != null) {
                cVar.b(-6102, System.currentTimeMillis() - currentTimeMillis);
            }
        } else {
            com.jd.security.jdguard.e.c cVar2 = this.f6901h;
            if (cVar2 != null) {
                cVar2.b(-1104, System.currentTimeMillis() - currentTimeMillis);
            }
        }
        return d;
    }

    public String w() {
        return "1.0";
    }

    public com.jd.security.jdguard.e.c y() {
        return this.f6901h;
    }

    public void z(com.jd.security.jdguard.e.c cVar) {
        this.f6901h = cVar;
    }
}
