package sun.security.pkcs;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Vector;
import jd.wjlogin_sdk.util.ReplyCode;
import m.a.b.h;
import m.a.b.i;
import m.a.b.j;
import m.a.c.f1;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseNetscapeCertChain(m.a.b.i r7) throws sun.security.pkcs.e, java.io.IOException {
        /*
            r6 = this;
            m.a.b.g r0 = new m.a.b.g
            byte[] r7 = r7.D()
            r0.<init>(r7)
            r7 = 2
            m.a.b.i[] r7 = r0.m(r7)
            int r0 = r7.length
            java.security.cert.X509Certificate[] r0 = new java.security.cert.X509Certificate[r0]
            r6.certificates = r0
            r0 = 0
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch: java.security.cert.CertificateException -> L1b
            goto L1c
        L1b:
            r1 = r0
        L1c:
            r2 = 0
        L1d:
            int r3 = r7.length
            if (r2 >= r3) goto L76
            if (r1 != 0) goto L2e
            java.security.cert.X509Certificate[] r3 = r6.certificates     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            m.a.c.j1 r4 = new m.a.c.j1     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            r5 = r7[r2]     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            r3[r2] = r4     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            goto L46
        L2e:
            r3 = r7[r2]     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            byte[] r3 = r3.D()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.security.cert.CertificateException -> L62
            java.security.cert.X509Certificate[] r3 = r6.certificates     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c java.security.cert.CertificateException -> L4f
            java.security.cert.Certificate r5 = r1.generateCertificate(r4)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c java.security.cert.CertificateException -> L4f
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c java.security.cert.CertificateException -> L4f
            r3[r2] = r5     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c java.security.cert.CertificateException -> L4f
            r4.close()     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c java.security.cert.CertificateException -> L4f
        L46:
            int r2 = r2 + 1
            goto L1d
        L49:
            r7 = move-exception
            r0 = r4
            goto L70
        L4c:
            r7 = move-exception
            r0 = r4
            goto L55
        L4f:
            r7 = move-exception
            r0 = r4
            goto L63
        L52:
            r7 = move-exception
            goto L70
        L54:
            r7 = move-exception
        L55:
            sun.security.pkcs.e r1 = new sun.security.pkcs.e     // Catch: java.lang.Throwable -> L52
            java.lang.String r2 = r7.getMessage()     // Catch: java.lang.Throwable -> L52
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L52
            r1.initCause(r7)     // Catch: java.lang.Throwable -> L52
            throw r1     // Catch: java.lang.Throwable -> L52
        L62:
            r7 = move-exception
        L63:
            sun.security.pkcs.e r1 = new sun.security.pkcs.e     // Catch: java.lang.Throwable -> L52
            java.lang.String r2 = r7.getMessage()     // Catch: java.lang.Throwable -> L52
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L52
            r1.initCause(r7)     // Catch: java.lang.Throwable -> L52
            throw r1     // Catch: java.lang.Throwable -> L52
        L70:
            if (r0 == 0) goto L75
            r0.close()
        L75:
            throw r7
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.PKCS7.parseNetscapeCertChain(m.a.b.i):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseOldSignedData(m.a.b.i r11) throws sun.security.pkcs.e, java.io.IOException {
        /*
            r10 = this;
            m.a.b.g r11 = r11.E()
            java.math.BigInteger r0 = r11.b()
            r10.version = r0
            r0 = 1
            m.a.b.i[] r1 = r11.n(r0)
            int r2 = r1.length
            m.a.c.e[] r3 = new m.a.c.e[r2]
            r10.digestAlgorithmIds = r3
            r3 = 0
            r4 = 0
        L16:
            if (r4 >= r2) goto L2d
            r5 = r1[r4]     // Catch: java.io.IOException -> L25
            m.a.c.e[] r6 = r10.digestAlgorithmIds     // Catch: java.io.IOException -> L25
            m.a.c.e r5 = m.a.c.e.parse(r5)     // Catch: java.io.IOException -> L25
            r6[r4] = r5     // Catch: java.io.IOException -> L25
            int r4 = r4 + 1
            goto L16
        L25:
            sun.security.pkcs.e r11 = new sun.security.pkcs.e
            java.lang.String r0 = "Error parsing digest AlgorithmId IDs"
            r11.<init>(r0)
            throw r11
        L2d:
            sun.security.pkcs.a r1 = new sun.security.pkcs.a
            r1.<init>(r11, r0)
            r10.contentInfo = r1
            r1 = 0
            java.lang.String r2 = "X.509"
            java.security.cert.CertificateFactory r2 = java.security.cert.CertificateFactory.getInstance(r2)     // Catch: java.security.cert.CertificateException -> L3c
            goto L3d
        L3c:
            r2 = r1
        L3d:
            r4 = 2
            m.a.b.i[] r4 = r11.n(r4)
            int r5 = r4.length
            java.security.cert.X509Certificate[] r6 = new java.security.cert.X509Certificate[r5]
            r10.certificates = r6
            r6 = 0
        L48:
            if (r6 >= r5) goto La0
            if (r2 != 0) goto L58
            java.security.cert.X509Certificate[] r7 = r10.certificates     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            m.a.c.j1 r8 = new m.a.c.j1     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            r9 = r4[r6]     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            r7[r6] = r8     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            goto L70
        L58:
            r7 = r4[r6]     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            byte[] r7 = r7.D()     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e java.security.cert.CertificateException -> L8c
            java.security.cert.X509Certificate[] r7 = r10.certificates     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 java.security.cert.CertificateException -> L79
            java.security.cert.Certificate r9 = r2.generateCertificate(r8)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 java.security.cert.CertificateException -> L79
            java.security.cert.X509Certificate r9 = (java.security.cert.X509Certificate) r9     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 java.security.cert.CertificateException -> L79
            r7[r6] = r9     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 java.security.cert.CertificateException -> L79
            r8.close()     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 java.security.cert.CertificateException -> L79
        L70:
            int r6 = r6 + 1
            goto L48
        L73:
            r11 = move-exception
            r1 = r8
            goto L9a
        L76:
            r11 = move-exception
            r1 = r8
            goto L7f
        L79:
            r11 = move-exception
            r1 = r8
            goto L8d
        L7c:
            r11 = move-exception
            goto L9a
        L7e:
            r11 = move-exception
        L7f:
            sun.security.pkcs.e r0 = new sun.security.pkcs.e     // Catch: java.lang.Throwable -> L7c
            java.lang.String r2 = r11.getMessage()     // Catch: java.lang.Throwable -> L7c
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L7c
            r0.initCause(r11)     // Catch: java.lang.Throwable -> L7c
            throw r0     // Catch: java.lang.Throwable -> L7c
        L8c:
            r11 = move-exception
        L8d:
            sun.security.pkcs.e r0 = new sun.security.pkcs.e     // Catch: java.lang.Throwable -> L7c
            java.lang.String r2 = r11.getMessage()     // Catch: java.lang.Throwable -> L7c
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L7c
            r0.initCause(r11)     // Catch: java.lang.Throwable -> L7c
            throw r0     // Catch: java.lang.Throwable -> L7c
        L9a:
            if (r1 == 0) goto L9f
            r1.close()
        L9f:
            throw r11
        La0:
            r11.n(r3)
            m.a.b.i[] r11 = r11.n(r0)
            int r1 = r11.length
            sun.security.pkcs.f[] r2 = new sun.security.pkcs.f[r1]
            r10.signerInfos = r2
        Lac:
            if (r3 >= r1) goto Lc0
            r2 = r11[r3]
            m.a.b.g r2 = r2.E()
            sun.security.pkcs.f[] r4 = r10.signerInfos
            sun.security.pkcs.f r5 = new sun.security.pkcs.f
            r5.<init>(r2, r0)
            r4[r3] = r5
            int r3 = r3 + 1
            goto Lac
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.PKCS7.parseOldSignedData(m.a.b.i):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseSignedData(m.a.b.i r11) throws sun.security.pkcs.e, java.io.IOException {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.pkcs.PKCS7.parseSignedData(m.a.b.i):void");
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
