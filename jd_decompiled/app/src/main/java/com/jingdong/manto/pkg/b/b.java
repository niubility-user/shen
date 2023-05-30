package com.jingdong.manto.pkg.b;

import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes16.dex */
public class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i2) {
        if (bArr == null) {
            return 0;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i2, 4);
        wrap.order(ByteOrder.BIG_ENDIAN);
        return wrap.getInt();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i2 = 0;
        while (i2 < str.length() && '/' == str.charAt(i2)) {
            i2++;
        }
        return str.substring(i2);
    }
}
