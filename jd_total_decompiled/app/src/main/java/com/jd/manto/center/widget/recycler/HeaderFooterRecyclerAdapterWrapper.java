package com.jd.manto.center.widget.recycler;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes17.dex */
public class HeaderFooterRecyclerAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SparseArray<View> a = new SparseArray<>();
    private SparseArray<View> b = new SparseArray<>();

    /* renamed from: c  reason: collision with root package name */
    private RecyclerView.Adapter f6613c;

    /* loaded from: classes17.dex */
    class a extends RecyclerView.AdapterDataObserver {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            HeaderFooterRecyclerAdapterWrapper.this.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i2, int i3) {
            HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = HeaderFooterRecyclerAdapterWrapper.this;
            headerFooterRecyclerAdapterWrapper.notifyItemRangeChanged(headerFooterRecyclerAdapterWrapper.m() + i2, i3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i2, int i3) {
            HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = HeaderFooterRecyclerAdapterWrapper.this;
            headerFooterRecyclerAdapterWrapper.notifyItemRangeInserted(headerFooterRecyclerAdapterWrapper.m() + i2, i3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i2, int i3, int i4) {
            HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = HeaderFooterRecyclerAdapterWrapper.this;
            headerFooterRecyclerAdapterWrapper.notifyItemMoved(headerFooterRecyclerAdapterWrapper.m() + i2, HeaderFooterRecyclerAdapterWrapper.this.m() + i3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i2, int i3) {
            HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = HeaderFooterRecyclerAdapterWrapper.this;
            headerFooterRecyclerAdapterWrapper.notifyItemRangeRemoved(headerFooterRecyclerAdapterWrapper.m() + i2, i3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i2, int i3, Object obj) {
            HeaderFooterRecyclerAdapterWrapper headerFooterRecyclerAdapterWrapper = HeaderFooterRecyclerAdapterWrapper.this;
            headerFooterRecyclerAdapterWrapper.notifyItemRangeChanged(headerFooterRecyclerAdapterWrapper.m() + i2, i3, obj);
        }
    }

    /* loaded from: classes17.dex */
    class b extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ GridLayoutManager a;
        final /* synthetic */ GridLayoutManager.SpanSizeLookup b;

        b(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
            this.a = gridLayoutManager;
            this.b = spanSizeLookup;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i2) {
            if (!HeaderFooterRecyclerAdapterWrapper.this.q(i2) && !HeaderFooterRecyclerAdapterWrapper.this.o(i2)) {
                return this.b.getSpanSize(i2 - HeaderFooterRecyclerAdapterWrapper.this.m());
            }
            return this.a.getSpanCount();
        }
    }

    /* loaded from: classes17.dex */
    private static class c extends RecyclerView.ViewHolder {
        public c(View view) {
            super(view);
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
    }

    public HeaderFooterRecyclerAdapterWrapper(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            this.f6613c = adapter;
            adapter.registerAdapterDataObserver(new a());
            return;
        }
        throw new NullPointerException("target adapter is null");
    }

    private int n() {
        return this.f6613c.getItemCount();
    }

    private boolean p(int i2) {
        return i2 >= m() + n();
    }

    private boolean r(int i2) {
        return i2 < m();
    }

    public void a(View view) {
        SparseArray<View> sparseArray = this.b;
        sparseArray.put(sparseArray.size() + 200000, view);
        notifyDataSetChanged();
    }

    public void addHeaderView(View view) {
        SparseArray<View> sparseArray = this.a;
        sparseArray.put(sparseArray.size() + 100000, view);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return m() + l() + n();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i2) {
        if (r(i2)) {
            return this.a.keyAt(i2);
        }
        if (p(i2)) {
            return this.b.keyAt((i2 - m()) - n());
        }
        return this.f6613c.getItemViewType(i2 - m());
    }

    public boolean h(View view) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            SparseArray<View> sparseArray = this.a;
            if (sparseArray.get(sparseArray.keyAt(i2)).equals(view)) {
                return true;
            }
        }
        return false;
    }

    public int l() {
        return this.b.size();
    }

    public int m() {
        return this.a.size();
    }

    public boolean o(int i2) {
        int itemCount = getItemCount();
        return i2 < itemCount && i2 >= itemCount - this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new b(gridLayoutManager, gridLayoutManager.getSpanSizeLookup()));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (r(i2) || p(i2)) {
            return;
        }
        this.f6613c.onBindViewHolder(viewHolder, i2 - m());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (this.a.get(i2) != null) {
            return new c(this.a.get(i2));
        }
        if (this.b.get(i2) != null) {
            return new c(this.b.get(i2));
        }
        return this.f6613c.onCreateViewHolder(viewGroup, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams;
        super.onViewAttachedToWindow(viewHolder);
        int layoutPosition = viewHolder.getLayoutPosition();
        if ((r(layoutPosition) || p(layoutPosition)) && (layoutParams = viewHolder.itemView.getLayoutParams()) != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }

    public boolean q(int i2) {
        return i2 >= 0 && i2 < this.a.size();
    }

    public void removeFooterView(View view) {
        this.b.removeAt(this.b.indexOfValue(view));
        notifyDataSetChanged();
    }

    public void removeHeaderView(View view) {
        this.a.removeAt(this.a.indexOfValue(view));
        notifyDataSetChanged();
    }
}
