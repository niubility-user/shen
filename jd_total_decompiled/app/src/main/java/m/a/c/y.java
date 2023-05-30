package m.a.c;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class y implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    int f20426g;

    public y() {
        this.f20426g = 0;
        this.f20426g = 0;
    }

    private void c(m.a.b.i iVar) throws IOException {
        if (iVar.w() && iVar.y()) {
            m.a.b.i e2 = iVar.f20295c.e();
            this.f20426g = e2.o();
            if (e2.f20295c.a() != 0) {
                throw new IOException("X.509 version, bad format");
            }
        }
    }

    private int f() {
        return this.f20426g;
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        if (this.f20426g == 0) {
            return;
        }
        m.a.b.h hVar = new m.a.b.h();
        hVar.k(this.f20426g);
        m.a.b.h hVar2 = new m.a.b.h();
        hVar2.y(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) 0), hVar);
        outputStream.write(hVar2.toByteArray());
    }

    public int b(int i2) {
        return this.f20426g - i2;
    }

    public void d(String str) throws IOException {
        if (str.equalsIgnoreCase("number")) {
            this.f20426g = 0;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateVersion.");
    }

    public Object e(String str) throws IOException {
        if (str.equalsIgnoreCase("number")) {
            return new Integer(f());
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateVersion.");
    }

    public void g(String str, Object obj) throws IOException {
        if (obj instanceof Integer) {
            if (str.equalsIgnoreCase("number")) {
                this.f20426g = ((Integer) obj).intValue();
                return;
            }
            throw new IOException("Attribute name not recognized by CertAttrSet: CertificateVersion.");
        }
        throw new IOException("Attribute must be of type Integer.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "version";
    }

    public String toString() {
        return "Version: V" + (this.f20426g + 1);
    }

    public y(m.a.b.i iVar) throws IOException {
        this.f20426g = 0;
        this.f20426g = 0;
        c(iVar);
    }
}
