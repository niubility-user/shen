package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class TAG_USERNAME_AND_KEYHANDLE {
    public String keyHandle;
    public String userName;

    public void deserialize(byte[] bArr) {
        int i2 = 0;
        if (10246 == Utility.byteToShort(bArr, 0, 2)) {
            int byteToShort = Utility.byteToShort(bArr, 2, 4) + 4;
            this.userName = Utility.byteToStr(bArr, 4, byteToShort);
            i2 = byteToShort;
        }
        int i3 = i2 + 2;
        if (10241 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            this.keyHandle = Utility.byteToStr(bArr, i4, Utility.byteToShort(bArr, i3, i4) + i4);
        }
    }

    public byte[] serialize() {
        int i2;
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, R2.id.lib_pd_tradein_estimate_old_device_name_hint);
        if (StringTools.isValidateString(this.userName)) {
            Utility.shortToByte(bArr, 4, 6, R2.drawable.main_bottom_tab_video_normal);
            int length = this.userName.getBytes().length;
            Utility.shortToByte(bArr, 6, 8, length);
            i2 = length + 8;
            Utility.strToByte(bArr, 8, i2, this.userName);
        } else {
            i2 = 4;
        }
        if (StringTools.isValidateString(this.keyHandle)) {
            int i3 = i2 + 2;
            Utility.shortToByte(bArr, i2, i3, R2.drawable.main_bottom_tab_personal_normal);
            int length2 = this.keyHandle.getBytes().length;
            int i4 = i3 + 2;
            Utility.shortToByte(bArr, i3, i4, length2);
            i2 = length2 + i4;
            Utility.strToByte(bArr, i4, i2, this.keyHandle);
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
