package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import com.jingdong.manto.o.m;
import com.jingdong.manto.sdk.api.IActionBar;
import com.jingdong.manto.widget.g.g;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class p {
    private Activity a;
    private com.jingdong.manto.q.n b;

    /* renamed from: c  reason: collision with root package name */
    private com.jingdong.manto.widget.g.g f13909c;
    private com.jingdong.manto.widget.j.c d;

    /* renamed from: e  reason: collision with root package name */
    private SparseArray<n> f13910e;

    /* renamed from: f  reason: collision with root package name */
    public m.a f13911f = new b();

    /* renamed from: g  reason: collision with root package name */
    public IActionBar.IMenuBtnClickCallBack f13912g = new c();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements g.c {
        final /* synthetic */ SparseArray a;

        a(SparseArray sparseArray) {
            this.a = sparseArray;
        }

        @Override // com.jingdong.manto.widget.g.g.c
        public void a(com.jingdong.manto.widget.j.d dVar) {
            m mVar = o.b().a.get(dVar.b());
            n nVar = (n) this.a.get(dVar.b());
            if (mVar == null || nVar == null) {
                return;
            }
            mVar.a(p.this.a, p.this.b, p.this.b.a(), nVar);
        }
    }

    /* loaded from: classes16.dex */
    class b implements m.a {
        b() {
        }

        @Override // com.jingdong.manto.o.m.a
        public void a() {
            if (p.this.d == null || p.this.f13910e == null || p.this.f13909c == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < p.this.d.size(); i2++) {
                com.jingdong.manto.widget.j.d item = p.this.d.getItem(i2);
                n nVar = (n) p.this.f13910e.get(item.b());
                if (nVar != null && nVar.b) {
                    arrayList.add(item);
                }
            }
            p.this.f13909c.b(arrayList);
        }
    }

    /* loaded from: classes16.dex */
    class c implements IActionBar.IMenuBtnClickCallBack {
        c() {
        }

        @Override // com.jingdong.manto.sdk.api.IActionBar.IMenuBtnClickCallBack
        public void clickDebugSwtich() {
            m mVar = o.b().a.get(9);
            if (mVar != null) {
                mVar.a(p.this.a, p.this.b, p.this.b.a(), null);
            }
        }

        @Override // com.jingdong.manto.sdk.api.IActionBar.IMenuBtnClickCallBack
        public boolean getDebugStatus() {
            return p.this.b.h().v();
        }
    }

    public p(Activity activity, com.jingdong.manto.q.n nVar) {
        this.a = activity;
        this.b = nVar;
        this.f13909c = new com.jingdong.manto.widget.g.g(activity);
        this.d = new com.jingdong.manto.widget.j.a(activity);
    }

    public void a(View view, SparseArray<n> sparseArray) {
        if (view == null || sparseArray == null || sparseArray.size() == 0) {
            return;
        }
        this.d.clear();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            Context context = view.getContext();
            com.jingdong.manto.q.n nVar = this.b;
            o.b().a.get(sparseArray.get(sparseArray.keyAt(i2)).f13908c).a(context, nVar, this.d, nVar.a(), this.f13911f);
        }
        this.f13910e = sparseArray;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            com.jingdong.manto.widget.j.d item = this.d.getItem(i3);
            n nVar2 = sparseArray.get(item.b());
            if (nVar2 != null && nVar2.b) {
                arrayList.add(item);
            }
        }
        this.f13909c.a(arrayList);
        this.f13909c.a(new a(sparseArray));
        this.f13909c.showAsDropDown(view);
    }
}
