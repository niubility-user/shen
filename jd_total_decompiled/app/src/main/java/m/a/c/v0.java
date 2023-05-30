package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class v0 extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private List<t> f20420j;

    public v0() {
        this.f20333g = s0.B;
        this.f20334h = false;
        this.f20420j = new ArrayList();
    }

    private void f() throws IOException {
        List<t> list = this.f20420j;
        if (list != null && !list.isEmpty()) {
            m.a.b.h hVar = new m.a.b.h();
            m.a.b.h hVar2 = new m.a.b.h();
            Iterator<t> it = this.f20420j.iterator();
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
            this.f20333g = s0.E;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "PolicyMappings";
    }

    @Override // m.a.c.e0
    public String toString() {
        if (this.f20420j == null) {
            return "";
        }
        return super.toString() + "PolicyMappings [\n" + this.f20420j.toString() + "]\n";
    }
}
