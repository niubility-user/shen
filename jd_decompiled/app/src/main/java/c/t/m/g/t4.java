package c.t.m.g;

import android.hardware.SensorManager;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes.dex */
public class t4 {

    /* renamed from: l  reason: collision with root package name */
    public static double[] f692l = new double[7];
    public c5 a;
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public float[] f693c = new float[9];
    public int d = 0;

    /* renamed from: e  reason: collision with root package name */
    public boolean f694e = false;

    /* renamed from: f  reason: collision with root package name */
    public volatile long f695f = 0;

    /* renamed from: g  reason: collision with root package name */
    public double[][] f696g = null;

    /* renamed from: h  reason: collision with root package name */
    public double[][] f697h = null;

    /* renamed from: i  reason: collision with root package name */
    public float[] f698i = {-10.0f, -10.0f, -10.0f};

    /* renamed from: j  reason: collision with root package name */
    public float[] f699j = {1.0f, 1.0f, 1.0f};

    /* renamed from: k  reason: collision with root package name */
    public float[] f700k = new float[3];

    public void a() {
        int i2 = 0;
        this.d = 0;
        this.f694e = false;
        this.f695f = 0L;
        while (true) {
            double[][] dArr = this.f696g;
            if (i2 >= dArr.length) {
                Arrays.fill(this.f698i, -10.0f);
                this.a.k();
                return;
            }
            Arrays.fill(dArr[i2], 0.0d);
            i2++;
        }
    }

    public void b(int i2, int i3, float f2, double[][] dArr, double[][] dArr2, double[][] dArr3, double[] dArr4) {
        if (this.b) {
            return;
        }
        k4.a = i2;
        k4.b = (i2 * (i2 - 1)) / 2;
        k4.f522c = i3;
        k4.d = f2;
        k4.f523e = dArr;
        k4.f524f = dArr2;
        k4.f525g = dArr3;
        k4.f526h = dArr4;
        this.f696g = (double[][]) Array.newInstance(double.class, 10, 128);
        this.f697h = (double[][]) Array.newInstance(double.class, 10, 128);
        this.a = new c5();
        this.b = true;
    }

    public void c(long j2, float f2) {
        this.a.a(j2, f2);
    }

    public void d(long j2, float[] fArr, float[] fArr2) {
        double d;
        e(this.f698i, fArr);
        Arrays.fill(this.f693c, 0.0f);
        SensorManager.getRotationMatrix(this.f693c, null, this.f698i, this.f699j);
        double[][] dArr = this.f696g;
        double[] dArr2 = dArr[0];
        int i2 = this.d;
        dArr2[i2] = fArr[0];
        dArr[1][i2] = fArr[1];
        dArr[2][i2] = fArr[2];
        dArr[3][i2] = Math.sqrt((fArr[0] * fArr[0]) + (fArr[1] * fArr[1]) + (fArr[2] * fArr[2]));
        this.f696g[4][this.d] = Math.sqrt((fArr2[0] * fArr2[0]) + (fArr2[1] * fArr2[1]) + (fArr2[2] * fArr2[2]));
        if (Math.abs(this.f696g[3][this.d] - 0.0d) < 1.0E-5d) {
            d = 2.0d;
        } else {
            double d2 = fArr[2];
            double d3 = this.f696g[3][this.d];
            Double.isNaN(d2);
            d = d2 / d3;
        }
        this.f696g[5][this.d] = Math.abs(d) <= 1.0d ? Math.acos(d) : 0.0d;
        f(this.f700k, this.f693c, fArr);
        double[] dArr3 = this.f696g[6];
        int i3 = this.d;
        float[] fArr3 = this.f700k;
        dArr3[i3] = Math.sqrt((fArr3[0] * fArr3[0]) + (fArr3[1] * fArr3[1]));
        double[] dArr4 = this.f696g[7];
        int i4 = this.d;
        float[] fArr4 = this.f700k;
        dArr4[i4] = fArr4[2];
        f(fArr4, this.f693c, fArr2);
        double[] dArr5 = this.f696g[8];
        int i5 = this.d;
        float[] fArr5 = this.f700k;
        dArr5[i5] = Math.sqrt((fArr5[0] * fArr5[0]) + (fArr5[1] * fArr5[1]));
        this.f696g[9][this.d] = this.f700k[2];
        this.f695f = j2;
        int i6 = this.d + 1;
        this.d = i6;
        if (i6 == 128) {
            this.d = 0;
            if (this.f694e) {
                return;
            }
            this.f694e = true;
        }
    }

    public final void e(float[] fArr, float[] fArr2) {
        float f2 = k4.d;
        if (fArr[0] == -10.0f) {
            System.arraycopy(fArr2, 0, fArr, 0, fArr.length);
            return;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = (fArr[i2] * f2) + ((1.0f - f2) * fArr2[i2]);
        }
    }

    public final void f(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[1]) + (fArr2[2] * fArr3[2]);
        fArr[1] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[1]) + (fArr2[5] * fArr3[2]);
        fArr[2] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[1]) + (fArr2[8] * fArr3[2]);
    }

    public double[] g(long j2) {
        if (!this.f694e || j2 - this.f695f >= 3000) {
            return null;
        }
        int i2 = this.d;
        int i3 = 0;
        while (true) {
            double[][] dArr = this.f696g;
            if (i3 >= dArr.length) {
                return this.a.f(j2, this.f697h);
            }
            System.arraycopy(dArr[i3], i2, this.f697h[i3], 0, dArr[i3].length - i2);
            if (this.d != 0) {
                double[][] dArr2 = this.f696g;
                System.arraycopy(dArr2[i3], 0, this.f697h[i3], dArr2[i3].length - i2, i2);
            }
            i3++;
        }
    }

    public void h() {
        if (this.b) {
            a();
            this.a.h();
            this.b = false;
        }
    }
}
