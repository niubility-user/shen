package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public class DERBMPString extends ASN1Object implements DERString {
    String string;

    public DERBMPString(String str) {
        this.string = str;
    }

    public DERBMPString(byte[] bArr) {
        int length = bArr.length / 2;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            int i3 = i2 * 2;
            cArr[i2] = (char) ((bArr[i3 + 1] & 255) | (bArr[i3] << 8));
        }
        this.string = new String(cArr);
    }

    public static DERBMPString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERBMPString getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERBMPString)) {
            if (obj instanceof ASN1OctetString) {
                return new DERBMPString(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DERBMPString) obj;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    protected boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERBMPString) {
            return getString().equals(((DERBMPString) dERObject).getString());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        char[] charArray = this.string.toCharArray();
        byte[] bArr = new byte[charArray.length * 2];
        for (int i2 = 0; i2 != charArray.length; i2++) {
            int i3 = i2 * 2;
            bArr[i3] = (byte) (charArray[i2] >> '\b');
            bArr[i3 + 1] = (byte) charArray[i2];
        }
        dEROutputStream.writeEncoded(30, bArr);
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
