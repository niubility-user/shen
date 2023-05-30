package com.jingdong.app.mall.home.category.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.n.g.u.e;
import com.jingdong.app.mall.home.o.a.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class CaItemAdapter extends RecyclerView.Adapter<c> implements com.jingdong.app.mall.home.category.adapter.a {

    /* renamed from: l */
    public static int f8641l;

    /* renamed from: g */
    private RelativeLayout f8642g;

    /* renamed from: h */
    private RecyclerView f8643h;

    /* renamed from: i */
    private Context f8644i;

    /* renamed from: j */
    private AtomicInteger f8645j = new AtomicInteger(0);

    /* renamed from: k */
    private List<e> f8646k = new ArrayList();

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ e f8647g;

        a(e eVar) {
            CaItemAdapter.this = r1;
            this.f8647g = eVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaItemAdapter.this.m(this.f8647g);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
            CaItemAdapter.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaItemAdapter.this.l();
        }
    }

    /* loaded from: classes4.dex */
    public static class c extends RecyclerView.ViewHolder {
        public c(View view) {
            super(view);
        }
    }

    public CaItemAdapter(Context context, RelativeLayout relativeLayout, RecyclerView recyclerView) {
        this.f8644i = context;
        this.f8642g = relativeLayout;
        this.f8643h = recyclerView;
    }

    public e a(int i2) {
        try {
            return this.f8646k.get(i2);
        } catch (Exception e2) {
            f.s0(this, e2);
            return null;
        }
    }

    @Override // com.jingdong.app.mall.home.category.adapter.a
    public ViewGroup c() {
        return this.f8642g;
    }

    @Override // com.jingdong.app.mall.home.category.adapter.a
    public RecyclerView f() {
        return this.f8643h;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f8646k.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return h(i2).getFloorIntType();
    }

    public com.jingdong.app.mall.home.n.b h(int i2) {
        e a2 = a(i2);
        return a2 == null ? com.jingdong.app.mall.home.n.b.S_EMPTY_0 : a2.l();
    }

    public void l() {
        if (f.p0() || this.f8643h.isComputingLayout()) {
            if (this.f8645j.getAndIncrement() > 100) {
                return;
            }
            f.u0(new b());
        }
        notifyDataSetChanged();
    }

    public void m(e eVar) {
        List<e> list;
        int indexOf;
        if (eVar == null || (list = this.f8646k) == null || (indexOf = list.indexOf(eVar)) < 0) {
            return;
        }
        if (!f.p0() && !this.f8643h.isComputingLayout()) {
            notifyItemChanged(indexOf, Collections.EMPTY_LIST);
        } else if (this.f8645j.getAndIncrement() > 100) {
        } else {
            f.u0(new a(eVar));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: n */
    public void onBindViewHolder(@NonNull c cVar, int i2) {
        View view = cVar.itemView;
        if (view instanceof com.jingdong.app.mall.home.category.floor.base.b) {
            ((com.jingdong.app.mall.home.category.floor.base.b) view).d(a(i2), this, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: o */
    public void onBindViewHolder(@NonNull c cVar, int i2, @NonNull List<Object> list) {
        View view = cVar.itemView;
        if (view instanceof com.jingdong.app.mall.home.category.floor.base.b) {
            ((com.jingdong.app.mall.home.category.floor.base.b) view).c(a(i2), this, i2, list);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: p */
    public c onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        c cVar = new c(com.jingdong.app.mall.home.n.f.b(i2).getFloorView(this.f8644i).getContentView());
        m.f(cVar);
        return cVar;
    }

    public void q(List<e> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f8645j.set(0);
        this.f8646k.clear();
        this.f8646k.addAll(list);
        l();
    }
}
