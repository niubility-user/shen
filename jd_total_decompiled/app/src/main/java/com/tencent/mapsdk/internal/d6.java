package com.tencent.mapsdk.internal;

import java.util.ArrayList;

/* loaded from: classes9.dex */
public class d6 {
    private ArrayList<c6> a = new ArrayList<>();

    public c6 a(int i2) {
        return this.a.get(i2);
    }

    public void a(c6 c6Var) {
        this.a.add(c6Var);
    }

    public void a(d6 d6Var) {
        this.a.addAll(d6Var.a);
    }

    public float[] a() {
        float[] fArr = new float[this.a.size() * 3];
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            int i3 = i2 * 3;
            fArr[i3 + 0] = this.a.get(i2).a;
            fArr[i3 + 1] = this.a.get(i2).b;
            fArr[i3 + 2] = this.a.get(i2).f16359c;
        }
        return fArr;
    }

    public int b() {
        return this.a.size();
    }
}
