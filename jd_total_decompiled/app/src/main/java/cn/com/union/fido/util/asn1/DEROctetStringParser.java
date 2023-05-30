package cn.com.union.fido.util.asn1;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class DEROctetStringParser implements ASN1OctetStringParser {
    private DefiniteLengthInputStream stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DEROctetStringParser(DefiniteLengthInputStream definiteLengthInputStream) {
        this.stream = definiteLengthInputStream;
    }

    @Override // cn.com.union.fido.util.asn1.DEREncodable
    public DERObject getDERObject() {
        try {
            return new DEROctetString(this.stream.toByteArray());
        } catch (IOException e2) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e2.getMessage(), e2);
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1OctetStringParser
    public InputStream getOctetStream() {
        return this.stream;
    }
}
