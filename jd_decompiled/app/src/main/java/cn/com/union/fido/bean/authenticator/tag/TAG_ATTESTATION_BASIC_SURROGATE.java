package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class TAG_ATTESTATION_BASIC_SURROGATE {
    public byte[] signature;

    public void deserialize(byte[] bArr) {
        if (11782 == Utility.byteToShort(bArr, 0, 2)) {
            int byteToShort = Utility.byteToShort(bArr, 2, 4);
            byte[] bArr2 = new byte[byteToShort];
            this.signature = bArr2;
            System.arraycopy(bArr, 4, bArr2, 0, byteToShort);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, R2.id.recommend_year_festival_shot_pd_title);
        if (CommonTools.isValidateByteArray(this.signature)) {
            Utility.shortToByte(bArr, 4, 6, R2.drawable.vf_nonfull_b_00033);
            int length = this.signature.length;
            Utility.shortToByte(bArr, 6, 8, length);
            System.arraycopy(this.signature, 0, bArr, 8, length);
            i2 = length + 8;
        } else {
            i2 = 4;
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
