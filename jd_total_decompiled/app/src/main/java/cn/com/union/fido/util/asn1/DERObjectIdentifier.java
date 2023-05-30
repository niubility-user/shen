package cn.com.union.fido.util.asn1;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes.dex */
public class DERObjectIdentifier extends ASN1Object {
    String identifier;

    public DERObjectIdentifier(String str) {
        if (isValidIdentifier(str)) {
            this.identifier = str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not an OID");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERObjectIdentifier(byte[] bArr) {
        long j2;
        StringBuffer stringBuffer = new StringBuffer();
        BigInteger bigInteger = null;
        long j3 = 0;
        boolean z = true;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            if (j3 < 36028797018963968L) {
                j3 = (j3 * 128) + (i3 & 127);
                if ((i3 & 128) == 0) {
                    if (z) {
                        int i4 = ((int) j3) / 40;
                        if (i4 != 0) {
                            if (i4 != 1) {
                                stringBuffer.append('2');
                                j2 = 80;
                            } else {
                                stringBuffer.append('1');
                                j2 = 40;
                            }
                            j3 -= j2;
                        } else {
                            stringBuffer.append('0');
                        }
                        z = false;
                    }
                    stringBuffer.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
                    stringBuffer.append(j3);
                    j3 = 0;
                }
            } else {
                bigInteger = (bigInteger == null ? BigInteger.valueOf(j3) : bigInteger).shiftLeft(7).or(BigInteger.valueOf(i3 & 127));
                if ((i3 & 128) == 0) {
                    stringBuffer.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
                    stringBuffer.append(bigInteger);
                    bigInteger = null;
                    j3 = 0;
                }
            }
        }
        this.identifier = stringBuffer.toString();
    }

    public static DERObjectIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERObjectIdentifier getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERObjectIdentifier)) {
            if (obj instanceof ASN1OctetString) {
                return new DERObjectIdentifier(((ASN1OctetString) obj).getOctets());
            }
            if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            }
            obj = ((ASN1TaggedObject) obj).getObject();
        }
        return (DERObjectIdentifier) obj;
    }

    private static boolean isValidIdentifier(String str) {
        char charAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (charAt = str.charAt(0)) < '0' || charAt > '2') {
            return false;
        }
        boolean z = false;
        for (int length = str.length() - 1; length >= 2; length--) {
            char charAt2 = str.charAt(length);
            if ('0' <= charAt2 && charAt2 <= '9') {
                z = true;
            } else if (charAt2 != '.' || !z) {
                return false;
            } else {
                z = false;
            }
        }
        return z;
    }

    private void writeField(OutputStream outputStream, long j2) {
        if (j2 >= 128) {
            if (j2 >= IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT) {
                if (j2 >= 2097152) {
                    if (j2 >= 268435456) {
                        if (j2 >= IjkMediaMeta.AV_CH_LOW_FREQUENCY_2) {
                            if (j2 >= 4398046511104L) {
                                if (j2 >= 562949953421312L) {
                                    if (j2 >= 72057594037927936L) {
                                        outputStream.write(((int) (j2 >> 56)) | 128);
                                    }
                                    outputStream.write(((int) (j2 >> 49)) | 128);
                                }
                                outputStream.write(((int) (j2 >> 42)) | 128);
                            }
                            outputStream.write(((int) (j2 >> 35)) | 128);
                        }
                        outputStream.write(((int) (j2 >> 28)) | 128);
                    }
                    outputStream.write(((int) (j2 >> 21)) | 128);
                }
                outputStream.write(((int) (j2 >> 14)) | 128);
            }
            outputStream.write(((int) (j2 >> 7)) | 128);
        }
        outputStream.write(((int) j2) & 127);
    }

    private void writeField(OutputStream outputStream, BigInteger bigInteger) {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            outputStream.write(0);
            return;
        }
        byte[] bArr = new byte[bitLength];
        int i2 = bitLength - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            bArr[i3] = (byte) ((bigInteger.intValue() & 127) | 128);
            bigInteger = bigInteger.shiftRight(7);
        }
        bArr[i2] = (byte) (bArr[i2] & Byte.MAX_VALUE);
        outputStream.write(bArr);
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERObjectIdentifier) {
            return this.identifier.equals(((DERObjectIdentifier) dERObject).identifier);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        String nextToken;
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream2 = new DEROutputStream(byteArrayOutputStream);
        long parseInt = (Integer.parseInt(oIDTokenizer.nextToken()) * 40) + Integer.parseInt(oIDTokenizer.nextToken());
        while (true) {
            writeField(byteArrayOutputStream, parseInt);
            while (oIDTokenizer.hasMoreTokens()) {
                nextToken = oIDTokenizer.nextToken();
                if (nextToken.length() < 18) {
                    break;
                }
                writeField(byteArrayOutputStream, new BigInteger(nextToken));
            }
            dEROutputStream2.close();
            dEROutputStream.writeEncoded(6, byteArrayOutputStream.toByteArray());
            return;
            parseInt = Long.parseLong(nextToken);
        }
    }

    public String getId() {
        return this.identifier;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public String toString() {
        return getId();
    }
}
