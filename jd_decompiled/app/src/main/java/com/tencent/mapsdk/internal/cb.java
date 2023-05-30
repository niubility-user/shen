package com.tencent.mapsdk.internal;

import android.opengl.Matrix;
import java.lang.reflect.Array;

/* loaded from: classes9.dex */
public class cb {
    private static boolean a(float[][] fArr, float[] fArr2) {
        int length = fArr2.length / 3;
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i6 + 1;
                if ((fArr[i2][0] * fArr2[i4]) + (fArr[i2][1] * fArr2[i5]) + (fArr[i2][2] * fArr2[i6]) + fArr[i2][3] > 0.0f) {
                    break;
                }
                i3++;
                i4 = i7;
            }
            if (i3 == length) {
                return false;
            }
        }
        return true;
    }

    public static float[][] a(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[16];
        Matrix.multiplyMM(fArr3, 0, fArr2, 0, fArr, 0);
        float[][] fArr4 = (float[][]) Array.newInstance(float.class, 6, 4);
        fArr4[0][0] = fArr3[3] - fArr3[0];
        fArr4[0][1] = fArr3[7] - fArr3[4];
        fArr4[0][2] = fArr3[11] - fArr3[8];
        fArr4[0][3] = fArr3[15] - fArr3[12];
        double sqrt = Math.sqrt((fArr4[0][0] * fArr4[0][0]) + (fArr4[0][1] * fArr4[0][1]) + (fArr4[0][2] * fArr4[0][2]));
        float[] fArr5 = fArr4[0];
        double d = fArr5[0];
        Double.isNaN(d);
        fArr5[0] = (float) (d / sqrt);
        float[] fArr6 = fArr4[0];
        double d2 = fArr6[1];
        Double.isNaN(d2);
        fArr6[1] = (float) (d2 / sqrt);
        float[] fArr7 = fArr4[0];
        double d3 = fArr7[2];
        Double.isNaN(d3);
        fArr7[2] = (float) (d3 / sqrt);
        float[] fArr8 = fArr4[0];
        double d4 = fArr8[3];
        Double.isNaN(d4);
        fArr8[3] = (float) (d4 / sqrt);
        fArr4[1][0] = fArr3[3] + fArr3[0];
        fArr4[1][1] = fArr3[7] + fArr3[4];
        fArr4[1][2] = fArr3[11] + fArr3[8];
        fArr4[1][3] = fArr3[15] + fArr3[12];
        double sqrt2 = Math.sqrt((fArr4[1][0] * fArr4[1][0]) + (fArr4[1][1] * fArr4[1][1]) + (fArr4[1][2] * fArr4[1][2]));
        float[] fArr9 = fArr4[1];
        double d5 = fArr9[0];
        Double.isNaN(d5);
        fArr9[0] = (float) (d5 / sqrt2);
        float[] fArr10 = fArr4[1];
        double d6 = fArr10[1];
        Double.isNaN(d6);
        fArr10[1] = (float) (d6 / sqrt2);
        float[] fArr11 = fArr4[1];
        double d7 = fArr11[2];
        Double.isNaN(d7);
        fArr11[2] = (float) (d7 / sqrt2);
        float[] fArr12 = fArr4[1];
        double d8 = fArr12[3];
        Double.isNaN(d8);
        fArr12[3] = (float) (d8 / sqrt2);
        fArr4[2][0] = fArr3[3] + fArr3[1];
        fArr4[2][1] = fArr3[7] + fArr3[5];
        fArr4[2][2] = fArr3[11] + fArr3[9];
        fArr4[2][3] = fArr3[15] + fArr3[13];
        double sqrt3 = Math.sqrt((fArr4[2][0] * fArr4[2][0]) + (fArr4[2][1] * fArr4[2][1]) + (fArr4[2][2] * fArr4[2][2]));
        float[] fArr13 = fArr4[2];
        double d9 = fArr13[0];
        Double.isNaN(d9);
        fArr13[0] = (float) (d9 / sqrt3);
        float[] fArr14 = fArr4[2];
        double d10 = fArr14[1];
        Double.isNaN(d10);
        fArr14[1] = (float) (d10 / sqrt3);
        float[] fArr15 = fArr4[2];
        double d11 = fArr15[2];
        Double.isNaN(d11);
        fArr15[2] = (float) (d11 / sqrt3);
        float[] fArr16 = fArr4[2];
        double d12 = fArr16[3];
        Double.isNaN(d12);
        fArr16[3] = (float) (d12 / sqrt3);
        fArr4[3][0] = fArr3[3] - fArr3[1];
        fArr4[3][1] = fArr3[7] - fArr3[5];
        fArr4[3][2] = fArr3[11] - fArr3[9];
        fArr4[3][3] = fArr3[15] - fArr3[13];
        double sqrt4 = Math.sqrt((fArr4[3][0] * fArr4[3][0]) + (fArr4[3][1] * fArr4[3][1]) + (fArr4[3][2] * fArr4[3][2]));
        float[] fArr17 = fArr4[3];
        double d13 = fArr17[0];
        Double.isNaN(d13);
        fArr17[0] = (float) (d13 / sqrt4);
        float[] fArr18 = fArr4[3];
        double d14 = fArr18[1];
        Double.isNaN(d14);
        fArr18[1] = (float) (d14 / sqrt4);
        float[] fArr19 = fArr4[3];
        double d15 = fArr19[2];
        Double.isNaN(d15);
        fArr19[2] = (float) (d15 / sqrt4);
        float[] fArr20 = fArr4[3];
        double d16 = fArr20[3];
        Double.isNaN(d16);
        fArr20[3] = (float) (d16 / sqrt4);
        fArr4[4][0] = fArr3[3] - fArr3[2];
        fArr4[4][1] = fArr3[7] - fArr3[6];
        fArr4[4][2] = fArr3[11] - fArr3[10];
        fArr4[4][3] = fArr3[15] - fArr3[14];
        double sqrt5 = Math.sqrt((fArr4[4][0] * fArr4[4][0]) + (fArr4[4][1] * fArr4[4][1]) + (fArr4[4][2] * fArr4[4][2]));
        float[] fArr21 = fArr4[4];
        double d17 = fArr21[0];
        Double.isNaN(d17);
        fArr21[0] = (float) (d17 / sqrt5);
        float[] fArr22 = fArr4[4];
        double d18 = fArr22[1];
        Double.isNaN(d18);
        fArr22[1] = (float) (d18 / sqrt5);
        float[] fArr23 = fArr4[4];
        double d19 = fArr23[2];
        Double.isNaN(d19);
        fArr23[2] = (float) (d19 / sqrt5);
        float[] fArr24 = fArr4[4];
        double d20 = fArr24[3];
        Double.isNaN(d20);
        fArr24[3] = (float) (d20 / sqrt5);
        fArr4[5][0] = fArr3[3] + fArr3[2];
        fArr4[5][1] = fArr3[7] + fArr3[6];
        fArr4[5][2] = fArr3[11] + fArr3[10];
        fArr4[5][3] = fArr3[15] + fArr3[14];
        double sqrt6 = Math.sqrt((fArr4[5][0] * fArr4[5][0]) + (fArr4[5][1] * fArr4[5][1]) + (fArr4[5][2] * fArr4[5][2]));
        float[] fArr25 = fArr4[5];
        double d21 = fArr25[0];
        Double.isNaN(d21);
        fArr25[0] = (float) (d21 / sqrt6);
        float[] fArr26 = fArr4[5];
        double d22 = fArr26[1];
        Double.isNaN(d22);
        fArr26[1] = (float) (d22 / sqrt6);
        float[] fArr27 = fArr4[5];
        double d23 = fArr27[2];
        Double.isNaN(d23);
        fArr27[2] = (float) (d23 / sqrt6);
        float[] fArr28 = fArr4[5];
        double d24 = fArr28[3];
        Double.isNaN(d24);
        fArr28[3] = (float) (d24 / sqrt6);
        return fArr4;
    }

    public static boolean b(float[][] fArr, float[] fArr2) {
        return a(fArr, fArr2);
    }
}
