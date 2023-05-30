package m.a.c;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import sun.misc.BASE64Decoder;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class j1 extends X509Certificate implements m.a.b.d {
    public static final String ALG_ID = "algorithm";
    public static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public static final String END_CERT = "-----END CERTIFICATE-----";
    public static final String INFO = "info";
    public static final String ISSUER_DN = "x509.info.issuer.dname";
    public static final String NAME = "x509";
    public static final String PUBLIC_KEY = "x509.info.key.value";
    public static final String SERIAL_ID = "x509.info.serialNumber.number";
    public static final String SIG = "x509.signature";
    public static final String SIGNATURE = "signature";
    public static final String SIGNED_CERT = "signed_cert";
    public static final String SIG_ALG = "x509.algorithm";
    public static final String SUBJECT_DN = "x509.info.subject.dname";
    public static final String VERSION = "x509.info.version.number";
    private static final long serialVersionUID = -3457612960190864406L;
    protected e algId;
    private Set<d> authInfoAccess;
    private List<String> extKeyUsage;
    protected k1 info;
    private Collection<List<?>> issuerAlternativeNames;
    private boolean readOnly;
    protected byte[] signature;
    private byte[] signedCert;
    private Collection<List<?>> subjectAlternativeNames;
    private boolean verificationResult;
    private String verifiedProvider;
    private PublicKey verifiedPublicKey;

    public j1() {
        this.readOnly = false;
        this.signedCert = null;
        this.info = null;
        this.algId = null;
        this.signature = null;
    }

    private static Collection<List<?>> a(Collection<List<?>> collection) {
        Iterator<List<?>> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().get(1) instanceof byte[]) {
                z = true;
            }
        }
        if (z) {
            HashSet hashSet = new HashSet();
            for (List<?> list : collection) {
                Object obj = list.get(1);
                if (obj instanceof byte[]) {
                    ArrayList arrayList = new ArrayList(list);
                    arrayList.set(1, ((byte[]) obj).clone());
                    hashSet.add(Collections.unmodifiableList(arrayList));
                } else {
                    hashSet.add(list);
                }
            }
            return Collections.unmodifiableCollection(hashSet);
        }
        return collection;
    }

    private static X500Principal b(X509Certificate x509Certificate, boolean z) throws Exception {
        m.a.b.g gVar = new m.a.b.g(x509Certificate.getEncoded()).m(3)[0].f20295c;
        if (gVar.e().z((byte) 0)) {
            gVar.e();
        }
        gVar.e();
        m.a.b.i e2 = gVar.e();
        if (!z) {
            gVar.e();
            e2 = gVar.e();
        }
        return new X500Principal(e2.D());
    }

    private static Collection<List<?>> c(h0 h0Var) {
        if (h0Var.d()) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        Iterator<f0> it = h0Var.e().iterator();
        while (it.hasNext()) {
            g0 b = it.next().b();
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(Integer.valueOf(b.getType()));
            int type = b.getType();
            if (type == 1) {
                arrayList.add(((y0) b).c());
            } else if (type == 2) {
                arrayList.add(((a0) b).c());
            } else if (type == 4) {
                arrayList.add(((f1) b).g());
            } else if (type == 6) {
                arrayList.add(((c1) b).e());
            } else if (type == 7) {
                try {
                    arrayList.add(((k0) b).d());
                } catch (IOException e2) {
                    throw new RuntimeException("IPAddress cannot be parsed", e2);
                }
            } else if (type != 8) {
                m.a.b.h hVar = new m.a.b.h();
                try {
                    b.b(hVar);
                    arrayList.add(hVar.toByteArray());
                } catch (IOException e3) {
                    throw new RuntimeException("name cannot be encoded", e3);
                }
            } else {
                arrayList.add(((q0) b).c().toString());
            }
            hashSet.add(Collections.unmodifiableList(arrayList));
        }
        return Collections.unmodifiableCollection(hashSet);
    }

    private void d(m.a.b.i iVar) throws CertificateException, IOException {
        if (!this.readOnly) {
            if (iVar.f20295c != null && iVar.a == 48) {
                this.signedCert = iVar.D();
                m.a.b.i[] iVarArr = {iVar.f20295c.e(), iVar.f20295c.e(), iVar.f20295c.e()};
                if (iVar.f20295c.a() == 0) {
                    if (iVarArr[0].a == 48) {
                        this.algId = e.parse(iVarArr[1]);
                        this.signature = iVarArr[2].i();
                        if (iVarArr[1].f20295c.a() == 0) {
                            if (iVarArr[2].f20295c.a() == 0) {
                                k1 k1Var = new k1(iVarArr[0]);
                                this.info = k1Var;
                                if (this.algId.equals((e) k1Var.f("algorithmID.algorithm"))) {
                                    this.readOnly = true;
                                    return;
                                }
                                throw new CertificateException("Signature algorithm mismatch");
                            }
                            throw new CertificateParsingException("signed fields overrun");
                        }
                        throw new CertificateParsingException("algid field overrun");
                    }
                    throw new CertificateParsingException("signed fields invalid");
                }
                throw new CertificateParsingException("signed overrun, bytes = " + iVar.f20295c.a());
            }
            throw new CertificateParsingException("invalid DER-encoded certificate data");
        }
        throw new CertificateParsingException("cannot over-write existing certificate");
    }

    private m.a.b.i e(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ASCII"));
        try {
            if (bufferedReader.readLine().equals(BEGIN_CERT)) {
                BASE64Decoder bASE64Decoder = new BASE64Decoder();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            return null;
                        }
                        if (readLine.equals(END_CERT)) {
                            return new m.a.b.i(byteArrayOutputStream.toByteArray());
                        }
                        byteArrayOutputStream.write(bASE64Decoder.decodeBuffer(readLine));
                    } catch (IOException e2) {
                        throw new IOException("Unable to read InputStream: " + e2.getMessage());
                    }
                }
            } else {
                throw new IOException("InputStream is not RFC1421 hex-encoded DER bytes");
            }
        } catch (IOException e3) {
            throw new IOException("Unable to read InputStream: " + e3.getMessage());
        }
    }

    public static synchronized j1 intern(X509Certificate x509Certificate) throws CertificateException {
        byte[] encoded;
        j1 j1Var;
        synchronized (j1.class) {
            if (x509Certificate == null) {
                return null;
            }
            boolean z = x509Certificate instanceof j1;
            if (z) {
                encoded = ((j1) x509Certificate).getEncodedInternal();
            } else {
                encoded = x509Certificate.getEncoded();
            }
            if (z) {
                j1Var = (j1) x509Certificate;
            } else {
                j1Var = new j1(encoded);
                j1Var.getEncodedInternal();
            }
            return j1Var;
        }
    }

    public static boolean isSelfIssued(X509Certificate x509Certificate) {
        return x509Certificate.getSubjectX500Principal().equals(x509Certificate.getIssuerX500Principal());
    }

    public static boolean isSelfSigned(X509Certificate x509Certificate, String str) {
        if (isSelfIssued(x509Certificate)) {
            try {
                if (str == null) {
                    x509Certificate.verify(x509Certificate.getPublicKey());
                    return true;
                }
                x509Certificate.verify(x509Certificate.getPublicKey(), str);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static j1 toImpl(X509Certificate x509Certificate) throws CertificateException {
        if (x509Certificate instanceof j1) {
            return (j1) x509Certificate;
        }
        return intern(x509Certificate);
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(new Date());
    }

    public void delete(String str) throws CertificateException, IOException {
        if (!this.readOnly) {
            g1 g1Var = new g1(str);
            String a = g1Var.a();
            if (a.equalsIgnoreCase(NAME)) {
                g1 g1Var2 = new g1(g1Var.b());
                String a2 = g1Var2.a();
                if (a2.equalsIgnoreCase("info")) {
                    if (g1Var2.b() != null) {
                        this.info = null;
                        return;
                    } else {
                        this.info.c(g1Var2.b());
                        return;
                    }
                } else if (a2.equalsIgnoreCase(ALG_ID)) {
                    this.algId = null;
                    return;
                } else if (a2.equalsIgnoreCase("signature")) {
                    this.signature = null;
                    return;
                } else if (a2.equalsIgnoreCase(SIGNED_CERT)) {
                    this.signedCert = null;
                    return;
                } else {
                    throw new CertificateException("Attribute name not recognized or delete() not allowed for the same: " + a2);
                }
            }
            throw new CertificateException("Invalid root of attribute name, expected [x509], received " + a);
        }
        throw new CertificateException("cannot over-write existing certificate");
    }

    @Override // m.a.b.d
    public void derEncode(OutputStream outputStream) throws IOException {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            outputStream.write((byte[]) bArr.clone());
            return;
        }
        throw new IOException("Null certificate to encode");
    }

    public void encode(OutputStream outputStream) throws CertificateEncodingException {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            try {
                outputStream.write((byte[]) bArr.clone());
                return;
            } catch (IOException e2) {
                throw new CertificateEncodingException(e2.toString());
            }
        }
        throw new CertificateEncodingException("Null certificate to encode");
    }

    public Object get(String str) throws CertificateParsingException {
        g1 g1Var = new g1(str);
        String a = g1Var.a();
        if (a.equalsIgnoreCase(NAME)) {
            g1 g1Var2 = new g1(g1Var.b());
            String a2 = g1Var2.a();
            if (a2.equalsIgnoreCase("info")) {
                if (this.info == null) {
                    return null;
                }
                if (g1Var2.b() != null) {
                    try {
                        return this.info.f(g1Var2.b());
                    } catch (IOException e2) {
                        throw new CertificateParsingException(e2.toString());
                    } catch (CertificateException e3) {
                        throw new CertificateParsingException(e3.toString());
                    }
                }
                return this.info;
            } else if (a2.equalsIgnoreCase(ALG_ID)) {
                return this.algId;
            } else {
                if (a2.equalsIgnoreCase("signature")) {
                    byte[] bArr = this.signature;
                    if (bArr != null) {
                        return bArr.clone();
                    }
                    return null;
                } else if (a2.equalsIgnoreCase(SIGNED_CERT)) {
                    byte[] bArr2 = this.signedCert;
                    if (bArr2 != null) {
                        return bArr2.clone();
                    }
                    return null;
                } else {
                    throw new CertificateParsingException("Attribute name not recognized or get() not allowed for the same: " + a2);
                }
            }
        }
        throw new CertificateParsingException("Invalid root of attribute name, expected [x509], received [" + a + "]");
    }

    public g getAuthorityInfoAccessExtension() {
        return (g) getExtension(s0.S);
    }

    public h getAuthorityKeyIdentifierExtension() {
        return (h) getExtension(s0.z);
    }

    @Override // java.security.cert.X509Certificate
    public int getBasicConstraints() {
        i iVar;
        try {
            String c2 = p0.c(s0.H);
            if (c2 == null || (iVar = (i) get(c2)) == null || !((Boolean) iVar.g("is_ca")).booleanValue()) {
                return -1;
            }
            return ((Integer) iVar.g("path_len")).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    public i getBasicConstraintsExtension() {
        return (i) getExtension(s0.H);
    }

    public j getCRLDistributionPointsExtension() {
        return (j) getExtension(s0.K);
    }

    public r getCertificatePoliciesExtension() {
        return (r) getExtension(s0.D);
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getCriticalExtensionOIDs() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            n nVar = (n) k1Var.f("extensions");
            if (nVar == null) {
                return null;
            }
            HashSet hashSet = new HashSet();
            for (e0 e0Var : nVar.e()) {
                if (e0Var.e()) {
                    hashSet.add(e0Var.c().toString());
                }
            }
            return hashSet;
        } catch (Exception unused) {
            return null;
        }
    }

    public Enumeration<String> getElements() {
        f fVar = new f();
        fVar.addElement("x509.info");
        fVar.addElement(SIG_ALG);
        fVar.addElement(SIG);
        fVar.addElement("x509.signed_cert");
        return fVar.elements();
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        return (byte[]) getEncodedInternal().clone();
    }

    public byte[] getEncodedInternal() throws CertificateEncodingException {
        byte[] bArr = this.signedCert;
        if (bArr != null) {
            return bArr;
        }
        throw new CertificateEncodingException("Null certificate to encode");
    }

    @Override // java.security.cert.X509Certificate
    public synchronized List<String> getExtendedKeyUsage() throws CertificateParsingException {
        List<String> list;
        if (!this.readOnly || (list = this.extKeyUsage) == null) {
            d0 extendedKeyUsageExtension = getExtendedKeyUsageExtension();
            if (extendedKeyUsageExtension == null) {
                return null;
            }
            List<String> unmodifiableList = Collections.unmodifiableList(extendedKeyUsageExtension.g());
            this.extKeyUsage = unmodifiableList;
            return unmodifiableList;
        }
        return list;
    }

    public d0 getExtendedKeyUsageExtension() {
        return (d0) getExtension(s0.P);
    }

    public e0 getExtension(m.a.b.j jVar) {
        n nVar;
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            nVar = (n) k1Var.f("extensions");
        } catch (IOException | CertificateException unused) {
        }
        if (nVar == null) {
            return null;
        }
        for (e0 e0Var : nVar.e()) {
            if (e0Var.c().equals(jVar)) {
                return e0Var;
            }
        }
        return null;
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        e0 e0Var;
        try {
            m.a.b.j jVar = new m.a.b.j(str);
            String c2 = p0.c(jVar);
            n nVar = (n) this.info.f("extensions");
            if (c2 != null) {
                try {
                    e0Var = (e0) get(c2);
                } catch (CertificateException unused) {
                }
            } else if (nVar == null) {
                return null;
            } else {
                Iterator<e0> it = nVar.e().iterator();
                while (it.hasNext()) {
                    e0Var = it.next();
                    if (e0Var.c().equals(jVar)) {
                        break;
                    }
                }
                e0Var = null;
            }
            if (e0Var == null) {
                if (nVar != null) {
                    e0Var = nVar.f().get(str);
                }
                if (e0Var == null) {
                    return null;
                }
            }
            byte[] d = e0Var.d();
            if (d == null) {
                return null;
            }
            m.a.b.h hVar = new m.a.b.h();
            hVar.q(d);
            return hVar.toByteArray();
        } catch (Exception unused2) {
            return null;
        }
    }

    public l0 getIssuerAlternativeNameExtension() {
        return (l0) getExtension(s0.G);
    }

    @Override // java.security.cert.X509Certificate
    public synchronized Collection<List<?>> getIssuerAlternativeNames() throws CertificateParsingException {
        Collection<List<?>> collection;
        if (this.readOnly && (collection = this.issuerAlternativeNames) != null) {
            return a(collection);
        }
        l0 issuerAlternativeNameExtension = getIssuerAlternativeNameExtension();
        if (issuerAlternativeNameExtension == null) {
            return null;
        }
        try {
            Collection<List<?>> c2 = c((h0) issuerAlternativeNameExtension.g("issuer_name"));
            this.issuerAlternativeNames = c2;
            return c2;
        } catch (IOException unused) {
            return Collections.emptySet();
        }
    }

    @Override // java.security.cert.X509Certificate
    public Principal getIssuerDN() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (Principal) k1Var.f("issuer.dname");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getIssuerUniqueID() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            d1 d1Var = (d1) k1Var.f("issuerID.id");
            if (d1Var == null) {
                return null;
            }
            return d1Var.b();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getIssuerX500Principal() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (X500Principal) k1Var.f("issuer.x500principal");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        n0 n0Var;
        try {
            String c2 = p0.c(s0.B);
            if (c2 == null || (n0Var = (n0) get(c2)) == null) {
                return null;
            }
            boolean[] h2 = n0Var.h();
            if (h2.length < 9) {
                boolean[] zArr = new boolean[9];
                System.arraycopy(h2, 0, zArr, 0, h2.length);
                return zArr;
            }
            return h2;
        } catch (Exception unused) {
            return null;
        }
    }

    public String getName() {
        return NAME;
    }

    public o0 getNameConstraintsExtension() {
        return (o0) getExtension(s0.I);
    }

    @Override // java.security.cert.X509Extension
    public Set<String> getNonCriticalExtensionOIDs() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            n nVar = (n) k1Var.f("extensions");
            if (nVar == null) {
                return null;
            }
            HashSet hashSet = new HashSet();
            for (e0 e0Var : nVar.e()) {
                if (!e0Var.e()) {
                    hashSet.add(e0Var.c().toString());
                }
            }
            hashSet.addAll(nVar.f().keySet());
            return hashSet;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotAfter() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (Date) k1Var.f("validity.notAfter");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotBefore() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (Date) k1Var.f("validity.notBefore");
        } catch (Exception unused) {
            return null;
        }
    }

    public t0 getPolicyConstraintsExtension() {
        return (t0) getExtension(s0.J);
    }

    public v0 getPolicyMappingsExtension() {
        return (v0) getExtension(s0.E);
    }

    public w0 getPrivateKeyUsageExtension() {
        return (w0) getExtension(s0.C);
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (PublicKey) k1Var.f("key.value");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        z0 serialNumberObject = getSerialNumberObject();
        if (serialNumberObject != null) {
            return serialNumberObject.c();
        }
        return null;
    }

    public z0 getSerialNumberObject() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (z0) k1Var.f("serialNumber.number");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgName() {
        e eVar = this.algId;
        if (eVar == null) {
            return null;
        }
        return eVar.getName();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgOID() {
        e eVar = this.algId;
        if (eVar == null) {
            return null;
        }
        return eVar.getOID().toString();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        e eVar = this.algId;
        if (eVar == null) {
            return null;
        }
        try {
            return eVar.getEncodedParams();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSignature() {
        byte[] bArr = this.signature;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public a1 getSubjectAlternativeNameExtension() {
        return (a1) getExtension(s0.F);
    }

    @Override // java.security.cert.X509Certificate
    public synchronized Collection<List<?>> getSubjectAlternativeNames() throws CertificateParsingException {
        Collection<List<?>> collection;
        if (this.readOnly && (collection = this.subjectAlternativeNames) != null) {
            return a(collection);
        }
        a1 subjectAlternativeNameExtension = getSubjectAlternativeNameExtension();
        if (subjectAlternativeNameExtension == null) {
            return null;
        }
        try {
            Collection<List<?>> c2 = c((h0) subjectAlternativeNameExtension.g("subject_name"));
            this.subjectAlternativeNames = c2;
            return c2;
        } catch (IOException unused) {
            return Collections.emptySet();
        }
    }

    @Override // java.security.cert.X509Certificate
    public Principal getSubjectDN() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (Principal) k1Var.f("subject.dname");
        } catch (Exception unused) {
            return null;
        }
    }

    public b1 getSubjectKeyIdentifierExtension() {
        return (b1) getExtension(s0.A);
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getSubjectUniqueID() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            d1 d1Var = (d1) k1Var.f("subjectID.id");
            if (d1Var == null) {
                return null;
            }
            return d1Var.b();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getSubjectX500Principal() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            return (X500Principal) k1Var.f("subject.x500principal");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        k1 k1Var = this.info;
        if (k1Var != null) {
            return k1Var.g();
        }
        throw new CertificateEncodingException("Uninitialized certificate");
    }

    public e0 getUnparseableExtension(m.a.b.j jVar) {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return null;
        }
        try {
            n nVar = (n) k1Var.f("extensions");
            if (nVar == null) {
                return null;
            }
            return nVar.f().get(jVar.toString());
        } catch (IOException | CertificateException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public int getVersion() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return -1;
        }
        try {
            return ((Integer) k1Var.f("version.number")).intValue() + 1;
        } catch (Exception unused) {
            return -1;
        }
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        k1 k1Var = this.info;
        if (k1Var == null) {
            return false;
        }
        try {
            n nVar = (n) k1Var.f("extensions");
            if (nVar == null) {
                return false;
            }
            return nVar.g();
        } catch (Exception unused) {
            return false;
        }
    }

    public void set(String str, Object obj) throws CertificateException, IOException {
        if (!this.readOnly) {
            g1 g1Var = new g1(str);
            String a = g1Var.a();
            if (a.equalsIgnoreCase(NAME)) {
                g1 g1Var2 = new g1(g1Var.b());
                String a2 = g1Var2.a();
                if (a2.equalsIgnoreCase("info")) {
                    if (g1Var2.b() == null) {
                        if (obj instanceof k1) {
                            this.info = (k1) obj;
                            this.signedCert = null;
                            return;
                        }
                        throw new CertificateException("Attribute value should be of type X509CertInfo.");
                    }
                    this.info.i(g1Var2.b(), obj);
                    this.signedCert = null;
                    return;
                }
                throw new CertificateException("Attribute name not recognized or set() not allowed for the same: " + a2);
            }
            throw new CertificateException("Invalid root of attribute name, expected [x509], received " + a);
        }
        throw new CertificateException("cannot over-write existing certificate");
    }

    public void sign(PrivateKey privateKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        sign(privateKey, str, null);
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        if (this.info == null || this.algId == null || this.signature == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append(this.info.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("  Algorithm: [" + this.algId.toString() + "]\n");
        sb.append("  Signature:\n" + new HexDumpEncoder().encodeBuffer(this.signature));
        sb.append("\n]");
        return sb.toString();
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        verify(publicKey, "");
    }

    public void sign(PrivateKey privateKey, String str, String str2) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature;
        try {
            if (!this.readOnly) {
                if (str2 != null && str2.length() != 0) {
                    signature = Signature.getInstance(str, str2);
                    signature.initSign(privateKey);
                    this.algId = e.get(signature.getAlgorithm());
                    m.a.b.h hVar = new m.a.b.h();
                    m.a.b.h hVar2 = new m.a.b.h();
                    this.info.a(hVar2);
                    byte[] byteArray = hVar2.toByteArray();
                    this.algId.encode(hVar2);
                    signature.update(byteArray, 0, byteArray.length);
                    byte[] sign = signature.sign();
                    this.signature = sign;
                    hVar2.f(sign);
                    hVar.y((byte) 48, hVar2);
                    this.signedCert = hVar.toByteArray();
                    this.readOnly = true;
                    return;
                }
                signature = Signature.getInstance(str);
                signature.initSign(privateKey);
                this.algId = e.get(signature.getAlgorithm());
                m.a.b.h hVar3 = new m.a.b.h();
                m.a.b.h hVar22 = new m.a.b.h();
                this.info.a(hVar22);
                byte[] byteArray2 = hVar22.toByteArray();
                this.algId.encode(hVar22);
                signature.update(byteArray2, 0, byteArray2.length);
                byte[] sign2 = signature.sign();
                this.signature = sign2;
                hVar22.f(sign2);
                hVar3.y((byte) 48, hVar22);
                this.signedCert = hVar3.toByteArray();
                this.readOnly = true;
                return;
            }
            throw new CertificateEncodingException("cannot over-write existing certificate");
        } catch (IOException e2) {
            throw new CertificateEncodingException(e2.toString());
        }
    }

    @Override // java.security.cert.Certificate
    public synchronized void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature;
        if (str == null) {
            str = "";
        }
        PublicKey publicKey2 = this.verifiedPublicKey;
        if (publicKey2 != null && publicKey2.equals(publicKey) && str.equals(this.verifiedProvider)) {
            if (!this.verificationResult) {
                throw new SignatureException("Signature does not match.");
            }
        } else if (this.signedCert != null) {
            if (str.length() == 0) {
                signature = Signature.getInstance(this.algId.getName());
            } else {
                signature = Signature.getInstance(this.algId.getName(), str);
            }
            signature.initVerify(publicKey);
            byte[] g2 = this.info.g();
            signature.update(g2, 0, g2.length);
            boolean verify2 = signature.verify(this.signature);
            this.verificationResult = verify2;
            this.verifiedPublicKey = publicKey;
            this.verifiedProvider = str;
            if (!verify2) {
                throw new SignatureException("Signature does not match.");
            }
        } else {
            throw new CertificateEncodingException("Uninitialized certificate");
        }
    }

    public static byte[] getEncodedInternal(Certificate certificate) throws CertificateEncodingException {
        if (certificate instanceof j1) {
            return ((j1) certificate).getEncodedInternal();
        }
        return certificate.getEncoded();
    }

    public static X500Principal getIssuerX500Principal(X509Certificate x509Certificate) {
        try {
            return b(x509Certificate, true);
        } catch (Exception e2) {
            throw new RuntimeException("Could not parse issuer", e2);
        }
    }

    public static X500Principal getSubjectX500Principal(X509Certificate x509Certificate) {
        try {
            return b(x509Certificate, false);
        } catch (Exception e2) {
            throw new RuntimeException("Could not parse subject", e2);
        }
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        try {
            x xVar = (x) this.info.f("validity");
            if (xVar != null) {
                xVar.h(date);
                return;
            }
            throw new CertificateNotYetValidException("Null validity period");
        } catch (Exception unused) {
            throw new CertificateNotYetValidException("Incorrect validity period");
        }
    }

    public j1(byte[] bArr) throws CertificateException {
        this.readOnly = false;
        this.signedCert = null;
        this.info = null;
        this.algId = null;
        this.signature = null;
        try {
            d(new m.a.b.i(bArr));
        } catch (IOException e2) {
            this.signedCert = null;
            CertificateException certificateException = new CertificateException("Unable to initialize, " + e2);
            certificateException.initCause(e2);
            throw certificateException;
        }
    }

    public static List<String> getExtendedKeyUsage(X509Certificate x509Certificate) throws CertificateParsingException {
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue("2.5.29.37");
            if (extensionValue == null) {
                return null;
            }
            return Collections.unmodifiableList(new d0(Boolean.FALSE, new m.a.b.i(extensionValue).q()).g());
        } catch (IOException e2) {
            CertificateParsingException certificateParsingException = new CertificateParsingException();
            certificateParsingException.initCause(e2);
            throw certificateParsingException;
        }
    }

    public static Collection<List<?>> getIssuerAlternativeNames(X509Certificate x509Certificate) throws CertificateParsingException {
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue("2.5.29.18");
            if (extensionValue == null) {
                return null;
            }
            try {
                return c((h0) new l0(Boolean.FALSE, new m.a.b.i(extensionValue).q()).g("issuer_name"));
            } catch (IOException unused) {
                return Collections.emptySet();
            }
        } catch (IOException e2) {
            CertificateParsingException certificateParsingException = new CertificateParsingException();
            certificateParsingException.initCause(e2);
            throw certificateParsingException;
        }
    }

    public static Collection<List<?>> getSubjectAlternativeNames(X509Certificate x509Certificate) throws CertificateParsingException {
        try {
            byte[] extensionValue = x509Certificate.getExtensionValue("2.5.29.17");
            if (extensionValue == null) {
                return null;
            }
            try {
                return c((h0) new a1(Boolean.FALSE, new m.a.b.i(extensionValue).q()).g("subject_name"));
            } catch (IOException unused) {
                return Collections.emptySet();
            }
        } catch (IOException e2) {
            CertificateParsingException certificateParsingException = new CertificateParsingException();
            certificateParsingException.initCause(e2);
            throw certificateParsingException;
        }
    }

    public j1(InputStream inputStream) throws CertificateException {
        m.a.b.i iVar;
        this.readOnly = false;
        this.signedCert = null;
        this.info = null;
        this.algId = null;
        this.signature = null;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
            try {
                bufferedInputStream.mark(Integer.MAX_VALUE);
                iVar = e(bufferedInputStream);
            } catch (IOException unused) {
                bufferedInputStream.reset();
                iVar = new m.a.b.i(bufferedInputStream);
            }
            try {
                d(iVar);
            } catch (IOException e2) {
                this.signedCert = null;
                CertificateException certificateException = new CertificateException("Unable to parse DER value of certificate, " + e2);
                certificateException.initCause(e2);
                throw certificateException;
            }
        } catch (IOException e3) {
            CertificateException certificateException2 = new CertificateException("Input stream must be either DER-encoded bytes or RFC1421 hex-encoded DER-encoded bytes: " + e3.getMessage());
            certificateException2.initCause(e3);
            throw certificateException2;
        }
    }

    public j1(k1 k1Var) {
        this.readOnly = false;
        this.signedCert = null;
        this.info = null;
        this.algId = null;
        this.signature = null;
        this.info = k1Var;
    }

    public j1(m.a.b.i iVar) throws CertificateException {
        this.readOnly = false;
        this.signedCert = null;
        this.info = null;
        this.algId = null;
        this.signature = null;
        try {
            d(iVar);
        } catch (IOException e2) {
            this.signedCert = null;
            CertificateException certificateException = new CertificateException("Unable to initialize, " + e2);
            certificateException.initCause(e2);
            throw certificateException;
        }
    }
}
