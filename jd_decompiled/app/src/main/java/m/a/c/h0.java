package m.a.c;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class h0 {
    private final List<f0> a;

    public h0(m.a.b.i iVar) throws IOException {
        this();
        if (iVar.a == 48) {
            if (iVar.f20295c.a() != 0) {
                while (iVar.f20295c.a() != 0) {
                    a(new f0(iVar.f20295c.e()));
                }
                return;
            }
            throw new IOException("No data available in passed DER encoded value.");
        }
        throw new IOException("Invalid encoding for GeneralNames.");
    }

    public h0 a(f0 f0Var) {
        f0Var.getClass();
        this.a.add(f0Var);
        return this;
    }

    public void b(m.a.b.h hVar) throws IOException {
        if (d()) {
            return;
        }
        m.a.b.h hVar2 = new m.a.b.h();
        Iterator<f0> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a(hVar2);
        }
        hVar.y((byte) 48, hVar2);
    }

    public f0 c(int i2) {
        return this.a.get(i2);
    }

    public boolean d() {
        return this.a.isEmpty();
    }

    public List<f0> e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h0) {
            return this.a.equals(((h0) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public h0() {
        this.a = new ArrayList();
    }
}
