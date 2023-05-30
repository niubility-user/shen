package cn.com.union.fido.util.asn1;

import java.util.Enumeration;

/* loaded from: classes.dex */
public class BERSet extends DERSet {
    public BERSet() {
    }

    public BERSet(DEREncodable dEREncodable) {
        super(dEREncodable);
    }

    public BERSet(DEREncodableVector dEREncodableVector) {
        super(dEREncodableVector, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERSet(DEREncodableVector dEREncodableVector, boolean z) {
        super(dEREncodableVector, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.DERSet, cn.com.union.fido.util.asn1.ASN1Set, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        if (!(dEROutputStream instanceof ASN1OutputStream) && !(dEROutputStream instanceof BEROutputStream)) {
            super.encode(dEROutputStream);
            return;
        }
        dEROutputStream.write(49);
        dEROutputStream.write(128);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            dEROutputStream.writeObject(objects.nextElement());
        }
        dEROutputStream.write(0);
        dEROutputStream.write(0);
    }
}
