package com.jingdong.common.utils.security;

import com.jingdong.common.R;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.secure.Base64;
import java.io.IOException;

/* loaded from: classes6.dex */
public class SaltGenerator {
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String privateKeyP3 = "JXReeQ==";

    private SaltGenerator() {
    }

    public static SaltGenerator newInstance() {
        return new SaltGenerator();
    }

    public byte[] getSalt() throws IOException {
        byte[] bArr = new byte[16];
        byte[] bytes = "!q@w".getBytes();
        byte[] bytes2 = JdSdk.getInstance().getApplicationContext().getResources().getString(R.string.privateKeyP2).getBytes();
        byte[] decode = Base64.decode(privateKeyP3);
        byte[] bArr2 = new byte[bytes.length];
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr2[i2] = (byte) (((bytes[i2] + bytes2[i2]) + decode[i2]) / 3);
        }
        System.arraycopy(bytes, 0, bArr, 0, 4);
        System.arraycopy(bytes2, 0, bArr, 4, 4);
        System.arraycopy(decode, 0, bArr, 8, 4);
        System.arraycopy(bArr2, 0, bArr, 12, 4);
        return bArr;
    }
}
