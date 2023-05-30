package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.security.PublicKey;

/* loaded from: classes11.dex */
public class z implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private PublicKey f20428g;

    public z(m.a.b.g gVar) throws IOException {
        this.f20428g = l1.parse(gVar.e());
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        hVar.write(this.f20428g.getEncoded());
        outputStream.write(hVar.toByteArray());
    }

    public void b(String str) throws IOException {
        if (str.equalsIgnoreCase("value")) {
            this.f20428g = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }

    public Object c(String str) throws IOException {
        if (str.equalsIgnoreCase("value")) {
            return this.f20428g;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }

    public void d(String str, Object obj) throws IOException {
        if (str.equalsIgnoreCase("value")) {
            this.f20428g = (PublicKey) obj;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "key";
    }

    public String toString() {
        PublicKey publicKey = this.f20428g;
        return publicKey == null ? "" : publicKey.toString();
    }
}
