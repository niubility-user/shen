package com.jingdong.jdsdk.auth;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes14.dex */
public class BASE64Encoder extends a {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    @Override // com.jingdong.jdsdk.auth.a
    protected int bytesPerAtom() {
        return 3;
    }

    @Override // com.jingdong.jdsdk.auth.a
    protected int bytesPerLine() {
        return 57;
    }

    @Override // com.jingdong.jdsdk.auth.a
    protected void encodeAtom(OutputStream outputStream, byte[] bArr, int i2, int i3) throws IOException {
        if (i3 == 1) {
            byte b = bArr[i2];
            char[] cArr = pem_array;
            outputStream.write(cArr[(b >>> 2) & 63]);
            outputStream.write(cArr[((b << 4) & 48) + 0]);
            outputStream.write(61);
            outputStream.write(61);
        } else if (i3 == 2) {
            byte b2 = bArr[i2];
            byte b3 = bArr[i2 + 1];
            char[] cArr2 = pem_array;
            outputStream.write(cArr2[(b2 >>> 2) & 63]);
            outputStream.write(cArr2[((b2 << 4) & 48) + ((b3 >>> 4) & 15)]);
            outputStream.write(cArr2[((b3 << 2) & 60) + 0]);
            outputStream.write(61);
        } else {
            byte b4 = bArr[i2];
            byte b5 = bArr[i2 + 1];
            byte b6 = bArr[i2 + 2];
            char[] cArr3 = pem_array;
            outputStream.write(cArr3[(b4 >>> 2) & 63]);
            outputStream.write(cArr3[((b4 << 4) & 48) + ((b5 >>> 4) & 15)]);
            outputStream.write(cArr3[((b5 << 2) & 60) + ((b6 >>> 6) & 3)]);
            outputStream.write(cArr3[b6 & 63]);
        }
    }
}
