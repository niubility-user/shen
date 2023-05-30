package org.apache.commons.codec.digest;

import androidx.core.view.ViewCompat;
import java.util.Random;

/* loaded from: classes11.dex */
class B64 {
    static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    B64() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b64from24bit(byte b, byte b2, byte b3, int i2, StringBuilder sb) {
        int i3 = ((b << 16) & ViewCompat.MEASURED_SIZE_MASK) | ((b2 << 8) & 65535) | (b3 & 255);
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            sb.append(B64T.charAt(i3 & 63));
            i3 >>= 6;
            i2 = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getRandomSalt(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 1; i3 <= i2; i3++) {
            sb.append(B64T.charAt(new Random().nextInt(64)));
        }
        return sb.toString();
    }
}
