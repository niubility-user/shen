package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.r8;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class q8 extends s8 {

    /* renamed from: g  reason: collision with root package name */
    private int f17035g;

    /* renamed from: h  reason: collision with root package name */
    private int f17036h;

    /* renamed from: i  reason: collision with root package name */
    private int f17037i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f17038j;

    public q8(r8.b... bVarArr) {
        super(bVarArr);
        this.f17038j = true;
    }

    @Override // com.tencent.mapsdk.internal.s8
    public Object a(float f2) {
        return Integer.valueOf(b(f2));
    }

    public int b(float f2) {
        Object e2;
        int i2 = this.a;
        if (i2 != 2) {
            if (f2 > 0.0f) {
                if (f2 < 1.0f) {
                    r8.b bVar = (r8.b) this.f17240e.get(0);
                    int i3 = 1;
                    while (true) {
                        int i4 = this.a;
                        if (i3 >= i4) {
                            e2 = this.f17240e.get(i4 - 1).e();
                            break;
                        }
                        r8.b bVar2 = (r8.b) this.f17240e.get(i3);
                        if (f2 < bVar2.b()) {
                            Interpolator c2 = bVar2.c();
                            if (c2 != null) {
                                f2 = c2.getInterpolation(f2);
                            }
                            float b = (f2 - bVar.b()) / (bVar2.b() - bVar.b());
                            int h2 = bVar.h();
                            int h3 = bVar2.h();
                            x8 x8Var = this.f17241f;
                            return x8Var == null ? h2 + ((int) (b * (h3 - h2))) : ((Number) x8Var.a(b, Integer.valueOf(h2), Integer.valueOf(h3))).intValue();
                        }
                        i3++;
                        bVar = bVar2;
                    }
                } else {
                    r8.b bVar3 = (r8.b) this.f17240e.get(i2 - 2);
                    r8.b bVar4 = (r8.b) this.f17240e.get(this.a - 1);
                    int h4 = bVar3.h();
                    int h5 = bVar4.h();
                    float b2 = bVar3.b();
                    float b3 = bVar4.b();
                    Interpolator c3 = bVar4.c();
                    if (c3 != null) {
                        f2 = c3.getInterpolation(f2);
                    }
                    float f3 = (f2 - b2) / (b3 - b2);
                    x8 x8Var2 = this.f17241f;
                    return x8Var2 == null ? h4 + ((int) (f3 * (h5 - h4))) : ((Number) x8Var2.a(f3, Integer.valueOf(h4), Integer.valueOf(h5))).intValue();
                }
            } else {
                r8.b bVar5 = (r8.b) this.f17240e.get(0);
                r8.b bVar6 = (r8.b) this.f17240e.get(1);
                int h6 = bVar5.h();
                int h7 = bVar6.h();
                float b4 = bVar5.b();
                float b5 = bVar6.b();
                Interpolator c4 = bVar6.c();
                if (c4 != null) {
                    f2 = c4.getInterpolation(f2);
                }
                float f4 = (f2 - b4) / (b5 - b4);
                x8 x8Var3 = this.f17241f;
                return x8Var3 == null ? h6 + ((int) (f4 * (h7 - h6))) : ((Number) x8Var3.a(f4, Integer.valueOf(h6), Integer.valueOf(h7))).intValue();
            }
        } else {
            if (this.f17038j) {
                this.f17038j = false;
                this.f17035g = ((r8.b) this.f17240e.get(0)).h();
                int h8 = ((r8.b) this.f17240e.get(1)).h();
                this.f17036h = h8;
                this.f17037i = h8 - this.f17035g;
            }
            Interpolator interpolator = this.d;
            if (interpolator != null) {
                f2 = interpolator.getInterpolation(f2);
            }
            x8 x8Var4 = this.f17241f;
            if (x8Var4 == null) {
                return this.f17035g + ((int) (f2 * this.f17037i));
            }
            e2 = x8Var4.a(f2, Integer.valueOf(this.f17035g), Integer.valueOf(this.f17036h));
        }
        return ((Number) e2).intValue();
    }

    @Override // com.tencent.mapsdk.internal.s8
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public q8 clone() {
        ArrayList<r8> arrayList = this.f17240e;
        int size = arrayList.size();
        r8.b[] bVarArr = new r8.b[size];
        for (int i2 = 0; i2 < size; i2++) {
            bVarArr[i2] = (r8.b) arrayList.get(i2).clone();
        }
        return new q8(bVarArr);
    }
}
