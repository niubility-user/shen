package m.a.c;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class t0 extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private int f20415j;

    /* renamed from: k  reason: collision with root package name */
    private int f20416k;

    private void f() throws IOException {
        if (this.f20415j == -1 && this.f20416k == -1) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        if (this.f20415j != -1) {
            m.a.b.h hVar3 = new m.a.b.h();
            hVar3.k(this.f20415j);
            hVar.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 0), hVar3);
        }
        if (this.f20416k != -1) {
            m.a.b.h hVar4 = new m.a.b.h();
            hVar4.k(this.f20416k);
            hVar.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 1), hVar4);
        }
        hVar2.y((byte) 48, hVar);
        this.f20335i = hVar2.toByteArray();
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.J;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "PolicyConstraints";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str;
        String str2;
        String str3 = super.toString() + "PolicyConstraints: [  Require: ";
        if (this.f20415j == -1) {
            str = str3 + "unspecified;";
        } else {
            str = str3 + this.f20415j + ";";
        }
        String str4 = str + "\tInhibit: ";
        if (this.f20416k == -1) {
            str2 = str4 + "unspecified";
        } else {
            str2 = str4 + this.f20416k;
        }
        return str2 + " ]\n";
    }
}
