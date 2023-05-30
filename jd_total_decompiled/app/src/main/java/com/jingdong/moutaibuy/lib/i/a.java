package com.jingdong.moutaibuy.lib.i;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.util.Base64;
import androidx.core.view.MotionEventCompat;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: classes16.dex */
public class a {
    public static Camera.Size a(List<Camera.Size> list, double d) {
        Camera.Size size = null;
        if (list != null && list.size() > 0) {
            double d2 = 0.1d;
            do {
                for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                    Camera.Size size3 = list.get(size2);
                    double d3 = size3.width;
                    double d4 = size3.height;
                    Double.isNaN(d3);
                    Double.isNaN(d4);
                    if (Math.abs((d3 / d4) - d) <= d2) {
                        size = size3;
                    }
                }
                d2 += 0.05d;
            } while (size == null);
        }
        return size;
    }

    public static int b(int i2, int i3) {
        int i4 = 1;
        while (true) {
            if (i2 <= 2500 && i3 <= 2500) {
                break;
            }
            i2 /= 2;
            i3 /= 2;
            i4 *= 2;
        }
        if (i4 < 1) {
            return 1;
        }
        return i4;
    }

    public static String c(com.jingdong.moutaibuy.lib.c.b bVar) {
        String str = "";
        try {
            Bitmap createBitmap = Bitmap.createBitmap(d(bVar.a, bVar.b, bVar.f14585c), bVar.b, bVar.f14585c, Bitmap.Config.RGB_565);
            float b = 1.0f / b(bVar.b, bVar.f14585c);
            Matrix matrix = new Matrix();
            matrix.setScale(b, b);
            matrix.postRotate(bVar.d);
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap2.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            str = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            byteArrayOutputStream.close();
            createBitmap.recycle();
            createBitmap2.recycle();
            return str;
        } catch (IOException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private static int[] d(byte[] bArr, int i2, int i3) {
        int i4 = i2 * i3;
        int[] iArr = new int[i4];
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = 0;
            while (i7 < i2) {
                int i8 = (bArr[i4 + 1] & 255) - 128;
                int i9 = (bArr[i4] & 255) - 128;
                int i10 = (bArr[i5] & 255) * R2.attr.layout_constraintBaseline_creator;
                int i11 = (i9 * R2.attr.radioButtonStyle) + i10;
                int i12 = (i10 - (i9 * R2.attr.et_clear_selector)) - (i8 * 400);
                int i13 = i10 + (i8 * R2.attr.themeName);
                if (i11 < 0) {
                    i11 = 0;
                } else if (i11 > 262143) {
                    i11 = 262143;
                }
                if (i12 < 0) {
                    i12 = 0;
                } else if (i12 > 262143) {
                    i12 = 262143;
                }
                if (i13 < 0) {
                    i13 = 0;
                } else if (i13 > 262143) {
                    i13 = 262143;
                }
                iArr[i5] = ((i13 >> 10) & 255) | ((i11 << 6) & 16711680) | ((i12 >> 2) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
                int i14 = i5 + 1;
                if ((i5 & 1) == 1) {
                    i4 += 2;
                }
                i7++;
                i5 = i14;
            }
            if ((i6 & 1) == 0) {
                i4 -= i2;
            }
        }
        return iArr;
    }
}
