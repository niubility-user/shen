package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class DeregisterCommand extends BaseCommand {
    public String appID;
    public byte authenticatorIndex;
    public short cmd;
    public String keyID;
    public String khAccessToken;
    public String userName;

    public DeregisterCommand() {
        this.cmd = AuthCmdConstance.TAG_UAFV1_DEREGISTER_CMD;
        this.authenticatorIndex = (byte) 0;
    }

    public DeregisterCommand(short s) {
        super(s);
        this.cmd = AuthCmdConstance.TAG_UAFV1_DEREGISTER_CMD;
        this.authenticatorIndex = (byte) 0;
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        int i2 = 4;
        Utility.byteToShort(bArr, 2, 4);
        if (10253 == Utility.byteToShort(bArr, 4, 6)) {
            this.authenticatorIndex = bArr[8];
            i2 = 9;
        }
        int i3 = i2 + 2;
        if (10244 == Utility.byteToShort(bArr, i2, i3)) {
            int i4 = i3 + 2;
            int byteToShort = Utility.byteToShort(bArr, i3, i4) + i4;
            this.appID = Utility.byteToStr(bArr, i4, byteToShort);
            i2 = byteToShort;
        }
        int i5 = i2 + 2;
        if (11785 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i5, i6) + i6;
            this.keyID = Utility.byteToStr(bArr, i6, byteToShort2);
            i2 = byteToShort2;
        }
        int i7 = i2 + 2;
        if (10245 == Utility.byteToShort(bArr, i2, i7)) {
            int i8 = i7 + 2;
            int byteToShort3 = Utility.byteToShort(bArr, i7, i8) + i8;
            this.khAccessToken = Utility.byteToStr(bArr, i8, byteToShort3);
            i2 = byteToShort3;
        }
        int i9 = i2 + 2;
        if (10258 == Utility.byteToShort(bArr, i2, i9)) {
            int i10 = i9 + 2;
            this.userName = Utility.byteToStr(bArr, i10, Utility.byteToShort(bArr, i9, i10) + i10);
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public byte[] serialize() {
        byte[] bArr = new byte[2048];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.manto_actionbar_close_black);
        Utility.shortToByte(bArr, 6, 8, 1);
        bArr[8] = this.authenticatorIndex;
        int i2 = 9;
        if (StringTools.isValidateString(this.appID)) {
            Utility.shortToByte(bArr, 9, 11, R2.drawable.main_bottom_tab_search_normal);
            int length = this.appID.getBytes().length;
            Utility.shortToByte(bArr, 11, 13, length);
            int i3 = length + 13;
            Utility.strToByte(bArr, 13, i3, this.appID);
            i2 = i3;
        }
        if (StringTools.isValidateString(this.keyID)) {
            int i4 = i2 + 2;
            Utility.shortToByte(bArr, i2, i4, R2.drawable.vf_nonfull_c_00001);
            int length2 = this.keyID.getBytes().length;
            int i5 = i4 + 2;
            Utility.shortToByte(bArr, i4, i5, length2);
            i2 = length2 + i5;
            Utility.strToByte(bArr, i5, i2, this.keyID);
        }
        if (StringTools.isValidateString(this.khAccessToken)) {
            int i6 = i2 + 2;
            Utility.shortToByte(bArr, i2, i6, R2.drawable.main_bottom_tab_video_focus);
            int length3 = this.khAccessToken.getBytes().length;
            int i7 = i6 + 2;
            Utility.shortToByte(bArr, i6, i7, length3);
            i2 = length3 + i7;
            Utility.strToByte(bArr, i7, i2, this.khAccessToken);
        }
        if (StringTools.isValidateString(this.userName)) {
            int i8 = i2 + 2;
            Utility.shortToByte(bArr, i2, i8, R2.drawable.manto_actionbar_follow_black_p);
            int length4 = this.userName.getBytes().length;
            int i9 = i8 + 2;
            Utility.shortToByte(bArr, i8, i9, length4);
            i2 = length4 + i9;
            Utility.strToByte(bArr, i9, i2, this.userName);
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
