package sun.security.pkcs;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Hashtable;
import jd.wjlogin_sdk.util.ReplyCode;
import m.a.b.h;
import m.a.b.i;
import m.a.b.j;
import m.a.c.n;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class c implements m.a.b.d {

    /* renamed from: i  reason: collision with root package name */
    private static final m.a.b.c f20483i = m.a.b.c.b("jar");

    /* renamed from: j  reason: collision with root package name */
    static final j[] f20484j = new j[18];

    /* renamed from: k  reason: collision with root package name */
    public static final j f20485k;

    /* renamed from: l  reason: collision with root package name */
    public static final j f20486l;

    /* renamed from: m  reason: collision with root package name */
    public static final j f20487m;

    /* renamed from: n  reason: collision with root package name */
    private static final Hashtable<String, j> f20488n;
    private static final Hashtable<j, String> o;
    private static final Byte[][] p;
    private static final Class[] q;
    private static final boolean[] r;

    /* renamed from: g  reason: collision with root package name */
    private int f20489g;

    /* renamed from: h  reason: collision with root package name */
    private Object f20490h;

    static {
        int i2 = 1;
        while (true) {
            j[] jVarArr = f20484j;
            if (i2 < jVarArr.length - 2) {
                jVarArr[i2] = j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 9, i2});
                i2++;
            } else {
                jVarArr[jVarArr.length - 2] = j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 9, 16, 2, 12});
                jVarArr[jVarArr.length - 1] = j.newInternal(new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 9, 16, 2, 14});
                f20485k = jVarArr[1];
                j jVar = jVarArr[2];
                f20486l = jVarArr[3];
                f20487m = jVarArr[4];
                j jVar2 = jVarArr[5];
                j jVar3 = jVarArr[6];
                j jVar4 = jVarArr[7];
                j jVar5 = jVarArr[8];
                j jVar6 = jVarArr[9];
                j jVar7 = jVarArr[10];
                j jVar8 = jVarArr[14];
                j jVar9 = jVarArr[15];
                j jVar10 = jVarArr[16];
                j jVar11 = jVarArr[17];
                Hashtable<String, j> hashtable = new Hashtable<>(18);
                f20488n = hashtable;
                hashtable.put("emailaddress", jVarArr[1]);
                hashtable.put("unstructuredname", jVarArr[2]);
                hashtable.put("contenttype", jVarArr[3]);
                hashtable.put("messagedigest", jVarArr[4]);
                hashtable.put("signingtime", jVarArr[5]);
                hashtable.put("countersignature", jVarArr[6]);
                hashtable.put("challengepassword", jVarArr[7]);
                hashtable.put("unstructuredaddress", jVarArr[8]);
                hashtable.put("extendedcertificateattributes", jVarArr[9]);
                hashtable.put("issuerandserialnumber", jVarArr[10]);
                hashtable.put("rsaproprietary", jVarArr[11]);
                hashtable.put("rsaproprietary", jVarArr[12]);
                hashtable.put("signingdescription", jVarArr[13]);
                hashtable.put("extensionrequest", jVarArr[14]);
                hashtable.put("smimecapability", jVarArr[15]);
                hashtable.put("signingcertificate", jVarArr[16]);
                hashtable.put("signaturetimestamptoken", jVarArr[17]);
                Hashtable<j, String> hashtable2 = new Hashtable<>(16);
                o = hashtable2;
                hashtable2.put(jVarArr[1], "EmailAddress");
                hashtable2.put(jVarArr[2], "UnstructuredName");
                hashtable2.put(jVarArr[3], "ContentType");
                hashtable2.put(jVarArr[4], "MessageDigest");
                hashtable2.put(jVarArr[5], "SigningTime");
                hashtable2.put(jVarArr[6], "Countersignature");
                hashtable2.put(jVarArr[7], "ChallengePassword");
                hashtable2.put(jVarArr[8], "UnstructuredAddress");
                hashtable2.put(jVarArr[9], "ExtendedCertificateAttributes");
                hashtable2.put(jVarArr[10], "IssuerAndSerialNumber");
                hashtable2.put(jVarArr[11], "RSAProprietary");
                hashtable2.put(jVarArr[12], "RSAProprietary");
                hashtable2.put(jVarArr[13], "SMIMESigningDesc");
                hashtable2.put(jVarArr[14], "ExtensionRequest");
                hashtable2.put(jVarArr[15], "SMIMECapability");
                hashtable2.put(jVarArr[16], "SigningCertificate");
                hashtable2.put(jVarArr[17], "SignatureTimestampToken");
                p = new Byte[][]{null, new Byte[]{new Byte((byte) 22)}, new Byte[]{new Byte((byte) 22)}, new Byte[]{new Byte((byte) 6)}, new Byte[]{new Byte((byte) 4)}, new Byte[]{new Byte((byte) 23)}, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 19), new Byte((byte) 20)}, new Byte[]{new Byte((byte) 19), new Byte((byte) 20)}, new Byte[]{new Byte((byte) ReplyCode.reply0x31)}, new Byte[]{new Byte((byte) 48)}, null, null, null, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 48)}};
                Class[] clsArr = new Class[18];
                q = clsArr;
                try {
                    Class<?> cls = Class.forName("[Ljava.lang.String;");
                    clsArr[0] = null;
                    clsArr[1] = cls;
                    clsArr[2] = cls;
                    clsArr[3] = Class.forName("m.a.b.j");
                    clsArr[4] = Class.forName("[B");
                    clsArr[5] = Class.forName("java.util.Date");
                    clsArr[6] = Class.forName("[Lsun.security.pkcs.SignerInfo;");
                    clsArr[7] = Class.forName("java.lang.String");
                    clsArr[8] = cls;
                    clsArr[9] = null;
                    clsArr[10] = null;
                    clsArr[11] = null;
                    clsArr[12] = null;
                    clsArr[13] = null;
                    clsArr[14] = Class.forName("m.a.c.n");
                    clsArr[15] = null;
                    clsArr[16] = null;
                    clsArr[17] = Class.forName("[B");
                    r = new boolean[]{false, false, false, true, true, true, false, true, false, false, true, false, false, false, true, true, true, true};
                    return;
                } catch (ClassNotFoundException e2) {
                    throw new ExceptionInInitializerError(e2.toString());
                }
            }
        }
    }

    public c(i iVar) throws IOException {
        m.a.b.g gVar = new m.a.b.g(iVar.D());
        i[] m2 = gVar.m(2);
        if (gVar.a() == 0) {
            if (m2.length == 2) {
                int i2 = 0;
                j p2 = m2[0].p();
                int d = d(p2, f20484j, 1);
                this.f20489g = d;
                if (d == -1) {
                    m.a.b.c cVar = f20483i;
                    if (cVar != null) {
                        cVar.f("ignoring unsupported signer attribute: " + p2);
                    }
                    throw new e("Unsupported PKCS9 attribute: " + p2);
                }
                i[] n2 = new m.a.b.g(m2[1].D()).n(1);
                if (r[this.f20489g] && n2.length > 1) {
                    e();
                    throw null;
                }
                for (i iVar2 : n2) {
                    Byte b = new Byte(iVar2.a);
                    if (d(b, p[this.f20489g], 0) == -1) {
                        f(b);
                        throw null;
                    }
                }
                switch (this.f20489g) {
                    case 1:
                    case 2:
                    case 8:
                        String[] strArr = new String[n2.length];
                        while (i2 < n2.length) {
                            strArr[i2] = n2[i2].f();
                            i2++;
                        }
                        this.f20490h = strArr;
                        return;
                    case 3:
                        this.f20490h = n2[0].p();
                        return;
                    case 4:
                        this.f20490h = n2[0].q();
                        return;
                    case 5:
                        this.f20490h = new m.a.b.g(n2[0].D()).p();
                        return;
                    case 6:
                        f[] fVarArr = new f[n2.length];
                        while (i2 < n2.length) {
                            fVarArr[i2] = new f(n2[i2].E());
                            i2++;
                        }
                        this.f20490h = fVarArr;
                        return;
                    case 7:
                        this.f20490h = n2[0].f();
                        return;
                    case 9:
                        throw new IOException("PKCS9 extended-certificate attribute not supported.");
                    case 10:
                        throw new IOException("PKCS9 IssuerAndSerialNumberattribute not supported.");
                    case 11:
                    case 12:
                        throw new IOException("PKCS9 RSA DSI attributes11 and 12, not supported.");
                    case 13:
                        throw new IOException("PKCS9 attribute #13 not supported.");
                    case 14:
                        this.f20490h = new n(new m.a.b.g(n2[0].D()));
                        return;
                    case 15:
                        throw new IOException("PKCS9 SMIMECapability attribute not supported.");
                    case 16:
                        this.f20490h = new g(n2[0].D());
                        return;
                    case 17:
                        this.f20490h = n2[0].D();
                        return;
                    default:
                        return;
                }
            }
            throw new IOException("PKCS9Attribute doesn't have two components");
        }
        throw new IOException("Excess data parsing PKCS9Attribute");
    }

    static int d(Object obj, Object[] objArr, int i2) {
        while (i2 < objArr.length) {
            if (obj.equals(objArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private void e() throws IOException {
        throw new IOException("Single-value attribute " + b() + " (" + a() + ") has multiple values.");
    }

    private void f(Byte b) throws IOException {
        Byte[] bArr = p[this.f20489g];
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("Value of attribute ");
        stringBuffer.append(b().toString());
        stringBuffer.append(" (");
        stringBuffer.append(a());
        stringBuffer.append(") has wrong tag: ");
        stringBuffer.append(b.toString());
        stringBuffer.append(".  Expected tags: ");
        stringBuffer.append(bArr[0].toString());
        for (int i2 = 1; i2 < bArr.length; i2++) {
            stringBuffer.append(", ");
            stringBuffer.append(bArr[i2].toString());
        }
        stringBuffer.append(OrderISVUtil.MONEY_DECIMAL);
        throw new IOException(stringBuffer.toString());
    }

    public String a() {
        return o.get(f20484j[this.f20489g]);
    }

    public j b() {
        return f20484j[this.f20489g];
    }

    public Object c() {
        return this.f20490h;
    }

    @Override // m.a.b.d
    public void derEncode(OutputStream outputStream) throws IOException {
        h hVar = new h();
        hVar.p(b());
        int i2 = 0;
        switch (this.f20489g) {
            case 1:
            case 2:
                String[] strArr = (String[]) this.f20490h;
                h[] hVarArr = new h[strArr.length];
                while (i2 < strArr.length) {
                    hVarArr[i2] = new h();
                    hVarArr[i2].j(strArr[i2]);
                    i2++;
                }
                hVar.s(ReplyCode.reply0x31, hVarArr);
                break;
            case 3:
                h hVar2 = new h();
                hVar2.p((j) this.f20490h);
                hVar.z(ReplyCode.reply0x31, hVar2.toByteArray());
                break;
            case 4:
                h hVar3 = new h();
                hVar3.q((byte[]) this.f20490h);
                hVar.z(ReplyCode.reply0x31, hVar3.toByteArray());
                break;
            case 5:
                h hVar4 = new h();
                hVar4.w((Date) this.f20490h);
                hVar.z(ReplyCode.reply0x31, hVar4.toByteArray());
                break;
            case 6:
                hVar.s(ReplyCode.reply0x31, (m.a.b.d[]) this.f20490h);
                break;
            case 7:
                h hVar5 = new h();
                hVar5.t((String) this.f20490h);
                hVar.z(ReplyCode.reply0x31, hVar5.toByteArray());
                break;
            case 8:
                String[] strArr2 = (String[]) this.f20490h;
                h[] hVarArr2 = new h[strArr2.length];
                while (i2 < strArr2.length) {
                    hVarArr2[i2] = new h();
                    hVarArr2[i2].t(strArr2[i2]);
                    i2++;
                }
                hVar.s(ReplyCode.reply0x31, hVarArr2);
                break;
            case 9:
                throw new IOException("PKCS9 extended-certificate attribute not supported.");
            case 10:
                throw new IOException("PKCS9 IssuerAndSerialNumberattribute not supported.");
            case 11:
            case 12:
                throw new IOException("PKCS9 RSA DSI attributes11 and 12, not supported.");
            case 13:
                throw new IOException("PKCS9 attribute #13 not supported.");
            case 14:
                h hVar6 = new h();
                try {
                    ((n) this.f20490h).c(hVar6, true);
                    hVar.z(ReplyCode.reply0x31, hVar6.toByteArray());
                    break;
                } catch (CertificateException e2) {
                    throw new IOException(e2.toString());
                }
            case 15:
                throw new IOException("PKCS9 attribute #15 not supported.");
            case 16:
                throw new IOException("PKCS9 SigningCertificate attribute not supported.");
            case 17:
                hVar.z(ReplyCode.reply0x31, (byte[]) this.f20490h);
                break;
        }
        h hVar7 = new h();
        hVar7.z((byte) 48, hVar.toByteArray());
        outputStream.write(hVar7.toByteArray());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("[");
        stringBuffer.append(o.get(f20484j[this.f20489g]));
        stringBuffer.append(": ");
        if (r[this.f20489g]) {
            Object obj = this.f20490h;
            if (obj instanceof byte[]) {
                stringBuffer.append(new HexDumpEncoder().encodeBuffer((byte[]) this.f20490h));
            } else {
                stringBuffer.append(obj.toString());
            }
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
        boolean z = true;
        for (Object obj2 : (Object[]) this.f20490h) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(", ");
            }
            stringBuffer.append(obj2.toString());
        }
        return stringBuffer.toString();
    }
}
