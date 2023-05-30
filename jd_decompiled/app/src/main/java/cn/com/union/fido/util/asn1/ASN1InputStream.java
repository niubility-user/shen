package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.io.Streams;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ASN1InputStream extends FilterInputStream implements DERTags {
    private final boolean lazyEvaluate;
    private final int limit;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, Integer.MAX_VALUE);
    }

    public ASN1InputStream(InputStream inputStream, int i2) {
        this(inputStream, i2, false);
    }

    public ASN1InputStream(InputStream inputStream, int i2, boolean z) {
        super(inputStream);
        this.limit = i2;
        this.lazyEvaluate = z;
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public static DERObject createPrimitiveDERObject(int i2, byte[] bArr) {
        switch (i2) {
            case 1:
                return new DERBoolean(bArr);
            case 2:
                return new DERInteger(bArr);
            case 3:
                byte b = bArr[0];
                byte[] bArr2 = new byte[bArr.length - 1];
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
                return new DERBitString(bArr2, b);
            case 4:
                return new DEROctetString(bArr);
            case 5:
                return DERNull.INSTANCE;
            case 6:
                return new DERObjectIdentifier(bArr);
            case 7:
            case 8:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 21:
            case 25:
            case 29:
            default:
                return new DERUnknownTag(false, i2, bArr);
            case 10:
                return new DEREnumerated(bArr);
            case 12:
                return new DERUTF8String(bArr);
            case 18:
                return new DERNumericString(bArr);
            case 19:
                return new DERPrintableString(bArr);
            case 20:
                return new DERT61String(bArr);
            case 22:
                return new DERIA5String(bArr);
            case 23:
                return new DERUTCTime(bArr);
            case 24:
                return new DERGeneralizedTime(bArr);
            case 26:
                return new DERVisibleString(bArr);
            case 27:
                return new DERGeneralString(bArr);
            case 28:
                return new DERUniversalString(bArr);
            case 30:
                return new DERBMPString(bArr);
        }
    }

    public static int readLength(InputStream inputStream, int i2) {
        int read = inputStream.read();
        if (read >= 0) {
            if (read == 128) {
                return -1;
            }
            if (read > 127) {
                int i3 = read & 127;
                if (i3 <= 4) {
                    int i4 = 0;
                    for (int i5 = 0; i5 < i3; i5++) {
                        int read2 = inputStream.read();
                        if (read2 < 0) {
                            throw new EOFException("EOF found reading length");
                        }
                        i4 = (i4 << 8) + read2;
                    }
                    if (i4 >= 0) {
                        if (i4 < i2) {
                            return i4;
                        }
                        throw new IOException("corrupted stream - out of bounds length found");
                    }
                    throw new IOException("corrupted stream - negative length found");
                }
                throw new IOException("DER length more than 4 bytes: ".concat(String.valueOf(i3)));
            }
            return read;
        }
        throw new EOFException("EOF found when length expected");
    }

    public static int readTagNumber(InputStream inputStream, int i2) {
        int i3 = i2 & 31;
        if (i3 == 31) {
            int i4 = 0;
            int read = inputStream.read();
            if ((read & 127) != 0) {
                while (read >= 0 && (read & 128) != 0) {
                    i4 = (i4 | (read & 127)) << 7;
                    read = inputStream.read();
                }
                if (read >= 0) {
                    return i4 | (read & 127);
                }
                throw new EOFException("EOF found inside tag value.");
            }
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        return i3;
    }

    ASN1EncodableVector buildDEREncodableVector(DefiniteLengthInputStream definiteLengthInputStream) {
        return new ASN1InputStream(definiteLengthInputStream).buildEncodableVector();
    }

    ASN1EncodableVector buildEncodableVector() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            DERObject readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(readObject);
        }
    }

    protected DERObject buildObject(int i2, int i3, int i4) {
        boolean z = (i2 & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i4);
        return (i2 & 64) != 0 ? new DERApplicationSpecific(z, i3, definiteLengthInputStream.toByteArray()) : (i2 & 128) != 0 ? new BERTaggedObjectParser(i2, i3, definiteLengthInputStream).getDERObject() : z ? i3 != 4 ? i3 != 8 ? i3 != 16 ? i3 != 17 ? new DERUnknownTag(true, i3, definiteLengthInputStream.toByteArray()) : DERFactory.createSet(buildDEREncodableVector(definiteLengthInputStream), false) : this.lazyEvaluate ? new LazyDERSequence(definiteLengthInputStream.toByteArray()) : DERFactory.createSequence(buildDEREncodableVector(definiteLengthInputStream)) : new DERExternal(buildDEREncodableVector(definiteLengthInputStream)) : new BERConstructedOctetString(buildDEREncodableVector(definiteLengthInputStream).v) : createPrimitiveDERObject(i3, definiteLengthInputStream.toByteArray());
    }

    protected void readFully(byte[] bArr) {
        if (Streams.readFully(this, bArr) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    protected int readLength() {
        return readLength(this, this.limit);
    }

    public DERObject readObject() {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int readTagNumber = readTagNumber(this, read);
        boolean z = (read & 32) != 0;
        int readLength = readLength();
        if (readLength < 0) {
            if (z) {
                IndefiniteLengthInputStream indefiniteLengthInputStream = new IndefiniteLengthInputStream(this);
                if ((read & 64) != 0) {
                    return new BERApplicationSpecificParser(readTagNumber, new ASN1StreamParser(indefiniteLengthInputStream, this.limit)).getDERObject();
                }
                if ((read & 128) != 0) {
                    return new BERTaggedObjectParser(read, readTagNumber, indefiniteLengthInputStream).getDERObject();
                }
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(indefiniteLengthInputStream, this.limit);
                if (readTagNumber != 4) {
                    if (readTagNumber != 8) {
                        if (readTagNumber != 16) {
                            if (readTagNumber == 17) {
                                return new BERSetParser(aSN1StreamParser).getDERObject();
                            }
                            throw new IOException("unknown BER object encountered");
                        }
                        return new BERSequenceParser(aSN1StreamParser).getDERObject();
                    }
                    return new DERExternalParser(aSN1StreamParser).getDERObject();
                }
                return new BEROctetStringParser(aSN1StreamParser).getDERObject();
            }
            throw new IOException("indefinite length primitive encoding encountered");
        }
        return buildObject(read, readTagNumber, readLength);
    }
}
