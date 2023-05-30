package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public class DERVisibleString extends ASN1Object implements DERString {
    String string;

    public DERVisibleString(String str) {
        this.string = str;
    }

    public DERVisibleString(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        this.string = new String(cArr);
    }

    public static DERVisibleString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERVisibleString getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERVisibleString)) {
            if (obj instanceof ASN1OctetString) {
                return new DERVisibleString(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DERVisibleString) obj;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERVisibleString) {
            return getString().equals(((DERVisibleString) dERObject).getString());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(26, getOctets());
    }

    public byte[] getOctets() {
        char[] charArray = this.string.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i2 = 0; i2 != charArray.length; i2++) {
            bArr[i2] = (byte) charArray[i2];
        }
        return bArr;
    }

    @Override // cn.com.union.fido.util.asn1.DERString
    public String getString() {
        return this.string;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return getString().hashCode();
    }

    public String toString() {
        return this.string;
    }
}
