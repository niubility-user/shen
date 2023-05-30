package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.util.Utility;

/* loaded from: classes.dex */
public class GetInfoCommand extends BaseCommand {
    public short cmd;

    public GetInfoCommand() {
        this.cmd = AuthCmdConstance.TAG_UAFV1_GETINFO_CMD;
    }

    public GetInfoCommand(short s) {
        super(s);
        this.cmd = AuthCmdConstance.TAG_UAFV1_GETINFO_CMD;
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public void deserialize(byte[] bArr) {
        this.cmd = (short) Utility.byteToShort(bArr, 0, 2);
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public byte[] serialize() {
        byte[] bArr = new byte[4];
        Utility.shortToByte(bArr, 0, 2, this.cmd);
        Utility.shortToByte(bArr, 2, 4, 0);
        return bArr;
    }
}
