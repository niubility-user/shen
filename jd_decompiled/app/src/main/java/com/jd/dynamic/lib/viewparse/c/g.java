package com.jd.dynamic.lib.viewparse.c;

import android.view.View;
import com.jd.dynamic.lib.viewparse.c.e.a0;
import com.jd.dynamic.lib.viewparse.c.e.q0;
import com.jd.dynamic.lib.views.CollectionView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes13.dex */
public class g extends f<CollectionView> {

    /* renamed from: c  reason: collision with root package name */
    private List<q0<CollectionView>> f2436c;

    public g() {
        ArrayList arrayList = new ArrayList();
        this.f2436c = arrayList;
        arrayList.add(new a0());
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ View a(HashMap hashMap, View view) {
        CollectionView collectionView = (CollectionView) view;
        d(hashMap, collectionView);
        return collectionView;
    }

    public CollectionView d(HashMap<String, String> hashMap, CollectionView collectionView) {
        for (q0<CollectionView> q0Var : this.f2436c) {
            if (q0Var instanceof a0) {
                ((a0) q0Var).g(this.a);
            }
            q0Var.a(hashMap, collectionView);
        }
        return collectionView;
    }
}
