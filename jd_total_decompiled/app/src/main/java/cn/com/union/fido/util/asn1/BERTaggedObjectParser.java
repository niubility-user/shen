package cn.com.union.fido.util.asn1;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class BERTaggedObjectParser implements ASN1TaggedObjectParser {
    private int _baseTag;
    private InputStream _contentStream;
    private boolean _indefiniteLength;
    private int _tagNumber;

    public BERTaggedObjectParser(int i2, int i3, InputStream inputStream) {
        this._baseTag = i2;
        this._tagNumber = i3;
        this._contentStream = inputStream;
        this._indefiniteLength = inputStream instanceof IndefiniteLengthInputStream;
    }

    private ASN1EncodableVector rLoadVector(InputStream inputStream) {
        try {
            return new ASN1StreamParser(inputStream).readVector();
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage(), e2);
        }
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        if (this._indefiniteLength) {
            ASN1EncodableVector rLoadVector = rLoadVector(this._contentStream);
            return rLoadVector.size() == 1 ? new BERTaggedObject(true, this._tagNumber, rLoadVector.get(0)) : new BERTaggedObject(false, this._tagNumber, BERFactory.createSequence(rLoadVector));
        } else if (isConstructed()) {
            ASN1EncodableVector rLoadVector2 = rLoadVector(this._contentStream);
            return rLoadVector2.size() == 1 ? new DERTaggedObject(true, this._tagNumber, rLoadVector2.get(0)) : new DERTaggedObject(false, this._tagNumber, DERFactory.createSequence(rLoadVector2));
        } else {
            try {
                return new DERTaggedObject(false, this._tagNumber, new DEROctetString(((DefiniteLengthInputStream) this._contentStream).toByteArray()));
            } catch (IOException e2) {
                throw new IllegalStateException(e2.getMessage());
            }
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1TaggedObjectParser
    public DEREncodable getObjectParser(int i2, boolean z) {
        if (z) {
            return new ASN1StreamParser(this._contentStream).readObject();
        }
        if (i2 == 4) {
            return (this._indefiniteLength || isConstructed()) ? new BEROctetStringParser(new ASN1StreamParser(this._contentStream)) : new DEROctetStringParser((DefiniteLengthInputStream) this._contentStream);
        } else if (i2 == 16) {
            return this._indefiniteLength ? new BERSequenceParser(new ASN1StreamParser(this._contentStream)) : new DERSequenceParser(new ASN1StreamParser(this._contentStream));
        } else if (i2 == 17) {
            return this._indefiniteLength ? new BERSetParser(new ASN1StreamParser(this._contentStream)) : new DERSetParser(new ASN1StreamParser(this._contentStream));
        } else {
            throw new RuntimeException("implicit tagging not implemented");
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this._tagNumber;
    }

    public boolean isConstructed() {
        return (this._baseTag & 32) != 0;
    }
}
