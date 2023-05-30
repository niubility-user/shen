package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.opengl.GLU;
import android.opengl.Matrix;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class ta {
    private static final String[] a = {"\u5317", "\u4e1c\u5317", "\u4e1c", "\u4e1c\u5357", "\u5357", "\u897f\u5357", "\u897f", "\u897f\u5317"};

    public static double a(double d) {
        return (d / 180.0d) * 3.141592653589793d;
    }

    public static double a(double d, double d2, double d3, double d4) {
        double asin = Math.asin((d3 - d) / c(d, d2, d3, d4));
        if (d4 - d2 < 0.0d) {
            asin = 3.141592653589793d - asin;
        }
        return (asin / 3.141592653589793d) * 180.0d;
    }

    public static float a(c6 c6Var, c6 c6Var2) {
        double b = b(c6Var, c6Var2);
        double d = c6Var2.a - c6Var.a;
        Double.isNaN(d);
        double asin = Math.asin(d / b);
        if (c6Var2.f16359c - c6Var.f16359c < 0.0f) {
            asin = 3.141592653589793d - asin;
        }
        return (float) ((asin / 3.141592653589793d) * 180.0d);
    }

    public static int a(float f2) {
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            int i4 = 2 << i2;
            if (i4 >= ((int) Math.ceil(f2))) {
                return i4;
            }
            i2 = i3;
        }
    }

    public static Bitmap a(GL10 gl10, int i2, int i3, int i4, int i5) {
        return a(gl10, i2, i3, i4, i5, i4, i5);
    }

    public static Bitmap a(GL10 gl10, int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i3 + i5;
        int[] iArr = new int[i4 * i8];
        int[] iArr2 = new int[i6 * i7];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        long currentTimeMillis = System.currentTimeMillis();
        gl10.glReadPixels(i2, 0, i4, i8, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, wrap);
        ma.c("readPixels \u4f7f\u7528\u7684\u65f6\u95f4:" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        long currentTimeMillis2 = System.currentTimeMillis();
        float f2 = ((float) i4) / ((float) i6);
        float f3 = ((float) i5) / ((float) i7);
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            for (int i11 = 0; i11 < i6; i11++) {
                double d = i11 * f2;
                Double.isNaN(d);
                int ceil = (int) Math.ceil(d - 0.5d);
                double d2 = i9 * f3;
                Double.isNaN(d2);
                int i12 = iArr[(((int) Math.ceil(d2 - 0.5d)) * i4) + ceil];
                iArr2[(((i7 - i10) - 1) * i6) + i11] = (i12 & (-16711936)) | ((i12 << 16) & 16711680) | ((i12 >> 16) & 255);
            }
            i9++;
            i10++;
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr2, i6, i7, Bitmap.Config.RGB_565);
        ma.c("buffer \u8f6c\u6210\u4f4d\u56fe\u4f7f\u7528\u7684\u65f6\u95f4:" + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
        return createBitmap;
    }

    public static PointF a(c6 c6Var, double d, double d2) {
        double d3 = c6Var.a;
        Double.isNaN(d3);
        double d4 = -c6Var.f16359c;
        Double.isNaN(d4);
        return new PointF((float) (d3 + d), (float) (d4 + d2));
    }

    public static b6 a(float f2, float f3, float[] fArr, float[] fArr2, int[] iArr) {
        float[] a2 = a(f2, f3, 0.0f, fArr, fArr2, iArr);
        float[] a3 = a(f2, f3, 1.0f, fArr, fArr2, iArr);
        for (int i2 = 0; i2 < 3; i2++) {
            a2[i2] = a2[i2] / a2[3];
            a3[i2] = a3[i2] / a3[3];
        }
        return new b6(a3[0] - a2[0], a3[1] - a2[1], a3[2] - a2[2]);
    }

    private static b6 a(float[] fArr) {
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        float[] fArr2 = {fArr[3] - f2, fArr[4] - f3, fArr[5] - f4};
        float[] fArr3 = {fArr[6] - f2, fArr[7] - f3, fArr[8] - f4};
        float[] fArr4 = {(fArr2[1] * fArr3[2]) - (fArr2[2] * fArr3[1]), (fArr2[2] * fArr3[0]) - (fArr2[0] * fArr3[2]), (fArr2[0] * fArr3[1]) - (fArr2[1] * fArr3[0])};
        return new b6(fArr4[0], fArr4[1], fArr4[2]);
    }

    public static c6 a(float f2, float f3, float f4, float[] fArr, float f5) {
        float[] fArr2 = new float[16];
        Matrix.invertM(fArr2, 0, fArr, 0);
        c6 a2 = new c6(f2, f3, f5).a(fArr2);
        float f6 = a2.b;
        float f7 = f6 != 0.0f ? f4 / f6 : 1.0f;
        return new c6(a2.a * f7, f4, a2.f16359c * f7);
    }

    private static boolean a(float[] fArr, float[] fArr2) {
        return new a6(fArr2).a(fArr);
    }

    public static float[] a(float f2, float f3, float f4, float[] fArr, float[] fArr2, int[] iArr) {
        float[] fArr3 = new float[4];
        if (GLU.gluUnProject(f2, iArr[3] - f3, f4, fArr, 0, fArr2, 0, iArr, 0, fArr3, 0) == 1) {
            return fArr3;
        }
        throw new RuntimeException("unProject fail");
    }

    public static float[] a(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        float[] fArr5 = new float[3];
        float f2 = fArr[0];
        float f3 = fArr[1];
        float f4 = fArr[2];
        float f5 = fArr2[0];
        float f6 = fArr2[1];
        float f7 = fArr2[2];
        float f8 = fArr3[0];
        float f9 = fArr3[1];
        float f10 = fArr3[2];
        float f11 = fArr4[0];
        float f12 = fArr4[1];
        float f13 = fArr4[2];
        float f14 = (f8 * f2) + (f9 * f3) + (f10 * f4);
        if (f14 == 0.0f) {
            return null;
        }
        float f15 = ((((f5 - f11) * f2) + ((f6 - f12) * f3)) + ((f7 - f13) * f4)) / f14;
        fArr5[0] = f11 + (f8 * f15);
        fArr5[1] = f12 + (f9 * f15);
        fArr5[2] = f13 + (f10 * f15);
        return fArr5;
    }

    public static double b(double d) {
        double abs = Math.abs(d % 360.0d);
        return abs > 270.0d ? 360.0d - abs : abs;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        if (r10 <= 0.0d) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static double b(double d, double d2, double d3, double d4) {
        double abs;
        double d5 = d3 - d;
        double d6 = d4 - d2;
        double atan = Math.atan(d6 / d5);
        double d7 = 1.5707963267948966d;
        int i2 = (d5 > 0.0d ? 1 : (d5 == 0.0d ? 0 : -1));
        if (i2 <= 0 || d6 <= 0.0d) {
            if (i2 < 0 || d6 > 0.0d) {
                d7 = 4.71238898038469d;
                if (d5 <= 0.0d) {
                }
            }
            abs = d7 + Math.abs(atan);
            return (abs * 180.0d) / 3.141592653589793d;
        }
        abs = d7 - Math.abs(atan);
        return (abs * 180.0d) / 3.141592653589793d;
    }

    public static double b(c6 c6Var, c6 c6Var2) {
        return Math.sqrt(Math.pow(c6Var.a - c6Var2.a, 2.0d) + Math.pow(c6Var.f16359c - c6Var2.f16359c, 2.0d));
    }

    public static String b(float f2) {
        if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        int i2 = 0;
        while (true) {
            String[] strArr = a;
            if (i2 >= strArr.length) {
                return strArr[0];
            }
            float f3 = (i2 * 45) - 22.0f;
            if (f2 < 45.0f + f3 && f2 >= f3) {
                return strArr[i2];
            }
            i2++;
        }
    }

    public static IntBuffer b(GL10 gl10, int i2, int i3, int i4, int i5) {
        IntBuffer wrap = IntBuffer.wrap(new int[i4 * i5]);
        wrap.position(0);
        gl10.glReadPixels(i2, i3, i4, i5, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, wrap);
        return wrap;
    }

    public static boolean b(float f2, float f3, float[] fArr, float[] fArr2, int[] iArr) {
        b6 a2 = a(fArr);
        float[] fArr3 = new float[16];
        Matrix.setIdentityM(fArr3, 0);
        float[] a3 = a(f2, f3, 0.0f, fArr3, fArr2, iArr);
        float[] a4 = a(f2, f3, 1.0f, fArr3, fArr2, iArr);
        for (int i2 = 0; i2 < 3; i2++) {
            a3[i2] = a3[i2] / a3[3];
            a4[i2] = a4[i2] / a4[3];
        }
        float[] a5 = a(a2.a(), new float[]{fArr[0], fArr[1], fArr[2]}, new b6(a4[0] - a3[0], a4[1] - a3[1], a4[2] - a3[2]).a(), new float[]{a3[0], a3[1], a3[2]});
        if (a5 == null) {
            return false;
        }
        return a(a5, fArr);
    }

    public static double c(double d) {
        return (d / 3.141592653589793d) * 180.0d;
    }

    public static double c(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d - d3, 2.0d) + Math.pow(d2 - d4, 2.0d));
    }

    public static c6 d(double d, double d2, double d3, double d4) {
        return new c6((float) (d - d3), 0.0f, -((float) (d2 - d4)));
    }
}
