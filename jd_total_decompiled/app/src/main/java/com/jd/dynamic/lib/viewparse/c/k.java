package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.lib.viewparse.c.e.j0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.views.CornerSimpleDraweeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class k extends f<CornerSimpleDraweeView> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<CornerSimpleDraweeView>> f2438c;

    public k() {
        ArrayList arrayList = new ArrayList();
        this.f2438c = arrayList;
        arrayList.add(new j0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        CornerSimpleDraweeView cornerSimpleDraweeView = (CornerSimpleDraweeView) view;
        d(hashMap, cornerSimpleDraweeView);
        return cornerSimpleDraweeView;
    }

    public CornerSimpleDraweeView d(HashMap<String, String> hashMap, CornerSimpleDraweeView cornerSimpleDraweeView) {
        for (q0<CornerSimpleDraweeView> q0Var : this.f2438c) {
            if (q0Var instanceof p0) {
                ((p0) q0Var).e(this.a);
            }
            q0Var.a(hashMap, cornerSimpleDraweeView);
        }
        return cornerSimpleDraweeView;
    }
}
