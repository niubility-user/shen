package com.jingdong.jdreact.plugin.banner;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import androidx.core.view.MotionEventCompat;
import java.lang.reflect.Array;

/* loaded from: classes13.dex */
public class FastBlur {
    public static Bitmap doBlur(Bitmap bitmap, int i2, boolean z) {
        Bitmap createBitmap;
        int[] iArr;
        int i3 = i2;
        if (z) {
            createBitmap = bitmap;
        } else {
            Matrix matrix = new Matrix();
            matrix.postScale(0.3f, 0.3f);
            if (bitmap == null || bitmap.isRecycled()) {
                return null;
            }
            createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        if (i3 < 1) {
            return null;
        }
        int width = createBitmap.getWidth();
        int height = createBitmap.getHeight();
        int i4 = width * height;
        int[] iArr2 = new int[i4];
        createBitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
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
            Bitmap bitmap2 = createBitmap;
            int i16 = height;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = -i3;
            int i26 = 0;
            while (i25 <= i3) {
                int i27 = i6;
                int[] iArr9 = iArr6;
                int i28 = iArr2[i14 + Math.min(i5, Math.max(i25, 0))];
                int[] iArr10 = iArr8[i25 + i3];
                iArr10[0] = (i28 & 16711680) >> 16;
                iArr10[1] = (i28 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i28 & 255;
                int abs = i12 - Math.abs(i25);
                i26 += iArr10[0] * abs;
                i17 += iArr10[1] * abs;
                i18 += iArr10[2] * abs;
                if (i25 > 0) {
                    i22 += iArr10[0];
                    i23 += iArr10[1];
                    i24 += iArr10[2];
                } else {
                    i19 += iArr10[0];
                    i20 += iArr10[1];
                    i21 += iArr10[2];
                }
                i25++;
                i6 = i27;
                iArr6 = iArr9;
            }
            int i29 = i6;
            int[] iArr11 = iArr6;
            int i30 = i3;
            int i31 = i26;
            int i32 = 0;
            while (i32 < width) {
                iArr3[i14] = iArr7[i31];
                iArr4[i14] = iArr7[i17];
                iArr5[i14] = iArr7[i18];
                int i33 = i31 - i19;
                int i34 = i17 - i20;
                int i35 = i18 - i21;
                int[] iArr12 = iArr8[((i30 - i3) + i7) % i7];
                int i36 = i19 - iArr12[0];
                int i37 = i20 - iArr12[1];
                int i38 = i21 - iArr12[2];
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
                int i40 = i22 + iArr12[0];
                int i41 = i23 + iArr12[1];
                int i42 = i24 + iArr12[2];
                i31 = i33 + i40;
                i17 = i34 + i41;
                i18 = i35 + i42;
                i30 = (i30 + 1) % i7;
                int[] iArr13 = iArr8[i30 % i7];
                i19 = i36 + iArr13[0];
                i20 = i37 + iArr13[1];
                i21 = i38 + iArr13[2];
                i22 = i40 - iArr13[0];
                i23 = i41 - iArr13[1];
                i24 = i42 - iArr13[2];
                i14++;
                i32++;
                iArr7 = iArr;
            }
            i15 += width;
            i13++;
            createBitmap = bitmap2;
            height = i16;
            i6 = i29;
            iArr6 = iArr11;
        }
        Bitmap bitmap3 = createBitmap;
        int i43 = i6;
        int[] iArr14 = iArr6;
        int i44 = height;
        int[] iArr15 = iArr7;
        int i45 = 0;
        while (i45 < width) {
            int i46 = -i3;
            int i47 = i7;
            int[] iArr16 = iArr2;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = i46;
            int i56 = i46 * width;
            int i57 = 0;
            int i58 = 0;
            while (i55 <= i3) {
                int i59 = width;
                int max = Math.max(0, i56) + i45;
                int[] iArr17 = iArr8[i55 + i3];
                iArr17[0] = iArr3[max];
                iArr17[1] = iArr4[max];
                iArr17[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i55);
                i57 += iArr3[max] * abs2;
                i58 += iArr4[max] * abs2;
                i48 += iArr5[max] * abs2;
                if (i55 > 0) {
                    i52 += iArr17[0];
                    i53 += iArr17[1];
                    i54 += iArr17[2];
                } else {
                    i49 += iArr17[0];
                    i50 += iArr17[1];
                    i51 += iArr17[2];
                }
                int i60 = i43;
                if (i55 < i60) {
                    i56 += i59;
                }
                i55++;
                i43 = i60;
                width = i59;
            }
            int i61 = width;
            int i62 = i43;
            int i63 = i3;
            int i64 = i45;
            int i65 = i44;
            int i66 = 0;
            while (i66 < i65) {
                iArr16[i64] = (iArr16[i64] & (-16777216)) | (iArr15[i57] << 16) | (iArr15[i58] << 8) | iArr15[i48];
                int i67 = i57 - i49;
                int i68 = i58 - i50;
                int i69 = i48 - i51;
                int[] iArr18 = iArr8[((i63 - i3) + i47) % i47];
                int i70 = i49 - iArr18[0];
                int i71 = i50 - iArr18[1];
                int i72 = i51 - iArr18[2];
                if (i45 == 0) {
                    iArr14[i66] = Math.min(i66 + i12, i62) * i61;
                }
                int i73 = iArr14[i66] + i45;
                iArr18[0] = iArr3[i73];
                iArr18[1] = iArr4[i73];
                iArr18[2] = iArr5[i73];
                int i74 = i52 + iArr18[0];
                int i75 = i53 + iArr18[1];
                int i76 = i54 + iArr18[2];
                i57 = i67 + i74;
                i58 = i68 + i75;
                i48 = i69 + i76;
                i63 = (i63 + 1) % i47;
                int[] iArr19 = iArr8[i63];
                i49 = i70 + iArr19[0];
                i50 = i71 + iArr19[1];
                i51 = i72 + iArr19[2];
                i52 = i74 - iArr19[0];
                i53 = i75 - iArr19[1];
                i54 = i76 - iArr19[2];
                i64 += i61;
                i66++;
                i3 = i2;
            }
            i45++;
            i3 = i2;
            i43 = i62;
            i44 = i65;
            i7 = i47;
            iArr2 = iArr16;
            width = i61;
        }
        int i77 = width;
        bitmap3.setPixels(iArr2, 0, i77, 0, 0, i77, i44);
        return bitmap3;
    }
}
