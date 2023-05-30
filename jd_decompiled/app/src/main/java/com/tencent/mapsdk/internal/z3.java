package com.tencent.mapsdk.internal;

import com.jingdong.common.unification.customtheme.CustomThemeConstance;

/* loaded from: classes9.dex */
public enum z3 {
    None("", 0),
    Gradient("heat", 2),
    Aggregation("honey", 3),
    ArcLine("arcline", 4),
    GLModel(CustomThemeConstance.NAVI_MODEL, 5),
    Trail("trail", 6),
    Scatter("scatter", 7);
    
    private final int a;
    private final String b;

    z3(String str, int i2) {
        this.b = str;
        this.a = i2;
    }

    public static z3 a(String str) {
        z3[] values = values();
        for (int i2 = 0; i2 < 7; i2++) {
            z3 z3Var = values[i2];
            if (z3Var.b(str)) {
                return z3Var;
            }
        }
        return None;
    }

    public static z3 b(int i2) {
        z3[] values = values();
        for (int i3 = 0; i3 < 7; i3++) {
            z3 z3Var = values[i3];
            if (z3Var.a(i2)) {
                return z3Var;
            }
        }
        return None;
    }

    public String a() {
        return this.b;
    }

    public boolean a(int i2) {
        return this.a == i2;
    }

    public boolean b(String str) {
        return this.b.equals(str);
    }
}
