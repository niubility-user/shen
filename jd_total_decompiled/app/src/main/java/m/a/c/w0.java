package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/* loaded from: classes11.dex */
public class w0 extends e0 implements l<String> {

    /* renamed from: j  reason: collision with root package name */
    private Date f20422j;

    /* renamed from: k  reason: collision with root package name */
    private Date f20423k;

    private void f() throws IOException {
        if (this.f20422j == null && this.f20423k == null) {
            this.f20335i = null;
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        if (this.f20422j != null) {
            m.a.b.h hVar3 = new m.a.b.h();
            hVar3.i(this.f20422j);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 0), hVar3);
        }
        if (this.f20423k != null) {
            m.a.b.h hVar4 = new m.a.b.h();
            hVar4.i(this.f20423k);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 1), hVar4);
        }
        hVar.y((byte) 48, hVar2);
        this.f20335i = hVar.toByteArray();
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        if (this.f20335i == null) {
            this.f20333g = s0.C;
            this.f20334h = false;
            f();
        }
        super.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    @Override // m.a.c.l
    public String getName() {
        return "PrivateKeyUsage";
    }

    @Override // m.a.c.e0
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("PrivateKeyUsage: [\n");
        String str2 = "";
        if (this.f20422j == null) {
            str = "";
        } else {
            str = "From: " + this.f20422j.toString() + ", ";
        }
        sb.append(str);
        if (this.f20423k != null) {
            str2 = "To: " + this.f20423k.toString();
        }
        sb.append(str2);
        sb.append("]\n");
        return sb.toString();
    }
}
