package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class v5 {
    private w5 a;
    private w5[] b;

    /* renamed from: c  reason: collision with root package name */
    private float f17377c;

    public v5(w5 w5Var, w5[] w5VarArr, float f2) {
        this.a = w5Var;
        this.b = w5VarArr;
        this.f17377c = f2;
    }

    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public v5 clone() {
        return new v5(this.a, this.b, this.f17377c);
    }

    public void a(w5 w5Var, w5[] w5VarArr, float f2) {
        this.a = w5Var;
        this.b = w5VarArr;
        this.f17377c = f2;
    }

    public boolean a(v5 v5Var) {
        w5[] w5VarArr;
        w5 w5Var;
        if (v5Var != null && Float.compare(v5Var.f17377c, this.f17377c) < 1) {
            w5 w5Var2 = this.a;
            if (w5Var2 == null || (w5Var = v5Var.a) == null || wa.a(w5Var2, w5Var) <= 50.0d) {
                w5[] w5VarArr2 = this.b;
                if (w5VarArr2 != null && (w5VarArr = v5Var.b) != null && w5VarArr2.length == w5VarArr.length) {
                    int i2 = 0;
                    while (true) {
                        w5[] w5VarArr3 = this.b;
                        if (i2 >= w5VarArr3.length) {
                            break;
                        } else if (wa.a(w5VarArr3[i2], v5Var.b[i2]) > 50.0d) {
                            return true;
                        } else {
                            i2++;
                        }
                    }
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public w5 b() {
        return this.a;
    }

    public w5[] c() {
        return this.b;
    }

    public float d() {
        return this.f17377c;
    }
}
