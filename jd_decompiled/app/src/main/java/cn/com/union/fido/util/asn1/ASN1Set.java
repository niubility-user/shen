package cn.com.union.fido.util.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes.dex */
public abstract class ASN1Set extends ASN1Object {
    protected Vector set = new Vector();

    private byte[] getEncoded(DEREncodable dEREncodable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).writeObject(dEREncodable);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.isExplicit()) {
                return (ASN1Set) aSN1TaggedObject.getObject();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (aSN1TaggedObject.isExplicit()) {
            return new DERSet(aSN1TaggedObject.getObject());
        } else {
            if (aSN1TaggedObject.getObject() instanceof ASN1Set) {
                return (ASN1Set) aSN1TaggedObject.getObject();
            }
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (!(aSN1TaggedObject.getObject() instanceof ASN1Sequence)) {
                throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
            }
            Enumeration objects = ((ASN1Sequence) aSN1TaggedObject.getObject()).getObjects();
            while (objects.hasMoreElements()) {
                aSN1EncodableVector.add((DEREncodable) objects.nextElement());
            }
            return new DERSet(aSN1EncodableVector, false);
        }
    }

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    private boolean lessThanOrEqual(byte[] bArr, byte[] bArr2) {
        int i2;
        int i3;
        if (bArr.length <= bArr2.length) {
            for (int i4 = 0; i4 != bArr.length && (i3 = bArr2[i4] & 255) <= (i2 = bArr[i4] & 255); i4++) {
                if (i2 > i3) {
                    return false;
                }
            }
            return true;
        }
        for (int i5 = 0; i5 != bArr2.length; i5++) {
            int i6 = bArr[i5] & 255;
            int i7 = bArr2[i5] & 255;
            if (i7 > i6) {
                return true;
            }
            if (i6 > i7) {
                return false;
            }
        }
        return false;
    }

    public void addObject(DEREncodable dEREncodable) {
        this.set.addElement(dEREncodable);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof ASN1Set) {
            ASN1Set aSN1Set = (ASN1Set) dERObject;
            if (size() != aSN1Set.size()) {
                return false;
            }
            Enumeration objects = getObjects();
            Enumeration objects2 = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                DERObject dERObject2 = ((DEREncodable) objects.nextElement()).getDERObject();
                DERObject dERObject3 = ((DEREncodable) objects2.nextElement()).getDERObject();
                if (dERObject2 != dERObject3 && (dERObject2 == null || !dERObject2.equals(dERObject3))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public abstract void encode(DEROutputStream dEROutputStream);

    public DEREncodable getObjectAt(int i2) {
        return (DEREncodable) this.set.elementAt(i2);
    }

    public Enumeration getObjects() {
        return this.set.elements();
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        Enumeration objects = getObjects();
        int size = size();
        while (objects.hasMoreElements()) {
            Object nextElement = objects.nextElement();
            size *= 17;
            if (nextElement != null) {
                size ^= nextElement.hashCode();
            }
        }
        return size;
    }

    public ASN1SetParser parser() {
        return new ASN1SetParser() { // from class: cn.com.union.fido.util.asn1.ASN1Set.1
            private int index;
            private final int max;

            {
                ASN1Set.this = this;
                this.max = this.size();
            }

            @Override // cn.com.union.fido.util.asn1.DEREncodable
            public DERObject getDERObject() {
                return this;
            }

            @Override // cn.com.union.fido.util.asn1.ASN1SetParser
            public DEREncodable readObject() {
                int i2 = this.index;
                if (i2 == this.max) {
                    return null;
                }
                ASN1Set aSN1Set = ASN1Set.this;
                this.index = i2 + 1;
                DEREncodable objectAt = aSN1Set.getObjectAt(i2);
                return objectAt instanceof ASN1Sequence ? ((ASN1Sequence) objectAt).parser() : objectAt instanceof ASN1Set ? ((ASN1Set) objectAt).parser() : objectAt;
            }
        };
    }

    public int size() {
        return this.set.size();
    }

    public void sort() {
        if (this.set.size() > 1) {
            int size = this.set.size() - 1;
            boolean z = true;
            while (z) {
                int i2 = 0;
                byte[] encoded = getEncoded((DEREncodable) this.set.elementAt(0));
                z = false;
                int i3 = 0;
                while (i3 != size) {
                    int i4 = i3 + 1;
                    byte[] encoded2 = getEncoded((DEREncodable) this.set.elementAt(i4));
                    if (lessThanOrEqual(encoded, encoded2)) {
                        encoded = encoded2;
                    } else {
                        Object elementAt = this.set.elementAt(i3);
                        Vector vector = this.set;
                        vector.setElementAt(vector.elementAt(i4), i3);
                        this.set.setElementAt(elementAt, i4);
                        i2 = i3;
                        z = true;
                    }
                    i3 = i4;
                }
                size = i2;
            }
        }
    }

    public String toString() {
        return this.set.toString();
    }
}
