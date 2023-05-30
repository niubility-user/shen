package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public class DERTaggedObject extends ASN1TaggedObject {
    private static final byte[] ZERO_BYTES = new byte[0];

    public DERTaggedObject(int i2) {
        super(false, i2, new DERSequence());
    }

    public DERTaggedObject(int i2, DEREncodable dEREncodable) {
        super(i2, dEREncodable);
    }

    public DERTaggedObject(boolean z, int i2, DEREncodable dEREncodable) {
        super(z, i2, dEREncodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1TaggedObject, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        if (this.empty) {
            dEROutputStream.writeEncoded(160, this.tagNo, ZERO_BYTES);
            return;
        }
        byte[] encoded = this.obj.getDERObject().getEncoded(ASN1Encodable.DER);
        if (this.explicit) {
            dEROutputStream.writeEncoded(160, this.tagNo, encoded);
            return;
        }
        dEROutputStream.writeTag((encoded[0] & 32) == 0 ? 128 : 160, this.tagNo);
        dEROutputStream.write(encoded, 1, encoded.length - 1);
    }
}
