package m.a.c;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class q implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private d1 f20396g;

    public q(m.a.b.i iVar) throws IOException {
        this.f20396g = new d1(iVar);
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        this.f20396g.a(hVar, m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 1));
        outputStream.write(hVar.toByteArray());
    }

    public void b(String str) throws IOException {
        if (str.equalsIgnoreCase("id")) {
            this.f20396g = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateIssuerUniqueIdentity.");
    }

    public Object c(String str) throws IOException {
        if (str.equalsIgnoreCase("id")) {
            return this.f20396g;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateIssuerUniqueIdentity.");
    }

    public void d(String str, Object obj) throws IOException {
        if (obj instanceof d1) {
            if (str.equalsIgnoreCase("id")) {
                this.f20396g = (d1) obj;
                return;
            }
            throw new IOException("Attribute name not recognized by CertAttrSet: CertificateIssuerUniqueIdentity.");
        }
        throw new IOException("Attribute must be of type UniqueIdentity.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "issuerID";
    }

    public String toString() {
        d1 d1Var = this.f20396g;
        return d1Var == null ? "" : d1Var.toString();
    }
}
