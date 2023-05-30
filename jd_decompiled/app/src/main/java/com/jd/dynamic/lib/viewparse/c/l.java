package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.lib.viewparse.c.e.l0;
import com.jd.dynamic.lib.viewparse.c.e.m0;
import com.jd.dynamic.lib.viewparse.c.e.n0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.viewparse.c.e.r0;
import com.jd.dynamic.lib.views.MarqueeTextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class l extends f<AppCompatTextView> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<AppCompatTextView>> f2439c;

    public l() {
        ArrayList arrayList = new ArrayList();
        this.f2439c = arrayList;
        arrayList.add(new n0());
        this.f2439c.add(new m0());
        this.f2439c.add(new l0());
        this.f2439c.add(new r0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) view;
        d(hashMap, appCompatTextView);
        return appCompatTextView;
    }

    public AppCompatTextView d(HashMap<String, String> hashMap, AppCompatTextView appCompatTextView) {
        for (q0<AppCompatTextView> q0Var : this.f2439c) {
            if (q0Var instanceof p0) {
                ((p0) q0Var).e(this.a);
            }
            q0Var.a(hashMap, appCompatTextView);
        }
        if (appCompatTextView instanceof MarqueeTextView) {
            ((MarqueeTextView) appCompatTextView).finishSetting();
        }
        return appCompatTextView;
    }
}
