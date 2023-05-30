package m.a.c;

import java.io.IOException;
import java.math.BigInteger;

/* loaded from: classes11.dex */
public class z0 {
    private BigInteger a;

    public z0(m.a.b.i iVar) throws IOException {
        a(iVar);
    }

    private void a(m.a.b.i iVar) throws IOException {
        this.a = iVar.h();
        if (iVar.f20295c.a() != 0) {
            throw new IOException("Excess SerialNumber data");
        }
    }

    public void b(m.a.b.h hVar) throws IOException {
        hVar.l(this.a);
    }

    public BigInteger c() {
        return this.a;
    }

    public String toString() {
        return "SerialNumber: [" + m.a.b.c.g(this.a) + "]";
    }
}
