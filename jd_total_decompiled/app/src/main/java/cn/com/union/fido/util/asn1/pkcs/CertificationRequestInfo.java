package cn.com.union.fido.util.asn1.pkcs;

import cn.com.union.fido.util.asn1.ASN1Encodable;
import cn.com.union.fido.util.asn1.ASN1EncodableVector;
import cn.com.union.fido.util.asn1.ASN1Sequence;
import cn.com.union.fido.util.asn1.ASN1Set;
import cn.com.union.fido.util.asn1.DERInteger;
import cn.com.union.fido.util.asn1.DERObject;
import cn.com.union.fido.util.asn1.DERSequence;
import cn.com.union.fido.util.asn1.DERTaggedObject;
import cn.com.union.fido.util.asn1.x509.SubjectPublicKeyInfo;
import cn.com.union.fido.util.asn1.x509.X509Name;

/* loaded from: classes.dex */
public class CertificationRequestInfo extends ASN1Encodable {
    ASN1Set attributes;
    X509Name subject;
    SubjectPublicKeyInfo subjectPKInfo;
    DERInteger version;

    public CertificationRequestInfo(ASN1Sequence aSN1Sequence) {
        this.version = new DERInteger(0);
        this.attributes = null;
        this.version = (DERInteger) aSN1Sequence.getObjectAt(0);
        this.subject = X509Name.getInstance(aSN1Sequence.getObjectAt(1));
        this.subjectPKInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(2));
        if (aSN1Sequence.size() > 3) {
            this.attributes = ASN1Set.getInstance((DERTaggedObject) aSN1Sequence.getObjectAt(3), false);
        }
        if (this.subject == null || this.version == null || this.subjectPKInfo == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }

    public CertificationRequestInfo(X509Name x509Name, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1Set aSN1Set) {
        DERInteger dERInteger = new DERInteger(0);
        this.version = dERInteger;
        this.attributes = null;
        this.subject = x509Name;
        this.subjectPKInfo = subjectPublicKeyInfo;
        this.attributes = aSN1Set;
        if (x509Name == null || dERInteger == null || subjectPublicKeyInfo == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }

    public static CertificationRequestInfo getInstance(Object obj) {
        if (obj instanceof CertificationRequestInfo) {
            return (CertificationRequestInfo) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new CertificationRequestInfo((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public ASN1Set getAttributes() {
        return this.attributes;
    }

    public X509Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPKInfo;
    }

    public DERInteger getVersion() {
        return this.version;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public DERObject toASN1Object() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.subject);
        aSN1EncodableVector.add(this.subjectPKInfo);
        if (this.attributes != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.attributes));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
