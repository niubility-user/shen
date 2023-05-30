package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.bean.authenticator.tag.TAG_USERNAME_AND_KEYHANDLE;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SignResponse extends BaseResponse implements Serializable {
    private static final long serialVersionUID = 7522446439331265752L;
    public byte[] assertion;
    public short cmd = AuthCmdConstance.TAG_UAFV1_SIGN_CMD_RESPONSE;
    public short statusCode = 0;
    public List<TAG_USERNAME_AND_KEYHANDLE> userNameAndKeyHandle;

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        int i2 = 4;
        Utility.byteToShort(bArr, 2, 4);
        if (10248 == Utility.byteToShort(bArr, 4, 6)) {
            this.statusCode = (short) Utility.byteToShort(bArr, 8, 10);
            i2 = 10;
        }
        this.userNameAndKeyHandle = new ArrayList();
        while (14338 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i3 = i2 + 2;
            int i4 = i3 + 2;
            int byteToShort = Utility.byteToShort(bArr, i3, i4);
            byte[] bArr2 = new byte[byteToShort];
            System.arraycopy(bArr, i4, bArr2, 0, byteToShort);
            TAG_USERNAME_AND_KEYHANDLE tag_username_and_keyhandle = new TAG_USERNAME_AND_KEYHANDLE();
            tag_username_and_keyhandle.deserialize(bArr2);
            this.userNameAndKeyHandle.add(tag_username_and_keyhandle);
            i2 = byteToShort + i4;
        }
        int i5 = i2 + 2;
        if (10255 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i5, i6);
            byte[] bArr3 = new byte[byteToShort2];
            this.assertion = bArr3;
            System.arraycopy(bArr, i6, bArr3, 0, byteToShort2);
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public byte[] serialize() {
        byte[] bArr = new byte[8192];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.main_navigation_background);
        Utility.shortToByte(bArr, 6, 8, 2);
        int i2 = 10;
        Utility.shortToByte(bArr, 8, 10, this.statusCode);
        if (CommonTools.isValidateList(this.userNameAndKeyHandle)) {
            for (int i3 = 0; i3 < this.userNameAndKeyHandle.size(); i3++) {
                TAG_USERNAME_AND_KEYHANDLE tag_username_and_keyhandle = this.userNameAndKeyHandle.get(i3);
                if (tag_username_and_keyhandle != null) {
                    byte[] serialize = tag_username_and_keyhandle.serialize();
                    System.arraycopy(serialize, 0, bArr, i2, serialize.length);
                    i2 += serialize.length;
                }
            }
        }
        byte[] bArr2 = this.assertion;
        if (bArr2 != null && bArr2.length > 0) {
            int i4 = i2 + 2;
            Utility.shortToByte(bArr, i2, i4, R2.drawable.manto_actionbar_close_white);
            int i5 = i4 + 2;
            Utility.shortToByte(bArr, i4, i5, this.assertion.length);
            byte[] bArr3 = this.assertion;
            System.arraycopy(bArr3, 0, bArr, i5, bArr3.length);
            i2 = i5 + this.assertion.length;
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArr, 0, bArr4, 0, i2);
        return bArr4;
    }
}
