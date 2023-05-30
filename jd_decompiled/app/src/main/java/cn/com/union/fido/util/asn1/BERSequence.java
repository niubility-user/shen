package cn.com.union.fido.util.asn1;

import java.util.Enumeration;

/* loaded from: classes.dex */
public class BERSequence extends DERSequence {
    public BERSequence() {
    }

    public BERSequence(DEREncodable dEREncodable) {
        super(dEREncodable);
    }

    public BERSequence(DEREncodableVector dEREncodableVector) {
        super(dEREncodableVector);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.DERSequence, cn.com.union.fido.util.asn1.ASN1Sequence, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        if (!(dEROutputStream instanceof ASN1OutputStream) && !(dEROutputStream instanceof BEROutputStream)) {
            super.encode(dEROutputStream);
            return;
        }
        dEROutputStream.write(48);
        dEROutputStream.write(128);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            dEROutputStream.writeObject(objects.nextElement());
        }
        dEROutputStream.write(0);
        dEROutputStream.write(0);
    }
}
