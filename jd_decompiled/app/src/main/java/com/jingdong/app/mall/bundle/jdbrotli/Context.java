package com.jingdong.app.mall.bundle.jdbrotli;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes2.dex */
public class Context {
    static final int[] LOOKUP;
    private static final String UTF_MAP = "         !!  !                  \"#$##%#$&'##(#)#++++++++++((&*'##,---,---,-----,-----,-----&#'###.///.///./////./////./////&#'# ";
    private static final String UTF_RLE = "A/*  ':  & : $  \u0081 @";

    static {
        int[] iArr = new int[2048];
        LOOKUP = iArr;
        unpackLookupTable(iArr, UTF_MAP, UTF_RLE);
    }

    private static void unpackLookupTable(int[] iArr, String str, String str2) {
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[i2] = i2 & 63;
            iArr[i2 + 512] = i2 >> 2;
            iArr[i2 + R2.attr.shadow_left] = (i2 >> 6) + 2;
        }
        for (int i3 = 0; i3 < 128; i3++) {
            iArr[i3 + 1024] = (str.charAt(i3) - ' ') * 4;
        }
        for (int i4 = 0; i4 < 64; i4++) {
            int i5 = i4 & 1;
            iArr[i4 + R2.attr.jdpay_width] = i5;
            iArr[i4 + R2.attr.layout_constraintLeft_creator] = i5 + 2;
        }
        int i6 = R2.attr.lineSpacing;
        for (int i7 = 0; i7 < 19; i7++) {
            int i8 = i7 & 3;
            int charAt = str2.charAt(i7) - ' ';
            int i9 = 0;
            while (i9 < charAt) {
                iArr[i6] = i8;
                i9++;
                i6++;
            }
        }
        for (int i10 = 0; i10 < 16; i10++) {
            iArr[i10 + R2.attr.shadow_left] = 1;
            iArr[i10 + R2.attr.textColorError] = 6;
        }
        iArr[1792] = 0;
        iArr[2047] = 7;
        for (int i11 = 0; i11 < 256; i11++) {
            iArr[i11 + R2.attr.placeholderImageScaleType] = iArr[i11 + R2.attr.shadow_left] << 3;
        }
    }
}
