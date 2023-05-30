package m.a.c;

import com.jd.dynamic.DYConstants;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class b1 extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private m0 f20306j;

    private void f() throws IOException {
        if (this.f20306j == null) {
            this.f20335i = null;
            return;
        }
        this.f20306j.a(new m.a.b.h());
        throw null;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.A;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "SubjectKeyIdentifier";
    }

    @Override // m.a.c.e0
    public String toString() {
        return super.toString() + "SubjectKeyIdentifier [\n" + DYConstants.DY_NULL_STR + "]\n";
    }
}
