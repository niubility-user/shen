package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.jingdong.common.utils.LangUtils;
import com.tencent.mapsdk.internal.r8;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class s8 {
    public int a;
    public r8 b;

    /* renamed from: c  reason: collision with root package name */
    public r8 f17239c;
    public Interpolator d;

    /* renamed from: e  reason: collision with root package name */
    public ArrayList<r8> f17240e;

    /* renamed from: f  reason: collision with root package name */
    public x8 f17241f;

    public s8(r8... r8VarArr) {
        this.a = r8VarArr.length;
        ArrayList<r8> arrayList = new ArrayList<>();
        this.f17240e = arrayList;
        arrayList.addAll(Arrays.asList(r8VarArr));
        this.b = this.f17240e.get(0);
        r8 r8Var = this.f17240e.get(this.a - 1);
        this.f17239c = r8Var;
        this.d = r8Var.c();
    }

    public static s8 a(double... dArr) {
        int length = dArr.length;
        r8.a[] aVarArr = new r8.a[Math.max(length, 2)];
        if (length == 1) {
            aVarArr[0] = (r8.a) r8.a(0.0f);
            aVarArr[1] = (r8.a) r8.a(1.0f, dArr[0]);
        } else {
            aVarArr[0] = (r8.a) r8.a(0.0f, dArr[0]);
            for (int i2 = 1; i2 < length; i2++) {
                aVarArr[i2] = (r8.a) r8.a(i2 / ((float) (length - 1)), dArr[i2]);
            }
        }
        return new o8(aVarArr);
    }

    public static s8 a(int... iArr) {
        int length = iArr.length;
        r8.b[] bVarArr = new r8.b[Math.max(length, 2)];
        if (length == 1) {
            bVarArr[0] = (r8.b) r8.b(0.0f);
            bVarArr[1] = (r8.b) r8.a(1.0f, iArr[0]);
        } else {
            bVarArr[0] = (r8.b) r8.a(0.0f, iArr[0]);
            for (int i2 = 1; i2 < length; i2++) {
                bVarArr[i2] = (r8.b) r8.a(i2 / ((float) (length - 1)), iArr[i2]);
            }
        }
        return new q8(bVarArr);
    }

    public static s8 a(r8... r8VarArr) {
        int length = r8VarArr.length;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < length; i3++) {
            if (r8VarArr[i3] instanceof r8.a) {
                z = true;
            } else if (r8VarArr[i3] instanceof r8.b) {
                z2 = true;
            } else {
                z3 = true;
            }
        }
        if (z && !z2 && !z3) {
            r8.a[] aVarArr = new r8.a[length];
            while (i2 < length) {
                aVarArr[i2] = (r8.a) r8VarArr[i2];
                i2++;
            }
            return new o8(aVarArr);
        } else if (!z2 || z || z3) {
            return new s8(r8VarArr);
        } else {
            r8.b[] bVarArr = new r8.b[length];
            while (i2 < length) {
                bVarArr[i2] = (r8.b) r8VarArr[i2];
                i2++;
            }
            return new q8(bVarArr);
        }
    }

    public static s8 a(Object... objArr) {
        int length = objArr.length;
        r8.c[] cVarArr = new r8.c[Math.max(length, 2)];
        if (length == 1) {
            cVarArr[0] = (r8.c) r8.c(0.0f);
            cVarArr[1] = (r8.c) r8.a(1.0f, objArr[0]);
        } else {
            cVarArr[0] = (r8.c) r8.a(0.0f, objArr[0]);
            for (int i2 = 1; i2 < length; i2++) {
                cVarArr[i2] = (r8.c) r8.a(i2 / ((float) (length - 1)), objArr[i2]);
            }
        }
        return new s8(cVarArr);
    }

    @Override // 
    /* renamed from: a */
    public s8 clone() {
        ArrayList<r8> arrayList = this.f17240e;
        int size = arrayList.size();
        r8[] r8VarArr = new r8[size];
        for (int i2 = 0; i2 < size; i2++) {
            r8VarArr[i2] = arrayList.get(i2).clone();
        }
        return new s8(r8VarArr);
    }

    public Object a(float f2) {
        int i2 = this.a;
        if (i2 == 2) {
            Interpolator interpolator = this.d;
            if (interpolator != null) {
                f2 = interpolator.getInterpolation(f2);
            }
            return this.f17241f.a(f2, this.b.e(), this.f17239c.e());
        }
        int i3 = 1;
        if (f2 <= 0.0f) {
            r8 r8Var = this.f17240e.get(1);
            Interpolator c2 = r8Var.c();
            if (c2 != null) {
                f2 = c2.getInterpolation(f2);
            }
            float b = this.b.b();
            return this.f17241f.a((f2 - b) / (r8Var.b() - b), this.b.e(), r8Var.e());
        } else if (f2 >= 1.0f) {
            r8 r8Var2 = this.f17240e.get(i2 - 2);
            Interpolator c3 = this.f17239c.c();
            if (c3 != null) {
                f2 = c3.getInterpolation(f2);
            }
            float b2 = r8Var2.b();
            return this.f17241f.a((f2 - b2) / (this.f17239c.b() - b2), r8Var2.e(), this.f17239c.e());
        } else {
            r8 r8Var3 = this.b;
            while (i3 < this.a) {
                r8 r8Var4 = this.f17240e.get(i3);
                if (f2 < r8Var4.b()) {
                    Interpolator c4 = r8Var4.c();
                    if (c4 != null) {
                        f2 = c4.getInterpolation(f2);
                    }
                    float b3 = r8Var3.b();
                    return this.f17241f.a((f2 - b3) / (r8Var4.b() - b3), r8Var3.e(), r8Var4.e());
                }
                i3++;
                r8Var3 = r8Var4;
            }
            return this.f17239c.e();
        }
    }

    public void a(x8 x8Var) {
        this.f17241f = x8Var;
    }

    public String toString() {
        String str = LangUtils.SINGLE_SPACE;
        for (int i2 = 0; i2 < this.a; i2++) {
            str = str + this.f17240e.get(i2).e() + "  ";
        }
        return str;
    }
}
