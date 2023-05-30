package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Arrays;
import cn.com.union.fido.util.asn1.util.encoders.Hex;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes.dex */
public abstract class ASN1OctetString extends ASN1Object implements ASN1OctetStringParser {
    byte[] string;

    public ASN1OctetString(DEREncodable dEREncodable) {
        try {
            this.string = dEREncodable.getDERObject().getEncoded(ASN1Encodable.DER);
        } catch (IOException e2) {
            throw new IllegalArgumentException("Error processing object : " + e2.toString());
        }
    }

    public ASN1OctetString(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("string cannot be null");
        }
        this.string = bArr;
    }

    public static ASN1OctetString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static ASN1OctetString getInstance(Object obj) {
        while (obj != null && !(obj instanceof ASN1OctetString)) {
            if (!(obj instanceof ASN1TaggedObject)) {
                if (!(obj instanceof ASN1Sequence)) {
                    throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
                }
                Vector vector = new Vector();
                Enumeration objects = ((ASN1Sequence) obj).getObjects();
                while (objects.hasMoreElements()) {
                    vector.addElement(objects.nextElement());
                }
                return new BERConstructedOctetString(vector);
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (ASN1OctetString) obj;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof ASN1OctetString) {
            return Arrays.areEqual(this.string, ((ASN1OctetString) dERObject).string);
        }
        return false;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public abstract void encode(DEROutputStream dEROutputStream);

    @Override // cn.com.union.fido.util.asn1.ASN1OctetStringParser
    public InputStream getOctetStream() {
        return new ByteArrayInputStream(this.string);
    }

    public byte[] getOctets() {
        return this.string;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return Arrays.hashCode(getOctets());
    }

    public ASN1OctetStringParser parser() {
        return this;
    }

    public String toString() {
        return "#" + new String(Hex.encode(this.string));
    }
}
