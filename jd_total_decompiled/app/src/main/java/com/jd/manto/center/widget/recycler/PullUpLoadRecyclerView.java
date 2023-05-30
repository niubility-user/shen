package com.jd.manto.center.widget.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.manto.center.widget.DefaultPullLoadFooter;

/* loaded from: classes17.dex */
public class PullUpLoadRecyclerView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    private c f6615g;

    /* renamed from: h  reason: collision with root package name */
    private com.jd.manto.center.widget.recycler.b f6616h;

    /* renamed from: i  reason: collision with root package name */
    private com.jd.manto.center.widget.recycler.a f6617i;

    /* renamed from: j  reason: collision with root package name */
    private HeaderFooterRecyclerAdapterWrapper f6618j;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayoutManager f6619k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends RecyclerView.OnScrollListener {
        int a;
        int b;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 != 0 || this.a != this.b - 1 || PullUpLoadRecyclerView.this.f6616h == com.jd.manto.center.widget.recycler.b.LOADING || PullUpLoadRecyclerView.this.f6616h == com.jd.manto.center.widget.recycler.b.COMPLETE || PullUpLoadRecyclerView.this.f6616h == com.jd.manto.center.widget.recycler.b.EMPTY) {
                return;
            }
            PullUpLoadRecyclerView.this.i();
            if (PullUpLoadRecyclerView.this.f6617i != null) {
                PullUpLoadRecyclerView.this.f6617i.onLoad();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            this.a = PullUpLoadRecyclerView.this.f6619k.findLastVisibleItemPosition();
            this.b = PullUpLoadRecyclerView.this.f6619k.getItemCount();
        }
    }

    /* loaded from: classes17.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PullUpLoadRecyclerView.this.f6617i != null) {
                PullUpLoadRecyclerView.this.i();
                PullUpLoadRecyclerView.this.f6617i.onLoad();
            }
        }
    }

    public PullUpLoadRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void d() {
        this.f6616h = com.jd.manto.center.widget.recycler.b.EMPTY;
        addOnScrollListener(new a());
    }

    private HeaderFooterRecyclerAdapterWrapper k(RecyclerView.Adapter adapter) {
        if (adapter instanceof HeaderFooterRecyclerAdapterWrapper) {
            return (HeaderFooterRecyclerAdapterWrapper) adapter;
        }
        return new HeaderFooterRecyclerAdapterWrapper(adapter);
    }

    public void e() {
        this.f6616h = com.jd.manto.center.widget.recycler.b.COMPLETE;
        this.f6615g.a();
    }

    public void f() {
        this.f6616h = com.jd.manto.center.widget.recycler.b.ERROR;
        this.f6615g.b();
        this.f6615g.getView().setOnClickListener(new b());
    }

    public <T extends View & c> void g(T t) {
        if (t != null) {
            c cVar = this.f6615g;
            if (cVar != null) {
                this.f6618j.removeFooterView((View) cVar);
            }
            T t2 = t;
            this.f6615g = t2;
            this.f6618j.a(t2);
            return;
        }
        throw new NullPointerException("loadFooter is null");
    }

    public void h() {
        this.f6616h = com.jd.manto.center.widget.recycler.b.LOADED;
        this.f6615g.d();
    }

    public void i() {
        this.f6616h = com.jd.manto.center.widget.recycler.b.LOADING;
        this.f6615g.c();
    }

    public void j(com.jd.manto.center.widget.recycler.a aVar) {
        this.f6617i = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        HeaderFooterRecyclerAdapterWrapper k2 = k(adapter);
        this.f6618j = k2;
        super.setAdapter(k2);
        g(new DefaultPullLoadFooter(getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            this.f6619k = (LinearLayoutManager) layoutManager;
            super.setLayoutManager(layoutManager);
            return;
        }
        throw new IllegalArgumentException("Only supports LinearLayoutManager");
    }

    public PullUpLoadRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        d();
    }
}
