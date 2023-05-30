package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class TAG_UAFV1_SIGNED_DATA {
    public String aaid;
    public byte authenticationMode;
    public short authenticatorVersion;
    public byte[] authnrNonce;
    public byte[] finalChallenge;
    public byte[] keyID;
    public int signCounter;
    public short signatureAlgAndEncoding;
    public byte[] tcHash;
    public String uvi;

    public void deserialize(byte[] bArr) {
        int i2;
        int byteToShort;
        int byteToShort2;
        int byteToShort3;
        int byteToShort4;
        int byteToShort5;
        int byteToShort6;
        int i3 = 4;
        if (11787 == Utility.byteToShort(bArr, 0, 2)) {
            int byteToShort7 = Utility.byteToShort(bArr, 2, 4);
            if (byteToShort7 > 0) {
                int i4 = byteToShort7 + 4;
                this.aaid = Utility.byteToStr(bArr, 4, i4);
                i3 = i4;
            }
        } else {
            i3 = 0;
        }
        int i5 = i3 + 2;
        if (11790 == Utility.byteToShort(bArr, i3, i5)) {
            i3 = i5 + 2;
            if (Utility.byteToShort(bArr, i5, i3) > 0) {
                int i6 = i3 + 2;
                this.authenticatorVersion = (short) Utility.byteToShort(bArr, i3, i6);
                this.authenticationMode = bArr[i6];
                int i7 = i6 + 1;
                i3 = i7 + 2;
                this.signatureAlgAndEncoding = (short) Utility.byteToShort(bArr, i7, i3);
            }
        }
        int i8 = i3 + 2;
        if (11791 == Utility.byteToShort(bArr, i3, i8) && (byteToShort6 = Utility.byteToShort(bArr, i8, (i3 = i8 + 2))) > 0) {
            byte[] bArr2 = new byte[byteToShort6];
            this.authnrNonce = bArr2;
            System.arraycopy(bArr, i3, bArr2, 0, byteToShort6);
            i3 += byteToShort6;
        }
        int i9 = i3 + 2;
        if (11786 == Utility.byteToShort(bArr, i3, i9) && (byteToShort5 = Utility.byteToShort(bArr, i9, (i3 = i9 + 2))) > 0) {
            byte[] bArr3 = new byte[byteToShort5];
            this.finalChallenge = bArr3;
            System.arraycopy(bArr, i3, bArr3, 0, byteToShort5);
            i3 += byteToShort5;
        }
        int i10 = i3 + 2;
        if (11792 == Utility.byteToShort(bArr, i3, i10) && (byteToShort4 = Utility.byteToShort(bArr, i10, (i3 = i10 + 2))) > 0) {
            byte[] bArr4 = new byte[byteToShort4];
            this.tcHash = bArr4;
            System.arraycopy(bArr, i3, bArr4, 0, byteToShort4);
            i3 += byteToShort4;
        }
        int i11 = i3 + 2;
        if (11785 == Utility.byteToShort(bArr, i3, i11) && (byteToShort3 = Utility.byteToShort(bArr, i11, (i3 = i11 + 2))) > 0) {
            byte[] bArr5 = new byte[byteToShort3];
            this.keyID = bArr5;
            System.arraycopy(bArr, i3, bArr5, 0, byteToShort3);
            i3 += byteToShort3;
        }
        int i12 = i3 + 2;
        if (11789 == Utility.byteToShort(bArr, i3, i12) && (byteToShort2 = Utility.byteToShort(bArr, i12, (i3 = i12 + 2))) > 0) {
            int i13 = byteToShort2 + i3;
            this.signCounter = Utility.byteToInt(bArr, i3, i13);
            i3 = i13;
        }
        int i14 = i3 + 2;
        if (260 != Utility.byteToShort(bArr, i3, i14) || (byteToShort = Utility.byteToShort(bArr, i14, (i2 = i14 + 2))) <= 0) {
            return;
        }
        this.uvi = Utility.byteToStr(bArr, i2, byteToShort + i2);
    }

    public byte[] serialize() {
        int i2;
        int i3;
        int i4;
        int i5;
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, R2.id.recommend_year_festival_shot_bg);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.vf_nonfull_c_00003);
        int i6 = 8;
        if (StringTools.isValidateString(this.aaid)) {
            int length = this.aaid.getBytes().length;
            Utility.shortToByte(bArr, 6, 8, length);
            int i7 = length + 8;
            Utility.strToByte(bArr, 8, i7, this.aaid);
            i6 = i7;
        } else {
            Utility.shortToByte(bArr, 6, 8, 0);
        }
        int i8 = i6 + 2;
        Utility.shortToByte(bArr, i6, i8, R2.drawable.vf_nonfull_c_00006);
        int i9 = i8 + 2;
        int i10 = i9 + 2;
        Utility.shortToByte(bArr, i9, i10, this.authenticatorVersion);
        bArr[i10] = this.authenticationMode;
        int i11 = i10 + 1;
        int i12 = i11 + 2;
        Utility.shortToByte(bArr, i11, i12, this.signatureAlgAndEncoding);
        Utility.shortToByte(bArr, i9 - 2, i9, i12 - i9);
        int i13 = i12 + 2;
        Utility.shortToByte(bArr, i12, i13, R2.drawable.vf_nonfull_c_00007);
        if (CommonTools.isValidateByteArray(this.authnrNonce)) {
            int length2 = this.authnrNonce.length;
            int i14 = i13 + 2;
            Utility.shortToByte(bArr, i13, i14, length2);
            System.arraycopy(this.authnrNonce, 0, bArr, i14, length2);
            i2 = i14 + length2;
        } else {
            i2 = i13 + 2;
            Utility.shortToByte(bArr, i13, i2, 0);
        }
        int i15 = i2 + 2;
        Utility.shortToByte(bArr, i2, i15, R2.drawable.vf_nonfull_c_00002);
        if (CommonTools.isValidateByteArray(this.finalChallenge)) {
            int length3 = this.finalChallenge.length;
            int i16 = i15 + 2;
            Utility.shortToByte(bArr, i15, i16, length3);
            System.arraycopy(this.finalChallenge, 0, bArr, i16, length3);
            i3 = i16 + length3;
        } else {
            i3 = i15 + 2;
            Utility.shortToByte(bArr, i15, i3, 0);
        }
        int i17 = i3 + 2;
        Utility.shortToByte(bArr, i3, i17, R2.drawable.vf_nonfull_c_00008);
        if (CommonTools.isValidateByteArray(this.tcHash)) {
            int length4 = this.tcHash.length;
            int i18 = i17 + 2;
            Utility.shortToByte(bArr, i17, i18, length4);
            System.arraycopy(this.tcHash, 0, bArr, i18, length4);
            i4 = i18 + length4;
        } else {
            i4 = i17 + 2;
            Utility.shortToByte(bArr, i17, i4, 0);
        }
        int i19 = i4 + 2;
        Utility.shortToByte(bArr, i4, i19, R2.drawable.vf_nonfull_c_00001);
        if (CommonTools.isValidateByteArray(this.keyID)) {
            int length5 = this.keyID.length;
            int i20 = i19 + 2;
            Utility.shortToByte(bArr, i19, i20, length5);
            System.arraycopy(this.keyID, 0, bArr, i20, length5);
            i5 = i20 + length5;
        } else {
            i5 = i19 + 2;
            Utility.shortToByte(bArr, i19, i5, 0);
        }
        int i21 = i5 + 2;
        Utility.shortToByte(bArr, i5, i21, R2.drawable.vf_nonfull_c_00005);
        int i22 = i21 + 2;
        Utility.shortToByte(bArr, i21, i22, 4);
        int i23 = i22 + 4;
        Utility.intToByte(bArr, i22, i23, this.signCounter);
        if (StringTools.isValidateString(this.uvi)) {
            int i24 = i23 + 2;
            Utility.shortToByte(bArr, i23, i24, 260);
            int length6 = this.uvi.getBytes().length;
            int i25 = i24 + 2;
            Utility.shortToByte(bArr, i24, i25, length6);
            i23 = length6 + i25;
            Utility.strToByte(bArr, i25, i23, this.uvi);
        }
        Utility.shortToByte(bArr, 2, 4, i23 - 4);
        byte[] bArr2 = new byte[i23];
        System.arraycopy(bArr, 0, bArr2, 0, i23);
        return bArr2;
    }
}
