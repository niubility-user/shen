package cn.com.union.fido.util.asn1.util.encoders;

import java.io.OutputStream;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes.dex */
public class HexEncoder implements Encoder {
    protected final byte[] encodingTable = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 97, 98, 99, ReplyCode.reply0x64, 101, 102};
    protected final byte[] decodingTable = new byte[128];

    public HexEncoder() {
        initialiseDecodingTable();
    }

    private boolean ignore(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    @Override // cn.com.union.fido.util.asn1.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            while (i2 < length && ignore(str.charAt(i2))) {
                i2++;
            }
            int i4 = i2 + 1;
            byte b = this.decodingTable[str.charAt(i2)];
            while (i4 < length && ignore(str.charAt(i4))) {
                i4++;
            }
            outputStream.write((b << 4) | this.decodingTable[str.charAt(i4)]);
            i3++;
            i2 = i4 + 1;
        }
        return i3;
    }

    @Override // cn.com.union.fido.util.asn1.util.encoders.Encoder
    public int decode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        int i4 = i3 + i2;
        while (i4 > i2 && ignore((char) bArr[i4 - 1])) {
            i4--;
        }
        int i5 = 0;
        while (i2 < i4) {
            while (i2 < i4 && ignore((char) bArr[i2])) {
                i2++;
            }
            int i6 = i2 + 1;
            byte b = this.decodingTable[bArr[i2]];
            while (i6 < i4 && ignore((char) bArr[i6])) {
                i6++;
            }
            outputStream.write((b << 4) | this.decodingTable[bArr[i6]]);
            i5++;
            i2 = i6 + 1;
        }
        return i5;
    }

    @Override // cn.com.union.fido.util.asn1.util.encoders.Encoder
    public int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            int i5 = bArr[i4] & 255;
            outputStream.write(this.encodingTable[i5 >>> 4]);
            outputStream.write(this.encodingTable[i5 & 15]);
        }
        return i3 * 2;
    }

    protected void initialiseDecodingTable() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.encodingTable;
            if (i2 >= bArr.length) {
                byte[] bArr2 = this.decodingTable;
                bArr2[65] = bArr2[97];
                bArr2[66] = bArr2[98];
                bArr2[67] = bArr2[99];
                bArr2[68] = bArr2[100];
                bArr2[69] = bArr2[101];
                bArr2[70] = bArr2[102];
                return;
            }
            this.decodingTable[bArr[i2]] = (byte) i2;
            i2++;
        }
    }
}
