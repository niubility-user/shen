package cn.com.union.fido.util.asn1;

import java.io.IOException;

/* loaded from: classes.dex */
public class DERExternalParser implements DEREncodable {
    private ASN1StreamParser _parser;

    public DERExternalParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new DERExternal(this._parser.readVector());
        } catch (IOException e2) {
            throw new ASN1ParsingException("unable to get DER object", e2);
        } catch (IllegalArgumentException e3) {
            throw new ASN1ParsingException("unable to get DER object", e3);
        }
    }

    public DEREncodable readObject() {
        return this._parser.readObject();
    }
}
