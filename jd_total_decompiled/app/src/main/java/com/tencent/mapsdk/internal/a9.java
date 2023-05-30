package com.tencent.mapsdk.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class a9 extends z8 {
    private ArrayList<z8> B;

    public a9(ArrayList<z8> arrayList) {
        this.B = arrayList;
    }

    @Override // com.tencent.mapsdk.internal.z8
    public boolean a(c9 c9Var) {
        if (this.B.isEmpty()) {
            return true;
        }
        Iterator<z8> it = this.B.iterator();
        while (it.hasNext()) {
            it.next().a(c9Var);
        }
        return true;
    }
}
