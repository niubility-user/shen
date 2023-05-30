package m.a.c;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class r0 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private String f20399g;

    /* renamed from: h  reason: collision with root package name */
    private m.a.b.j f20400h;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f20401i;

    /* renamed from: j  reason: collision with root package name */
    private g0 f20402j;

    /* renamed from: k  reason: collision with root package name */
    private int f20403k = -1;

    public r0(m.a.b.i iVar) throws IOException {
        this.f20401i = null;
        this.f20402j = null;
        m.a.b.g E = iVar.E();
        this.f20400h = E.k();
        byte[] D = E.e().D();
        this.f20401i = D;
        g0 c2 = c(this.f20400h, D);
        this.f20402j = c2;
        if (c2 != null) {
            this.f20399g = c2.toString();
            return;
        }
        this.f20399g = "Unrecognized ObjectIdentifier: " + this.f20400h.toString();
    }

    private g0 c(m.a.b.j jVar, byte[] bArr) throws IOException {
        try {
            Class b = p0.b(jVar);
            if (b == null) {
                return null;
            }
            return (g0) b.getConstructor(Object.class).newInstance(bArr);
        } catch (Exception e2) {
            throw ((IOException) new IOException("Instantiation error: " + e2).initCause(e2));
        }
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) {
        if (g0Var != null && g0Var.getType() == 0) {
            throw new UnsupportedOperationException("Narrowing, widening, and matching are not supported for OtherName.");
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        g0 g0Var = this.f20402j;
        if (g0Var != null) {
            g0Var.b(hVar);
            return;
        }
        m.a.b.h hVar2 = new m.a.b.h();
        hVar2.p(this.f20400h);
        hVar2.z(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 0), this.f20401i);
        hVar.y((byte) 48, hVar2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof r0) {
            r0 r0Var = (r0) obj;
            if (r0Var.f20400h.equals(this.f20400h)) {
                try {
                    g0 c2 = c(r0Var.f20400h, r0Var.f20401i);
                    if (c2 != null) {
                        return c2.a(this) == 0;
                    }
                    return Arrays.equals(this.f20401i, r0Var.f20401i);
                } catch (IOException | UnsupportedOperationException unused) {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    @Override // m.a.c.g0
    public int getType() {
        return 0;
    }

    public int hashCode() {
        if (this.f20403k == -1) {
            this.f20403k = this.f20400h.hashCode() + 37;
            int i2 = 0;
            while (true) {
                byte[] bArr = this.f20401i;
                if (i2 >= bArr.length) {
                    break;
                }
                this.f20403k = (this.f20403k * 37) + bArr[i2];
                i2++;
            }
        }
        return this.f20403k;
    }

    public String toString() {
        return "Other-Name: " + this.f20399g;
    }
}
