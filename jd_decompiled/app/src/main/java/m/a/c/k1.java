package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jdcn.biz.client.BankCardConstants;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class k1 implements l<String> {
    private static final Map<String, Integer> r;

    /* renamed from: g */
    protected y f20374g = new y();

    /* renamed from: h */
    protected u f20375h = null;

    /* renamed from: i */
    protected m f20376i = null;

    /* renamed from: j */
    protected p f20377j = null;

    /* renamed from: k */
    protected x f20378k = null;

    /* renamed from: l */
    protected v f20379l = null;

    /* renamed from: m */
    protected z f20380m = null;

    /* renamed from: n */
    protected q f20381n = null;
    protected w o = null;
    protected n p = null;
    private byte[] q = null;

    static {
        HashMap hashMap = new HashMap();
        r = hashMap;
        hashMap.put("version", 1);
        hashMap.put("serialNumber", 2);
        hashMap.put("algorithmID", 3);
        hashMap.put(BankCardConstants.KEY_ISSUER, 4);
        hashMap.put("validity", 5);
        hashMap.put("subject", 6);
        hashMap.put("key", 7);
        hashMap.put("issuerID", 8);
        hashMap.put("subjectID", 9);
        hashMap.put("extensions", 10);
    }

    public k1() {
    }

    private int b(String str) {
        Integer num = r.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private void d(m.a.b.h hVar) throws CertificateException, IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        this.f20374g.a(hVar2);
        this.f20375h.a(hVar2);
        this.f20376i.a(hVar2);
        if (this.f20374g.b(0) == 0 && this.f20377j.toString() == null) {
            throw new CertificateParsingException("Null issuer DN not allowed in v1 certificate");
        }
        this.f20377j.a(hVar2);
        this.f20378k.a(hVar2);
        if (this.f20374g.b(0) == 0 && this.f20379l.toString() == null) {
            throw new CertificateParsingException("Null subject DN not allowed in v1 certificate");
        }
        this.f20379l.a(hVar2);
        this.f20380m.a(hVar2);
        q qVar = this.f20381n;
        if (qVar != null) {
            qVar.a(hVar2);
        }
        w wVar = this.o;
        if (wVar != null) {
            wVar.a(hVar2);
        }
        n nVar = this.p;
        if (nVar != null) {
            nVar.a(hVar2);
        }
        hVar.y((byte) 48, hVar2);
    }

    private void h(m.a.b.i iVar) throws CertificateParsingException, IOException {
        if (iVar.a == 48) {
            this.q = iVar.D();
            m.a.b.g gVar = iVar.f20295c;
            m.a.b.i e2 = gVar.e();
            if (e2.z((byte) 0)) {
                this.f20374g = new y(e2);
                e2 = gVar.e();
            }
            this.f20375h = new u(e2);
            this.f20376i = new m(gVar);
            p pVar = new p(gVar);
            this.f20377j = pVar;
            if (!((f1) pVar.c("dname")).j()) {
                this.f20378k = new x(gVar);
                v vVar = new v(gVar);
                this.f20379l = vVar;
                f1 f1Var = (f1) vVar.c("dname");
                if (this.f20374g.b(0) == 0 && f1Var.j()) {
                    throw new CertificateParsingException("Empty subject DN not allowed in v1 certificate");
                }
                this.f20380m = new z(gVar);
                if (gVar.a() != 0) {
                    if (this.f20374g.b(0) != 0) {
                        m.a.b.i e3 = gVar.e();
                        if (e3.z((byte) 1)) {
                            this.f20381n = new q(e3);
                            if (gVar.a() == 0) {
                                return;
                            }
                            e3 = gVar.e();
                        }
                        if (e3.z((byte) 2)) {
                            this.o = new w(e3);
                            if (gVar.a() == 0) {
                                return;
                            }
                            e3 = gVar.e();
                        }
                        if (this.f20374g.b(2) == 0) {
                            if (e3.w() && e3.z((byte) 3)) {
                                this.p = new n(e3.f20295c);
                            }
                            t(this.f20379l, this.p);
                            return;
                        }
                        throw new CertificateParsingException("Extensions not allowed in v2 certificate");
                    }
                    throw new CertificateParsingException("no more data allowed for version 1 certificate");
                }
                return;
            }
            throw new CertificateParsingException("Empty issuer DN not allowed in X509Certificates");
        }
        throw new CertificateParsingException("signed fields invalid");
    }

    private void j(Object obj) throws CertificateException {
        if (obj instanceof m) {
            this.f20376i = (m) obj;
            return;
        }
        throw new CertificateException("AlgorithmId class type invalid.");
    }

    private void k(Object obj) throws CertificateException {
        if (this.f20374g.b(2) >= 0) {
            if (obj instanceof n) {
                this.p = (n) obj;
                return;
            }
            throw new CertificateException("Extensions class type invalid.");
        }
        throw new CertificateException("Invalid version");
    }

    private void l(Object obj) throws CertificateException {
        if (obj instanceof p) {
            this.f20377j = (p) obj;
            return;
        }
        throw new CertificateException("Issuer class type invalid.");
    }

    private void m(Object obj) throws CertificateException {
        if (this.f20374g.b(1) >= 0) {
            if (obj instanceof q) {
                this.f20381n = (q) obj;
                return;
            }
            throw new CertificateException("IssuerUniqueId class type invalid.");
        }
        throw new CertificateException("Invalid version");
    }

    private void n(Object obj) throws CertificateException {
        if (obj instanceof z) {
            this.f20380m = (z) obj;
            return;
        }
        throw new CertificateException("Key class type invalid.");
    }

    private void o(Object obj) throws CertificateException {
        if (obj instanceof u) {
            this.f20375h = (u) obj;
            return;
        }
        throw new CertificateException("SerialNumber class type invalid.");
    }

    private void p(Object obj) throws CertificateException {
        if (obj instanceof v) {
            this.f20379l = (v) obj;
            return;
        }
        throw new CertificateException("Subject class type invalid.");
    }

    private void q(Object obj) throws CertificateException {
        if (this.f20374g.b(1) >= 0) {
            if (obj instanceof w) {
                this.o = (w) obj;
                return;
            }
            throw new CertificateException("SubjectUniqueId class type invalid.");
        }
        throw new CertificateException("Invalid version");
    }

    private void r(Object obj) throws CertificateException {
        if (obj instanceof x) {
            this.f20378k = (x) obj;
            return;
        }
        throw new CertificateException("CertificateValidity class type invalid.");
    }

    private void s(Object obj) throws CertificateException {
        if (obj instanceof y) {
            this.f20374g = (y) obj;
            return;
        }
        throw new CertificateException("Version class type invalid.");
    }

    private void t(v vVar, n nVar) throws CertificateParsingException, IOException {
        if (((f1) vVar.c("dname")).j()) {
            if (nVar != null) {
                try {
                    a1 a1Var = (a1) nVar.d("SubjectAlternativeName");
                    h0 h0Var = (h0) a1Var.g("subject_name");
                    if (h0Var != null && !h0Var.d()) {
                        if (!a1Var.e()) {
                            throw new CertificateParsingException("X.509 Certificate is incomplete: SubjectAlternativeName extension MUST be marked critical when subject field is empty");
                        }
                        return;
                    }
                    throw new CertificateParsingException("X.509 Certificate is incomplete: subject field is empty, and SubjectAlternativeName extension is empty");
                } catch (IOException unused) {
                    throw new CertificateParsingException("X.509 Certificate is incomplete: subject field is empty, and SubjectAlternativeName extension is absent");
                }
            }
            throw new CertificateParsingException("X.509 Certificate is incomplete: subject field is empty, and certificate has no extensions");
        }
    }

    @Override // m.a.c.l
    public void a(OutputStream outputStream) throws CertificateException, IOException {
        if (this.q == null) {
            m.a.b.h hVar = new m.a.b.h();
            d(hVar);
            this.q = hVar.toByteArray();
        }
        outputStream.write((byte[]) this.q.clone());
    }

    public void c(String str) throws CertificateException, IOException {
        g1 g1Var = new g1(str);
        int b = b(g1Var.a());
        if (b != 0) {
            this.q = null;
            String b2 = g1Var.b();
            switch (b) {
                case 1:
                    if (b2 == null) {
                        this.f20374g = null;
                        return;
                    } else {
                        this.f20374g.d(b2);
                        return;
                    }
                case 2:
                    if (b2 == null) {
                        this.f20375h = null;
                        return;
                    } else {
                        this.f20375h.b(b2);
                        return;
                    }
                case 3:
                    if (b2 == null) {
                        this.f20376i = null;
                        return;
                    } else {
                        this.f20376i.b(b2);
                        return;
                    }
                case 4:
                    if (b2 == null) {
                        this.f20377j = null;
                        return;
                    } else {
                        this.f20377j.b(b2);
                        return;
                    }
                case 5:
                    if (b2 == null) {
                        this.f20378k = null;
                        return;
                    } else {
                        this.f20378k.c(b2);
                        return;
                    }
                case 6:
                    if (b2 == null) {
                        this.f20379l = null;
                        return;
                    } else {
                        this.f20379l.b(b2);
                        return;
                    }
                case 7:
                    if (b2 == null) {
                        this.f20380m = null;
                        return;
                    } else {
                        this.f20380m.b(b2);
                        return;
                    }
                case 8:
                    if (b2 == null) {
                        this.f20381n = null;
                        return;
                    } else {
                        this.f20381n.b(b2);
                        return;
                    }
                case 9:
                    if (b2 == null) {
                        this.o = null;
                        return;
                    } else {
                        this.o.b(b2);
                        return;
                    }
                case 10:
                    if (b2 == null) {
                        this.p = null;
                        return;
                    }
                    n nVar = this.p;
                    if (nVar != null) {
                        nVar.b(b2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        throw new CertificateException("Attribute name not recognized: " + str);
    }

    public boolean e(k1 k1Var) {
        byte[] bArr;
        if (this == k1Var) {
            return true;
        }
        byte[] bArr2 = this.q;
        if (bArr2 == null || (bArr = k1Var.q) == null || bArr2.length != bArr.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr3 = this.q;
            if (i2 >= bArr3.length) {
                return true;
            }
            if (bArr3[i2] != k1Var.q[i2]) {
                return false;
            }
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof k1) {
            return e((k1) obj);
        }
        return false;
    }

    public Object f(String str) throws CertificateException, IOException {
        g1 g1Var = new g1(str);
        int b = b(g1Var.a());
        if (b != 0) {
            String b2 = g1Var.b();
            switch (b) {
                case 1:
                    if (b2 == null) {
                        return this.f20374g;
                    }
                    return this.f20374g.e(b2);
                case 2:
                    if (b2 == null) {
                        return this.f20375h;
                    }
                    return this.f20375h.c(b2);
                case 3:
                    if (b2 == null) {
                        return this.f20376i;
                    }
                    return this.f20376i.c(b2);
                case 4:
                    if (b2 == null) {
                        return this.f20377j;
                    }
                    return this.f20377j.c(b2);
                case 5:
                    if (b2 == null) {
                        return this.f20378k;
                    }
                    return this.f20378k.d(b2);
                case 6:
                    if (b2 == null) {
                        return this.f20379l;
                    }
                    return this.f20379l.c(b2);
                case 7:
                    if (b2 == null) {
                        return this.f20380m;
                    }
                    return this.f20380m.c(b2);
                case 8:
                    if (b2 == null) {
                        return this.f20381n;
                    }
                    q qVar = this.f20381n;
                    if (qVar == null) {
                        return null;
                    }
                    return qVar.c(b2);
                case 9:
                    if (b2 == null) {
                        return this.o;
                    }
                    w wVar = this.o;
                    if (wVar == null) {
                        return null;
                    }
                    return wVar.c(b2);
                case 10:
                    if (b2 == null) {
                        return this.p;
                    }
                    n nVar = this.p;
                    if (nVar == null) {
                        return null;
                    }
                    return nVar.d(b2);
                default:
                    return null;
            }
        }
        throw new CertificateParsingException("Attribute name not recognized: " + str);
    }

    public byte[] g() throws CertificateEncodingException {
        try {
            if (this.q == null) {
                m.a.b.h hVar = new m.a.b.h();
                d(hVar);
                this.q = hVar.toByteArray();
            }
            return (byte[]) this.q.clone();
        } catch (IOException e2) {
            throw new CertificateEncodingException(e2.toString());
        } catch (CertificateException e3) {
            throw new CertificateEncodingException(e3.toString());
        }
    }

    @Override // m.a.c.l
    public String getName() {
        return "info";
    }

    public int hashCode() {
        int i2 = 1;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.q;
            if (i2 >= bArr.length) {
                return i3;
            }
            i3 += bArr[i2] * i2;
            i2++;
        }
    }

    public void i(String str, Object obj) throws CertificateException, IOException {
        g1 g1Var = new g1(str);
        int b = b(g1Var.a());
        if (b != 0) {
            this.q = null;
            String b2 = g1Var.b();
            switch (b) {
                case 1:
                    if (b2 == null) {
                        s(obj);
                        return;
                    } else {
                        this.f20374g.g(b2, obj);
                        return;
                    }
                case 2:
                    if (b2 == null) {
                        o(obj);
                        return;
                    } else {
                        this.f20375h.d(b2, obj);
                        return;
                    }
                case 3:
                    if (b2 == null) {
                        j(obj);
                        return;
                    } else {
                        this.f20376i.d(b2, obj);
                        return;
                    }
                case 4:
                    if (b2 == null) {
                        l(obj);
                        return;
                    } else {
                        this.f20377j.d(b2, obj);
                        return;
                    }
                case 5:
                    if (b2 == null) {
                        r(obj);
                        return;
                    } else {
                        this.f20378k.g(b2, obj);
                        return;
                    }
                case 6:
                    if (b2 == null) {
                        p(obj);
                        return;
                    } else {
                        this.f20379l.d(b2, obj);
                        return;
                    }
                case 7:
                    if (b2 == null) {
                        n(obj);
                        return;
                    } else {
                        this.f20380m.d(b2, obj);
                        return;
                    }
                case 8:
                    if (b2 == null) {
                        m(obj);
                        return;
                    } else {
                        this.f20381n.d(b2, obj);
                        return;
                    }
                case 9:
                    if (b2 == null) {
                        q(obj);
                        return;
                    } else {
                        this.o.d(b2, obj);
                        return;
                    }
                case 10:
                    if (b2 == null) {
                        k(obj);
                        return;
                    }
                    if (this.p == null) {
                        this.p = new n();
                    }
                    this.p.j(b2, obj);
                    return;
                default:
                    return;
            }
        }
        throw new CertificateException("Attribute name not recognized: " + str);
    }

    public String toString() {
        if (this.f20379l != null && this.f20380m != null && this.f20378k != null && this.f20377j != null && this.f20376i != null && this.f20375h != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            sb.append("  " + this.f20374g.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  Subject: " + this.f20379l.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  Signature Algorithm: " + this.f20376i.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  Key:  " + this.f20380m.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  " + this.f20378k.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  Issuer: " + this.f20377j.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  " + this.f20375h.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (this.f20381n != null) {
                sb.append("  Issuer Id:\n" + this.f20381n.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            if (this.o != null) {
                sb.append("  Subject Id:\n" + this.o.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            n nVar = this.p;
            if (nVar != null) {
                Object[] array = nVar.e().toArray();
                sb.append("\nCertificate Extensions: " + array.length);
                int i2 = 0;
                while (i2 < array.length) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\n[");
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
                Map<String, e0> f2 = this.p.f();
                if (!f2.isEmpty()) {
                    sb.append("\nUnparseable certificate extensions: " + f2.size());
                    Iterator<e0> it = f2.values().iterator();
                    int i4 = 1;
                    while (it.hasNext()) {
                        sb.append("\n[" + i4 + "]: ");
                        sb.append(it.next());
                        i4++;
                    }
                }
            }
            sb.append("\n]");
            return sb.toString();
        }
        throw new NullPointerException("X.509 cert is incomplete");
    }

    public k1(byte[] bArr) throws CertificateParsingException {
        try {
            h(new m.a.b.i(bArr));
        } catch (IOException e2) {
            CertificateParsingException certificateParsingException = new CertificateParsingException(e2.toString());
            certificateParsingException.initCause(e2);
            throw certificateParsingException;
        }
    }

    public k1(m.a.b.i iVar) throws CertificateParsingException {
        try {
            h(iVar);
        } catch (IOException e2) {
            CertificateParsingException certificateParsingException = new CertificateParsingException(e2.toString());
            certificateParsingException.initCause(e2);
            throw certificateParsingException;
        }
    }
}
