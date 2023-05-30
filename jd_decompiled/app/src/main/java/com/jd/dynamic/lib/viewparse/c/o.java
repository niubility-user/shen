package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.viewparse.c.e.s0;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class o extends f<YogaLayout> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<YogaLayout>> f2441c;

    public o() {
        ArrayList arrayList = new ArrayList();
        this.f2441c = arrayList;
        arrayList.add(new s0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        YogaLayout yogaLayout = (YogaLayout) view;
        d(hashMap, yogaLayout);
        return yogaLayout;
    }

    public YogaLayout d(HashMap<String, String> hashMap, YogaLayout yogaLayout) {
        Iterator<q0<YogaLayout>> it = this.f2441c.iterator();
        while (it.hasNext()) {
            it.next().a(hashMap, yogaLayout);
        }
        return yogaLayout;
    }
}
