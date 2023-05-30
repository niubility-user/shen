package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class DeregisterResponse extends BaseResponse {
    public short cmd = AuthCmdConstance.TAG_UAFV1_DEREGISTER_CMD_RESPONSE;
    public short statusCode = 0;

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        Utility.byteToShort(bArr, 2, 4);
        if (10248 == Utility.byteToShort(bArr, 4, 6)) {
            this.statusCode = (short) Utility.byteToShort(bArr, 8, 10);
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public byte[] serialize() {
        byte[] bArr = new byte[32];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.main_navigation_background);
        Utility.shortToByte(bArr, 6, 8, 2);
        Utility.shortToByte(bArr, 8, 10, this.statusCode);
        Utility.shortToByte(bArr, 2, 4, 6);
        byte[] bArr2 = new byte[10];
        System.arraycopy(bArr, 0, bArr2, 0, 10);
        return bArr2;
    }
}
