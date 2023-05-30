package com.jd.lib.cashier.sdk.core.commonfloor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class BaseQuickAdapter<T, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {

    /* renamed from: g  reason: collision with root package name */
    private boolean f2940g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2941h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2942i;

    /* renamed from: j  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.d.a.d.a f2943j;

    /* renamed from: k  reason: collision with root package name */
    private b f2944k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f2945l;

    /* renamed from: m  reason: collision with root package name */
    private LinearLayout f2946m;

    /* renamed from: n  reason: collision with root package name */
    private FrameLayout f2947n;
    private boolean o;
    private boolean p;
    private boolean q;
    protected Context r;
    protected int s;
    protected LayoutInflater t;
    protected List<T> u;
    private int v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BaseQuickAdapter.this.f2943j.e() == 3) {
                BaseQuickAdapter.this.f2943j.h(1);
                BaseQuickAdapter baseQuickAdapter = BaseQuickAdapter.this;
                baseQuickAdapter.notifyItemChanged(baseQuickAdapter.getHeaderLayoutCount() + BaseQuickAdapter.this.u.size() + BaseQuickAdapter.this.getFooterLayoutCount());
            }
        }
    }

    /* loaded from: classes14.dex */
    public interface b {
        void onLoadMoreRequested();
    }

    public BaseQuickAdapter(int i2, List<T> list) {
        this.f2940g = false;
        this.f2941h = false;
        this.f2942i = false;
        this.f2943j = new com.jd.lib.cashier.sdk.d.a.d.b();
        new LinearInterpolator();
        this.o = true;
        this.v = 1;
        this.u = list == null ? new ArrayList<>() : list;
        if (i2 != 0) {
            this.s = i2;
        }
    }

    private K getLoadingView(ViewGroup viewGroup) {
        K createBaseViewHolder = createBaseViewHolder(getItemView(this.f2943j.b(), viewGroup));
        this.f2943j.i();
        createBaseViewHolder.itemView.setOnClickListener(new a());
        return createBaseViewHolder;
    }

    public void autoLoadMore(int i2) {
        if (getLoadMoreViewCount() != 0 && i2 >= getItemCount() - this.v && this.f2943j.e() == 1) {
            this.f2943j.h(2);
            if (this.f2942i) {
                return;
            }
            this.f2942i = true;
            this.f2944k.onLoadMoreRequested();
        }
    }

    protected abstract void convert(K k2, T t);

    protected abstract K createBaseViewHolder(View view);

    /* JADX INFO: Access modifiers changed from: protected */
    public K createBaseViewHolder(ViewGroup viewGroup, int i2) {
        return createBaseViewHolder(getItemView(i2, viewGroup));
    }

    public List<T> getData() {
        return this.u;
    }

    protected int getDefItemViewType(int i2) {
        return super.getItemViewType(i2);
    }

    public int getEmptyViewCount() {
        FrameLayout frameLayout = this.f2947n;
        return (frameLayout == null || frameLayout.getChildCount() == 0 || !this.o || this.u.size() != 0) ? 0 : 1;
    }

    public int getFooterLayoutCount() {
        LinearLayout linearLayout = this.f2946m;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    public int getHeaderLayoutCount() {
        LinearLayout linearLayout = this.f2945l;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i2 = 1;
        if (getEmptyViewCount() != 1) {
            return getLoadMoreViewCount() + getHeaderLayoutCount() + this.u.size() + getFooterLayoutCount();
        }
        if (this.p && getHeaderLayoutCount() != 0) {
            i2 = 2;
        }
        return (!this.q || getFooterLayoutCount() == 0) ? i2 : i2 + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    protected View getItemView(int i2, ViewGroup viewGroup) {
        return this.t.inflate(i2, viewGroup, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (getEmptyViewCount() == 1) {
            boolean z = this.p && getHeaderLayoutCount() != 0;
            return i2 != 0 ? i2 != 1 ? i2 != 2 ? 1365 : 819 : z ? 1365 : 819 : z ? 273 : 1365;
        }
        autoLoadMore(i2);
        int headerLayoutCount = getHeaderLayoutCount();
        if (i2 < headerLayoutCount) {
            return 273;
        }
        int i3 = i2 - headerLayoutCount;
        int size = this.u.size();
        if (i3 < size) {
            return getDefItemViewType(i3);
        }
        return i3 - size < getFooterLayoutCount() ? 819 : 546;
    }

    public int getLoadMoreViewCount() {
        if (this.f2944k == null || !this.f2941h) {
            return 0;
        }
        return ((this.f2940g || !this.f2943j.g()) && this.u.size() != 0) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(K k2, int i2) {
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 0) {
            convert(k2, this.u.get(k2.getLayoutPosition() - getHeaderLayoutCount()));
        } else if (itemViewType != 273) {
            if (itemViewType == 546) {
                this.f2943j.a(k2);
            } else if (itemViewType == 819 || itemViewType == 1365) {
            } else {
                convert(k2, this.u.get(k2.getLayoutPosition() - getHeaderLayoutCount()));
            }
        }
    }

    protected K onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        return createBaseViewHolder(viewGroup, this.s);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public K onCreateViewHolder(ViewGroup viewGroup, int i2) {
        Context context = viewGroup.getContext();
        this.r = context;
        this.t = LayoutInflater.from(context);
        if (i2 != 273) {
            if (i2 != 546) {
                if (i2 != 819) {
                    if (i2 != 1365) {
                        return onCreateDefViewHolder(viewGroup, i2);
                    }
                    return createBaseViewHolder(this.f2947n);
                }
                return createBaseViewHolder(this.f2946m);
            }
            return getLoadingView(viewGroup);
        }
        return createBaseViewHolder(this.f2945l);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(K k2) {
        super.onViewAttachedToWindow(k2);
        int itemViewType = k2.getItemViewType();
        if (itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546) {
            setFullSpan(k2);
        }
    }

    public void setEnableLoadMore(boolean z) {
        int loadMoreViewCount = getLoadMoreViewCount();
        this.f2941h = z;
        int loadMoreViewCount2 = getLoadMoreViewCount();
        if (loadMoreViewCount == 1) {
            if (loadMoreViewCount2 == 0) {
                notifyItemRemoved(getHeaderLayoutCount() + this.u.size() + getFooterLayoutCount());
            }
        } else if (loadMoreViewCount2 == 1) {
            this.f2943j.h(1);
            notifyItemInserted(getHeaderLayoutCount() + this.u.size() + getFooterLayoutCount());
        }
    }

    protected void setFullSpan(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    public void setNewData(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.u = list;
        if (this.f2944k != null) {
            this.f2940g = true;
            this.f2941h = true;
            this.f2942i = false;
            this.f2943j.h(1);
        }
        notifyDataSetChanged();
    }

    public BaseQuickAdapter(List<T> list) {
        this(0, list);
    }
}
