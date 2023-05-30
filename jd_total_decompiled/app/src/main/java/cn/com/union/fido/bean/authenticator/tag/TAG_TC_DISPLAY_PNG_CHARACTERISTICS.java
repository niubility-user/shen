package cn.com.union.fido.bean.authenticator.tag;

import cn.com.union.fido.bean.authenticator.RgbPalletteEntry;
import cn.com.union.fido.util.Utility;
import java.util.List;

/* loaded from: classes.dex */
public class TAG_TC_DISPLAY_PNG_CHARACTERISTICS {
    public byte bitDepth;
    public byte colorType;
    public byte compression;
    public byte filter;
    public int height;
    public byte interlace;
    public List<RgbPalletteEntry> plte;
    public int width;

    public void deserialize(byte[] bArr) {
        int length = bArr.length;
        this.width = Utility.byteToInt(bArr, 0, 4);
        this.height = Utility.byteToInt(bArr, 4, 8);
        this.bitDepth = bArr[8];
        this.colorType = bArr[9];
        this.compression = bArr[10];
        this.filter = bArr[11];
        this.interlace = bArr[12];
        int i2 = 13;
        while (i2 < length) {
            int i3 = i2 + 2;
            int i4 = i3 + 2;
            int i5 = i4 + 2;
            this.plte.add(new RgbPalletteEntry((short) Utility.byteToShort(bArr, i2, i3), (short) Utility.byteToShort(bArr, i3, i4), (short) Utility.byteToShort(bArr, i4, i5)));
            i2 = i5;
        }
    }

    public byte[] serialize() {
        byte[] bArr = new byte[48];
        Utility.intToByte(bArr, 0, 4, this.width);
        Utility.intToByte(bArr, 4, 8, this.height);
        bArr[8] = this.bitDepth;
        bArr[9] = this.colorType;
        bArr[10] = this.compression;
        bArr[11] = this.filter;
        bArr[12] = this.interlace;
        List<RgbPalletteEntry> list = this.plte;
        int i2 = 13;
        if (list != null && list.size() > 0) {
            for (RgbPalletteEntry rgbPalletteEntry : this.plte) {
                int i3 = i2 + 2;
                Utility.shortToByte(bArr, i2, i3, rgbPalletteEntry.getR());
                int i4 = i3 + 2;
                Utility.shortToByte(bArr, i3, i4, rgbPalletteEntry.getG());
                int i5 = i4 + 2;
                Utility.shortToByte(bArr, i4, i5, rgbPalletteEntry.getB());
                i2 = i5;
            }
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }
}
