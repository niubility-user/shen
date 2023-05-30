package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public abstract class ASN1Object extends DERObject {
    public static ASN1Object fromByteArray(byte[] bArr) {
        return (ASN1Object) new ASN1InputStream(bArr).readObject();
    }

    abstract boolean asn1Equals(DERObject dERObject);

    @Override // cn.com.union.fido.util.asn1.DERObject
    public abstract void encode(DEROutputStream dEROutputStream);

    @Override // cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DEREncodable) && asn1Equals(((DEREncodable) obj).getDERObject());
    }

    @Override // cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public abstract int hashCode();
}
