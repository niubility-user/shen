package cn.com.union.fido.util.asn1;

/* loaded from: classes.dex */
public abstract class ASN1TaggedObject extends ASN1Object implements ASN1TaggedObjectParser {
    boolean empty = false;
    boolean explicit;
    DEREncodable obj;
    int tagNo;

    public ASN1TaggedObject(int i2, DEREncodable dEREncodable) {
        this.explicit = true;
        this.obj = null;
        this.explicit = true;
        this.tagNo = i2;
        this.obj = dEREncodable;
    }

    public ASN1TaggedObject(boolean z, int i2, DEREncodable dEREncodable) {
        this.explicit = true;
        this.obj = null;
        if (dEREncodable instanceof ASN1Choice) {
            this.explicit = true;
        } else {
            this.explicit = z;
        }
        this.tagNo = i2;
        this.obj = dEREncodable;
    }

    public static ASN1TaggedObject getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return (ASN1TaggedObject) aSN1TaggedObject.getObject();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    public static ASN1TaggedObject getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) dERObject;
            if (this.tagNo == aSN1TaggedObject.tagNo && this.empty == aSN1TaggedObject.empty && this.explicit == aSN1TaggedObject.explicit) {
                DEREncodable dEREncodable = this.obj;
                return dEREncodable == null ? aSN1TaggedObject.obj == null : dEREncodable.getDERObject().equals(aSN1TaggedObject.obj.getDERObject());
            }
            return false;
        }
        return false;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public abstract void encode(DEROutputStream dEROutputStream);

    public DERObject getObject() {
        DEREncodable dEREncodable = this.obj;
        if (dEREncodable != null) {
            return dEREncodable.getDERObject();
        }
        return null;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1TaggedObjectParser
    public DEREncodable getObjectParser(int i2, boolean z) {
        if (i2 != 4) {
            if (i2 != 16) {
                if (i2 != 17) {
                    if (z) {
                        return getObject();
                    }
                    throw new RuntimeException("implicit tagging not implemented for tag: ".concat(String.valueOf(i2)));
                }
                return ASN1Set.getInstance(this, z).parser();
            }
            return ASN1Sequence.getInstance(this, z).parser();
        }
        return ASN1OctetString.getInstance(this, z).parser();
    }

    @Override // cn.com.union.fido.util.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.tagNo;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        int i2 = this.tagNo;
        DEREncodable dEREncodable = this.obj;
        return dEREncodable != null ? i2 ^ dEREncodable.hashCode() : i2;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isExplicit() {
        return this.explicit;
    }

    public String toString() {
        return "[" + this.tagNo + "]" + this.obj;
    }
}
