package m.a.c;

import com.jdcn.biz.client.BankCardConstants;
import java.io.IOException;
import java.io.OutputStream;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes11.dex */
public class p implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private f1 f20393g;

    /* renamed from: h  reason: collision with root package name */
    private X500Principal f20394h;

    public p(m.a.b.g gVar) throws IOException {
        this.f20393g = new f1(gVar);
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        this.f20393g.b(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public void b(String str) throws IOException {
        if (str.equalsIgnoreCase("dname")) {
            this.f20393g = null;
            this.f20394h = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuerName.");
    }

    public Object c(String str) throws IOException {
        f1 f1Var;
        if (str.equalsIgnoreCase("dname")) {
            return this.f20393g;
        }
        if (str.equalsIgnoreCase("x500principal")) {
            if (this.f20394h == null && (f1Var = this.f20393g) != null) {
                this.f20394h = f1Var.c();
            }
            return this.f20394h;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuerName.");
    }

    public void d(String str, Object obj) throws IOException {
        if (obj instanceof f1) {
            if (str.equalsIgnoreCase("dname")) {
                this.f20393g = (f1) obj;
                this.f20394h = null;
                return;
            }
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuerName.");
        }
        throw new IOException("Attribute must be of type X500Name.");
    }

    @Override // m.a.c.l
    public String getName() {
        return BankCardConstants.KEY_ISSUER;
    }

    public String toString() {
        f1 f1Var = this.f20393g;
        return f1Var == null ? "" : f1Var.toString();
    }
}
