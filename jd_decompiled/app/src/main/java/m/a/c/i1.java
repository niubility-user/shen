package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class i1 extends X509CRL {

    /* renamed from: g */
    private byte[] f20358g;

    /* renamed from: k */
    private int f20362k;
    private PublicKey s;
    private String t;

    /* renamed from: h */
    private byte[] f20359h = null;

    /* renamed from: i */
    private byte[] f20360i = null;

    /* renamed from: j */
    private e f20361j = null;

    /* renamed from: l */
    private f1 f20363l = null;

    /* renamed from: m */
    private X500Principal f20364m = null;

    /* renamed from: n */
    private Date f20365n = null;
    private Date o = null;
    private Map<a, X509CRLEntry> p = new LinkedHashMap();
    private k q = null;
    private boolean r = false;

    public i1(m.a.b.i iVar) throws CRLException {
        this.f20358g = null;
        try {
            c(iVar);
        } catch (IOException e2) {
            this.f20358g = null;
            throw new CRLException("Parsing error: " + e2.getMessage());
        }
    }

    private X500Principal a(h1 h1Var, X500Principal x500Principal) throws IOException {
        o b = h1Var.b();
        return b != null ? ((f1) ((h0) b.g(BankCardConstants.KEY_ISSUER)).c(0).b()).c() : x500Principal;
    }

    private void c(m.a.b.i iVar) throws CRLException, IOException {
        if (!this.r) {
            if (iVar.k() != null && iVar.a == 48) {
                this.f20358g = iVar.D();
                m.a.b.i[] iVarArr = {iVar.f20295c.e(), iVar.f20295c.e(), iVar.f20295c.e()};
                if (iVar.f20295c.a() == 0) {
                    if (iVarArr[0].a == 48) {
                        this.f20361j = e.parse(iVarArr[1]);
                        this.f20359h = iVarArr[2].i();
                        if (iVarArr[1].f20295c.a() == 0) {
                            if (iVarArr[2].f20295c.a() == 0) {
                                this.f20360i = iVarArr[0].D();
                                m.a.b.g gVar = iVarArr[0].f20295c;
                                this.f20362k = 0;
                                if (((byte) gVar.t()) == 2) {
                                    int g2 = gVar.g();
                                    this.f20362k = g2;
                                    if (g2 != 1) {
                                        throw new CRLException("Invalid version");
                                    }
                                }
                                if (e.parse(gVar.e()).equals(this.f20361j)) {
                                    f1 f1Var = new f1(gVar);
                                    this.f20363l = f1Var;
                                    if (!f1Var.j()) {
                                        byte t = (byte) gVar.t();
                                        if (t == 23) {
                                            this.f20365n = gVar.p();
                                        } else if (t == 24) {
                                            this.f20365n = gVar.f();
                                        } else {
                                            throw new CRLException("Invalid encoding for thisUpdate (tag=" + ((int) t) + ")");
                                        }
                                        if (gVar.a() == 0) {
                                            return;
                                        }
                                        byte t2 = (byte) gVar.t();
                                        if (t2 == 23) {
                                            this.o = gVar.p();
                                        } else if (t2 == 24) {
                                            this.o = gVar.f();
                                        }
                                        if (gVar.a() == 0) {
                                            return;
                                        }
                                        byte t3 = (byte) gVar.t();
                                        if (t3 == 48 && (t3 & 192) != 128) {
                                            m.a.b.i[] m2 = gVar.m(4);
                                            X500Principal issuerX500Principal = getIssuerX500Principal();
                                            X500Principal x500Principal = issuerX500Principal;
                                            for (m.a.b.i iVar2 : m2) {
                                                h1 h1Var = new h1(iVar2);
                                                x500Principal = a(h1Var, x500Principal);
                                                h1Var.e(issuerX500Principal, x500Principal);
                                                this.p.put(new a(x500Principal, h1Var.getSerialNumber()), h1Var);
                                            }
                                        }
                                        if (gVar.a() == 0) {
                                            return;
                                        }
                                        m.a.b.i e2 = gVar.e();
                                        if (e2.w() && e2.z((byte) 0)) {
                                            this.q = new k(e2.f20295c);
                                        }
                                        this.r = true;
                                        return;
                                    }
                                    throw new CRLException("Empty issuer DN not allowed in X509CRLs");
                                }
                                throw new CRLException("Signature algorithm mismatch");
                            }
                            throw new CRLException("Signature field overrun");
                        }
                        throw new CRLException("AlgorithmId field overrun");
                    }
                    throw new CRLException("signed CRL fields invalid");
                }
                throw new CRLException("signed overrun, bytes = " + iVar.f20295c.a());
            }
            throw new CRLException("Invalid DER-encoded CRL data");
        }
        throw new CRLException("cannot over-write existing CRL");
    }

    public byte[] b() throws CRLException {
        byte[] bArr = this.f20358g;
        if (bArr != null) {
            return bArr;
        }
        throw new CRLException("Null CRL to encode");
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getCriticalExtensionOIDs() {
        if (this.q == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (e0 e0Var : this.q.c()) {
            if (e0Var.e()) {
                hashSet.add(e0Var.c().toString());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509CRL
    public byte[] getEncoded() throws CRLException {
        return (byte[]) b().clone();
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        e0 b;
        byte[] d;
        if (this.q == null) {
            return null;
        }
        try {
            String c2 = p0.c(new m.a.b.j(str));
            if (c2 == null) {
                m.a.b.j jVar = new m.a.b.j(str);
                Enumeration<e0> d2 = this.q.d();
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
                b = this.q.b(c2);
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

    @Override // java.security.cert.X509CRL
    public Principal getIssuerDN() {
        return this.f20363l;
    }

    @Override // java.security.cert.X509CRL
    public X500Principal getIssuerX500Principal() {
        if (this.f20364m == null) {
            this.f20364m = this.f20363l.c();
        }
        return this.f20364m;
    }

    @Override // java.security.cert.X509CRL
    public Date getNextUpdate() {
        if (this.o == null) {
            return null;
        }
        return new Date(this.o.getTime());
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getNonCriticalExtensionOIDs() {
        if (this.q == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (e0 e0Var : this.q.c()) {
            if (!e0Var.e()) {
                hashSet.add(e0Var.c().toString());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509CRL
    public X509CRLEntry getRevokedCertificate(BigInteger bigInteger) {
        if (this.p.isEmpty()) {
            return null;
        }
        return this.p.get(new a(getIssuerX500Principal(), bigInteger));
    }

    @Override // java.security.cert.X509CRL
    public Set<X509CRLEntry> getRevokedCertificates() {
        if (this.p.isEmpty()) {
            return null;
        }
        return new HashSet(this.p.values());
    }

    @Override // java.security.cert.X509CRL
    public String getSigAlgName() {
        e eVar = this.f20361j;
        if (eVar == null) {
            return null;
        }
        return eVar.getName();
    }

    @Override // java.security.cert.X509CRL
    public String getSigAlgOID() {
        e eVar = this.f20361j;
        if (eVar == null) {
            return null;
        }
        return eVar.getOID().toString();
    }

    @Override // java.security.cert.X509CRL
    public byte[] getSigAlgParams() {
        e eVar = this.f20361j;
        if (eVar == null) {
            return null;
        }
        try {
            return eVar.getEncodedParams();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509CRL
    public byte[] getSignature() {
        byte[] bArr = this.f20359h;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    @Override // java.security.cert.X509CRL
    public byte[] getTBSCertList() throws CRLException {
        byte[] bArr = this.f20360i;
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            return bArr2;
        }
        throw new CRLException("Uninitialized CRL");
    }

    @Override // java.security.cert.X509CRL
    public Date getThisUpdate() {
        return new Date(this.f20365n.getTime());
    }

    @Override // java.security.cert.X509CRL
    public int getVersion() {
        return this.f20362k + 1;
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        k kVar = this.q;
        if (kVar == null) {
            return false;
        }
        return kVar.e();
    }

    @Override // java.security.cert.CRL
    public boolean isRevoked(Certificate certificate) {
        if (this.p.isEmpty() || !(certificate instanceof X509Certificate)) {
            return false;
        }
        return this.p.containsKey(new a((X509Certificate) certificate));
    }

    @Override // java.security.cert.CRL
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("X.509 CRL v" + (this.f20362k + 1) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (this.f20361j != null) {
            stringBuffer.append("Signature Algorithm: " + this.f20361j.toString() + ", OID=" + this.f20361j.getOID().toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.f20363l != null) {
            stringBuffer.append("Issuer: " + this.f20363l.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.f20365n != null) {
            stringBuffer.append("\nThis Update: " + this.f20365n.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.o != null) {
            stringBuffer.append("Next Update: " + this.o.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.p.isEmpty()) {
            stringBuffer.append("\nNO certificates have been revoked\n");
        } else {
            stringBuffer.append("\nRevoked Certificates: " + this.p.size());
            Iterator<X509CRLEntry> it = this.p.values().iterator();
            int i2 = 1;
            while (it.hasNext()) {
                stringBuffer.append("\n[" + i2 + "] " + it.next().toString());
                i2++;
            }
        }
        k kVar = this.q;
        if (kVar != null) {
            Object[] array = kVar.c().toArray();
            stringBuffer.append("\nCRL Extensions: " + array.length);
            int i3 = 0;
            while (i3 < array.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n[");
                int i4 = i3 + 1;
                sb.append(i4);
                sb.append("]: ");
                stringBuffer.append(sb.toString());
                e0 e0Var = (e0) array[i3];
                try {
                    if (p0.b(e0Var.c()) == null) {
                        stringBuffer.append(e0Var.toString());
                        byte[] d = e0Var.d();
                        if (d != null) {
                            m.a.b.h hVar = new m.a.b.h();
                            hVar.q(d);
                            byte[] byteArray = hVar.toByteArray();
                            stringBuffer.append("Extension unknown: DER encoded OCTET string =\n" + new HexDumpEncoder().encodeBuffer(byteArray) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                    } else {
                        stringBuffer.append(e0Var.toString());
                    }
                } catch (Exception unused) {
                    stringBuffer.append(", Error parsing this extension");
                }
                i3 = i4;
            }
        }
        if (this.f20359h != null) {
            stringBuffer.append("\nSignature:\n" + new HexDumpEncoder().encodeBuffer(this.f20359h) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        } else {
            stringBuffer.append("NOT signed yet\n");
        }
        return stringBuffer.toString();
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        verify(publicKey, "");
    }

    @Override // java.security.cert.X509CRL
    public synchronized void verify(PublicKey publicKey, String str) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature;
        if (str == null) {
            str = "";
        }
        PublicKey publicKey2 = this.s;
        if (publicKey2 != null && publicKey2.equals(publicKey) && str.equals(this.t)) {
            return;
        }
        if (this.f20358g != null) {
            if (str.length() == 0) {
                signature = Signature.getInstance(this.f20361j.getName());
            } else {
                signature = Signature.getInstance(this.f20361j.getName(), str);
            }
            signature.initVerify(publicKey);
            byte[] bArr = this.f20360i;
            if (bArr != null) {
                signature.update(bArr, 0, bArr.length);
                if (signature.verify(this.f20359h)) {
                    this.s = publicKey;
                    this.t = str;
                    return;
                }
                throw new SignatureException("Signature does not match.");
            }
            throw new CRLException("Uninitialized CRL");
        }
        throw new CRLException("Uninitialized CRL");
    }

    /* loaded from: classes11.dex */
    public static final class a {
        final X500Principal a;
        final BigInteger b;

        /* renamed from: c */
        volatile int f20366c;

        a(X500Principal x500Principal, BigInteger bigInteger) {
            this.f20366c = 0;
            this.a = x500Principal;
            this.b = bigInteger;
        }

        X500Principal a() {
            return this.a;
        }

        BigInteger b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.b.equals(aVar.b()) && this.a.equals(aVar.a());
            }
            return false;
        }

        public int hashCode() {
            if (this.f20366c == 0) {
                this.f20366c = ((R2.attr.closeIconEndPadding + this.a.hashCode()) * 37) + this.b.hashCode();
            }
            return this.f20366c;
        }

        a(X509Certificate x509Certificate) {
            this(x509Certificate.getIssuerX500Principal(), x509Certificate.getSerialNumber());
        }
    }

    @Override // java.security.cert.X509CRL
    public X509CRLEntry getRevokedCertificate(X509Certificate x509Certificate) {
        if (this.p.isEmpty()) {
            return null;
        }
        return this.p.get(new a(x509Certificate));
    }
}
