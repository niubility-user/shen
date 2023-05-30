package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class r extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private List<u0> f20398j;

    private void f() throws IOException {
        List<u0> list = this.f20398j;
        if (list != null && !list.isEmpty()) {
            m.a.b.h hVar = new m.a.b.h();
            m.a.b.h hVar2 = new m.a.b.h();
            Iterator<u0> it = this.f20398j.iterator();
            if (!it.hasNext()) {
                hVar.y((byte) 48, hVar2);
                this.f20335i = hVar.toByteArray();
                return;
            }
            it.next().a(hVar2);
            throw null;
        }
        this.f20335i = null;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.D;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "CertificatePolicies";
    }

    @Override // m.a.c.e0
    public String toString() {
        if (this.f20398j == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("CertificatePolicies [\n");
        Iterator<u0> it = this.f20398j.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        sb.append("]\n");
        return sb.toString();
    }
}
