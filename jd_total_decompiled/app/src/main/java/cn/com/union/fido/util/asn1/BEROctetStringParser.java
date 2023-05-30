package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.io.Streams;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class BEROctetStringParser implements ASN1OctetStringParser {
    private ASN1StreamParser _parser;

    protected BEROctetStringParser(ASN1ObjectParser aSN1ObjectParser) {
        this._parser = aSN1ObjectParser._aIn;
    }

    public BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new BERConstructedOctetString(Streams.readAll(getOctetStream()));
        } catch (IOException e2) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e2.getMessage(), e2);
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1OctetStringParser
    public InputStream getOctetStream() {
        return new ConstructedOctetStream(this._parser);
    }
}
