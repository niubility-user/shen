package com.jingdong.common.widget.custom.livewidget.util;

import android.graphics.Bitmap;
import androidx.core.view.MotionEventCompat;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Array;

/* loaded from: classes12.dex */
class FastBlur {
    FastBlur() {
    }

    private static Bitmap fastBlur(Bitmap bitmap, float f2, int i2) {
        int[] iArr;
        int i3 = i2;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * f2), Math.round(bitmap.getHeight() * f2), false);
        Bitmap copy = createScaledBitmap.copy(createScaledBitmap.getConfig(), true);
        if (i3 < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i4 = width * height;
        int[] iArr2 = new int[i4];
        String str = width + LangUtils.SINGLE_SPACE + height + LangUtils.SINGLE_SPACE + i4;
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = i3 + i3 + 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[Math.max(width, height)];
        int i8 = (i7 + 1) >> 1;
        int i9 = i8 * i8;
        int i10 = i9 * 256;
        int[] iArr7 = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr7[i11] = i11 / i9;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, i7, 3);
        int i12 = i3 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            int i16 = i4;
            int i17 = height;
            int i18 = -i3;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            int i26 = 0;
            int i27 = 0;
            while (i18 <= i3) {
                int i28 = i6;
                int[] iArr9 = iArr6;
                int i29 = iArr2[i14 + Math.min(i5, Math.max(i18, 0))];
                int[] iArr10 = iArr8[i18 + i3];
                iArr10[0] = (i29 & 16711680) >> 16;
                iArr10[1] = (i29 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i29 & 255;
                int abs = i12 - Math.abs(i18);
                i19 += iArr10[0] * abs;
                i20 += iArr10[1] * abs;
                i21 += iArr10[2] * abs;
                if (i18 > 0) {
                    i25 += iArr10[0];
                    i26 += iArr10[1];
                    i27 += iArr10[2];
                } else {
                    i22 += iArr10[0];
                    i23 += iArr10[1];
                    i24 += iArr10[2];
                }
                i18++;
                i6 = i28;
                iArr6 = iArr9;
            }
            int i30 = i6;
            int[] iArr11 = iArr6;
            int i31 = i3;
            int i32 = 0;
            while (i32 < width) {
                iArr3[i14] = iArr7[i19];
                iArr4[i14] = iArr7[i20];
                iArr5[i14] = iArr7[i21];
                int i33 = i19 - i22;
                int i34 = i20 - i23;
                int i35 = i21 - i24;
                int[] iArr12 = iArr8[((i31 - i3) + i7) % i7];
                int i36 = i22 - iArr12[0];
                int i37 = i23 - iArr12[1];
                int i38 = i24 - iArr12[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr11[i32] = Math.min(i32 + i3 + 1, i5);
                } else {
                    iArr = iArr7;
                }
                int i39 = iArr2[i15 + iArr11[i32]];
                iArr12[0] = (i39 & 16711680) >> 16;
                iArr12[1] = (i39 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr12[2] = i39 & 255;
                int i40 = i25 + iArr12[0];
                int i41 = i26 + iArr12[1];
                int i42 = i27 + iArr12[2];
                i19 = i33 + i40;
                i20 = i34 + i41;
                i21 = i35 + i42;
                i31 = (i31 + 1) % i7;
                int[] iArr13 = iArr8[i31 % i7];
                i22 = i36 + iArr13[0];
                i23 = i37 + iArr13[1];
                i24 = i38 + iArr13[2];
                i25 = i40 - iArr13[0];
                i26 = i41 - iArr13[1];
                i27 = i42 - iArr13[2];
                i14++;
                i32++;
                iArr7 = iArr;
            }
            i15 += width;
            i13++;
            i4 = i16;
            height = i17;
            i6 = i30;
            iArr6 = iArr11;
        }
        int[] iArr14 = iArr7;
        int i43 = i6;
        int[] iArr15 = iArr6;
        int i44 = height;
        int i45 = i4;
        int i46 = 0;
        while (i46 < width) {
            int i47 = -i3;
            int i48 = i7;
            int[] iArr16 = iArr2;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = i47;
            int i57 = i47 * width;
            int i58 = 0;
            int i59 = 0;
            while (i56 <= i3) {
                int i60 = width;
                int max = Math.max(0, i57) + i46;
                int[] iArr17 = iArr8[i56 + i3];
                iArr17[0] = iArr3[max];
                iArr17[1] = iArr4[max];
                iArr17[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i56);
                i58 += iArr3[max] * abs2;
                i59 += iArr4[max] * abs2;
                i49 += iArr5[max] * abs2;
                if (i56 > 0) {
                    i53 += iArr17[0];
                    i54 += iArr17[1];
                    i55 += iArr17[2];
                } else {
                    i50 += iArr17[0];
                    i51 += iArr17[1];
                    i52 += iArr17[2];
                }
                int i61 = i43;
                if (i56 < i61) {
                    i57 += i60;
                }
                i56++;
                i43 = i61;
                width = i60;
            }
            int i62 = width;
            int i63 = i43;
            int i64 = i3;
            int i65 = i46;
            int i66 = i49;
            int i67 = i44;
            int i68 = i59;
            int i69 = i58;
            int i70 = 0;
            while (i70 < i67) {
                iArr16[i65] = (iArr16[i65] & (-16777216)) | (iArr14[i69] << 16) | (iArr14[i68] << 8) | iArr14[i66];
                int i71 = i69 - i50;
                int i72 = i68 - i51;
                int i73 = i66 - i52;
                int[] iArr18 = iArr8[((i64 - i3) + i48) % i48];
                int i74 = i50 - iArr18[0];
                int i75 = i51 - iArr18[1];
                int i76 = i52 - iArr18[2];
                if (i46 == 0) {
                    iArr15[i70] = Math.min(i70 + i12, i63) * i62;
                }
                int i77 = iArr15[i70] + i46;
                iArr18[0] = iArr3[i77];
                iArr18[1] = iArr4[i77];
                iArr18[2] = iArr5[i77];
                int i78 = i53 + iArr18[0];
                int i79 = i54 + iArr18[1];
                int i80 = i55 + iArr18[2];
                i69 = i71 + i78;
                i68 = i72 + i79;
                i66 = i73 + i80;
                i64 = (i64 + 1) % i48;
                int[] iArr19 = iArr8[i64];
                i50 = i74 + iArr19[0];
                i51 = i75 + iArr19[1];
                i52 = i76 + iArr19[2];
                i53 = i78 - iArr19[0];
                i54 = i79 - iArr19[1];
                i55 = i80 - iArr19[2];
                i65 += i62;
                i70++;
                i3 = i2;
            }
            i46++;
            i3 = i2;
            i43 = i63;
            i44 = i67;
            i7 = i48;
            iArr2 = iArr16;
            width = i62;
        }
        int i81 = width;
        int[] iArr20 = iArr2;
        int i82 = i44;
        String str2 = i81 + LangUtils.SINGLE_SPACE + i82 + LangUtils.SINGLE_SPACE + i45;
        copy.setPixels(iArr20, 0, i81, 0, 0, i81, i82);
        return copy;
    }
}
