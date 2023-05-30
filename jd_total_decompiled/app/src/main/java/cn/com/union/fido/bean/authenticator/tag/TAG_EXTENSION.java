package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.common.UAFPredefinedValues;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class TAG_EXTENSION {
    public short cmd = UAFPredefinedValues.TAG_EXTENSION1;
    public String data;
    public String id;

    public void deserialize(byte[] bArr) {
        int i2 = 0;
        if (11795 == Utility.byteToShort(bArr, 0, 2)) {
            int byteToShort = Utility.byteToShort(bArr, 2, 4) + 4;
            this.id = Utility.byteToStr(bArr, 4, byteToShort);
            i2 = byteToShort;
        }
        int i3 = i2 + 2;
        if (11796 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            this.data = Utility.byteToStr(bArr, i4, Utility.byteToShort(bArr, i3, i4) + i4);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        if (StringTools.isValidateString(this.id)) {
            Utility.shortToByte(bArr, 4, 6, R2.drawable.vf_nonfull_c_00011);
            int length = this.id.getBytes().length;
            Utility.shortToByte(bArr, 6, 8, length);
            i2 = length + 8;
            Utility.strToByte(bArr, 8, i2, this.id);
        } else {
            i2 = 4;
        }
        if (StringTools.isValidateString(this.data)) {
            int i3 = i2 + 2;
            Utility.shortToByte(bArr, i2, i3, R2.drawable.vf_nonfull_c_00012);
            int length2 = this.data.getBytes().length;
            int i4 = i3 + 2;
            Utility.shortToByte(bArr, i3, i4, length2);
            i2 = length2 + i4;
            Utility.strToByte(bArr, i4, i2, this.data);
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
