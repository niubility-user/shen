package m.a.c;

import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes11.dex */
public class f1 implements g0, Principal {
    private static final int[] A;
    private static final int[] B;
    private static final int[] C;
    public static final m.a.b.j D;
    public static final m.a.b.j E;
    public static final m.a.b.j F;
    public static final m.a.b.j G;
    public static final m.a.b.j H;
    public static final m.a.b.j I;
    public static final m.a.b.j J;
    public static final m.a.b.j K;
    public static final m.a.b.j L;
    public static final m.a.b.j M;
    public static final m.a.b.j N;
    public static final m.a.b.j O;
    public static final m.a.b.j P;
    public static final m.a.b.j Q;
    public static final m.a.b.j R;
    public static final m.a.b.j S;
    public static final m.a.b.j T;

    /* renamed from: l */
    private static final Map<m.a.b.j, m.a.b.j> f20338l = new HashMap();

    /* renamed from: m */
    private static final int[] f20339m;

    /* renamed from: n */
    private static final int[] f20340n;
    private static final int[] o;
    private static final int[] p;
    private static final int[] q;
    private static final int[] r;
    private static final int[] s;
    private static final int[] t;
    private static final int[] u;
    private static final int[] v;
    private static final int[] w;
    private static final int[] x;
    private static final int[] y;
    private static final int[] z;

    /* renamed from: g */
    private String f20341g;

    /* renamed from: h */
    private String f20342h;

    /* renamed from: i */
    private String f20343i;

    /* renamed from: j */
    private x0[] f20344j;

    /* renamed from: k */
    private X500Principal f20345k;

    static {
        int[] iArr = {2, 5, 4, 3};
        f20339m = iArr;
        int[] iArr2 = {2, 5, 4, 4};
        f20340n = iArr2;
        int[] iArr3 = {2, 5, 4, 5};
        o = iArr3;
        int[] iArr4 = {2, 5, 4, 6};
        p = iArr4;
        int[] iArr5 = {2, 5, 4, 7};
        q = iArr5;
        int[] iArr6 = {2, 5, 4, 8};
        r = iArr6;
        int[] iArr7 = {2, 5, 4, 9};
        s = iArr7;
        int[] iArr8 = {2, 5, 4, 10};
        t = iArr8;
        int[] iArr9 = {2, 5, 4, 11};
        u = iArr9;
        int[] iArr10 = {2, 5, 4, 12};
        v = iArr10;
        int[] iArr11 = {2, 5, 4, 42};
        w = iArr11;
        int[] iArr12 = {2, 5, 4, 43};
        x = iArr12;
        int[] iArr13 = {2, 5, 4, 44};
        y = iArr13;
        int[] iArr14 = {2, 5, 4, 46};
        z = iArr14;
        int[] iArr15 = {1, 3, 6, 1, 4, 1, 42, 2, 11, 2, 1};
        A = iArr15;
        int[] iArr16 = {0, 9, R2.attr.yg_aspectRatio, 19200300, 100, 1, 25};
        B = iArr16;
        int[] iArr17 = {0, 9, R2.attr.yg_aspectRatio, 19200300, 100, 1, 1};
        C = iArr17;
        D = i(m.a.b.j.newInternal(iArr));
        T = i(m.a.b.j.newInternal(iArr3));
        E = i(m.a.b.j.newInternal(iArr4));
        F = i(m.a.b.j.newInternal(iArr5));
        G = i(m.a.b.j.newInternal(iArr8));
        H = i(m.a.b.j.newInternal(iArr9));
        I = i(m.a.b.j.newInternal(iArr6));
        J = i(m.a.b.j.newInternal(iArr7));
        K = i(m.a.b.j.newInternal(iArr10));
        L = i(m.a.b.j.newInternal(iArr14));
        M = i(m.a.b.j.newInternal(iArr2));
        N = i(m.a.b.j.newInternal(iArr11));
        O = i(m.a.b.j.newInternal(iArr12));
        P = i(m.a.b.j.newInternal(iArr13));
        Q = i(m.a.b.j.newInternal(iArr15));
        R = i(m.a.b.j.newInternal(iArr16));
        S = i(m.a.b.j.newInternal(iArr17));
    }

    public f1(m.a.b.i iVar) throws IOException {
        this(iVar.E());
    }

    private void d() {
        x0[] x0VarArr = this.f20344j;
        if (x0VarArr.length == 1) {
            this.f20341g = x0VarArr[0].toString();
            return;
        }
        StringBuilder sb = new StringBuilder(48);
        x0[] x0VarArr2 = this.f20344j;
        if (x0VarArr2 != null) {
            for (int length = x0VarArr2.length - 1; length >= 0; length--) {
                if (length != this.f20344j.length - 1) {
                    sb.append(", ");
                }
                sb.append(this.f20344j[length].toString());
            }
        }
        this.f20341g = sb.toString();
    }

    private String e(Map<String, String> map) {
        if (this.f20344j.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(48);
        for (int length = this.f20344j.length - 1; length >= 0; length--) {
            if (length < this.f20344j.length - 1) {
                sb.append(',');
            }
            sb.append(this.f20344j[length].b(map));
        }
        return sb.toString();
    }

    public static m.a.b.j i(m.a.b.j jVar) {
        Map<m.a.b.j, m.a.b.j> map = f20338l;
        m.a.b.j jVar2 = map.get(jVar);
        if (jVar2 != null) {
            return jVar2;
        }
        map.put(jVar, jVar);
        return jVar;
    }

    private boolean k(f1 f1Var) {
        if (this == f1Var) {
            return true;
        }
        if (f1Var == null) {
            return false;
        }
        x0[] x0VarArr = f1Var.f20344j;
        if (x0VarArr.length == 0) {
            return true;
        }
        x0[] x0VarArr2 = this.f20344j;
        if (x0VarArr2.length == 0 || x0VarArr2.length < x0VarArr.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            x0[] x0VarArr3 = f1Var.f20344j;
            if (i2 >= x0VarArr3.length) {
                return true;
            }
            if (!this.f20344j[i2].equals(x0VarArr3[i2])) {
                return false;
            }
            i2++;
        }
    }

    private void l(m.a.b.g gVar) throws IOException {
        m.a.b.i[] m2;
        byte[] x2 = gVar.x();
        try {
            m2 = gVar.m(5);
        } catch (IOException unused) {
            m2 = x2 == null ? null : new m.a.b.g(new m.a.b.i((byte) 48, x2).D()).m(5);
        }
        if (m2 == null) {
            this.f20344j = new x0[0];
            return;
        }
        this.f20344j = new x0[m2.length];
        for (int i2 = 0; i2 < m2.length; i2++) {
            this.f20344j[i2] = new x0(m2[i2]);
        }
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) throws UnsupportedOperationException {
        if (g0Var != null && g0Var.getType() == 4) {
            f1 f1Var = (f1) g0Var;
            if (f1Var.equals(this)) {
                return 0;
            }
            if (f1Var.f20344j.length != 0) {
                if (this.f20344j.length == 0 || f1Var.k(this)) {
                    return 1;
                }
                if (!k(f1Var)) {
                    return 3;
                }
            }
            return 2;
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        int i2 = 0;
        while (true) {
            x0[] x0VarArr = this.f20344j;
            if (i2 < x0VarArr.length) {
                x0VarArr[i2].a(hVar2);
                i2++;
            } else {
                hVar.y((byte) 48, hVar2);
                return;
            }
        }
    }

    public X500Principal c() {
        if (this.f20345k == null) {
            try {
                if (this.f20341g == null) {
                    d();
                }
                this.f20345k = new X500Principal(this.f20341g);
            } catch (Exception e2) {
                throw new RuntimeException("Unexpected exception", e2);
            }
        }
        return this.f20345k;
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj instanceof f1) {
            f1 f1Var = (f1) obj;
            String str2 = this.f20343i;
            if (str2 != null && (str = f1Var.f20343i) != null) {
                return str2.equals(str);
            }
            int length = this.f20344j.length;
            if (length != f1Var.f20344j.length) {
                return false;
            }
            for (int i2 = 0; i2 < length; i2++) {
                if (this.f20344j[i2].a.length != f1Var.f20344j[i2].a.length) {
                    return false;
                }
            }
            return f().equals(f1Var.f());
        }
        return false;
    }

    public String f() {
        String str = this.f20343i;
        if (str != null) {
            return str;
        }
        if (this.f20344j.length == 0) {
            this.f20343i = "";
            return "";
        }
        StringBuilder sb = new StringBuilder(48);
        for (int length = this.f20344j.length - 1; length >= 0; length--) {
            if (length < this.f20344j.length - 1) {
                sb.append(',');
            }
            sb.append(this.f20344j[length].c(true));
        }
        String sb2 = sb.toString();
        this.f20343i = sb2;
        return sb2;
    }

    public String g() {
        return h(Collections.emptyMap());
    }

    @Override // java.security.Principal
    public String getName() {
        return toString();
    }

    @Override // m.a.c.g0
    public int getType() {
        return 4;
    }

    public String h(Map<String, String> map) {
        if (map.isEmpty()) {
            String str = this.f20342h;
            if (str != null) {
                return str;
            }
            String e2 = e(map);
            this.f20342h = e2;
            return e2;
        }
        return e(map);
    }

    @Override // java.security.Principal
    public int hashCode() {
        return f().hashCode();
    }

    public boolean j() {
        int length = this.f20344j.length;
        if (length == 0) {
            return true;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (this.f20344j[i2].a.length != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // java.security.Principal
    public String toString() {
        if (this.f20341g == null) {
            d();
        }
        return this.f20341g;
    }

    public f1(m.a.b.g gVar) throws IOException {
        l(gVar);
    }
}
