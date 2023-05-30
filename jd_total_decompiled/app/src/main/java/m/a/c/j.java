package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class j extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private List<b0> f20367j;

    /* renamed from: k  reason: collision with root package name */
    private String f20368k;

    private void g() throws IOException {
        if (this.f20367j.isEmpty()) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        Iterator<b0> it = this.f20367j.iterator();
        while (it.hasNext()) {
            it.next().a(hVar);
        }
        m.a.b.h hVar2 = new m.a.b.h();
        hVar2.y((byte) 48, hVar);
        this.f20335i = hVar2.toByteArray();
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        f(outputStream, s0.K, false);
    }

    protected void f(OutputStream outputStream, m.a.b.j jVar, boolean z) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = jVar;
            this.f20334h = z;
            g();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return this.f20368k;
    }

    @Override // m.a.c.e0
    public String toString() {
        return super.toString() + this.f20368k + " [\n  " + this.f20367j + "]\n";
    }
}
