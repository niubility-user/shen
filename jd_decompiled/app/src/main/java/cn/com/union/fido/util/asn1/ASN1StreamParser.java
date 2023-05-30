package cn.com.union.fido.util.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i2) {
        this._in = inputStream;
        this._limit = i2;
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    private static int findLimit(InputStream inputStream) {
        if (inputStream instanceof DefiniteLengthInputStream) {
            return ((DefiniteLengthInputStream) inputStream).getRemaining();
        }
        return Integer.MAX_VALUE;
    }

    private void set00Check(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }

    public DEREncodable readObject() {
        int read = this._in.read();
        if (read == -1) {
            return null;
        }
        set00Check(false);
        int readTagNumber = ASN1InputStream.readTagNumber(this._in, read);
        boolean z = (read & 32) != 0;
        int readLength = ASN1InputStream.readLength(this._in, this._limit);
        if (readLength >= 0) {
            DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, readLength);
            return (read & 64) != 0 ? new DERApplicationSpecific(z, readTagNumber, definiteLengthInputStream.toByteArray()) : (read & 128) != 0 ? new BERTaggedObjectParser(read, readTagNumber, definiteLengthInputStream) : z ? readTagNumber != 4 ? readTagNumber != 8 ? readTagNumber != 16 ? readTagNumber != 17 ? new DERUnknownTag(true, readTagNumber, definiteLengthInputStream.toByteArray()) : new DERSetParser(new ASN1StreamParser(definiteLengthInputStream)) : new DERSequenceParser(new ASN1StreamParser(definiteLengthInputStream)) : new DERExternalParser(new ASN1StreamParser(definiteLengthInputStream)) : new BEROctetStringParser(new ASN1StreamParser(definiteLengthInputStream)) : readTagNumber != 4 ? ASN1InputStream.createPrimitiveDERObject(readTagNumber, definiteLengthInputStream.toByteArray()) : new DEROctetStringParser(definiteLengthInputStream);
        } else if (z) {
            IndefiniteLengthInputStream indefiniteLengthInputStream = new IndefiniteLengthInputStream(this._in);
            if ((read & 64) != 0) {
                return new BERApplicationSpecificParser(readTagNumber, new ASN1StreamParser(indefiniteLengthInputStream, this._limit));
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(read, readTagNumber, indefiniteLengthInputStream);
            }
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(indefiniteLengthInputStream, this._limit);
            if (readTagNumber != 4) {
                if (readTagNumber != 8) {
                    if (readTagNumber != 16) {
                        if (readTagNumber == 17) {
                            return new BERSetParser(aSN1StreamParser);
                        }
                        throw new IOException("unknown BER object encountered: 0x" + Integer.toHexString(readTagNumber));
                    }
                    return new BERSequenceParser(aSN1StreamParser);
                }
                return new DERExternalParser(aSN1StreamParser);
            }
            return new BEROctetStringParser(aSN1StreamParser);
        } else {
            throw new IOException("indefinite length primitive encoding encountered");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1EncodableVector readVector() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            DEREncodable readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(readObject.getDERObject());
        }
    }
}
