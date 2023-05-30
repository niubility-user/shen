package com.jingdong.app.mall.home.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.common.i.u;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.e;
import com.jingdong.app.mall.home.widget.recommend.HomeRecommend;
import com.jingdong.app.mall.home.widget.recommend.NewHomeRecommendContent;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: j */
    private static final String f11019j = "HomeRecyclerAdapter";

    /* renamed from: k */
    public static int f11020k = 1;

    /* renamed from: c */
    private BaseActivity f11021c;
    private HomeRecycleView d;

    /* renamed from: e */
    private NewHomeRecommendContent f11022e;

    /* renamed from: f */
    private RecyclerView.ViewHolder f11023f;

    /* renamed from: g */
    private RecyclerView.ViewHolder f11024g;

    /* renamed from: h */
    private e f11025h;
    private AtomicBoolean a = new AtomicBoolean(false);
    private AtomicInteger b = new AtomicInteger(0);

    /* renamed from: i */
    private List<d> f11026i = new ArrayList();

    /* loaded from: classes4.dex */
    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
            HomeRecyclerAdapter.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (HomeRecyclerAdapter.this.d.isComputingLayout()) {
                if (HomeRecyclerAdapter.this.b.getAndIncrement() < 2) {
                    HomeRecyclerAdapter.this.A();
                    return;
                }
                return;
            }
            HomeRecyclerAdapter.this.notifyDataSetChanged();
        }
    }

    public HomeRecyclerAdapter(BaseActivity baseActivity, IHomeTitle iHomeTitle, HomeRecycleView homeRecycleView) {
        this.f11021c = baseActivity;
        this.d = homeRecycleView;
        this.f11022e = new NewHomeRecommendContent(homeRecycleView, iHomeTitle, baseActivity);
        this.f11023f = new SimpleViewHolder(this.f11022e);
    }

    private RecyclerView.ViewHolder l(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder a2 = com.jingdong.app.mall.home.widget.a.a(this.f11021c, i2);
        if (a2 != null) {
            return a2;
        }
        if (i2 == 0) {
            return this.f11023f;
        }
        t h2 = u.h(i2);
        if (t.FLOOR_ERROR == h2) {
            return this.f11024g;
        }
        return new SimpleViewHolder(h2.getFloorViewByCache(this.f11021c).getContentView());
    }

    private d m(int i2) {
        try {
            return this.f11026i.get(i2);
        } catch (Exception e2) {
            if (Log.E) {
                Log.e(f11019j, "getItemAt(int position)  Error" + e2.getMessage());
                return null;
            }
            return null;
        }
    }

    private synchronized void s() {
        if (this.f11021c == null) {
            return;
        }
        if (this.f11026i.size() > 0) {
            if (!this.d.isComputingLayout() && f.o0()) {
                notifyDataSetChanged();
            } else {
                f.u0(new a());
            }
        }
    }

    public synchronized void A() {
        try {
            s();
            k.d();
        } catch (Exception e2) {
            e2.printStackTrace();
            z();
        }
    }

    public void B(e eVar) {
        if (eVar == null) {
            return;
        }
        this.f11025h = eVar;
        this.f11024g = new SimpleViewHolder(eVar.z);
        ArrayList arrayList = new ArrayList();
        arrayList.add(eVar);
        this.f11026i = arrayList;
        r(this.d.getHeight());
        this.b.set(0);
        A();
    }

    public void C(List<d> list, boolean z) {
        int size = list.size();
        if (size <= 0) {
            return;
        }
        this.f11026i = list;
        if (s.d.get()) {
            this.f11022e.k(m(size - 1));
        }
        this.b.set(0);
        A();
    }

    public synchronized List<d> getDataList() {
        return this.f11026i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f11026i.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        d m2 = m(i2);
        if (m2 == null) {
            return t.UNKNOWN.getFloorIntType();
        }
        if (t.FLOOR_RECOMMEND == m2.q) {
            return 0;
        }
        int b = com.jingdong.app.mall.home.widget.a.b(m2);
        return b > 0 ? b : m2.i();
    }

    public int n() {
        NewHomeRecommendContent newHomeRecommendContent = this.f11022e;
        if (newHomeRecommendContent != null && (newHomeRecommendContent.getParent() instanceof RecyclerView)) {
            return this.f11022e.getTop();
        }
        return com.jingdong.app.mall.home.floor.common.d.f9278f;
    }

    public HomeRecommend o() {
        NewHomeRecommendContent newHomeRecommendContent = this.f11022e;
        if (newHomeRecommendContent != null) {
            return newHomeRecommendContent.j();
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (view instanceof b) {
                ((b) view).onViewBind(m(i2));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder l2 = l(viewGroup, i2);
        m.f(l2);
        return l2;
    }

    public void onResume() {
        this.f11022e.p();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder == this.f11023f) {
            View view = viewHolder.itemView;
            NewHomeRecommendContent newHomeRecommendContent = this.f11022e;
            if (view == newHomeRecommendContent) {
                newHomeRecommendContent.t();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (view instanceof b) {
                ((b) view).onViewRecycle();
            }
        }
    }

    public boolean p() {
        return this.f11022e.l();
    }

    public void q(d dVar) {
        if (dVar != null && this.f11026i.indexOf(dVar) >= 0) {
            A();
        }
    }

    public void r(int i2) {
        if (this.f11025h == null || this.f11026i.size() > 1) {
            return;
        }
        this.f11025h.x(i2);
    }

    public void t() {
    }

    public void u() {
    }

    public void v() {
        this.f11022e.q();
    }

    public void w() {
        this.f11022e.o();
    }

    public void x(int i2, int i3, int i4) {
        this.f11022e.r(i2, i3, i4);
    }

    public void y(int i2) {
        this.f11022e.s(i2);
    }

    public synchronized void z() {
        AtomicBoolean atomicBoolean;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
            atomicBoolean = this.a;
        }
        if (this.a.get()) {
            this.a.set(false);
            return;
        }
        this.a.set(true);
        this.d.clearCurrentFocus();
        List<d> list = this.f11026i;
        this.f11026i = new ArrayList();
        s();
        this.f11026i = list;
        this.b.set(0);
        this.d.refreshLayoutManager();
        s();
        atomicBoolean = this.a;
        atomicBoolean.set(false);
    }
}
