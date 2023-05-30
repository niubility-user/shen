package cn.com.union.fido.util.asn1;

import java.io.IOException;

/* loaded from: classes.dex */
public class DERSetParser implements ASN1SetParser {
    private ASN1StreamParser _parser;

    public DERSetParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new DERSet(this._parser.readVector(), false);
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage(), e2);
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1SetParser
    public DEREncodable readObject() {
        return this._parser.readObject();
    }
}
