package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class DERApplicationSpecific extends ASN1Object {
    private final boolean isConstructed;
    private final byte[] octets;
    private final int tag;

    public DERApplicationSpecific(int i2, ASN1EncodableVector aSN1EncodableVector) {
        this.tag = i2;
        this.isConstructed = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i3 = 0; i3 != aSN1EncodableVector.size(); i3++) {
            try {
                byteArrayOutputStream.write(((ASN1Encodable) aSN1EncodableVector.get(i3)).getEncoded());
            } catch (IOException e2) {
                throw new ASN1ParsingException("malformed object: ".concat(String.valueOf(e2)), e2);
            }
        }
        this.octets = byteArrayOutputStream.toByteArray();
    }

    public DERApplicationSpecific(int i2, DEREncodable dEREncodable) {
        this(true, i2, dEREncodable);
    }

    public DERApplicationSpecific(int i2, byte[] bArr) {
        this(false, i2, bArr);
    }

    public DERApplicationSpecific(boolean z, int i2, DEREncodable dEREncodable) {
        byte[] dEREncoded = dEREncodable.getDERObject().getDEREncoded();
        this.isConstructed = z;
        this.tag = i2;
        if (z) {
            this.octets = dEREncoded;
            return;
        }
        int lengthOfLength = getLengthOfLength(dEREncoded);
        int length = dEREncoded.length - lengthOfLength;
        byte[] bArr = new byte[length];
        System.arraycopy(dEREncoded, lengthOfLength, bArr, 0, length);
        this.octets = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERApplicationSpecific(boolean z, int i2, byte[] bArr) {
        this.isConstructed = z;
        this.tag = i2;
        this.octets = bArr;
    }

    private int getLengthOfLength(byte[] bArr) {
        int i2 = 2;
        while ((bArr[i2 - 1] & 128) != 0) {
            i2++;
        }
        return i2;
    }

    private byte[] replaceTagNumber(int i2, byte[] bArr) {
        int i3;
        if ((bArr[0] & 31) == 31) {
            i3 = 2;
            int i4 = bArr[1] & 255;
            if ((i4 & 127) == 0) {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
            while (i4 >= 0 && (i4 & 128) != 0) {
                i4 = bArr[i3] & 255;
                i3++;
            }
        } else {
            i3 = 1;
        }
        int length = (bArr.length - i3) + 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i3, bArr2, 1, length - 1);
        bArr2[0] = (byte) i2;
        return bArr2;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERApplicationSpecific) {
            DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) dERObject;
            return this.isConstructed == dERApplicationSpecific.isConstructed && this.tag == dERApplicationSpecific.tag && Arrays.areEqual(this.octets, dERApplicationSpecific.octets);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject
    public void encode(DEROutputStream dEROutputStream) {
        dEROutputStream.writeEncoded(this.isConstructed ? 96 : 64, this.tag, this.octets);
    }

    public int getApplicationTag() {
        return this.tag;
    }

    public byte[] getContents() {
        return this.octets;
    }

    public DERObject getObject() {
        return new ASN1InputStream(getContents()).readObject();
    }

    public DERObject getObject(int i2) {
        if (i2 < 31) {
            byte[] encoded = getEncoded();
            byte[] replaceTagNumber = replaceTagNumber(i2, encoded);
            if ((encoded[0] & 32) != 0) {
                replaceTagNumber[0] = (byte) (replaceTagNumber[0] | 32);
            }
            return new ASN1InputStream(replaceTagNumber).readObject();
        }
        throw new IOException("unsupported tag number");
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Object, cn.com.union.fido.util.asn1.DERObject, cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        boolean z = this.isConstructed;
        return ((z ? 1 : 0) ^ this.tag) ^ Arrays.hashCode(this.octets);
    }

    public boolean isConstructed() {
        return this.isConstructed;
    }
}
