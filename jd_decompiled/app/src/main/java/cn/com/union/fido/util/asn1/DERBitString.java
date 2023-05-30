package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class DERBitString extends ASN1Object implements DERString {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected byte[] data;
    protected int padBits;

    protected DERBitString(byte b, int i2) {
        this.data = r0;
        byte[] bArr = {b};
        this.padBits = i2;
    }

    public DERBitString(DEREncodable dEREncodable) {
        try {
            this.data = dEREncodable.getDERObject().getEncoded(ASN1Encodable.DER);
            this.padBits = 0;
        } catch (IOException e2) {
            throw new IllegalArgumentException("Error processing object : " + e2.toString());
        }
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(byte[] bArr, int i2) {
        this.data = bArr;
        this.padBits = i2;
    }

    protected static byte[] getBytes(int i2) {
        int i3 = 4;
        for (int i4 = 3; i4 > 0 && ((255 << (i4 * 8)) & i2) == 0; i4--) {
            i3--;
        }
        byte[] bArr = new byte[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr[i5] = (byte) ((i2 >> (i5 * 8)) & 255);
        }
        return bArr;
    }

    public static DERBitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public static DERBitString getInstance(Object obj) {
        while (obj != null && !(obj instanceof DERBitString)) {
            if (obj instanceof ASN1OctetString) {
                byte[] octets = ((ASN1OctetString) obj).getOctets();
                byte b = octets[0];
                byte[] bArr = new byte[octets.length - 1];
                System.arraycopy(octets, 1, bArr, 0, octets.length - 1);
                return new DERBitString(bArr, b);
            } else if (!(obj instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
            } else {
                obj = ((ASN1TaggedObject) obj).getObject();
            }
        }
        return (DERBitString) obj;
    }

    protected static int getPadBits(int i2) {
        int i3;
        int i4 = 3;
        while (true) {
            if (i4 < 0) {
                i3 = 0;
                break;
            } else if (i4 != 0) {
                int i5 = i2 >> (i4 * 8);
                if (i5 != 0) {
                    i3 = i5 & 255;
                    break;
                }
                i4--;
            } else if (i2 != 0) {
                i3 = i2 & 255;
                break;
            } else {
                i4--;
            }
        }
        if (i3 == 0) {
            return 7;
        }
        int i6 = 1;
        while (true) {
            i3 <<= 1;
            if ((i3 & 255) == 0) {
                return 8 - i6;
            }
            i6++;
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    protected boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERBitString) {
            DERBitString dERBitString = (DERBitString) dERObject;
            return this.padBits == dERBitString.padBits && Arrays.areEqual(this.data, dERBitString.data);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        int length = getBytes().length + 1;
        byte[] bArr = new byte[length];
        bArr[0] = (byte) getPadBits();
        System.arraycopy(getBytes(), 0, bArr, 1, length - 1);
        dEROutputStream.writeEncoded(3, bArr);
    }

    public byte[] getBytes() {
        return this.data;
    }

    public int getPadBits() {
        return this.padBits;
    }

    @Override // cn.com.union.fido.util.asn1.DERString
    public String getString() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).writeObject(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i2 = 0; i2 != byteArray.length; i2++) {
                char[] cArr = table;
                stringBuffer.append(cArr[(byteArray[i2] >>> 4) & 15]);
                stringBuffer.append(cArr[byteArray[i2] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException unused) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        return this.padBits ^ Arrays.hashCode(this.data);
    }

    public int intValue() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i2 == bArr.length || i2 == 4) {
                break;
            }
            i3 |= (bArr[i2] & 255) << (i2 * 8);
            i2++;
        }
        return i3;
    }

    public String toString() {
        return getString();
    }
}
