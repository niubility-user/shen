package sun.security.pkcs;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Vector;
import jd.wjlogin_sdk.util.ReplyCode;
import m.a.b.h;
import m.a.b.i;
import m.a.b.j;
import m.a.c.f1;
import m.a.c.i1;
import m.a.c.j1;
import m.a.c.k1;

/* loaded from: classes11.dex */
public class PKCS7 {
    private Principal[] certIssuerNames;
    private X509Certificate[] certificates;
    private a contentInfo;
    private j contentType;
    private X509CRL[] crls;
    private m.a.c.e[] digestAlgorithmIds;
    private boolean oldStyle;
    private f[] signerInfos;
    private BigInteger version;

    public PKCS7(InputStream inputStream) throws e, IOException {
        this.version = null;
        this.digestAlgorithmIds = null;
        this.contentInfo = null;
        this.certificates = null;
        this.crls = null;
        this.signerInfos = null;
        this.oldStyle = false;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bArr = new byte[dataInputStream.available()];
        dataInputStream.readFully(bArr);
        parse(new m.a.b.g(bArr));
    }

    private void parse(m.a.b.g gVar) throws e {
        try {
            try {
                gVar.s(gVar.a());
                parse(gVar, false);
            } catch (IOException unused) {
                gVar.v();
                parse(gVar, true);
                this.oldStyle = true;
            }
        } catch (IOException e2) {
            e eVar = new e(e2.getMessage());
            eVar.initCause(e2);
            throw eVar;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseNetscapeCertChain(i iVar) throws e, IOException {
        CertificateFactory certificateFactory;
        i[] m2 = new m.a.b.g(iVar.D()).m(2);
        this.certificates = new X509Certificate[m2.length];
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException unused) {
            certificateFactory = null;
        }
        for (int i2 = 0; i2 < m2.length; i2++) {
            if (certificateFactory == null) {
                try {
                    try {
                        this.certificates[i2] = new j1(m2[i2]);
                    } catch (IOException e2) {
                        e = e2;
                        e eVar = new e(e.getMessage());
                        eVar.initCause(e);
                        throw eVar;
                    } catch (CertificateException e3) {
                        e = e3;
                        e eVar2 = new e(e.getMessage());
                        eVar2.initCause(e);
                        throw eVar2;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th;
                }
            } else {
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(m2[i2].D());
                try {
                    this.certificates[i2] = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream2);
                    byteArrayInputStream2.close();
                } catch (IOException e4) {
                    e = e4;
                    e eVar3 = new e(e.getMessage());
                    eVar3.initCause(e);
                    throw eVar3;
                } catch (CertificateException e5) {
                    e = e5;
                    e eVar22 = new e(e.getMessage());
                    eVar22.initCause(e);
                    throw eVar22;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayInputStream = byteArrayInputStream2;
                    if (byteArrayInputStream != null) {
                    }
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseOldSignedData(i iVar) throws e, IOException {
        CertificateFactory certificateFactory;
        m.a.b.g E = iVar.E();
        this.version = E.b();
        i[] n2 = E.n(1);
        int length = n2.length;
        this.digestAlgorithmIds = new m.a.c.e[length];
        for (int i2 = 0; i2 < length; i2++) {
            try {
                this.digestAlgorithmIds[i2] = m.a.c.e.parse(n2[i2]);
            } catch (IOException unused) {
                throw new e("Error parsing digest AlgorithmId IDs");
            }
        }
        this.contentInfo = new a(E, true);
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException unused2) {
            certificateFactory = null;
        }
        i[] n3 = E.n(2);
        int length2 = n3.length;
        this.certificates = new X509Certificate[length2];
        for (int i3 = 0; i3 < length2; i3++) {
            if (certificateFactory == null) {
                try {
                    try {
                        this.certificates[i3] = new j1(n3[i3]);
                    } catch (IOException e2) {
                        e = e2;
                        e eVar = new e(e.getMessage());
                        eVar.initCause(e);
                        throw eVar;
                    } catch (CertificateException e3) {
                        e = e3;
                        e eVar2 = new e(e.getMessage());
                        eVar2.initCause(e);
                        throw eVar2;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th;
                }
            } else {
                ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(n3[i3].D());
                try {
                    this.certificates[i3] = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream2);
                    byteArrayInputStream2.close();
                } catch (IOException e4) {
                    e = e4;
                    e eVar3 = new e(e.getMessage());
                    eVar3.initCause(e);
                    throw eVar3;
                } catch (CertificateException e5) {
                    e = e5;
                    e eVar22 = new e(e.getMessage());
                    eVar22.initCause(e);
                    throw eVar22;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayInputStream = byteArrayInputStream2;
                    if (byteArrayInputStream != null) {
                    }
                    throw th;
                }
            }
        }
        E.n(0);
        i[] n4 = E.n(1);
        int length3 = n4.length;
        this.signerInfos = new f[length3];
        for (int i4 = 0; i4 < length3; i4++) {
            this.signerInfos[i4] = new f(n4[i4].E(), true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseSignedData(i iVar) throws e, IOException {
        CertificateFactory certificateFactory;
        Throwable th;
        m.a.b.g E = iVar.E();
        this.version = E.b();
        i[] n2 = E.n(1);
        int length = n2.length;
        this.digestAlgorithmIds = new m.a.c.e[length];
        for (int i2 = 0; i2 < length; i2++) {
            try {
                this.digestAlgorithmIds[i2] = m.a.c.e.parse(n2[i2]);
            } catch (IOException e2) {
                e eVar = new e("Error parsing digest AlgorithmId IDs: " + e2.getMessage());
                eVar.initCause(e2);
                throw eVar;
            }
        }
        this.contentInfo = new a(E);
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException unused) {
            certificateFactory = null;
        }
        if (((byte) E.t()) == -96) {
            i[] o = E.o(2, true);
            int length2 = o.length;
            this.certificates = new X509Certificate[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                if (certificateFactory == null) {
                    try {
                        try {
                            this.certificates[i3] = new j1(o[i3]);
                        } catch (IOException e3) {
                            e = e3;
                            e eVar2 = new e(e.getMessage());
                            eVar2.initCause(e);
                            throw eVar2;
                        } catch (CertificateException e4) {
                            e = e4;
                            e eVar3 = new e(e.getMessage());
                            eVar3.initCause(e);
                            throw eVar3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        throw th;
                    }
                } else {
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(o[i3].D());
                    try {
                        this.certificates[i3] = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream2);
                        byteArrayInputStream2.close();
                    } catch (IOException e5) {
                        e = e5;
                        e eVar22 = new e(e.getMessage());
                        eVar22.initCause(e);
                        throw eVar22;
                    } catch (CertificateException e6) {
                        e = e6;
                        e eVar32 = new e(e.getMessage());
                        eVar32.initCause(e);
                        throw eVar32;
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayInputStream = byteArrayInputStream2;
                        if (byteArrayInputStream != null) {
                        }
                        throw th;
                    }
                }
            }
        }
        if (((byte) E.t()) == -95) {
            i[] o2 = E.o(1, true);
            int length3 = o2.length;
            this.crls = new X509CRL[length3];
            for (int i4 = 0; i4 < length3; i4++) {
                if (certificateFactory == null) {
                    try {
                        try {
                            this.crls[i4] = new i1(o2[i4]);
                        } catch (Throwable th4) {
                            th = th4;
                            if (byteArrayInputStream != null) {
                                byteArrayInputStream.close();
                            }
                            throw th;
                        }
                    } catch (CRLException e7) {
                        e = e7;
                        e eVar4 = new e(e.getMessage());
                        eVar4.initCause(e);
                        throw eVar4;
                    }
                } else {
                    ByteArrayInputStream byteArrayInputStream3 = new ByteArrayInputStream(o2[i4].D());
                    try {
                        this.crls[i4] = (X509CRL) certificateFactory.generateCRL(byteArrayInputStream3);
                        byteArrayInputStream3.close();
                    } catch (CRLException e8) {
                        e = e8;
                        byteArrayInputStream = byteArrayInputStream3;
                        e eVar42 = new e(e.getMessage());
                        eVar42.initCause(e);
                        throw eVar42;
                    } catch (Throwable th5) {
                        th = th5;
                        byteArrayInputStream = byteArrayInputStream3;
                        if (byteArrayInputStream != null) {
                        }
                        throw th;
                    }
                }
            }
        }
        i[] n3 = E.n(1);
        int length4 = n3.length;
        this.signerInfos = new f[length4];
        for (int i5 = 0; i5 < length4; i5++) {
            this.signerInfos[i5] = new f(n3[i5].E());
        }
    }

    private void populateCertIssuerNames() {
        X509Certificate[] x509CertificateArr = this.certificates;
        if (x509CertificateArr == null) {
            return;
        }
        this.certIssuerNames = new Principal[x509CertificateArr.length];
        int i2 = 0;
        while (true) {
            X509Certificate[] x509CertificateArr2 = this.certificates;
            if (i2 >= x509CertificateArr2.length) {
                return;
            }
            X509Certificate x509Certificate = x509CertificateArr2[i2];
            Principal issuerDN = x509Certificate.getIssuerDN();
            if (!(issuerDN instanceof f1)) {
                try {
                    issuerDN = (Principal) new k1(x509Certificate.getTBSCertificate()).f("issuer.dname");
                } catch (Exception unused) {
                }
            }
            this.certIssuerNames[i2] = issuerDN;
            i2++;
        }
    }

    public void encodeSignedData(OutputStream outputStream) throws IOException {
        h hVar = new h();
        encodeSignedData(hVar);
        outputStream.write(hVar.toByteArray());
    }

    public X509CRL[] getCRLs() {
        X509CRL[] x509crlArr = this.crls;
        if (x509crlArr != null) {
            return (X509CRL[]) x509crlArr.clone();
        }
        return null;
    }

    public X509Certificate getCertificate(BigInteger bigInteger, f1 f1Var) {
        if (this.certificates == null) {
            return null;
        }
        if (this.certIssuerNames == null) {
            populateCertIssuerNames();
        }
        int i2 = 0;
        while (true) {
            X509Certificate[] x509CertificateArr = this.certificates;
            if (i2 >= x509CertificateArr.length) {
                return null;
            }
            X509Certificate x509Certificate = x509CertificateArr[i2];
            if (bigInteger.equals(x509Certificate.getSerialNumber()) && f1Var.equals(this.certIssuerNames[i2])) {
                return x509Certificate;
            }
            i2++;
        }
    }

    public X509Certificate[] getCertificates() {
        X509Certificate[] x509CertificateArr = this.certificates;
        if (x509CertificateArr != null) {
            return (X509Certificate[]) x509CertificateArr.clone();
        }
        return null;
    }

    public a getContentInfo() {
        return this.contentInfo;
    }

    public m.a.c.e[] getDigestAlgorithmIds() {
        return this.digestAlgorithmIds;
    }

    public f[] getSignerInfos() {
        return this.signerInfos;
    }

    public BigInteger getVersion() {
        return this.version;
    }

    public boolean isOldStyle() {
        return this.oldStyle;
    }

    public String toString() {
        String str = "" + this.contentInfo + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        if (this.version != null) {
            str = str + "PKCS7 :: version: " + m.a.b.c.g(this.version) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        }
        if (this.digestAlgorithmIds != null) {
            str = str + "PKCS7 :: digest AlgorithmIds: \n";
            for (int i2 = 0; i2 < this.digestAlgorithmIds.length; i2++) {
                str = str + "\t" + this.digestAlgorithmIds[i2] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        if (this.certificates != null) {
            str = str + "PKCS7 :: certificates: \n";
            for (int i3 = 0; i3 < this.certificates.length; i3++) {
                str = str + "\t" + i3 + ".   " + this.certificates[i3] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        if (this.crls != null) {
            str = str + "PKCS7 :: crls: \n";
            for (int i4 = 0; i4 < this.crls.length; i4++) {
                str = str + "\t" + i4 + ".   " + this.crls[i4] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        if (this.signerInfos != null) {
            str = str + "PKCS7 :: signer infos: \n";
            for (int i5 = 0; i5 < this.signerInfos.length; i5++) {
                str = str + "\t" + i5 + ".  " + this.signerInfos[i5] + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        return str;
    }

    public f verify(f fVar, byte[] bArr) throws NoSuchAlgorithmException, SignatureException {
        return fVar.d(this, bArr);
    }

    public f[] verify(byte[] bArr) throws NoSuchAlgorithmException, SignatureException {
        Vector vector = new Vector();
        int i2 = 0;
        while (true) {
            f[] fVarArr = this.signerInfos;
            if (i2 >= fVarArr.length) {
                break;
            }
            f verify2 = verify(fVarArr[i2], bArr);
            if (verify2 != null) {
                vector.addElement(verify2);
            }
            i2++;
        }
        if (vector.size() != 0) {
            f[] fVarArr2 = new f[vector.size()];
            vector.copyInto(fVarArr2);
            return fVarArr2;
        }
        return null;
    }

    public void encodeSignedData(h hVar) throws IOException {
        h hVar2 = new h();
        hVar2.l(this.version);
        hVar2.s(ReplyCode.reply0x31, this.digestAlgorithmIds);
        this.contentInfo.a(hVar2);
        X509Certificate[] x509CertificateArr = this.certificates;
        if (x509CertificateArr != null && x509CertificateArr.length != 0) {
            j1[] j1VarArr = new j1[x509CertificateArr.length];
            int i2 = 0;
            while (true) {
                X509Certificate[] x509CertificateArr2 = this.certificates;
                if (i2 >= x509CertificateArr2.length) {
                    break;
                }
                if (x509CertificateArr2[i2] instanceof j1) {
                    j1VarArr[i2] = (j1) x509CertificateArr2[i2];
                } else {
                    try {
                        j1VarArr[i2] = new j1(x509CertificateArr2[i2].getEncoded());
                    } catch (CertificateException e2) {
                        IOException iOException = new IOException(e2.getMessage());
                        iOException.initCause(e2);
                        throw iOException;
                    }
                }
                i2++;
            }
            hVar2.s(ReplyCode.reply0xa0, j1VarArr);
        }
        hVar2.s(ReplyCode.reply0x31, this.signerInfos);
        new a(a.f20481n, new i((byte) 48, hVar2.toByteArray())).a(hVar);
    }

    private void parse(m.a.b.g gVar, boolean z) throws IOException {
        a aVar = new a(gVar, z);
        this.contentInfo = aVar;
        this.contentType = aVar.a;
        i b = aVar.b();
        if (this.contentType.equals(a.f20481n)) {
            parseSignedData(b);
        } else if (this.contentType.equals(a.o)) {
            parseOldSignedData(b);
        } else if (this.contentType.equals(a.p)) {
            parseNetscapeCertChain(b);
        } else {
            throw new e("content type " + this.contentType + " not supported.");
        }
    }

    public f[] verify() throws NoSuchAlgorithmException, SignatureException {
        return verify(null);
    }

    public PKCS7(m.a.b.g gVar) throws e {
        this.version = null;
        this.digestAlgorithmIds = null;
        this.contentInfo = null;
        this.certificates = null;
        this.crls = null;
        this.signerInfos = null;
        this.oldStyle = false;
        parse(gVar);
    }

    public PKCS7(byte[] bArr) throws e {
        this.version = null;
        this.digestAlgorithmIds = null;
        this.contentInfo = null;
        this.certificates = null;
        this.crls = null;
        this.signerInfos = null;
        this.oldStyle = false;
        try {
            parse(new m.a.b.g(bArr));
        } catch (IOException e2) {
            e eVar = new e("Unable to parse the encoded bytes");
            eVar.initCause(e2);
            throw eVar;
        }
    }

    public PKCS7(m.a.c.e[] eVarArr, a aVar, X509Certificate[] x509CertificateArr, f[] fVarArr) {
        this.version = null;
        this.digestAlgorithmIds = null;
        this.contentInfo = null;
        this.certificates = null;
        this.crls = null;
        this.signerInfos = null;
        this.oldStyle = false;
        this.version = BigInteger.ONE;
        this.digestAlgorithmIds = eVarArr;
        this.contentInfo = aVar;
        this.certificates = x509CertificateArr;
        this.signerInfos = fVarArr;
    }
}
