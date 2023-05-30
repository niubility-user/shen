package cn.com.union.fido.util.p10;

import cn.com.union.fido.util.X509Principal;
import cn.com.union.fido.util.asn1.ASN1Encodable;
import cn.com.union.fido.util.asn1.ASN1InputStream;
import cn.com.union.fido.util.asn1.ASN1Object;
import cn.com.union.fido.util.asn1.ASN1Sequence;
import cn.com.union.fido.util.asn1.ASN1Set;
import cn.com.union.fido.util.asn1.DERBitString;
import cn.com.union.fido.util.asn1.DEREncodable;
import cn.com.union.fido.util.asn1.DERObjectIdentifier;
import cn.com.union.fido.util.asn1.pkcs.CertificationRequest;
import cn.com.union.fido.util.asn1.pkcs.CertificationRequestInfo;
import cn.com.union.fido.util.asn1.pkcs.PKCSObjectIdentifiers;
import cn.com.union.fido.util.asn1.util.Strings;
import cn.com.union.fido.util.asn1.x509.AlgorithmIdentifier;
import cn.com.union.fido.util.asn1.x509.SubjectPublicKeyInfo;
import cn.com.union.fido.util.asn1.x509.X509Name;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public class PKCS10CertificationRequest extends CertificationRequest {
    private static Hashtable algorithms = new Hashtable();
    private static Hashtable params = new Hashtable();
    private static Hashtable keyAlgorithms = new Hashtable();
    private static Hashtable oids = new Hashtable();
    private static Set noParams = new HashSet();

    static {
        algorithms.put("MD2WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD2WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD5WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("MD5WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("RSAWITHMD5", new DERObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("SHA1WITHRSAENCRYPTION", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA1WITHRSA", new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        Hashtable hashtable = algorithms;
        DERObjectIdentifier dERObjectIdentifier = PKCSObjectIdentifiers.sha224WithRSAEncryption;
        hashtable.put("SHA224WITHRSAENCRYPTION", dERObjectIdentifier);
        algorithms.put("SHA224WITHRSA", dERObjectIdentifier);
        Hashtable hashtable2 = algorithms;
        DERObjectIdentifier dERObjectIdentifier2 = PKCSObjectIdentifiers.sha256WithRSAEncryption;
        hashtable2.put("SHA256WITHRSAENCRYPTION", dERObjectIdentifier2);
        algorithms.put("SHA256WITHRSA", dERObjectIdentifier2);
        Hashtable hashtable3 = algorithms;
        DERObjectIdentifier dERObjectIdentifier3 = PKCSObjectIdentifiers.sha384WithRSAEncryption;
        hashtable3.put("SHA384WITHRSAENCRYPTION", dERObjectIdentifier3);
        algorithms.put("SHA384WITHRSA", dERObjectIdentifier3);
        Hashtable hashtable4 = algorithms;
        DERObjectIdentifier dERObjectIdentifier4 = PKCSObjectIdentifiers.sha512WithRSAEncryption;
        hashtable4.put("SHA512WITHRSAENCRYPTION", dERObjectIdentifier4);
        algorithms.put("SHA512WITHRSA", dERObjectIdentifier4);
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(dERObjectIdentifier, "SHA224WITHRSA");
        oids.put(dERObjectIdentifier2, "SHA256WITHRSA");
        oids.put(dERObjectIdentifier3, "SHA384WITHRSA");
        oids.put(dERObjectIdentifier4, "SHA512WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        oids.put(new DERObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        keyAlgorithms.put(PKCSObjectIdentifiers.rsaEncryption, RSAUtils.KEY_ALGORITHM);
    }

    public PKCS10CertificationRequest(ASN1Sequence aSN1Sequence) {
        super(aSN1Sequence);
    }

    public PKCS10CertificationRequest(String str, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set) {
        AlgorithmIdentifier algorithmIdentifier;
        String upperCase = Strings.toUpperCase(str);
        DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) algorithms.get(upperCase);
        if (dERObjectIdentifier == null) {
            throw new IllegalArgumentException("Unknown signature type requested");
        }
        if (x509Name == null) {
            throw new IllegalArgumentException("subject must not be null");
        }
        if (publicKey == null) {
            throw new IllegalArgumentException("public key must not be null");
        }
        try {
            if (noParams.contains(dERObjectIdentifier)) {
                algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier);
            } else if (params.containsKey(upperCase)) {
                this.sigAlgId = new AlgorithmIdentifier(dERObjectIdentifier, (DEREncodable) params.get(upperCase));
                this.reqInfo = new CertificationRequestInfo(x509Name, new SubjectPublicKeyInfo((ASN1Sequence) ASN1Object.fromByteArray(publicKey.getEncoded())), aSN1Set);
                return;
            } else {
                algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier, null);
            }
            this.reqInfo = new CertificationRequestInfo(x509Name, new SubjectPublicKeyInfo((ASN1Sequence) ASN1Object.fromByteArray(publicKey.getEncoded())), aSN1Set);
            return;
        } catch (IOException unused) {
            throw new IllegalArgumentException("can't encode public key");
        }
        this.sigAlgId = algorithmIdentifier;
    }

    public PKCS10CertificationRequest(String str, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey) {
        AlgorithmIdentifier algorithmIdentifier;
        String upperCase = Strings.toUpperCase(str);
        DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) algorithms.get(upperCase);
        if (dERObjectIdentifier == null) {
            throw new IllegalArgumentException("Unknown signature type requested");
        }
        if (x509Name == null) {
            throw new IllegalArgumentException("subject must not be null");
        }
        if (publicKey == null) {
            throw new IllegalArgumentException("public key must not be null");
        }
        try {
            try {
                if (noParams.contains(dERObjectIdentifier)) {
                    algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier);
                } else if (params.containsKey(upperCase)) {
                    this.sigAlgId = new AlgorithmIdentifier(dERObjectIdentifier, (DEREncodable) params.get(upperCase));
                    this.reqInfo = new CertificationRequestInfo(x509Name, new SubjectPublicKeyInfo((ASN1Sequence) ASN1Object.fromByteArray(publicKey.getEncoded())), aSN1Set);
                    Signature signature = Signature.getInstance(str);
                    signature.initSign(privateKey);
                    signature.update(this.reqInfo.getEncoded(ASN1Encodable.DER));
                    this.sigBits = new DERBitString(signature.sign());
                    return;
                } else {
                    algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier, null);
                }
                signature.update(this.reqInfo.getEncoded(ASN1Encodable.DER));
                this.sigBits = new DERBitString(signature.sign());
                return;
            } catch (Exception e2) {
                throw new IllegalArgumentException("exception encoding TBS cert request - ".concat(String.valueOf(e2)));
            }
            this.reqInfo = new CertificationRequestInfo(x509Name, new SubjectPublicKeyInfo((ASN1Sequence) ASN1Object.fromByteArray(publicKey.getEncoded())), aSN1Set);
            Signature signature2 = Signature.getInstance(str);
            signature2.initSign(privateKey);
        } catch (IOException unused) {
            throw new IllegalArgumentException("can't encode public key");
        }
        this.sigAlgId = algorithmIdentifier;
    }

    public PKCS10CertificationRequest(String str, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set, Signature signature) {
        AlgorithmIdentifier algorithmIdentifier;
        String upperCase = Strings.toUpperCase(str);
        DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) algorithms.get(upperCase);
        if (dERObjectIdentifier == null) {
            throw new IllegalArgumentException("Unknown signature type requested");
        }
        if (x509Name == null) {
            throw new IllegalArgumentException("subject must not be null");
        }
        if (publicKey == null) {
            throw new IllegalArgumentException("public key must not be null");
        }
        try {
            try {
                if (noParams.contains(dERObjectIdentifier)) {
                    algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier);
                } else if (params.containsKey(upperCase)) {
                    this.sigAlgId = new AlgorithmIdentifier(dERObjectIdentifier, (DEREncodable) params.get(upperCase));
                    CertificationRequestInfo certificationRequestInfo = new CertificationRequestInfo(x509Name, new SubjectPublicKeyInfo((ASN1Sequence) ASN1Object.fromByteArray(publicKey.getEncoded())), aSN1Set);
                    this.reqInfo = certificationRequestInfo;
                    signature.update(certificationRequestInfo.getEncoded(ASN1Encodable.DER));
                    this.sigBits = new DERBitString(signature.sign());
                    return;
                } else {
                    algorithmIdentifier = new AlgorithmIdentifier(dERObjectIdentifier, null);
                }
                signature.update(certificationRequestInfo.getEncoded(ASN1Encodable.DER));
                this.sigBits = new DERBitString(signature.sign());
                return;
            } catch (Exception e2) {
                throw new IllegalArgumentException("exception encoding TBS cert request - ".concat(String.valueOf(e2)));
            }
            CertificationRequestInfo certificationRequestInfo2 = new CertificationRequestInfo(x509Name, new SubjectPublicKeyInfo((ASN1Sequence) ASN1Object.fromByteArray(publicKey.getEncoded())), aSN1Set);
            this.reqInfo = certificationRequestInfo2;
        } catch (IOException unused) {
            throw new IllegalArgumentException("can't encode public key");
        }
        this.sigAlgId = algorithmIdentifier;
    }

    public PKCS10CertificationRequest(String str, X500Principal x500Principal, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey) {
        this(str, convertName(x500Principal), publicKey, aSN1Set, privateKey);
    }

    public PKCS10CertificationRequest(byte[] bArr) {
        super(toDERSequence(bArr));
    }

    private static X509Name convertName(X500Principal x500Principal) {
        try {
            return new X509Principal(x500Principal.getEncoded());
        } catch (IOException unused) {
            throw new IllegalArgumentException("can't convert name");
        }
    }

    private static ASN1Sequence toDERSequence(byte[] bArr) {
        try {
            return (ASN1Sequence) new ASN1InputStream(bArr).readObject();
        } catch (Exception unused) {
            throw new IllegalArgumentException("badly encoded request");
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public byte[] getEncoded() {
        try {
            return getEncoded(ASN1Encodable.DER);
        } catch (IOException e2) {
            throw new RuntimeException(e2.toString());
        }
    }

    public byte[] getReqInfoEncoded() {
        try {
            return this.reqInfo.getEncoded(ASN1Encodable.DER);
        } catch (Exception e2) {
            throw new IllegalArgumentException("exception encoding TBS cert request - ".concat(String.valueOf(e2)));
        }
    }

    public void setSigBits(byte[] bArr) {
        this.sigBits = new DERBitString(bArr);
    }
}
