package com.xiaomi.push;

import android.content.Context;
import java.util.List;

/* loaded from: classes11.dex */
public abstract class i1 {
    private int a;

    public i1(int i2) {
        this.a = i2;
    }

    public int a() {
        return this.a;
    }

    public abstract String b(Context context, String str, List<i0> list);

    public boolean c(Context context, String str, List<i0> list) {
        return true;
    }
}
