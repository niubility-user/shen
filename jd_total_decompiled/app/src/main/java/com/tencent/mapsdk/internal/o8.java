package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.r8;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class o8 extends s8 {

    /* renamed from: g */
    private double f16917g;

    /* renamed from: h */
    private double f16918h;

    /* renamed from: i */
    private double f16919i;

    /* renamed from: j */
    private boolean f16920j;

    public o8(r8.a... aVarArr) {
        super(aVarArr);
        this.f16920j = true;
    }

    @Override // com.tencent.mapsdk.internal.s8
    public Object a(float f2) {
        return Double.valueOf(b(f2));
    }

    public double b(float f2) {
        int i2 = this.a;
        if (i2 == 2) {
            if (this.f16920j) {
                this.f16920j = false;
                this.f16917g = ((r8.a) this.f17240e.get(0)).h();
                double h2 = ((r8.a) this.f17240e.get(1)).h();
                this.f16918h = h2;
                this.f16919i = h2 - this.f16917g;
            }
            Interpolator interpolator = this.d;
            if (interpolator != null) {
                f2 = interpolator.getInterpolation(f2);
            }
            x8 x8Var = this.f17241f;
            if (x8Var == null) {
                double d = this.f16917g;
                double d2 = f2;
                double d3 = this.f16919i;
                Double.isNaN(d2);
                return d + (d2 * d3);
            }
            return ((Number) x8Var.a(f2, Double.valueOf(this.f16917g), Double.valueOf(this.f16918h))).doubleValue();
        } else if (f2 <= 0.0f) {
            r8.a aVar = (r8.a) this.f17240e.get(0);
            r8.a aVar2 = (r8.a) this.f17240e.get(1);
            double h3 = aVar.h();
            double h4 = aVar2.h();
            float b = aVar.b();
            float b2 = aVar2.b();
            Interpolator c2 = aVar2.c();
            if (c2 != null) {
                f2 = c2.getInterpolation(f2);
            }
            float f3 = (f2 - b) / (b2 - b);
            if (this.f17241f == null) {
                double d4 = f3;
                Double.isNaN(d4);
                return h3 + (d4 * (h4 - h3));
            }
            return ((Number) r0.a(f3, Double.valueOf(h3), Double.valueOf(h4))).floatValue();
        } else if (f2 >= 1.0f) {
            r8.a aVar3 = (r8.a) this.f17240e.get(i2 - 2);
            r8.a aVar4 = (r8.a) this.f17240e.get(this.a - 1);
            double h5 = aVar3.h();
            double h6 = aVar4.h();
            float b3 = aVar3.b();
            float b4 = aVar4.b();
            Interpolator c3 = aVar4.c();
            if (c3 != null) {
                f2 = c3.getInterpolation(f2);
            }
            float f4 = (f2 - b3) / (b4 - b3);
            if (this.f17241f == null) {
                double d5 = f4;
                Double.isNaN(d5);
                return h5 + (d5 * (h6 - h5));
            }
            return ((Number) r0.a(f4, Double.valueOf(h5), Double.valueOf(h6))).floatValue();
        } else {
            r8.a aVar5 = (r8.a) this.f17240e.get(0);
            int i3 = 1;
            while (true) {
                if (i3 >= this.a) {
                    return ((Number) this.f17240e.get(r2 - 1).e()).floatValue();
                }
                r8.a aVar6 = (r8.a) this.f17240e.get(i3);
                if (f2 < aVar6.b()) {
                    Interpolator c4 = aVar6.c();
                    if (c4 != null) {
                        f2 = c4.getInterpolation(f2);
                    }
                    float b5 = (f2 - aVar5.b()) / (aVar6.b() - aVar5.b());
                    double h7 = aVar5.h();
                    double h8 = aVar6.h();
                    if (this.f17241f == null) {
                        double d6 = b5;
                        Double.isNaN(d6);
                        return h7 + (d6 * (h8 - h7));
                    }
                    return ((Number) r4.a(b5, Double.valueOf(h7), Double.valueOf(h8))).floatValue();
                }
                i3++;
                aVar5 = aVar6;
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.s8
    /* renamed from: b */
    public o8 clone() {
        ArrayList<r8> arrayList = this.f17240e;
        int size = arrayList.size();
        r8.a[] aVarArr = new r8.a[size];
        for (int i2 = 0; i2 < size; i2++) {
            aVarArr[i2] = (r8.a) arrayList.get(i2).clone();
        }
        return new o8(aVarArr);
    }
}
