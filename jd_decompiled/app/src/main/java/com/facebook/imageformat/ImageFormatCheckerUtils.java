package com.facebook.imageformat;

import com.facebook.common.internal.Preconditions;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class ImageFormatCheckerUtils {
    private ImageFormatCheckerUtils() {
    }

    public static byte[] asciiBytes(String str) {
        Preconditions.checkNotNull(str);
        try {
            return str.getBytes("ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("ASCII not found!", e2);
        }
    }

    public static boolean hasPatternAt(byte[] bArr, byte[] bArr2, int i2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(bArr2);
        if (bArr2.length + i2 > bArr.length) {
            return false;
        }
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            if (bArr[i2 + i3] != bArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r1 > r9) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r8[r1] == r2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001d, code lost:
        if (r1 > r9) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
        r3 = r1 + 1;
        r5 = (r3 + r11) - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0024, code lost:
        if (r3 >= r5) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (r8[r3] != r10[r4]) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002c, code lost:
        r3 = r3 + 1;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0031, code lost:
        if (r3 != r5) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0033, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0034, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r8[r1] != r2) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0015, code lost:
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOfPattern(byte[] r8, int r9, byte[] r10, int r11) {
        /*
            com.facebook.common.internal.Preconditions.checkNotNull(r8)
            com.facebook.common.internal.Preconditions.checkNotNull(r10)
            r0 = -1
            if (r11 <= r9) goto La
            return r0
        La:
            r1 = 0
            r2 = r10[r1]
            int r9 = r9 - r11
        Le:
            if (r1 > r9) goto L37
            r3 = r8[r1]
            r4 = 1
            if (r3 == r2) goto L1d
        L15:
            int r1 = r1 + r4
            if (r1 > r9) goto L1d
            r3 = r8[r1]
            if (r3 == r2) goto L1d
            goto L15
        L1d:
            if (r1 > r9) goto L34
            int r3 = r1 + 1
            int r5 = r3 + r11
            int r5 = r5 - r4
        L24:
            if (r3 >= r5) goto L31
            r6 = r8[r3]
            r7 = r10[r4]
            if (r6 != r7) goto L31
            int r3 = r3 + 1
            int r4 = r4 + 1
            goto L24
        L31:
            if (r3 != r5) goto L34
            return r1
        L34:
            int r1 = r1 + 1
            goto Le
        L37:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imageformat.ImageFormatCheckerUtils.indexOfPattern(byte[], int, byte[], int):int");
    }

    public static boolean startsWithPattern(byte[] bArr, byte[] bArr2) {
        return hasPatternAt(bArr, bArr2, 0);
    }
}
