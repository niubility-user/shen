package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public class DEROctetString extends ASN1OctetString {
    public DEROctetString(DEREncodable dEREncodable) {
        super(dEREncodable);
    }

    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    static void encode(DEROutputStream dEROutputStream, byte[] bArr) {
        dEROutputStream.writeEncoded(4, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1OctetString, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(4, this.string);
    }
}
