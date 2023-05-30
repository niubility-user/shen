package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.lib.viewparse.c.e.f0;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class u<T extends View> extends f<T> {
    @Override // com.jd.dynamic.lib.viewparse.c.j
    public T a(HashMap<String, String> hashMap, T t) {
        com.jd.dynamic.lib.utils.m.z(hashMap);
        if (t == null) {
            return t;
        }
        f0 f0Var = new f0();
        f0Var.e(this.a);
        f0Var.a(hashMap, t);
        return t;
    }
}
