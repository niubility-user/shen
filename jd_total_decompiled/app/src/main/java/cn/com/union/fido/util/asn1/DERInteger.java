package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Arrays;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class DERInteger extends ASN1Object {
    byte[] bytes;

    public DERInteger(int i2) {
        this.bytes = BigInteger.valueOf(i2).toByteArray();
    }

    public DERInteger(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public DERInteger(byte[] bArr) {
        this.bytes = bArr;
    }

    public static DERInteger getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERInteger getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERInteger)) {
            if (obj instanceof ASN1OctetString) {
                return new DERInteger(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DERInteger) obj;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERInteger) {
            return Arrays.areEqual(this.bytes, ((DERInteger) dERObject).bytes);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(2, this.bytes);
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i2 == bArr.length) {
                return i3;
            }
            i3 ^= (bArr[i2] & 255) << (i2 % 4);
            i2++;
        }
    }

    public String toString() {
        return getValue().toString();
    }
}
