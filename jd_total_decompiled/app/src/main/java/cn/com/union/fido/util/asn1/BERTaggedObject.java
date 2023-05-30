package cn.com.union.fido.util.asn1;

import java.util.Enumeration;

/* loaded from: classes.dex */
public class BERTaggedObject extends DERTaggedObject {
    public BERTaggedObject(int i2) {
        super(false, i2, new BERSequence());
    }

    public BERTaggedObject(int i2, DEREncodable dEREncodable) {
        super(i2, dEREncodable);
    }

    public BERTaggedObject(boolean z, int i2, DEREncodable dEREncodable) {
        super(z, i2, dEREncodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.DERTaggedObject, cn.com.union.fido.util.asn1.ASN1TaggedObject, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        Enumeration objects;
        if (!(dEROutputStream instanceof ASN1OutputStream) && !(dEROutputStream instanceof BEROutputStream)) {
            super.encode(dEROutputStream);
            return;
        }
        dEROutputStream.writeTag(160, this.tagNo);
        dEROutputStream.write(128);
        if (!this.empty) {
            if (this.explicit) {
                dEROutputStream.writeObject(this.obj);
            } else {
                DEREncodable dEREncodable = this.obj;
                if (dEREncodable instanceof ASN1OctetString) {
                    objects = dEREncodable instanceof BERConstructedOctetString ? ((BERConstructedOctetString) dEREncodable).getObjects() : new BERConstructedOctetString(((ASN1OctetString) dEREncodable).getOctets()).getObjects();
                } else if (dEREncodable instanceof ASN1Sequence) {
                    objects = ((ASN1Sequence) dEREncodable).getObjects();
                } else if (!(dEREncodable instanceof ASN1Set)) {
                    throw new RuntimeException("not implemented: " + this.obj.getClass().getName());
                } else {
                    objects = ((ASN1Set) dEREncodable).getObjects();
                }
                while (objects.hasMoreElements()) {
                    dEROutputStream.writeObject(objects.nextElement());
                }
            }
        }
        dEROutputStream.write(0);
        dEROutputStream.write(0);
    }
}
