package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Serializable;

/* loaded from: classes.dex */
public class RegisterResponse extends BaseResponse implements Serializable {
    private static final long serialVersionUID = -2920292398804218187L;
    public byte[] assertion;
    public String keyHandle;
    public short cmd = AuthCmdConstance.TAG_UAFV1_REGISTER_CMD_RESPONSE;
    public short statusCode = 0;

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        int i2 = 4;
        Utility.byteToShort(bArr, 2, 4);
        if (10248 == Utility.byteToShort(bArr, 4, 6)) {
            this.statusCode = (short) Utility.byteToShort(bArr, 8, 10);
            i2 = 10;
        }
        int i3 = i2 + 2;
        if (10255 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            int byteToShort = Utility.byteToShort(bArr, i3, i4);
            byte[] bArr2 = new byte[byteToShort];
            this.assertion = bArr2;
            System.arraycopy(bArr, i4, bArr2, 0, byteToShort);
            i2 = i4 + byteToShort;
        }
        int i5 = i2 + 2;
        if (10241 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            this.keyHandle = Utility.byteToStr(bArr, i6, Utility.byteToShort(bArr, i5, i6) + i6);
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public byte[] serialize() {
        byte[] bArr = new byte[10240];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.main_navigation_background);
        Utility.shortToByte(bArr, 6, 8, 2);
        int i2 = 10;
        Utility.shortToByte(bArr, 8, 10, this.statusCode);
        byte[] bArr2 = this.assertion;
        if (bArr2 != null && bArr2.length > 0) {
            Utility.shortToByte(bArr, 10, 12, R2.drawable.manto_actionbar_close_white);
            Utility.shortToByte(bArr, 12, 14, this.assertion.length);
            byte[] bArr3 = this.assertion;
            System.arraycopy(bArr3, 0, bArr, 14, bArr3.length);
            i2 = 14 + this.assertion.length;
        }
        if (StringTools.isValidateString(this.keyHandle)) {
            int i3 = i2 + 2;
            Utility.shortToByte(bArr, i2, i3, R2.drawable.main_bottom_tab_personal_normal);
            int length = this.keyHandle.getBytes().length;
            int i4 = i3 + 2;
            Utility.shortToByte(bArr, i3, i4, length);
            int i5 = i4 + length;
            Utility.strToByte(bArr, i4, i5, this.keyHandle);
            i2 = i5;
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArr, 0, bArr4, 0, i2);
        return bArr4;
    }
}
