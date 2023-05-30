package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.viewparse.c.e.f0;
import com.jd.dynamic.lib.viewparse.c.e.g0;
import com.jd.dynamic.lib.viewparse.c.e.h0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.viewparse.c.e.u0;
import com.jd.dynamic.lib.viewparse.c.e.y;
import com.jd.dynamic.lib.viewparse.c.e.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class t<T extends View> extends f<T> {

    /* renamed from: c */
    private final List<q0<View>> f2445c;

    public t() {
        ArrayList arrayList = new ArrayList();
        this.f2445c = arrayList;
        arrayList.add(new z());
        arrayList.add(new f0());
        arrayList.add(new h0());
        arrayList.add(new g0());
        arrayList.add(new y());
        arrayList.add(new u0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public T a(HashMap<String, String> hashMap, T t) {
        com.jd.dynamic.lib.utils.m.z(hashMap);
        if (t == null) {
            return t;
        }
        for (q0<View> q0Var : this.f2445c) {
            if (q0Var instanceof p0) {
                p0 p0Var = (p0) q0Var;
                p0Var.c(this.b);
                p0Var.e(this.a);
            }
            if (t.getTag(R.id.dynamic_tag_view_item) == null || !(q0Var instanceof g0)) {
                DynamicTemplateEngine dynamicTemplateEngine = this.a;
                if (dynamicTemplateEngine == null || dynamicTemplateEngine.isAttached || !(q0Var instanceof g0)) {
                    q0Var.a(hashMap, t);
                } else {
                    dynamicTemplateEngine.unBindListenerViews.put(t, hashMap);
                }
            }
        }
        return t;
    }
}
