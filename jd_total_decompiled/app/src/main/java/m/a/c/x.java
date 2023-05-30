package m.a.c;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Date;

/* loaded from: classes11.dex */
public class x implements l<String> {

    /* renamed from: g  reason: collision with root package name */
    private Date f20424g;

    /* renamed from: h  reason: collision with root package name */
    private Date f20425h;

    public x() {
    }

    private void b(m.a.b.i iVar) throws IOException {
        if (iVar.a == 48) {
            if (iVar.f20295c.a() != 0) {
                m.a.b.i[] m2 = new m.a.b.g(iVar.D()).m(2);
                if (m2.length == 2) {
                    if (m2[0].a == 23) {
                        this.f20424g = iVar.f20295c.p();
                    } else if (m2[0].a == 24) {
                        this.f20424g = iVar.f20295c.f();
                    } else {
                        throw new IOException("Invalid encoding for CertificateValidity");
                    }
                    if (m2[1].a == 23) {
                        this.f20425h = iVar.f20295c.p();
                        return;
                    } else if (m2[1].a == 24) {
                        this.f20425h = iVar.f20295c.f();
                        return;
                    } else {
                        throw new IOException("Invalid encoding for CertificateValidity");
                    }
                }
                throw new IOException("Invalid encoding for CertificateValidity");
            }
            throw new IOException("No data encoded for CertificateValidity");
        }
        throw new IOException("Invalid encoded CertificateValidity, starting sequence tag missing.");
    }

    private Date e() {
        return new Date(this.f20425h.getTime());
    }

    private Date f() {
        return new Date(this.f20424g.getTime());
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws IOException {
        if (this.f20424g != null && this.f20425h != null) {
            m.a.b.h hVar = new m.a.b.h();
            if (this.f20424g.getTime() < 2524636800000L) {
                hVar.w(this.f20424g);
            } else {
                hVar.i(this.f20424g);
            }
            if (this.f20425h.getTime() < 2524636800000L) {
                hVar.w(this.f20425h);
            } else {
                hVar.i(this.f20425h);
            }
            m.a.b.h hVar2 = new m.a.b.h();
            hVar2.y((byte) 48, hVar);
            outputStream.write(hVar2.toByteArray());
            return;
        }
        throw new IOException("CertAttrSet:CertificateValidity: null values to encode.\n");
    }

    public void c(String str) throws IOException {
        if (str.equalsIgnoreCase("notBefore")) {
            this.f20424g = null;
        } else if (str.equalsIgnoreCase("notAfter")) {
            this.f20425h = null;
        } else {
            throw new IOException("Attribute name not recognized by CertAttrSet: CertificateValidity.");
        }
    }

    public Object d(String str) throws IOException {
        if (str.equalsIgnoreCase("notBefore")) {
            return f();
        }
        if (str.equalsIgnoreCase("notAfter")) {
            return e();
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateValidity.");
    }

    public void g(String str, Object obj) throws IOException {
        if (obj instanceof Date) {
            if (str.equalsIgnoreCase("notBefore")) {
                this.f20424g = (Date) obj;
                return;
            } else if (str.equalsIgnoreCase("notAfter")) {
                this.f20425h = (Date) obj;
                return;
            } else {
                throw new IOException("Attribute name not recognized by CertAttrSet: CertificateValidity.");
            }
        }
        throw new IOException("Attribute must be of type Date.");
    }

    @Override // m.a.c.l
    public String getName() {
        return "validity";
    }

    public void h(Date date) throws CertificateNotYetValidException, CertificateExpiredException {
        if (!this.f20424g.after(date)) {
            if (this.f20425h.before(date)) {
                throw new CertificateExpiredException("NotAfter: " + this.f20425h.toString());
            }
            return;
        }
        throw new CertificateNotYetValidException("NotBefore: " + this.f20424g.toString());
    }

    public String toString() {
        if (this.f20424g == null || this.f20425h == null) {
            return "";
        }
        return "Validity: [From: " + this.f20424g.toString() + ",\n               To: " + this.f20425h.toString() + "]";
    }

    public x(m.a.b.g gVar) throws IOException {
        b(gVar.e());
    }
}
