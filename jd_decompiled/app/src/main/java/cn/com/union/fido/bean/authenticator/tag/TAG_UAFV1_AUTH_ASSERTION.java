package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.common.UAFPredefinedValues;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class TAG_UAFV1_AUTH_ASSERTION {
    public short cmd = UAFPredefinedValues.TAG_UAFV1_AUTH_ASSERTION;
    public TAG_EXTENSION extension;
    public byte[] signature;
    public TAG_UAFV1_SIGNED_DATA signedData;

    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        int i2 = 4;
        Utility.byteToShort(bArr, 2, 4);
        if (15876 == Utility.byteToShort(bArr, 4, 6)) {
            int byteToShort = Utility.byteToShort(bArr, 6, 8);
            byte[] bArr2 = new byte[byteToShort];
            System.arraycopy(bArr, 8, bArr2, 0, byteToShort);
            TAG_UAFV1_SIGNED_DATA tag_uafv1_signed_data = new TAG_UAFV1_SIGNED_DATA();
            this.signedData = tag_uafv1_signed_data;
            tag_uafv1_signed_data.deserialize(bArr2);
            i2 = 8 + byteToShort;
        }
        int i3 = i2 + 2;
        if (11782 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i3, i4);
            byte[] bArr3 = new byte[byteToShort2];
            this.signature = bArr3;
            System.arraycopy(bArr, i4, bArr3, 0, byteToShort2);
            i2 = i4 + byteToShort2;
        }
        int i5 = i2 + 2;
        if (15889 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            int byteToShort3 = Utility.byteToShort(bArr, i5, i6);
            byte[] bArr4 = new byte[byteToShort3];
            System.arraycopy(bArr, i6, bArr4, 0, byteToShort3);
            TAG_EXTENSION tag_extension = new TAG_EXTENSION();
            this.extension = tag_extension;
            tag_extension.deserialize(bArr4);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        TAG_UAFV1_SIGNED_DATA tag_uafv1_signed_data = this.signedData;
        if (tag_uafv1_signed_data != null) {
            byte[] serialize = tag_uafv1_signed_data.serialize();
            System.arraycopy(serialize, 0, bArr, 4, serialize.length);
            i2 = serialize.length + 4;
        } else {
            i2 = 4;
        }
        if (CommonTools.isValidateByteArray(this.signature)) {
            int i3 = i2 + 2;
            Utility.shortToByte(bArr, i2, i3, R2.drawable.vf_nonfull_b_00033);
            int length = this.signature.length;
            int i4 = i3 + 2;
            Utility.shortToByte(bArr, i3, i4, length);
            System.arraycopy(this.signature, 0, bArr, i4, length);
            i2 = length + i4;
        }
        TAG_EXTENSION tag_extension = this.extension;
        if (tag_extension != null) {
            byte[] serialize2 = tag_extension.serialize();
            System.arraycopy(serialize2, 0, bArr, i2, serialize2.length);
            i2 += serialize2.length;
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
