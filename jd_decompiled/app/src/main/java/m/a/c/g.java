package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class g extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private List<d> f20346j;

    private void f() throws IOException {
        if (this.f20346j.isEmpty()) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        Iterator<d> it = this.f20346j.iterator();
        while (it.hasNext()) {
            it.next().a(hVar);
        }
        m.a.b.h hVar2 = new m.a.b.h();
        hVar2.y((byte) 48, hVar);
        this.f20335i = hVar2.toByteArray();
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.S;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "AuthorityInfoAccess";
    }

    @Override // m.a.c.e0
    public String toString() {
        return super.toString() + "AuthorityInfoAccess [\n  " + this.f20346j + "\n]\n";
    }
}
