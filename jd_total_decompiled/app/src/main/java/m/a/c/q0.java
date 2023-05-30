package m.a.c;

import java.io.IOException;

/* loaded from: classes11.dex */
public class q0 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private m.a.b.j f20397g;

    public q0(m.a.b.i iVar) throws IOException {
        this.f20397g = iVar.p();
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) throws UnsupportedOperationException {
        if (g0Var != null && g0Var.getType() == 8) {
            if (equals((q0) g0Var)) {
                return 0;
            }
            throw new UnsupportedOperationException("Narrowing and widening are not supported for OIDNames");
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        hVar.p(this.f20397g);
    }

    public m.a.b.j c() {
        return this.f20397g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q0) {
            return this.f20397g.equals(((q0) obj).f20397g);
        }
        return false;
    }

    @Override // m.a.c.g0
    public int getType() {
        return 8;
    }

    public int hashCode() {
        return this.f20397g.hashCode();
    }

    public String toString() {
        return "OIDName: " + this.f20397g.toString();
    }
}
