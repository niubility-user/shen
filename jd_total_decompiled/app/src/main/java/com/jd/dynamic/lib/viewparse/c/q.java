package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.lib.viewparse.c.e.k0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.views.TagView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class q extends f<TagView> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<TagView>> f2443c;

    public q() {
        ArrayList arrayList = new ArrayList();
        this.f2443c = arrayList;
        arrayList.add(new k0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        TagView tagView = (TagView) view;
        d(hashMap, tagView);
        return tagView;
    }

    public TagView d(HashMap<String, String> hashMap, TagView tagView) {
        tagView.attachEngine(this.a);
        for (q0<TagView> q0Var : this.f2443c) {
            if (q0Var instanceof p0) {
                ((p0) q0Var).e(this.a);
            }
            q0Var.a(hashMap, tagView);
        }
        return tagView;
    }
}
