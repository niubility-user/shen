package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.common.UAFPredefinedValues;
import cn.com.union.fido.util.Utility;

/* loaded from: classes.dex */
public class TAG_UAFV1_REG_ASSERTION {
    public TAG_ATTESTATION_BASIC_FULL attestationBasicFull;
    public TAG_ATTESTATION_BASIC_SURROGATE attestationBasicSurrogate;
    public short cmd = UAFPredefinedValues.TAG_UAFV1_REG_ASSERTION;
    public TAG_EXTENSION extension;
    public TAG_UAFV1_KRD uafv1Krd;

    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        int i2 = 4;
        Utility.byteToShort(bArr, 2, 4);
        if (15875 == Utility.byteToShort(bArr, 4, 6)) {
            int byteToShort = Utility.byteToShort(bArr, 6, 8);
            byte[] bArr2 = new byte[byteToShort];
            System.arraycopy(bArr, 8, bArr2, 0, byteToShort);
            TAG_UAFV1_KRD tag_uafv1_krd = new TAG_UAFV1_KRD();
            this.uafv1Krd = tag_uafv1_krd;
            tag_uafv1_krd.deserialize(bArr2);
            i2 = 8 + byteToShort;
        }
        int i3 = i2 + 2;
        if (15879 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i3, i4);
            byte[] bArr3 = new byte[byteToShort2];
            System.arraycopy(bArr, i4, bArr3, 0, byteToShort2);
            TAG_ATTESTATION_BASIC_FULL tag_attestation_basic_full = new TAG_ATTESTATION_BASIC_FULL();
            this.attestationBasicFull = tag_attestation_basic_full;
            tag_attestation_basic_full.deserialize(bArr3);
            i2 = i4 + byteToShort2;
        }
        int i5 = i2 + 2;
        if (15880 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            int byteToShort3 = Utility.byteToShort(bArr, i5, i6);
            byte[] bArr4 = new byte[byteToShort3];
            System.arraycopy(bArr, i6, bArr4, 0, byteToShort3);
            TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate = new TAG_ATTESTATION_BASIC_SURROGATE();
            this.attestationBasicSurrogate = tag_attestation_basic_surrogate;
            tag_attestation_basic_surrogate.deserialize(bArr4);
            i2 = i6 + byteToShort3;
        }
        int i7 = i2 + 2;
        if (15889 == Utility.byteToShort(bArr, i2, i7)) {
            int i8 = i7 + 2;
            int byteToShort4 = Utility.byteToShort(bArr, i7, i8);
            byte[] bArr5 = new byte[byteToShort4];
            System.arraycopy(bArr, i8, bArr5, 0, byteToShort4);
            TAG_EXTENSION tag_extension = new TAG_EXTENSION();
            this.extension = tag_extension;
            tag_extension.deserialize(bArr5);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[10240];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        TAG_UAFV1_KRD tag_uafv1_krd = this.uafv1Krd;
        if (tag_uafv1_krd != null) {
            byte[] serialize = tag_uafv1_krd.serialize();
            System.arraycopy(serialize, 0, bArr, 4, serialize.length);
            i2 = serialize.length + 4;
        } else {
            i2 = 4;
        }
        TAG_ATTESTATION_BASIC_FULL tag_attestation_basic_full = this.attestationBasicFull;
        if (tag_attestation_basic_full != null) {
            byte[] serialize2 = tag_attestation_basic_full.serialize();
            System.arraycopy(serialize2, 0, bArr, i2, serialize2.length);
            i2 += serialize2.length;
        }
        TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate = this.attestationBasicSurrogate;
        if (tag_attestation_basic_surrogate != null) {
            byte[] serialize3 = tag_attestation_basic_surrogate.serialize();
            System.arraycopy(serialize3, 0, bArr, i2, serialize3.length);
            i2 += serialize3.length;
        }
        TAG_EXTENSION tag_extension = this.extension;
        if (tag_extension != null) {
            byte[] serialize4 = tag_extension.serialize();
            System.arraycopy(serialize4, 0, bArr, i2, serialize4.length);
            i2 += serialize4.length;
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
