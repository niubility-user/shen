package cn.com.union.fido.util.asn1;

import java.util.Enumeration;

/* loaded from: classes.dex */
public class LazyDERSequence extends DERSequence {
    private byte[] encoded;
    private boolean parsed = false;
    private int size = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LazyDERSequence(byte[] bArr) {
        this.encoded = bArr;
    }

    private void parse() {
        LazyDERConstructionEnumeration lazyDERConstructionEnumeration = new LazyDERConstructionEnumeration(this.encoded);
        while (lazyDERConstructionEnumeration.hasMoreElements()) {
            addObject((DEREncodable) lazyDERConstructionEnumeration.nextElement());
        }
        this.parsed = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.DERSequence, cn.com.union.fido.util.asn1.ASN1Sequence, cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(48, this.encoded);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Sequence
    public DEREncodable getObjectAt(int i2) {
        if (!this.parsed) {
            parse();
        }
        return super.getObjectAt(i2);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Sequence
    public Enumeration getObjects() {
        return this.parsed ? super.getObjects() : new LazyDERConstructionEnumeration(this.encoded);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Sequence
    public int size() {
        if (this.size < 0) {
            LazyDERConstructionEnumeration lazyDERConstructionEnumeration = new LazyDERConstructionEnumeration(this.encoded);
            int i2 = 0;
            while (true) {
                this.size = i2;
                if (!lazyDERConstructionEnumeration.hasMoreElements()) {
                    break;
                }
                lazyDERConstructionEnumeration.nextElement();
                i2 = this.size + 1;
            }
        }
        return this.size;
    }
}
