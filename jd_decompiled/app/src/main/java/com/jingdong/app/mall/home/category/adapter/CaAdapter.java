package com.jingdong.app.mall.home.category.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.n.g.f;
import com.jingdong.app.mall.home.n.g.n;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class CaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements com.jingdong.app.mall.home.category.adapter.a {
    public static int p;

    /* renamed from: k  reason: collision with root package name */
    private Context f8633k;

    /* renamed from: l  reason: collision with root package name */
    private ViewGroup f8634l;

    /* renamed from: m  reason: collision with root package name */
    private RecyclerView f8635m;

    /* renamed from: g  reason: collision with root package name */
    private boolean f8629g = true;

    /* renamed from: h  reason: collision with root package name */
    private n f8630h = new n(null, com.jingdong.app.mall.home.n.a.C_LOADING);

    /* renamed from: i  reason: collision with root package name */
    private f f8631i = new f(null, com.jingdong.app.mall.home.n.a.C_EMPTY);

    /* renamed from: j  reason: collision with root package name */
    private AtomicInteger f8632j = new AtomicInteger(0);

    /* renamed from: n  reason: collision with root package name */
    private List<com.jingdong.app.mall.home.n.g.u.c> f8636n = new CopyOnWriteArrayList();
    private List<com.jingdong.app.mall.home.n.g.u.c> o = new CopyOnWriteArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f8637g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f8638h;

        a(int i2, int i3) {
            this.f8637g = i2;
            this.f8638h = i3;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaAdapter.this.notifyItemRangeChanged(this.f8637g, this.f8638h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaAdapter.this.u();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class c extends RecyclerView.ViewHolder {
        public com.jingdong.app.mall.home.n.a a;

        public c(View view, com.jingdong.app.mall.home.n.a aVar) {
            super(view);
            this.a = aVar;
        }
    }

    public CaAdapter(Context context, ViewGroup viewGroup, RecyclerView recyclerView) {
        this.f8633k = context;
        this.f8634l = viewGroup;
        this.f8635m = recyclerView;
    }

    private RecyclerView.ViewHolder n(ViewGroup viewGroup, int i2) {
        com.jingdong.app.mall.home.n.a c2 = com.jingdong.app.mall.home.n.f.c(i2);
        return new c(c2.getFloorView(this.f8633k, this).getContentView(), c2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (!com.jingdong.app.mall.home.o.a.f.p0() && !this.f8635m.isComputingLayout()) {
            notifyDataSetChanged();
        } else if (this.f8632j.getAndIncrement() > 100) {
        } else {
            com.jingdong.app.mall.home.o.a.f.u0(new b());
        }
    }

    private void v(int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (!com.jingdong.app.mall.home.o.a.f.p0() && !this.f8635m.isComputingLayout()) {
            notifyItemRangeChanged(i2, i3);
        } else if (this.f8632j.getAndIncrement() > 100) {
        } else {
            com.jingdong.app.mall.home.o.a.f.u0(new a(i2, i3));
        }
    }

    @Override // com.jingdong.app.mall.home.category.adapter.a
    public ViewGroup c() {
        return this.f8634l;
    }

    @Override // com.jingdong.app.mall.home.category.adapter.a
    public RecyclerView f() {
        return this.f8635m;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int o = o();
        if (o <= 0) {
            return 0;
        }
        if (this.f8636n.get(0) == this.f8631i) {
            return 1;
        }
        return o + (this.f8629g ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        com.jingdong.app.mall.home.n.g.u.c r = r(i2);
        return (r == null ? com.jingdong.app.mall.home.n.a.C_EMPTY : r.f()).getFloorIntType();
    }

    public void h(List<com.jingdong.app.mall.home.n.g.u.c> list, boolean z) {
        if (list == null) {
            return;
        }
        int itemCount = getItemCount() - 1;
        if (z) {
            this.o.clear();
            itemCount = q() - 1;
        }
        this.o.addAll(list);
        v(itemCount, getItemCount() - itemCount);
    }

    public void l() {
        this.f8632j.set(0);
        this.f8636n.clear();
        this.o.clear();
        this.f8636n.add(this.f8631i);
        u();
    }

    public void m() {
        if (p() <= 0) {
            return;
        }
        this.f8632j.set(0);
        this.o.clear();
        u();
    }

    public int o() {
        return q() + p();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        View view = viewHolder.itemView;
        if (view instanceof com.jingdong.app.mall.home.category.floor.base.b) {
            ((com.jingdong.app.mall.home.category.floor.base.b) view).d(r(i2), this, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder n2 = n(viewGroup, i2);
        m.f(n2);
        return n2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        if ((layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) && (viewHolder instanceof c)) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(((c) viewHolder).a.isFullSpan());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        if (view instanceof com.jingdong.app.mall.home.category.floor.base.b) {
            ((com.jingdong.app.mall.home.category.floor.base.b) view).onViewRecycle();
        }
    }

    public int p() {
        return this.o.size();
    }

    public int q() {
        return this.f8636n.size();
    }

    com.jingdong.app.mall.home.n.g.u.c r(int i2) {
        try {
            if (this.f8629g && i2 == o() && getItemCount() > 1) {
                return this.f8630h;
            }
            if (i2 < q()) {
                return this.f8636n.get(i2);
            }
            return this.o.get(i2 - q());
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            return new f(null, com.jingdong.app.mall.home.n.a.C_EMPTY);
        }
    }

    public boolean s() {
        return this.f8636n.size() <= 0 || (this.f8636n.size() == 1 && this.f8636n.get(0) == this.f8631i);
    }

    public void t() {
        ViewGroup viewGroup = this.f8634l;
        if (viewGroup instanceof CaContentLayout) {
            ((CaContentLayout) viewGroup).n();
        }
    }

    public void w(List<com.jingdong.app.mall.home.n.g.u.c> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f8636n.clear();
        this.f8636n.addAll(list);
        u();
    }

    public void x(boolean z) {
        this.f8629g = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2, @NonNull List<Object> list) {
        View view = viewHolder.itemView;
        if (view instanceof com.jingdong.app.mall.home.category.floor.base.b) {
            ((com.jingdong.app.mall.home.category.floor.base.b) view).c(r(i2), this, i2, list);
        }
    }
}
