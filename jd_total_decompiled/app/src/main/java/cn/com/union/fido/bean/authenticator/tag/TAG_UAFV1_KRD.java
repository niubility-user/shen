package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class TAG_UAFV1_KRD {
    public String aaid;
    public byte authenticationMode;
    public short authenticatorVersion;
    public byte[] finalChallenge;
    public String keyID;
    public byte[] publicKey;
    public short publicKeyAlgAndEncoding;
    public int regCounter;
    public int signCounter;
    public short signatureAlgAndEncoding;
    public String uvi;

    public void deserialize(byte[] bArr) {
        int i2;
        if (11787 == Utility.byteToShort(bArr, 0, 2)) {
            i2 = Utility.byteToShort(bArr, 2, 4) + 4;
            this.aaid = Utility.byteToStr(bArr, 4, i2);
        } else {
            i2 = 0;
        }
        int i3 = i2 + 2;
        if (11790 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            Utility.byteToShort(bArr, i3, i4);
            int i5 = i4 + 2;
            this.authenticatorVersion = (short) Utility.byteToShort(bArr, i4, i5);
            this.authenticationMode = bArr[i5];
            int i6 = i5 + 1;
            int i7 = i6 + 2;
            this.signatureAlgAndEncoding = (short) Utility.byteToShort(bArr, i6, i7);
            int i8 = i7 + 2;
            this.publicKeyAlgAndEncoding = (short) Utility.byteToShort(bArr, i7, i8);
            i2 = i8;
        }
        int i9 = i2 + 2;
        if (11786 == Utility.byteToShort(bArr, i2, i9)) {
            int i10 = i9 + 2;
            int byteToShort = Utility.byteToShort(bArr, i9, i10);
            byte[] bArr2 = new byte[byteToShort];
            this.finalChallenge = bArr2;
            System.arraycopy(bArr, i10, bArr2, 0, byteToShort);
            i2 = i10 + byteToShort;
        }
        int i11 = i2 + 2;
        if (11785 == Utility.byteToShort(bArr, i2, i11)) {
            int i12 = i11 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i11, i12) + i12;
            this.keyID = Utility.byteToStr(bArr, i12, byteToShort2);
            i2 = byteToShort2;
        }
        int i13 = i2 + 2;
        if (11789 == Utility.byteToShort(bArr, i2, i13)) {
            int i14 = i13 + 2;
            Utility.byteToShort(bArr, i13, i14);
            int i15 = i14 + 4;
            this.signCounter = Utility.byteToInt(bArr, i14, i15);
            i2 = i15 + 4;
            this.regCounter = Utility.byteToInt(bArr, i15, i2);
        }
        int i16 = i2 + 2;
        if (11788 == Utility.byteToShort(bArr, i2, i16)) {
            int i17 = i16 + 2;
            int byteToShort3 = Utility.byteToShort(bArr, i16, i17);
            byte[] bArr3 = new byte[byteToShort3];
            this.publicKey = bArr3;
            System.arraycopy(bArr, i17, bArr3, 0, byteToShort3);
            i2 = i17 + byteToShort3;
        }
        int i18 = i2 + 2;
        if (260 == Utility.byteToShort(bArr, i2, i18)) {
            int i19 = i18 + 2;
            this.uvi = Utility.byteToStr(bArr, i19, Utility.byteToShort(bArr, i18, i19) + i19);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, R2.id.recommend_year_festival_short_product_right);
        if (StringTools.isValidateString(this.aaid)) {
            Utility.shortToByte(bArr, 4, 6, R2.drawable.vf_nonfull_c_00003);
            int length = this.aaid.getBytes().length;
            Utility.shortToByte(bArr, 6, 8, length);
            i2 = length + 8;
            Utility.strToByte(bArr, 8, i2, this.aaid);
        } else {
            i2 = 4;
        }
        int i3 = i2 + 2;
        Utility.shortToByte(bArr, i2, i3, R2.drawable.vf_nonfull_c_00006);
        int i4 = i3 + 2;
        int i5 = i4 + 2;
        Utility.shortToByte(bArr, i4, i5, this.authenticatorVersion);
        bArr[i5] = this.authenticationMode;
        int i6 = i5 + 1;
        int i7 = i6 + 2;
        Utility.shortToByte(bArr, i6, i7, this.signatureAlgAndEncoding);
        int i8 = i7 + 2;
        Utility.shortToByte(bArr, i7, i8, this.publicKeyAlgAndEncoding);
        Utility.shortToByte(bArr, i4 - 2, i4, i8 - i4);
        if (CommonTools.isValidateByteArray(this.finalChallenge)) {
            int i9 = i8 + 2;
            Utility.shortToByte(bArr, i8, i9, R2.drawable.vf_nonfull_c_00002);
            int length2 = this.finalChallenge.length;
            int i10 = i9 + 2;
            Utility.shortToByte(bArr, i9, i10, length2);
            System.arraycopy(this.finalChallenge, 0, bArr, i10, length2);
            i8 = length2 + i10;
        }
        if (StringTools.isValidateString(this.keyID)) {
            int i11 = i8 + 2;
            Utility.shortToByte(bArr, i8, i11, R2.drawable.vf_nonfull_c_00001);
            int length3 = this.keyID.getBytes().length;
            int i12 = i11 + 2;
            Utility.shortToByte(bArr, i11, i12, length3);
            i8 = length3 + i12;
            Utility.strToByte(bArr, i12, i8, this.keyID);
        }
        int i13 = i8 + 2;
        Utility.shortToByte(bArr, i8, i13, R2.drawable.vf_nonfull_c_00005);
        int i14 = i13 + 2;
        int i15 = i14 + 4;
        Utility.intToByte(bArr, i14, i15, this.signCounter);
        int i16 = i15 + 4;
        Utility.intToByte(bArr, i15, i16, this.regCounter);
        Utility.shortToByte(bArr, i14 - 2, i14, i16 - i14);
        if (CommonTools.isValidateByteArray(this.publicKey)) {
            int i17 = i16 + 2;
            Utility.shortToByte(bArr, i16, i17, R2.drawable.vf_nonfull_c_00004);
            int length4 = this.publicKey.length;
            int i18 = i17 + 2;
            Utility.shortToByte(bArr, i17, i18, length4);
            System.arraycopy(this.publicKey, 0, bArr, i18, length4);
            i16 = i18 + length4;
        }
        if (StringTools.isValidateString(this.uvi)) {
            int i19 = i16 + 2;
            Utility.shortToByte(bArr, i16, i19, 260);
            int length5 = this.uvi.getBytes().length;
            int i20 = i19 + 2;
            Utility.shortToByte(bArr, i19, i20, length5);
            int i21 = i20 + length5;
            Utility.strToByte(bArr, i20, i21, this.uvi);
            i16 = i21;
        }
        Utility.shortToByte(bArr, 2, 4, i16 - 4);
        byte[] bArr2 = new byte[i16];
        System.arraycopy(bArr, 0, bArr2, 0, i16);
        return bArr2;
    }
}
