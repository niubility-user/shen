package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes11.dex */
public class v implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private f1 f20418g;

    /* renamed from: h  reason: collision with root package name */
    private X500Principal f20419h;

    public v(m.a.b.g gVar) throws IOException {
        this.f20418g = new f1(gVar);
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        this.f20418g.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public void b(String str) throws IOException {
        if (str.equalsIgnoreCase("dname")) {
            this.f20418g = null;
            this.f20419h = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSubjectName.");
    }

    public Object c(String str) throws IOException {
        f1 f1Var;
        if (str.equalsIgnoreCase("dname")) {
            return this.f20418g;
        }
        if (str.equalsIgnoreCase("x500principal")) {
            if (this.f20419h == null && (f1Var = this.f20418g) != null) {
                this.f20419h = f1Var.c();
            }
            return this.f20419h;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSubjectName.");
    }

    public void d(String str, Object obj) throws IOException {
        if (obj instanceof f1) {
            if (str.equalsIgnoreCase("dname")) {
                this.f20418g = (f1) obj;
                this.f20419h = null;
                return;
            }
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateSubjectName.");
        }
        throw new IOException("Attribute must be of type X500Name.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "subject";
    }

    public String toString() {
        f1 f1Var = this.f20418g;
        return f1Var == null ? "" : f1Var.toString();
    }
}
