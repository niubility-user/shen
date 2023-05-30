package m.a.c;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class o0 extends e0 implements l<String>, Cloneable {

    /* renamed from: j  reason: collision with root package name */
    private j0 f20391j;

    /* renamed from: k  reason: collision with root package name */
    private j0 f20392k;

    private void f() throws IOException {
        if (this.f20391j == null && this.f20392k == null) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        if (this.f20391j != null) {
            m.a.b.h hVar3 = new m.a.b.h();
            this.f20391j.a(hVar3);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 0), hVar3);
        }
        if (this.f20392k != null) {
            m.a.b.h hVar4 = new m.a.b.h();
            this.f20392k.a(hVar4);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 1), hVar4);
        }
        hVar.y((byte) 48, hVar2);
        this.f20335i = hVar.toByteArray();
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.I;
            this.f20334h = true;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public Object clone() {
        try {
            o0 o0Var = (o0) super.clone();
            j0 j0Var = this.f20391j;
            if (j0Var != null) {
                o0Var.f20391j = (j0) j0Var.clone();
            }
            j0 j0Var2 = this.f20392k;
            if (j0Var2 != null) {
                o0Var.f20392k = (j0) j0Var2.clone();
            }
            return o0Var;
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("CloneNotSupportedException while cloning NameConstraintsException. This should never happen.");
        }
    }

    @Override // m.a.c.l
    public String getName() {
        return "NameConstraints";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("NameConstraints: [");
        String str2 = "";
        if (this.f20391j == null) {
            str = "";
        } else {
            str = "\n    Permitted:" + this.f20391j.toString();
        }
        sb.append(str);
        if (this.f20392k != null) {
            str2 = "\n    Excluded:" + this.f20392k.toString();
        }
        sb.append(str2);
        sb.append("   ]\n");
        return sb.toString();
    }
}
