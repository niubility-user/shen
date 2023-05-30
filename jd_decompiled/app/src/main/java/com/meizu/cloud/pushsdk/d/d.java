package com.meizu.cloud.pushsdk.d;

import com.huawei.hms.framework.common.ContainerUtils;

/* loaded from: classes14.dex */
class d {
    private static final char[] d = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* renamed from: e  reason: collision with root package name */
    private static final char f15707e = (char) Integer.parseInt("00000011", 2);

    /* renamed from: f  reason: collision with root package name */
    private static final char f15708f = (char) Integer.parseInt("00001111", 2);

    /* renamed from: g  reason: collision with root package name */
    private static final char f15709g = (char) Integer.parseInt("00111111", 2);
    private final String a;
    private char[] b;

    /* renamed from: c  reason: collision with root package name */
    private int f15710c = 0;

    public d(String str) {
        this.a = str;
        b();
    }

    private void b() {
        char[] cArr = new char[d.length];
        int i2 = 0;
        this.f15710c = this.a.charAt(0) % '\r';
        while (true) {
            char[] cArr2 = d;
            if (i2 >= cArr2.length) {
                this.b = cArr;
                return;
            } else {
                cArr[i2] = cArr2[(this.f15710c + i2) % cArr2.length];
                i2++;
            }
        }
    }

    public String a(byte[] bArr) {
        String str;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(((bArr.length + 2) / 3) * 4);
        int i2 = 0;
        int length = bArr.length;
        while (i2 < length) {
            int i3 = i2 + 1;
            int i4 = bArr[i2] & 255;
            if (i3 == length) {
                sb.append(this.b[i4 >>> 2]);
                sb.append(this.b[(i4 & f15707e) << 4]);
                str = "==";
            } else {
                int i5 = i3 + 1;
                int i6 = bArr[i3] & 255;
                if (i5 == length) {
                    sb.append(this.b[i4 >>> 2]);
                    sb.append(this.b[((i4 & f15707e) << 4) | (i6 >>> 4)]);
                    sb.append(this.b[(f15708f & i6) << 2]);
                    str = ContainerUtils.KEY_VALUE_DELIMITER;
                } else {
                    int i7 = i5 + 1;
                    int i8 = bArr[i5] & 255;
                    sb.append(this.b[i4 >>> 2]);
                    sb.append(this.b[((i4 & f15707e) << 4) | (i6 >>> 4)]);
                    sb.append(this.b[((i6 & f15708f) << 2) | (i8 >>> 6)]);
                    sb.append(this.b[f15709g & i8]);
                    i2 = i7;
                }
            }
            sb.append(str);
            break;
        }
        return sb.toString();
    }
}
