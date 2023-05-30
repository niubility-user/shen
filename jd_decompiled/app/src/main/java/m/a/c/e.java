package m.a.c;

import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes11.dex */
public class e implements Serializable, m.a.b.d {
    public static final m.a.b.j DH_PKIX_oid;
    public static final m.a.b.j DH_oid;
    public static final m.a.b.j DSA_OIW_oid;
    public static final m.a.b.j DSA_oid;
    public static final m.a.b.j EC_oid;
    public static final m.a.b.j MD2_oid;
    public static final m.a.b.j MD5_oid;
    public static final m.a.b.j RSAEncryption_oid;
    public static final m.a.b.j RSA_oid;
    public static final m.a.b.j SHA256_oid;
    public static final m.a.b.j SHA384_oid;
    public static final m.a.b.j SHA512_oid;
    public static final m.a.b.j SHA_oid;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f20325g = false;

    /* renamed from: h  reason: collision with root package name */
    private static Map<String, m.a.b.j> f20326h = null;

    /* renamed from: i  reason: collision with root package name */
    private static final Map<m.a.b.j, String> f20327i;

    /* renamed from: j  reason: collision with root package name */
    private static final int[] f20328j;

    /* renamed from: k  reason: collision with root package name */
    private static final int[] f20329k;

    /* renamed from: l  reason: collision with root package name */
    private static final int[] f20330l;

    /* renamed from: m  reason: collision with root package name */
    private static final int[] f20331m;
    public static final m.a.b.j md2WithRSAEncryption_oid;
    public static final m.a.b.j md5WithRSAEncryption_oid;

    /* renamed from: n  reason: collision with root package name */
    private static final int[] f20332n;
    private static final int[] o;
    private static final int[] p;
    public static final m.a.b.j pbeWithMD5AndDES_oid;
    public static final m.a.b.j pbeWithMD5AndRC2_oid;
    public static final m.a.b.j pbeWithSHA1AndDES_oid;
    public static m.a.b.j pbeWithSHA1AndDESede_oid = null;
    public static m.a.b.j pbeWithSHA1AndRC2_40_oid = null;
    public static final m.a.b.j pbeWithSHA1AndRC2_oid;
    private static final int[] q;
    private static final int[] r;
    private static final int[] s;
    private static final long serialVersionUID = 7205873507486557157L;
    public static final m.a.b.j sha1WithDSA_OIW_oid;
    public static final m.a.b.j sha1WithDSA_oid;
    public static final m.a.b.j sha1WithECDSA_oid;
    public static final m.a.b.j sha1WithRSAEncryption_OIW_oid;
    public static final m.a.b.j sha1WithRSAEncryption_oid;
    public static final m.a.b.j sha224WithECDSA_oid;
    public static final m.a.b.j sha256WithECDSA_oid;
    public static final m.a.b.j sha256WithRSAEncryption_oid;
    public static final m.a.b.j sha384WithECDSA_oid;
    public static final m.a.b.j sha384WithRSAEncryption_oid;
    public static final m.a.b.j sha512WithECDSA_oid;
    public static final m.a.b.j sha512WithRSAEncryption_oid;
    public static final m.a.b.j shaWithDSA_OIW_oid;
    public static final m.a.b.j specifiedWithECDSA_oid;
    private static final int[] t;
    private static final int[] u;
    private static final int[] v;
    private static final int[] w;
    private static final int[] x;
    private static final int[] y;
    private AlgorithmParameters algParams;
    private m.a.b.j algid;
    private boolean constructedFromDer;
    protected m.a.b.i params;

    static {
        m.a.b.j newInternal = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 2, 2});
        MD2_oid = newInternal;
        m.a.b.j newInternal2 = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 2, 5});
        MD5_oid = newInternal2;
        m.a.b.j newInternal3 = m.a.b.j.newInternal(new int[]{1, 3, 14, 3, 2, 26});
        SHA_oid = newInternal3;
        m.a.b.j newInternal4 = m.a.b.j.newInternal(new int[]{2, 16, R2.attr.exTabIndicatorHeight, 1, 101, 3, 4, 2, 1});
        SHA256_oid = newInternal4;
        m.a.b.j newInternal5 = m.a.b.j.newInternal(new int[]{2, 16, R2.attr.exTabIndicatorHeight, 1, 101, 3, 4, 2, 2});
        SHA384_oid = newInternal5;
        m.a.b.j newInternal6 = m.a.b.j.newInternal(new int[]{2, 16, R2.attr.exTabIndicatorHeight, 1, 101, 3, 4, 2, 3});
        SHA512_oid = newInternal6;
        int[] iArr = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 3, 1};
        f20328j = iArr;
        int[] iArr2 = {1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_section, 2, 1};
        f20329k = iArr2;
        int[] iArr3 = {1, 3, 14, 3, 2, 12};
        f20330l = iArr3;
        int[] iArr4 = {1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_num_reduce_disable_dark, 4, 1};
        f20331m = iArr4;
        int[] iArr5 = {1, 2, 5, 8, 1, 1};
        f20332n = iArr5;
        int[] iArr6 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 1};
        o = iArr6;
        m.a.b.j c2 = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 2, 1);
        EC_oid = c2;
        int[] iArr7 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 2};
        p = iArr7;
        int[] iArr8 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 4};
        q = iArr8;
        int[] iArr9 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 5};
        r = iArr9;
        int[] iArr10 = {1, 3, 14, 3, 2, 29};
        s = iArr10;
        int[] iArr11 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 11};
        t = iArr11;
        int[] iArr12 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 12};
        u = iArr12;
        int[] iArr13 = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 1, 13};
        v = iArr13;
        int[] iArr14 = {1, 3, 14, 3, 2, 13};
        w = iArr14;
        int[] iArr15 = {1, 3, 14, 3, 2, 27};
        x = iArr15;
        int[] iArr16 = {1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_num_reduce_disable_dark, 4, 3};
        y = iArr16;
        m.a.b.j c3 = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 4, 1);
        sha1WithECDSA_oid = c3;
        m.a.b.j c4 = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 4, 3, 1);
        sha224WithECDSA_oid = c4;
        m.a.b.j c5 = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 4, 3, 2);
        sha256WithECDSA_oid = c5;
        m.a.b.j c6 = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 4, 3, 3);
        sha384WithECDSA_oid = c6;
        m.a.b.j c7 = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 4, 3, 4);
        sha512WithECDSA_oid = c7;
        specifiedWithECDSA_oid = c(1, 2, R2.attr.exTabIndicatorHeight, R2.drawable.libpdstyleinfoview_style_service_discount_selector, 4, 3);
        m.a.b.j newInternal7 = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 5, 3});
        pbeWithMD5AndDES_oid = newInternal7;
        m.a.b.j newInternal8 = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 5, 6});
        pbeWithMD5AndRC2_oid = newInternal8;
        m.a.b.j newInternal9 = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 5, 10});
        pbeWithSHA1AndDES_oid = newInternal9;
        m.a.b.j newInternal10 = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 5, 11});
        pbeWithSHA1AndRC2_oid = newInternal10;
        pbeWithSHA1AndDESede_oid = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 12, 1, 3});
        pbeWithSHA1AndRC2_40_oid = m.a.b.j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 12, 1, 6});
        m.a.b.j newInternal11 = m.a.b.j.newInternal(iArr);
        DH_oid = newInternal11;
        m.a.b.j newInternal12 = m.a.b.j.newInternal(iArr2);
        DH_PKIX_oid = newInternal12;
        m.a.b.j newInternal13 = m.a.b.j.newInternal(iArr3);
        DSA_OIW_oid = newInternal13;
        m.a.b.j newInternal14 = m.a.b.j.newInternal(iArr4);
        DSA_oid = newInternal14;
        m.a.b.j newInternal15 = m.a.b.j.newInternal(iArr5);
        RSA_oid = newInternal15;
        m.a.b.j newInternal16 = m.a.b.j.newInternal(iArr6);
        RSAEncryption_oid = newInternal16;
        m.a.b.j newInternal17 = m.a.b.j.newInternal(iArr7);
        md2WithRSAEncryption_oid = newInternal17;
        m.a.b.j newInternal18 = m.a.b.j.newInternal(iArr8);
        md5WithRSAEncryption_oid = newInternal18;
        m.a.b.j newInternal19 = m.a.b.j.newInternal(iArr9);
        sha1WithRSAEncryption_oid = newInternal19;
        m.a.b.j newInternal20 = m.a.b.j.newInternal(iArr10);
        sha1WithRSAEncryption_OIW_oid = newInternal20;
        m.a.b.j newInternal21 = m.a.b.j.newInternal(iArr11);
        sha256WithRSAEncryption_oid = newInternal21;
        m.a.b.j newInternal22 = m.a.b.j.newInternal(iArr12);
        sha384WithRSAEncryption_oid = newInternal22;
        m.a.b.j newInternal23 = m.a.b.j.newInternal(iArr13);
        sha512WithRSAEncryption_oid = newInternal23;
        m.a.b.j newInternal24 = m.a.b.j.newInternal(iArr14);
        shaWithDSA_OIW_oid = newInternal24;
        m.a.b.j newInternal25 = m.a.b.j.newInternal(iArr15);
        sha1WithDSA_OIW_oid = newInternal25;
        m.a.b.j newInternal26 = m.a.b.j.newInternal(iArr16);
        sha1WithDSA_oid = newInternal26;
        HashMap hashMap = new HashMap();
        f20327i = hashMap;
        hashMap.put(newInternal2, MessageDigestAlgorithms.MD5);
        hashMap.put(newInternal, MessageDigestAlgorithms.MD2);
        hashMap.put(newInternal3, "SHA");
        hashMap.put(newInternal4, "SHA256");
        hashMap.put(newInternal5, "SHA384");
        hashMap.put(newInternal6, "SHA512");
        hashMap.put(newInternal16, RSAUtils.KEY_ALGORITHM);
        hashMap.put(newInternal15, RSAUtils.KEY_ALGORITHM);
        hashMap.put(newInternal11, "Diffie-Hellman");
        hashMap.put(newInternal12, "Diffie-Hellman");
        hashMap.put(newInternal14, "DSA");
        hashMap.put(newInternal13, "DSA");
        hashMap.put(c2, "EC");
        hashMap.put(c3, "SHA1withECDSA");
        hashMap.put(c4, "SHA224withECDSA");
        hashMap.put(c5, "SHA256withECDSA");
        hashMap.put(c6, "SHA384withECDSA");
        hashMap.put(c7, "SHA512withECDSA");
        hashMap.put(newInternal18, RSAUtils.SIGNATURE_ALGORITHM);
        hashMap.put(newInternal17, "MD2withRSA");
        hashMap.put(newInternal26, "SHA1withDSA");
        hashMap.put(newInternal25, "SHA1withDSA");
        hashMap.put(newInternal24, "SHA1withDSA");
        hashMap.put(newInternal19, "SHA1withRSA");
        hashMap.put(newInternal20, "SHA1withRSA");
        hashMap.put(newInternal21, "SHA256withRSA");
        hashMap.put(newInternal22, "SHA384withRSA");
        hashMap.put(newInternal23, "SHA512withRSA");
        hashMap.put(newInternal7, "PBEWithMD5AndDES");
        hashMap.put(newInternal8, "PBEWithMD5AndRC2");
        hashMap.put(newInternal9, "PBEWithSHA1AndDES");
        hashMap.put(newInternal10, "PBEWithSHA1AndRC2");
        hashMap.put(pbeWithSHA1AndDESede_oid, "PBEWithSHA1AndDESede");
        hashMap.put(pbeWithSHA1AndRC2_40_oid, "PBEWithSHA1AndRC2_40");
    }

    @Deprecated
    public e() {
        this.constructedFromDer = true;
    }

    private static m.a.b.j a(String str) throws IOException {
        int indexOf;
        if (str.indexOf(46) != -1) {
            if (str.startsWith("OID.")) {
                return new m.a.b.j(str.substring(4));
            }
            return new m.a.b.j(str);
        } else if (str.equalsIgnoreCase(MessageDigestAlgorithms.MD5)) {
            return MD5_oid;
        } else {
            if (str.equalsIgnoreCase(MessageDigestAlgorithms.MD2)) {
                return MD2_oid;
            }
            if (!str.equalsIgnoreCase("SHA") && !str.equalsIgnoreCase("SHA1") && !str.equalsIgnoreCase(MessageDigestAlgorithms.SHA_1)) {
                if (!str.equalsIgnoreCase(MessageDigestAlgorithms.SHA_256) && !str.equalsIgnoreCase("SHA256")) {
                    if (!str.equalsIgnoreCase(MessageDigestAlgorithms.SHA_384) && !str.equalsIgnoreCase("SHA384")) {
                        if (!str.equalsIgnoreCase(MessageDigestAlgorithms.SHA_512) && !str.equalsIgnoreCase("SHA512")) {
                            if (str.equalsIgnoreCase(RSAUtils.KEY_ALGORITHM)) {
                                return RSAEncryption_oid;
                            }
                            if (!str.equalsIgnoreCase("Diffie-Hellman") && !str.equalsIgnoreCase("DH")) {
                                if (str.equalsIgnoreCase("DSA")) {
                                    return DSA_oid;
                                }
                                if (str.equalsIgnoreCase("EC")) {
                                    return EC_oid;
                                }
                                if (!str.equalsIgnoreCase(RSAUtils.SIGNATURE_ALGORITHM) && !str.equalsIgnoreCase("MD5/RSA")) {
                                    if (!str.equalsIgnoreCase("MD2withRSA") && !str.equalsIgnoreCase("MD2/RSA")) {
                                        if (!str.equalsIgnoreCase("SHAwithDSA") && !str.equalsIgnoreCase("SHA1withDSA") && !str.equalsIgnoreCase("SHA/DSA") && !str.equalsIgnoreCase("SHA1/DSA") && !str.equalsIgnoreCase("DSAWithSHA1") && !str.equalsIgnoreCase("DSS") && !str.equalsIgnoreCase("SHA-1/DSA")) {
                                            if (!str.equalsIgnoreCase("SHA1WithRSA") && !str.equalsIgnoreCase("SHA1/RSA")) {
                                                if (!str.equalsIgnoreCase("SHA1withECDSA") && !str.equalsIgnoreCase("ECDSA")) {
                                                    if (!f20325g) {
                                                        Provider[] providers = Security.getProviders();
                                                        for (int i2 = 0; i2 < providers.length; i2++) {
                                                            Enumeration<Object> keys = providers[i2].keys();
                                                            while (keys.hasMoreElements()) {
                                                                String str2 = (String) keys.nextElement();
                                                                if (str2.toUpperCase().startsWith("ALG.ALIAS") && (indexOf = str2.toUpperCase().indexOf("OID.", 0)) != -1) {
                                                                    int i3 = indexOf + 4;
                                                                    if (i3 == str2.length()) {
                                                                        break;
                                                                    }
                                                                    if (f20326h == null) {
                                                                        f20326h = new HashMap();
                                                                    }
                                                                    String substring = str2.substring(i3);
                                                                    String upperCase = providers[i2].getProperty(str2).toUpperCase();
                                                                    if (f20326h.get(upperCase) == null) {
                                                                        f20326h.put(upperCase, new m.a.b.j(substring));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        f20325g = true;
                                                    }
                                                    return f20326h.get(str.toUpperCase());
                                                }
                                                return sha1WithECDSA_oid;
                                            }
                                            return sha1WithRSAEncryption_oid;
                                        }
                                        return sha1WithDSA_oid;
                                    }
                                    return md2WithRSAEncryption_oid;
                                }
                                return md5WithRSAEncryption_oid;
                            }
                            return DH_oid;
                        }
                        return SHA512_oid;
                    }
                    return SHA384_oid;
                }
                return SHA256_oid;
            }
            return SHA_oid;
        }
    }

    private static m.a.b.j c(int... iArr) {
        return m.a.b.j.newInternal(iArr);
    }

    public static e get(String str) throws NoSuchAlgorithmException {
        try {
            m.a.b.j a = a(str);
            if (a != null) {
                return new e(a);
            }
            throw new NoSuchAlgorithmException("unrecognized algorithm name: " + str);
        } catch (IOException unused) {
            throw new NoSuchAlgorithmException("Invalid ObjectIdentifier " + str);
        }
    }

    @Deprecated
    public static e getAlgorithmId(String str) throws NoSuchAlgorithmException {
        return get(str);
    }

    public static e parse(m.a.b.i iVar) throws IOException {
        if (iVar.a == 48) {
            m.a.b.g E = iVar.E();
            m.a.b.j k2 = E.k();
            m.a.b.i iVar2 = null;
            if (E.a() != 0) {
                m.a.b.i e2 = E.e();
                if (e2.a != 5) {
                    iVar2 = e2;
                } else if (e2.B() != 0) {
                    throw new IOException("invalid NULL");
                }
                if (E.a() != 0) {
                    throw new IOException("Invalid AlgorithmIdentifier: extra data");
                }
            }
            return new e(k2, iVar2);
        }
        throw new IOException("algid parse error, not a sequence");
    }

    protected void b() throws IOException {
        try {
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(this.algid.toString());
            this.algParams = algorithmParameters;
            algorithmParameters.init(this.params.D());
        } catch (NoSuchAlgorithmException unused) {
            this.algParams = null;
        }
    }

    protected String d() {
        if (this.params == null) {
            return "";
        }
        AlgorithmParameters algorithmParameters = this.algParams;
        return algorithmParameters != null ? algorithmParameters.toString() : ", params unparsed";
    }

    @Override // m.a.b.d
    public void derEncode(OutputStream outputStream) throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        m.a.b.h hVar2 = new m.a.b.h();
        hVar.p(this.algid);
        if (!this.constructedFromDer) {
            AlgorithmParameters algorithmParameters = this.algParams;
            if (algorithmParameters != null) {
                this.params = new m.a.b.i(algorithmParameters.getEncoded());
            } else {
                this.params = null;
            }
        }
        m.a.b.i iVar = this.params;
        if (iVar == null) {
            hVar.o();
        } else {
            hVar.h(iVar);
        }
        hVar2.y((byte) 48, hVar);
        outputStream.write(hVar2.toByteArray());
    }

    public final void encode(m.a.b.h hVar) throws IOException {
        derEncode(hVar);
    }

    public boolean equals(e eVar) {
        m.a.b.i iVar = this.params;
        return this.algid.equals(eVar.algid) && (iVar == null ? eVar.params == null : iVar.e(eVar.params));
    }

    public byte[] getEncodedParams() throws IOException {
        m.a.b.i iVar = this.params;
        if (iVar == null) {
            return null;
        }
        return iVar.D();
    }

    public String getName() {
        String str = f20327i.get(this.algid);
        if (str != null) {
            return str;
        }
        if (this.params != null && this.algid.equals(specifiedWithECDSA_oid)) {
            try {
                String name = parse(new m.a.b.i(getEncodedParams())).getName();
                if (name.equals("SHA")) {
                    name = "SHA1";
                }
                str = name + "withECDSA";
            } catch (IOException unused) {
            }
        }
        return str == null ? this.algid.toString() : str;
    }

    public final m.a.b.j getOID() {
        return this.algid;
    }

    public AlgorithmParameters getParameters() {
        return this.algParams;
    }

    public int hashCode() {
        return (this.algid.toString() + d()).hashCode();
    }

    public String toString() {
        return getName() + d();
    }

    public final byte[] encode() throws IOException {
        m.a.b.h hVar = new m.a.b.h();
        derEncode(hVar);
        return hVar.toByteArray();
    }

    public e(m.a.b.j jVar) {
        this.constructedFromDer = true;
        this.algid = jVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            return equals((e) obj);
        }
        if (obj instanceof m.a.b.j) {
            return equals((m.a.b.j) obj);
        }
        return false;
    }

    public static e get(AlgorithmParameters algorithmParameters) throws NoSuchAlgorithmException {
        String algorithm = algorithmParameters.getAlgorithm();
        try {
            m.a.b.j a = a(algorithm);
            if (a != null) {
                return new e(a, algorithmParameters);
            }
            throw new NoSuchAlgorithmException("unrecognized algorithm name: " + algorithm);
        } catch (IOException unused) {
            throw new NoSuchAlgorithmException("Invalid ObjectIdentifier " + algorithm);
        }
    }

    public e(m.a.b.j jVar, AlgorithmParameters algorithmParameters) {
        this.constructedFromDer = true;
        this.algid = jVar;
        this.algParams = algorithmParameters;
        this.constructedFromDer = false;
    }

    public final boolean equals(m.a.b.j jVar) {
        return this.algid.equals(jVar);
    }

    private e(m.a.b.j jVar, m.a.b.i iVar) throws IOException {
        this.constructedFromDer = true;
        this.algid = jVar;
        this.params = iVar;
        if (iVar != null) {
            b();
        }
    }
}
