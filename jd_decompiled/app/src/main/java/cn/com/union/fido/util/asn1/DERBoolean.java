package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public class DERBoolean extends ASN1Object {
    public static final DERBoolean FALSE = new DERBoolean(false);
    public static final DERBoolean TRUE = new DERBoolean(true);
    byte value;

    public DERBoolean(boolean z) {
        this.value = z ? (byte) -1 : (byte) 0;
    }

    public DERBoolean(byte[] bArr) {
        this.value = bArr[0];
    }

    public static DERBoolean getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERBoolean getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERBoolean)) {
            if (obj instanceof ASN1OctetString) {
                return new DERBoolean(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DERBoolean) obj;
    }

    public static DERBoolean getInstance(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    protected boolean asn1Equals(DERObject dERObject) {
        return dERObject != null && (dERObject instanceof DERBoolean) && this.value == ((DERBoolean) dERObject).value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(1, new byte[]{this.value});
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return this.value;
    }

    public boolean isTrue() {
        return this.value != 0;
    }

    public String toString() {
        return this.value != 0 ? "TRUE" : "FALSE";
    }
}
