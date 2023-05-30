package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.bean.authenticator.tag.TAG_AUTHENTICATOR_INFO;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GetInfoResponse extends BaseResponse {
    public List<TAG_AUTHENTICATOR_INFO> authenticatorInfo;
    public short cmd = AuthCmdConstance.TAG_UAFV1_GETINFO_CMD_RESPONSE;
    public short statusCode = 0;
    public byte apiVersion = 0;

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
        if (10254 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            this.apiVersion = bArr[i4];
            i2 = i4 + 1;
        }
        this.authenticatorInfo = new ArrayList();
        while (14353 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i5 = i2 + 2;
            int i6 = i5 + 2;
            int byteToShort = Utility.byteToShort(bArr, i5, i6);
            if (byteToShort > 0) {
                byte[] bArr2 = new byte[byteToShort];
                System.arraycopy(bArr, i6, bArr2, 0, byteToShort);
                TAG_AUTHENTICATOR_INFO tag_authenticator_info = new TAG_AUTHENTICATOR_INFO();
                tag_authenticator_info.deserialize(bArr2);
                this.authenticatorInfo.add(tag_authenticator_info);
                i6 += byteToShort;
            }
            i2 = i6;
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseResponse
    public byte[] serialize() {
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.main_navigation_background);
        Utility.shortToByte(bArr, 6, 8, 2);
        Utility.shortToByte(bArr, 8, 10, this.statusCode);
        Utility.shortToByte(bArr, 10, 12, R2.drawable.manto_actionbar_close_black_p);
        Utility.shortToByte(bArr, 12, 14, 1);
        bArr[14] = this.apiVersion;
        int i2 = 19;
        if (CommonTools.isValidateList(this.authenticatorInfo)) {
            i2 = 15;
            for (int i3 = 0; i3 < this.authenticatorInfo.size(); i3++) {
                TAG_AUTHENTICATOR_INFO tag_authenticator_info = this.authenticatorInfo.get(i3);
                if (tag_authenticator_info != null) {
                    byte[] serialize = tag_authenticator_info.serialize();
                    int i4 = i2 + 2;
                    Utility.shortToByte(bArr, i2, i4, R2.id.lib_photo_detail_confirm);
                    int length = serialize.length;
                    int i5 = i4 + 2;
                    Utility.shortToByte(bArr, i4, i5, length);
                    System.arraycopy(serialize, 0, bArr, i5, length);
                    i2 = length + i5;
                }
            }
        } else {
            Utility.shortToByte(bArr, 15, 17, R2.id.lib_photo_detail_confirm);
            Utility.shortToByte(bArr, 17, 19, 0);
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
