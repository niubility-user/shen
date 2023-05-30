package cn.com.union.fido.util.asn1;

import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes.dex */
public abstract class ASN1Sequence extends ASN1Object {
    private Vector seq = new Vector();

    public static ASN1Sequence getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (!aSN1TaggedObject.isExplicit()) {
                throw new IllegalArgumentException("object implicit - explicit expected.");
            }
        } else if (aSN1TaggedObject.isExplicit()) {
            return aSN1TaggedObject instanceof BERTaggedObject ? new BERSequence(aSN1TaggedObject.getObject()) : new DERSequence(aSN1TaggedObject.getObject());
        } else if (!(aSN1TaggedObject.getObject() instanceof ASN1Sequence)) {
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
        return (ASN1Sequence) aSN1TaggedObject.getObject();
    }

    public static ASN1Sequence getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Sequence)) {
            return (ASN1Sequence) obj;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public void addObject(DEREncodable dEREncodable) {
        this.seq.addElement(dEREncodable);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof ASN1Sequence) {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) dERObject;
            if (size() != aSN1Sequence.size()) {
                return false;
            }
            Enumeration objects = getObjects();
            Enumeration objects2 = aSN1Sequence.getObjects();
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
        return (DEREncodable) this.seq.elementAt(i2);
    }

    public Enumeration getObjects() {
        return this.seq.elements();
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

    public ASN1SequenceParser parser() {
        return new ASN1SequenceParser() { // from class: cn.com.union.fido.util.asn1.ASN1Sequence.1
            private int index;
            private final int max;

            {
                ASN1Sequence.this = this;
                this.max = this.size();
            }

            @Override // cn.com.union.fido.util.asn1.DEREncodable
            public DERObject getDERObject() {
                return this;
            }

            @Override // cn.com.union.fido.util.asn1.ASN1SequenceParser
            public DEREncodable readObject() {
                int i2 = this.index;
                if (i2 == this.max) {
                    return null;
                }
                ASN1Sequence aSN1Sequence = ASN1Sequence.this;
                this.index = i2 + 1;
                DEREncodable objectAt = aSN1Sequence.getObjectAt(i2);
                return objectAt instanceof ASN1Sequence ? ((ASN1Sequence) objectAt).parser() : objectAt instanceof ASN1Set ? ((ASN1Set) objectAt).parser() : objectAt;
            }
        };
    }

    public int size() {
        return this.seq.size();
    }

    public String toString() {
        return this.seq.toString();
    }
}
