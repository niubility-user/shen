package sun.security.pkcs;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import jd.wjlogin_sdk.util.ReplyCode;
import m.a.b.h;
import m.a.b.i;
import m.a.b.j;
import m.a.c.f1;
import m.a.c.n0;
import sun.misc.HexDumpEncoder;

/* loaded from: classes11.dex */
public class f implements m.a.b.d {

    /* renamed from: g */
    BigInteger f20492g;

    /* renamed from: h */
    f1 f20493h;

    /* renamed from: i */
    BigInteger f20494i;

    /* renamed from: j */
    m.a.c.e f20495j;

    /* renamed from: k */
    m.a.c.e f20496k;

    /* renamed from: l */
    byte[] f20497l;

    /* renamed from: m */
    d f20498m;

    /* renamed from: n */
    d f20499n;

    public f(m.a.b.g gVar) throws IOException, e {
        this(gVar, false);
    }

    public X509Certificate a(PKCS7 pkcs7) throws IOException {
        return pkcs7.getCertificate(this.f20494i, this.f20493h);
    }

    public m.a.c.e b() {
        return this.f20495j;
    }

    public m.a.c.e c() {
        return this.f20496k;
    }

    public f d(PKCS7 pkcs7, byte[] bArr) throws NoSuchAlgorithmException, SignatureException {
        byte[] bArr2;
        try {
            try {
                a contentInfo = pkcs7.getContentInfo();
                if (bArr == null) {
                    bArr = contentInfo.c();
                }
                String name = b().getName();
                if (name.equalsIgnoreCase("SHA")) {
                    name = "SHA1";
                }
                d dVar = this.f20498m;
                if (dVar != null) {
                    j jVar = (j) dVar.f(c.f20486l);
                    if (jVar == null || !jVar.equals(contentInfo.a) || (bArr2 = (byte[]) this.f20498m.f(c.f20487m)) == null) {
                        return null;
                    }
                    byte[] digest = MessageDigest.getInstance(name).digest(bArr);
                    if (bArr2.length != digest.length) {
                        return null;
                    }
                    for (int i2 = 0; i2 < bArr2.length; i2++) {
                        if (bArr2[i2] != digest[i2]) {
                            return null;
                        }
                    }
                    bArr = this.f20498m.g();
                }
                String name2 = c().getName();
                if (name2.equalsIgnoreCase("SHA1withDSA")) {
                    name2 = "DSA";
                }
                Signature signature = Signature.getInstance(name + "with" + name2);
                X509Certificate a = a(pkcs7);
                if (a == null) {
                    return null;
                }
                if (!a.hasUnsupportedCriticalExtension()) {
                    boolean[] keyUsage = a.getKeyUsage();
                    if (keyUsage != null) {
                        try {
                            n0 n0Var = new n0(keyUsage);
                            boolean booleanValue = ((Boolean) n0Var.g("digital_signature")).booleanValue();
                            boolean booleanValue2 = ((Boolean) n0Var.g("non_repudiation")).booleanValue();
                            if (!booleanValue && !booleanValue2) {
                                throw new SignatureException("Key usage restricted: cannot be used for digital signatures");
                            }
                        } catch (IOException unused) {
                            throw new SignatureException("Failed to parse keyUsage extension");
                        }
                    }
                    signature.initVerify(a.getPublicKey());
                    signature.update(bArr);
                    if (signature.verify(this.f20497l)) {
                        return this;
                    }
                    return null;
                }
                throw new SignatureException("Certificate has unsupported critical extension(s)");
            } catch (IOException e2) {
                throw new SignatureException("IO error verifying signature:\n" + e2.getMessage());
            }
        } catch (InvalidKeyException e3) {
            throw new SignatureException("InvalidKey: " + e3.getMessage());
        }
    }

    @Override // m.a.b.d
    public void derEncode(OutputStream outputStream) throws IOException {
        h hVar = new h();
        hVar.l(this.f20492g);
        h hVar2 = new h();
        this.f20493h.b(hVar2);
        hVar2.l(this.f20494i);
        hVar.y((byte) 48, hVar2);
        this.f20495j.encode(hVar);
        d dVar = this.f20498m;
        if (dVar != null) {
            dVar.c(ReplyCode.reply0xa0, hVar);
        }
        this.f20496k.encode(hVar);
        hVar.q(this.f20497l);
        d dVar2 = this.f20499n;
        if (dVar2 != null) {
            dVar2.c((byte) -95, hVar);
        }
        h hVar3 = new h();
        hVar3.y((byte) 48, hVar);
        outputStream.write(hVar3.toByteArray());
    }

    public String toString() {
        HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
        String str = ((("Signer Info for (issuer): " + this.f20493h + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + "\tversion: " + m.a.b.c.g(this.f20492g) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + "\tcertificateSerialNumber: " + m.a.b.c.g(this.f20494i) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + "\tdigestAlgorithmId: " + this.f20495j + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        if (this.f20498m != null) {
            str = str + "\tauthenticatedAttributes: " + this.f20498m + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        }
        String str2 = (str + "\tdigestEncryptionAlgorithmId: " + this.f20496k + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + "\tencryptedDigest: \n" + hexDumpEncoder.encodeBuffer(this.f20497l) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        if (this.f20499n != null) {
            return str2 + "\tunauthenticatedAttributes: " + this.f20499n + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        }
        return str2;
    }

    public f(m.a.b.g gVar, boolean z) throws IOException, e {
        this.f20492g = gVar.b();
        i[] m2 = gVar.m(2);
        this.f20493h = new f1(new i((byte) 48, m2[0].D()));
        this.f20494i = m2[1].h();
        this.f20495j = m.a.c.e.parse(gVar.e());
        if (z) {
            gVar.n(0);
        } else if (((byte) gVar.t()) == -96) {
            this.f20498m = new d(gVar);
        }
        this.f20496k = m.a.c.e.parse(gVar.e());
        this.f20497l = gVar.l();
        if (z) {
            gVar.n(0);
        } else if (gVar.a() != 0 && ((byte) gVar.t()) == -95) {
            this.f20499n = new d(gVar, true);
        }
        if (gVar.a() != 0) {
            throw new e("extra data at the end");
        }
    }
}
