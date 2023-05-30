package cn.com.union.fido.util.asn1;

import java.io.IOException;

/* loaded from: classes.dex */
public class BERApplicationSpecificParser implements ASN1ApplicationSpecificParser {
    private final ASN1StreamParser parser;
    private final int tag;

    public BERApplicationSpecificParser(int i2, ASN1StreamParser aSN1StreamParser) {
        this.tag = i2;
        this.parser = aSN1StreamParser;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new BERApplicationSpecific(this.tag, this.parser.readVector());
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage(), e2);
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1ApplicationSpecificParser
    public DEREncodable readObject() {
        return this.parser.readObject();
    }
}
