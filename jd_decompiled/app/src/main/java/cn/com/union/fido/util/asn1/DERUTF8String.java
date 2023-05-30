package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Strings;

/* loaded from: classes.dex */
public class DERUTF8String extends ASN1Object implements DERString {
    String string;

    public DERUTF8String(String str) {
        this.string = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERUTF8String(byte[] bArr) {
        this.string = Strings.fromUTF8ByteArray(bArr);
    }

    public static DERUTF8String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERUTF8String getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERUTF8String)) {
            if (obj instanceof ASN1OctetString) {
                return new DERUTF8String(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DERUTF8String) obj;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERUTF8String) {
            return getString().equals(((DERUTF8String) dERObject).getString());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(12, Strings.toUTF8ByteArray(this.string));
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
