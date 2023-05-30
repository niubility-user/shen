package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class h1 extends X509CRLEntry {

    /* renamed from: g  reason: collision with root package name */
    private z0 f20350g = null;

    /* renamed from: h  reason: collision with root package name */
    private Date f20351h = null;

    /* renamed from: i  reason: collision with root package name */
    private k f20352i = null;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f20353j;

    /* renamed from: k  reason: collision with root package name */
    private X500Principal f20354k;

    public h1(m.a.b.i iVar) throws CRLException {
        this.f20353j = null;
        try {
            d(iVar);
        } catch (IOException e2) {
            this.f20353j = null;
            throw new CRLException("Parsing error: " + e2.toString());
        }
    }

    private void d(m.a.b.i iVar) throws CRLException, IOException {
        if (iVar.a == 48) {
            if (iVar.f20295c.a() != 0) {
                this.f20353j = iVar.D();
                this.f20350g = new z0(iVar.E().e());
                byte t = (byte) iVar.f20295c.t();
                if (t == 23) {
                    this.f20351h = iVar.f20295c.p();
                } else if (t == 24) {
                    this.f20351h = iVar.f20295c.f();
                } else {
                    throw new CRLException("Invalid encoding for revocation date");
                }
                if (iVar.f20295c.a() == 0) {
                    return;
                }
                this.f20352i = new k(iVar.E());
                return;
            }
            throw new CRLException("No data encoded for RevokedCertificates");
        }
        throw new CRLException("Invalid encoded RevokedCertificate, starting sequence tag missing.");
    }

    public void a(m.a.b.h hVar) throws CRLException {
        try {
            if (this.f20353j == null) {
                m.a.b.h hVar2 = new m.a.b.h();
                this.f20350g.b(hVar2);
                if (this.f20351h.getTime() < 2524636800000L) {
                    hVar2.w(this.f20351h);
                } else {
                    hVar2.i(this.f20351h);
                }
                k kVar = this.f20352i;
                if (kVar != null) {
                    kVar.a(hVar2, false);
                }
                m.a.b.h hVar3 = new m.a.b.h();
                hVar3.y((byte) 48, hVar2);
                this.f20353j = hVar3.toByteArray();
            }
            hVar.write(this.f20353j);
        } catch (IOException e2) {
            throw new CRLException("Encoding error: " + e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o b() {
        return (o) c(s0.R);
    }

    public e0 c(m.a.b.j jVar) {
        k kVar = this.f20352i;
        if (kVar == null) {
            return null;
        }
        return kVar.b(p0.c(jVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(X500Principal x500Principal, X500Principal x500Principal2) {
        if (x500Principal.equals(x500Principal2)) {
            this.f20354k = null;
        } else {
            this.f20354k = x500Principal2;
        }
    }

    @Override // java.security.cert.X509CRLEntry
    public X500Principal getCertificateIssuer() {
        return this.f20354k;
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getCriticalExtensionOIDs() {
        if (this.f20352i == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (e0 e0Var : this.f20352i.c()) {
            if (e0Var.e()) {
                hashSet.add(e0Var.c().toString());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509CRLEntry
    public byte[] getEncoded() throws CRLException {
        if (this.f20353j == null) {
            a(new m.a.b.h());
        }
        return (byte[]) this.f20353j.clone();
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        e0 b;
        byte[] d;
        if (this.f20352i == null) {
            return null;
        }
        try {
            String c2 = p0.c(new m.a.b.j(str));
            if (c2 == null) {
                m.a.b.j jVar = new m.a.b.j(str);
                Enumeration<e0> d2 = this.f20352i.d();
                while (true) {
                    if (!d2.hasMoreElements()) {
                        b = null;
                        break;
                    }
                    b = d2.nextElement();
                    if (b.c().equals(jVar)) {
                        break;
                    }
                }
            } else {
                b = this.f20352i.b(c2);
            }
            if (b == null || (d = b.d()) == null) {
                return null;
            }
            m.a.b.h hVar = new m.a.b.h();
            hVar.q(d);
            return hVar.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getNonCriticalExtensionOIDs() {
        if (this.f20352i == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (e0 e0Var : this.f20352i.c()) {
            if (!e0Var.e()) {
                hashSet.add(e0Var.c().toString());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509CRLEntry
    public Date getRevocationDate() {
        return new Date(this.f20351h.getTime());
    }

    @Override // java.security.cert.X509CRLEntry
    public BigInteger getSerialNumber() {
        return this.f20350g.c();
    }

    @Override // java.security.cert.X509CRLEntry
    public boolean hasExtensions() {
        return this.f20352i != null;
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        k kVar = this.f20352i;
        if (kVar == null) {
            return false;
        }
        return kVar.e();
    }

    @Override // java.security.cert.X509CRLEntry
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f20350g.toString());
        sb.append("  On: " + this.f20351h.toString());
        if (this.f20354k != null) {
            sb.append("\n    Certificate issuer: " + this.f20354k);
        }
        k kVar = this.f20352i;
        if (kVar != null) {
            Object[] array = kVar.c().toArray();
            sb.append("\n    CRL Entry Extensions: " + array.length);
            int i2 = 0;
            while (i2 < array.length) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\n    [");
                int i3 = i2 + 1;
                sb2.append(i3);
                sb2.append("]: ");
                sb.append(sb2.toString());
                e0 e0Var = (e0) array[i2];
                try {
                    if (p0.b(e0Var.c()) == null) {
                        sb.append(e0Var.toString());
                        byte[] d = e0Var.d();
                        if (d != null) {
                            m.a.b.h hVar = new m.a.b.h();
                            hVar.q(d);
                            byte[] byteArray = hVar.toByteArray();
                            sb.append("Extension unknown: DER encoded OCTET string =\n" + new HexDumpEncoder().encodeBuffer(byteArray) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                    } else {
                        sb.append(e0Var.toString());
                    }
                } catch (Exception unused) {
                    sb.append(", Error parsing this extension");
                }
                i2 = i3;
            }
        }
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return sb.toString();
    }
}
