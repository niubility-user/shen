package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes.dex */
public class OpenSettingsCommand extends BaseCommand {
    public byte authenticatorIndex;
    public short cmd;

    public OpenSettingsCommand() {
        this.cmd = AuthCmdConstance.TAG_UAFV1_OPEN_SETTINGS_CMD;
        this.authenticatorIndex = (byte) 0;
    }

    public OpenSettingsCommand(short s) {
        super(s);
        this.cmd = AuthCmdConstance.TAG_UAFV1_OPEN_SETTINGS_CMD;
        this.authenticatorIndex = (byte) 0;
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
        Utility.byteToShort(bArr, 2, 4);
        if (10253 == Utility.byteToShort(bArr, 4, 6)) {
            this.authenticatorIndex = bArr[8];
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public byte[] serialize() {
        byte[] bArr = new byte[32];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 4, 6, R2.drawable.manto_actionbar_close_black);
        Utility.shortToByte(bArr, 6, 8, 1);
        bArr[8] = this.authenticatorIndex;
        Utility.shortToByte(bArr, 2, 4, 5);
        byte[] bArr2 = new byte[9];
        System.arraycopy(bArr, 0, bArr2, 0, 9);
        return bArr2;
    }
}
