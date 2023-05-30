package cn.com.union.fido.util.asn1;

import java.io.IOException;

/* loaded from: classes.dex */
public class DERSequenceParser implements ASN1SequenceParser {
    private ASN1StreamParser _parser;

    public DERSequenceParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new DERSequence(this._parser.readVector());
        } catch (IOException e2) {
            throw new IllegalStateException(e2.getMessage());
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1SequenceParser
    public DEREncodable readObject() {
        return this._parser.readObject();
    }
}
