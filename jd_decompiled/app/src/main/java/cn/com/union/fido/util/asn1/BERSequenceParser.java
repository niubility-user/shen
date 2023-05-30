package cn.com.union.fido.util.asn1;

import java.io.IOException;

/* loaded from: classes.dex */
public class BERSequenceParser implements ASN1SequenceParser {
    private ASN1StreamParser _parser;

    public BERSequenceParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new BERSequence(this._parser.readVector());
        } catch (IOException e2) {
            throw new IllegalStateException(e2.getMessage());
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1SequenceParser
    public DEREncodable readObject() {
        return this._parser.readObject();
    }
}
