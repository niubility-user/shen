package cn.com.union.fido.util.asn1;

import java.io.ByteArrayOutputStream;

/* loaded from: classes.dex */
public class DERExternal extends ASN1Object {
    private ASN1Object dataValueDescriptor;
    private DERObjectIdentifier directReference;
    private int encoding;
    private DERObject externalContent;
    private DERInteger indirectReference;

    public DERExternal(ASN1EncodableVector aSN1EncodableVector) {
        int i2 = 0;
        DERObject dERObject = aSN1EncodableVector.get(0).getDERObject();
        if (dERObject instanceof DERObjectIdentifier) {
            this.directReference = (DERObjectIdentifier) dERObject;
            dERObject = aSN1EncodableVector.get(1).getDERObject();
            i2 = 1;
        }
        if (dERObject instanceof DERInteger) {
            this.indirectReference = (DERInteger) dERObject;
            i2++;
            dERObject = aSN1EncodableVector.get(i2).getDERObject();
        }
        if (!(dERObject instanceof DERTaggedObject)) {
            this.dataValueDescriptor = (ASN1Object) dERObject;
            dERObject = aSN1EncodableVector.get(i2 + 1).getDERObject();
        }
        if (!(dERObject instanceof DERTaggedObject)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
        DERTaggedObject dERTaggedObject = (DERTaggedObject) dERObject;
        setEncoding(dERTaggedObject.getTagNo());
        this.externalContent = dERTaggedObject.getObject();
    }

    public DERExternal(DERObjectIdentifier dERObjectIdentifier, DERInteger dERInteger, ASN1Object aSN1Object, int i2, DERObject dERObject) {
        setDirectReference(dERObjectIdentifier);
        setIndirectReference(dERInteger);
        setDataValueDescriptor(aSN1Object);
        setEncoding(i2);
        setExternalContent(dERObject.getDERObject());
    }

    public DERExternal(DERObjectIdentifier dERObjectIdentifier, DERInteger dERInteger, ASN1Object aSN1Object, DERTaggedObject dERTaggedObject) {
        this(dERObjectIdentifier, dERInteger, aSN1Object, dERTaggedObject.getTagNo(), dERTaggedObject.getDERObject());
    }

    private void setDataValueDescriptor(ASN1Object aSN1Object) {
        this.dataValueDescriptor = aSN1Object;
    }

    private void setDirectReference(DERObjectIdentifier dERObjectIdentifier) {
        this.directReference = dERObjectIdentifier;
    }

    private void setEncoding(int i2) {
        if (i2 < 0 || i2 > 2) {
            throw new IllegalArgumentException("invalid encoding value: ".concat(String.valueOf(i2)));
        }
        this.encoding = i2;
    }

    private void setExternalContent(DERObject dERObject) {
        this.externalContent = dERObject;
    }

    private void setIndirectReference(DERInteger dERInteger) {
        this.indirectReference = dERInteger;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        ASN1Object aSN1Object;
        DERInteger dERInteger;
        DERObjectIdentifier dERObjectIdentifier;
        if (dERObject instanceof DERExternal) {
            if (this == dERObject) {
                return true;
            }
            DERExternal dERExternal = (DERExternal) dERObject;
            DERObjectIdentifier dERObjectIdentifier2 = this.directReference;
            if (dERObjectIdentifier2 == null || ((dERObjectIdentifier = dERExternal.directReference) != null && dERObjectIdentifier.equals(dERObjectIdentifier2))) {
                DERInteger dERInteger2 = this.indirectReference;
                if (dERInteger2 == null || ((dERInteger = dERExternal.indirectReference) != null && dERInteger.equals(dERInteger2))) {
                    ASN1Object aSN1Object2 = this.dataValueDescriptor;
                    if (aSN1Object2 == null || ((aSN1Object = dERExternal.dataValueDescriptor) != null && aSN1Object.equals(aSN1Object2))) {
                        return this.externalContent.equals(dERExternal.externalContent);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DERObjectIdentifier dERObjectIdentifier = this.directReference;
        if (dERObjectIdentifier != null) {
            byteArrayOutputStream.write(dERObjectIdentifier.getDEREncoded());
        }
        DERInteger dERInteger = this.indirectReference;
        if (dERInteger != null) {
            byteArrayOutputStream.write(dERInteger.getDEREncoded());
        }
        ASN1Object aSN1Object = this.dataValueDescriptor;
        if (aSN1Object != null) {
            byteArrayOutputStream.write(aSN1Object.getDEREncoded());
        }
        byteArrayOutputStream.write(new DERTaggedObject(this.encoding, this.externalContent).getDEREncoded());
        dEROutputStream.writeEncoded(32, 8, byteArrayOutputStream.toByteArray());
    }

    public ASN1Object getDataValueDescriptor() {
        return this.dataValueDescriptor;
    }

    public DERObjectIdentifier getDirectReference() {
        return this.directReference;
    }

    public int getEncoding() {
        return this.encoding;
    }

    public DERObject getExternalContent() {
        return this.externalContent;
    }

    public DERInteger getIndirectReference() {
        return this.indirectReference;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        DERObjectIdentifier dERObjectIdentifier = this.directReference;
        int hashCode = dERObjectIdentifier != null ? dERObjectIdentifier.hashCode() : 0;
        DERInteger dERInteger = this.indirectReference;
        if (dERInteger != null) {
            hashCode ^= dERInteger.hashCode();
        }
        ASN1Object aSN1Object = this.dataValueDescriptor;
        if (aSN1Object != null) {
            hashCode ^= aSN1Object.hashCode();
        }
        return hashCode ^ this.externalContent.hashCode();
    }
}
