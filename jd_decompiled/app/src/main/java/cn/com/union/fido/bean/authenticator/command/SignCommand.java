package cn.com.union.fido.bean.authenticator.command;

import cn.com.union.fido.bean.authenticator.AuthCmdConstance;
import cn.com.union.fido.bean.authenticator.tag.TAG_EXTENSION;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SignCommand extends BaseCommand implements Serializable {
    private static final long serialVersionUID = 5524143886070252506L;
    public String appID;
    public byte authenticatorIndex;
    public short cmd;
    public List<TAG_EXTENSION> extensions;
    public byte[] finalChallenge;
    public List<String> keyHandles;
    public String khAccessToken;
    public String transactionContent;
    public String userName;
    public String verificationToken;

    public SignCommand() {
        this.cmd = AuthCmdConstance.TAG_UAFV1_SIGN_CMD;
        this.authenticatorIndex = (byte) 0;
    }

    public SignCommand(short s) {
        super(s);
        this.cmd = AuthCmdConstance.TAG_UAFV1_SIGN_CMD;
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
        if (11786 == Utility.byteToShort(bArr, i2, i5)) {
            int i6 = i5 + 2;
            int byteToShort2 = Utility.byteToShort(bArr, i5, i6);
            byte[] bArr2 = new byte[byteToShort2];
            this.finalChallenge = bArr2;
            System.arraycopy(bArr, i6, bArr2, 0, byteToShort2);
            i2 = i6 + byteToShort2;
        }
        int i7 = i2 + 2;
        if (10256 == Utility.byteToShort(bArr, i2, i7)) {
            int i8 = i7 + 2;
            int byteToShort3 = Utility.byteToShort(bArr, i7, i8) + i8;
            this.transactionContent = Utility.byteToStr(bArr, i8, byteToShort3);
            i2 = byteToShort3;
        }
        int i9 = i2 + 2;
        if (10245 == Utility.byteToShort(bArr, i2, i9)) {
            int i10 = i9 + 2;
            int byteToShort4 = Utility.byteToShort(bArr, i9, i10) + i10;
            this.khAccessToken = Utility.byteToStr(bArr, i10, byteToShort4);
            i2 = byteToShort4;
        }
        int i11 = i2 + 2;
        if (10243 == Utility.byteToShort(bArr, i2, i11)) {
            int i12 = i11 + 2;
            int byteToShort5 = Utility.byteToShort(bArr, i11, i12) + i12;
            this.verificationToken = Utility.byteToStr(bArr, i12, byteToShort5);
            i2 = byteToShort5;
        }
        this.keyHandles = new ArrayList();
        while (10241 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i13 = i2 + 2;
            int i14 = i13 + 2;
            i2 = Utility.byteToShort(bArr, i13, i14) + i14;
            this.keyHandles.add(Utility.byteToStr(bArr, i14, i2));
        }
        int i15 = i2 + 2;
        if (10246 == Utility.byteToShort(bArr, i2, i15)) {
            int i16 = i15 + 2;
            int byteToShort6 = Utility.byteToShort(bArr, i15, i16) + i16;
            this.userName = Utility.byteToStr(bArr, i16, byteToShort6);
            i2 = byteToShort6;
        }
        this.extensions = new ArrayList();
        while (15889 == Utility.byteToShort(bArr, i2, i2 + 2)) {
            int i17 = i2 + 2;
            int i18 = i17 + 2;
            int byteToShort7 = Utility.byteToShort(bArr, i17, i18);
            byte[] bArr3 = new byte[byteToShort7];
            System.arraycopy(bArr, i18, bArr3, 0, byteToShort7);
            TAG_EXTENSION tag_extension = new TAG_EXTENSION();
            tag_extension.deserialize(bArr3);
            this.extensions.add(tag_extension);
            i2 = byteToShort7 + i18;
        }
    }

    @Override // cn.com.union.fido.bean.authenticator.command.BaseCommand
    public byte[] serialize() {
        byte[] bArr = new byte[8192];
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
        if (CommonTools.isValidateByteArray(this.finalChallenge)) {
            int i4 = i2 + 2;
            Utility.shortToByte(bArr, i2, i4, R2.drawable.vf_nonfull_c_00002);
            int length2 = this.finalChallenge.length;
            int i5 = i4 + 2;
            Utility.shortToByte(bArr, i4, i5, length2);
            System.arraycopy(this.finalChallenge, 0, bArr, i5, length2);
            i2 = length2 + i5;
        }
        if (StringTools.isValidateString(this.transactionContent)) {
            int i6 = i2 + 2;
            Utility.shortToByte(bArr, i2, i6, R2.drawable.manto_actionbar_close_white_p);
            int length3 = this.transactionContent.getBytes().length;
            int i7 = i6 + 2;
            Utility.shortToByte(bArr, i6, i7, length3);
            i2 = length3 + i7;
            Utility.strToByte(bArr, i7, i2, this.transactionContent);
        }
        if (StringTools.isValidateString(this.khAccessToken)) {
            int i8 = i2 + 2;
            Utility.shortToByte(bArr, i2, i8, R2.drawable.main_bottom_tab_video_focus);
            int length4 = this.khAccessToken.getBytes().length;
            int i9 = i8 + 2;
            Utility.shortToByte(bArr, i8, i9, length4);
            i2 = length4 + i9;
            Utility.strToByte(bArr, i9, i2, this.khAccessToken);
        }
        if (StringTools.isValidateString(this.verificationToken)) {
            int i10 = i2 + 2;
            Utility.shortToByte(bArr, i2, i10, R2.drawable.main_bottom_tab_search_focus);
            int length5 = this.verificationToken.getBytes().length;
            int i11 = i10 + 2;
            Utility.shortToByte(bArr, i10, i11, length5);
            i2 = length5 + i11;
            Utility.strToByte(bArr, i11, i2, this.verificationToken);
        }
        if (CommonTools.isValidateList(this.keyHandles)) {
            int i12 = 0;
            while (i12 < this.keyHandles.size()) {
                int i13 = i2 + 2;
                Utility.shortToByte(bArr, i2, i13, R2.drawable.main_bottom_tab_personal_normal);
                String str = this.keyHandles.get(i12);
                int length6 = str.getBytes().length;
                int i14 = i13 + 2;
                Utility.shortToByte(bArr, i13, i14, length6);
                int i15 = i14 + length6;
                Utility.strToByte(bArr, i14, i15, str);
                i12++;
                i2 = i15;
            }
        }
        if (StringTools.isValidateString(this.userName)) {
            int i16 = i2 + 2;
            Utility.shortToByte(bArr, i2, i16, R2.drawable.main_bottom_tab_video_normal);
            int length7 = this.userName.getBytes().length;
            int i17 = i16 + 2;
            Utility.shortToByte(bArr, i16, i17, length7);
            i2 = length7 + i17;
            Utility.strToByte(bArr, i17, i2, this.userName);
        }
        if (CommonTools.isValidateList(this.extensions)) {
            for (int i18 = 0; i18 < this.extensions.size(); i18++) {
                byte[] serialize = this.extensions.get(i18).serialize();
                System.arraycopy(serialize, 0, bArr, i2, serialize.length);
                i2 += serialize.length;
            }
        }
        Utility.shortToByte(bArr, 2, 4, i2 - 4);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
