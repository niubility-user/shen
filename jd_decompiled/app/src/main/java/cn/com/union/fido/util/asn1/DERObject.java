package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public abstract class DERObject extends ASN1Encodable implements DERTags {
    public abstract void encode(DEROutputStream dEROutputStream);

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public abstract boolean equals(Object obj);

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public abstract int hashCode();

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public DERObject toASN1Object() {
        return this;
    }
}
