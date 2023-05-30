package com.tencent.mapsdk.internal;

import java.util.ArrayList;

/* loaded from: classes9.dex */
public class b9 extends z8 {
    private ArrayList<z8> B;

    public b9(ArrayList<z8> arrayList) {
        this.B = arrayList;
    }

    @Override // com.tencent.mapsdk.internal.z8
    public boolean a(c9 c9Var) {
        if (this.B.isEmpty()) {
            return true;
        }
        z8 z8Var = this.B.get(0);
        if (z8Var.a(c9Var)) {
            z8Var.c();
            this.B.remove(z8Var);
        }
        return this.B.isEmpty();
    }
}
