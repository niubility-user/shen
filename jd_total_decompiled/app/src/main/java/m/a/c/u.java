package m.a.c;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes11.dex */
public class u implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private z0 f20417g;

    public u(m.a.b.i iVar) throws IOException {
        this.f20417g = new z0(iVar);
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        this.f20417g.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public void b(String str) throws IOException {
        if (str.equalsIgnoreCase("number")) {
            this.f20417g = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
    }

    public Object c(String str) throws IOException {
        if (str.equalsIgnoreCase("number")) {
            return this.f20417g;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
    }

    public void d(String str, Object obj) throws IOException {
        if (obj instanceof z0) {
            if (str.equalsIgnoreCase("number")) {
                this.f20417g = (z0) obj;
                return;
            }
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSerialNumber.");
        }
        throw new IOException("Attribute must be of type SerialNumber.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "serialNumber";
    }

    public String toString() {
        z0 z0Var = this.f20417g;
        return z0Var == null ? "" : z0Var.toString();
    }
}
