package cn.com.union.fido.bean.authenticator;

import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.Utility;

/* loaded from: classes.dex */
public class RawKeyHandle {
    public String KHAccessToken;
    public String PrivateKey;
    public String Username;

    public void deserialize(byte[] bArr) {
        int byteToShort = Utility.byteToShort(bArr, 0, 2) + 2;
        this.KHAccessToken = Utility.byteToStr(bArr, 2, byteToShort);
        int i2 = byteToShort + 2;
        int byteToShort2 = Utility.byteToShort(bArr, byteToShort, i2) + i2;
        this.PrivateKey = Utility.byteToStr(bArr, i2, byteToShort2);
        int i3 = byteToShort2 + 2;
        int byteToShort3 = Utility.byteToShort(bArr, byteToShort2, i3);
        if (byteToShort3 > 0) {
            this.Username = Utility.byteToStr(bArr, i3, byteToShort3 + i3);
        }
    }

    public byte[] serialize() {
        byte[] bArr = new byte[2048];
        int length = this.KHAccessToken.length();
        Utility.shortToByte(bArr, 0, 2, length);
        int i2 = length + 2;
        Utility.strToByte(bArr, 2, i2, this.KHAccessToken);
        int length2 = this.PrivateKey.length();
        int i3 = i2 + 2;
        Utility.shortToByte(bArr, i2, i3, length2);
        int i4 = length2 + i3;
        Utility.strToByte(bArr, i3, i4, this.PrivateKey);
        if (StringTools.isValidateString(this.Username)) {
            byte[] strToByte = Utility.strToByte(this.Username);
            int length3 = strToByte.length;
            int i5 = i4 + 2;
            Utility.shortToByte(bArr, i4, i5, length3);
            System.arraycopy(strToByte, 0, bArr, i5, length3);
            i4 = i5 + length3;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        return bArr2;
    }
}
