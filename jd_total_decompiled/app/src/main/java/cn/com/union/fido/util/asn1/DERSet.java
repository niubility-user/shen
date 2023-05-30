package cn.com.union.fido.util.asn1;

import java.io.ByteArrayOutputStream;
import java.util.Enumeration;

/* loaded from: classes.dex */
public class DERSet extends ASN1Set {
    public DERSet() {
    }

    public DERSet(DEREncodable dEREncodable) {
        addObject(dEREncodable);
    }

    public DERSet(DEREncodableVector dEREncodableVector) {
        this(dEREncodableVector, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERSet(DEREncodableVector dEREncodableVector, boolean z) {
        for (int i2 = 0; i2 != dEREncodableVector.size(); i2++) {
            addObject(dEREncodableVector.get(i2));
        }
        if (z) {
            sort();
        }
    }

    public DERSet(ASN1Encodable[] aSN1EncodableArr) {
        for (int i2 = 0; i2 != aSN1EncodableArr.length; i2++) {
            addObject(aSN1EncodableArr[i2]);
        }
        sort();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Set, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream2 = new DEROutputStream(byteArrayOutputStream);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            dEROutputStream2.writeObject(objects.nextElement());
        }
        dEROutputStream2.close();
        dEROutputStream.writeEncoded(49, byteArrayOutputStream.toByteArray());
    }
}
