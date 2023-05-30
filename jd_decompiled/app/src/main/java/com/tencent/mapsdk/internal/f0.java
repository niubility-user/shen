package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class f0 {
    private m0 a;

    public f0(m0 m0Var) {
        this.a = null;
        this.a = m0Var;
    }

    public void a() {
        if (this.a != null) {
            this.a = null;
        }
    }

    public v1 b() {
        m0 m0Var = this.a;
        if (m0Var == null) {
            return null;
        }
        return m0Var.getView();
    }
}
