package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.lib.viewparse.c.e.l0;
import com.jd.dynamic.lib.viewparse.c.e.m0;
import com.jd.dynamic.lib.viewparse.c.e.n0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.viewparse.c.e.t0;
import com.jd.dynamic.lib.views.SpanView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class p extends f<SpanView> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<AppCompatTextView>> f2442c;
    private t0 d;

    public p() {
        ArrayList arrayList = new ArrayList();
        this.f2442c = arrayList;
        this.d = new t0();
        arrayList.add(new m0());
        this.f2442c.add(new n0());
        this.f2442c.add(new l0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        SpanView spanView = (SpanView) view;
        d(hashMap, spanView);
        return spanView;
    }

    public SpanView d(HashMap<String, String> hashMap, SpanView spanView) {
        for (q0<AppCompatTextView> q0Var : this.f2442c) {
            if (q0Var instanceof p0) {
                ((p0) q0Var).e(this.a);
            }
            q0Var.a(hashMap, spanView);
        }
        this.d.a(hashMap, spanView);
        spanView.onParseFinish();
        return spanView;
    }
}
