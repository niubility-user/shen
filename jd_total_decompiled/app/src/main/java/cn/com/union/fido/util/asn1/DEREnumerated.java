package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Arrays;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class DEREnumerated extends ASN1Object {
    byte[] bytes;

    public DEREnumerated(int i2) {
        this.bytes = BigInteger.valueOf(i2).toByteArray();
    }

    public DEREnumerated(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public DEREnumerated(byte[] bArr) {
        this.bytes = bArr;
    }

    public static DEREnumerated getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DEREnumerated getInstance(Object obj) {
        while (obj != null && !(obj instanceof DEREnumerated)) {
            if (obj instanceof ASN1OctetString) {
                return new DEREnumerated(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DEREnumerated) obj;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DEREnumerated) {
            return Arrays.areEqual(this.bytes, ((DEREnumerated) dERObject).bytes);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(10, this.bytes);
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }
}
