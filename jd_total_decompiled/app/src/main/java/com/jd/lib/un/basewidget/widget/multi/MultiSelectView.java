package com.jd.lib.un.basewidget.widget.multi;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.multi.ui.MultiContentAdapter;
import com.jd.lib.un.basewidget.widget.multi.ui.MultiIndicator;
import com.jd.lib.un.basewidget.widget.multi.ui.MultiTagLayout;
import java.util.List;

/* loaded from: classes16.dex */
public class MultiSelectView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    private com.jd.lib.un.basewidget.widget.multi.b.b f5712g;

    /* renamed from: h  reason: collision with root package name */
    private int f5713h;

    /* renamed from: i  reason: collision with root package name */
    private int f5714i;

    /* renamed from: j  reason: collision with root package name */
    private int f5715j;

    /* renamed from: k  reason: collision with root package name */
    private int f5716k;

    /* renamed from: l  reason: collision with root package name */
    private int f5717l;

    /* renamed from: m  reason: collision with root package name */
    private int f5718m;

    /* renamed from: n  reason: collision with root package name */
    private int f5719n;
    private MultiTagLayout o;
    private MultiIndicator p;
    private RecyclerView q;
    private MultiContentAdapter r;
    private HorizontalScrollView s;
    private View t;
    private com.jd.lib.un.basewidget.widget.multi.a.a<String> u;
    private com.jd.lib.un.basewidget.widget.multi.a.a<List<String>> v;
    private Handler w;
    private int x;
    private boolean y;
    private f z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements MultiContentAdapter.b {
        a() {
        }

        @Override // com.jd.lib.un.basewidget.widget.multi.ui.MultiContentAdapter.b
        public void a(int i2, String str) {
            if (str == null || MultiSelectView.this.u.contains(str)) {
                return;
            }
            MultiSelectView.this.l(str);
            MultiSelectView.this.G(str);
            MultiSelectView.this.D();
            MultiSelectView.this.q(str, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements MultiTagLayout.a {
        b() {
        }

        @Override // com.jd.lib.un.basewidget.widget.multi.ui.MultiTagLayout.a
        public void a(int i2, View view) {
            MultiSelectView.this.m(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f5720g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f5721h;

        c(int i2, int i3) {
            this.f5720g = i2;
            this.f5721h = i3;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            MultiSelectView.this.o.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MultiSelectView.this.p.b(MultiSelectView.this.o.getTagView(this.f5720g), MultiSelectView.this.o.getTagView(this.f5721h));
            MultiSelectView.this.s();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d implements com.jd.lib.un.basewidget.widget.multi.b.a {
        d(MultiSelectView multiSelectView, boolean z, String str) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MultiSelectView.this.s.smoothScrollTo(MultiSelectView.this.s.getWidth(), 0);
        }
    }

    /* loaded from: classes16.dex */
    public interface f {
        void a();

        void b();
    }

    public MultiSelectView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private <T> void A(com.jd.lib.un.basewidget.widget.multi.a.a<T> aVar, int i2, T t) {
        if (aVar == null || t == null) {
            return;
        }
        if (i2 >= 0 && i2 <= aVar.size() - 1) {
            aVar.set(i2, t);
        } else {
            aVar.add(t);
        }
    }

    private void B() {
        this.o.setTextSize(this.f5713h);
        this.r.s(this.f5714i);
        this.o.setSelectedTextColor(this.f5715j);
        this.o.setNormalTextColor(this.f5716k);
        this.p.c(this.f5715j);
        this.r.o(this.f5716k);
        this.r.q(this.f5715j);
        this.t.setLayoutParams(new LinearLayout.LayoutParams(-1, this.f5718m));
        this.t.setBackgroundColor(this.f5717l);
        this.p.d(this.f5719n);
    }

    private void C() {
        this.y = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        y(this.u, this.x + 1, r0.size() - 1);
        y(this.v, this.x + 1, r0.size() - 1);
    }

    private void E(int i2, int i3) {
        int tagSize;
        if (i2 != i3 && this.o.getTagSize() - 1 >= i2 && tagSize >= i3 && i2 >= 0 && i3 >= 0) {
            this.p.b(this.o.getTagView(i2), this.o.getTagView(i3));
        }
    }

    private void F(int i2, int i3) {
        this.o.getViewTreeObserver().addOnGlobalLayoutListener(new c(i2, i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(String str) {
        int i2 = this.x;
        int tagSize = this.o.getTagSize() - 1;
        if (tagSize > i2) {
            this.o.removeTag(i2 + 1, tagSize);
        }
        this.o.updateTag(this.x, str);
        F(-1, this.x);
    }

    private void j() {
        LayoutInflater.from(getContext()).inflate(R.layout.multi_select_layout, (ViewGroup) this, true);
    }

    private void k() {
        this.r.p(new a());
        this.o.setTagClickListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(String str) {
        this.r.r(str);
        this.r.notifyDataSetChanged();
        A(this.u, this.x, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(int i2) {
        int i3 = this.x;
        if (i2 == i3) {
            return;
        }
        this.x = i2;
        List<String> list = (List) z(this.v, i2);
        String str = (String) z(this.u, i2);
        if (list == null && str != null) {
            q(str, true);
        } else {
            x(list, str);
            t(list, str);
        }
        E(i3, i2);
    }

    private void n() {
        u();
    }

    private void o(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MultiSelectView);
            this.f5713h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiSelectView_select_tag_text_size, this.f5713h);
            this.f5714i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiSelectView_select_item_text_size, this.f5714i);
            this.f5715j = obtainStyledAttributes.getColor(R.styleable.MultiSelectView_select_selected_color, this.f5715j);
            this.f5716k = obtainStyledAttributes.getColor(R.styleable.MultiSelectView_select_normal_color, this.f5716k);
            this.f5717l = obtainStyledAttributes.getColor(R.styleable.MultiSelectView_select_divide_line_color, this.f5717l);
            this.f5718m = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiSelectView_select_divide_line_height, this.f5718m);
            this.f5719n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MultiSelectView_select_indicator_height, this.f5719n);
            obtainStyledAttributes.getString(R.styleable.MultiSelectView_select_un_selected_text);
            obtainStyledAttributes.recycle();
        }
        j();
    }

    private void p() {
        this.o = (MultiTagLayout) findViewById(R.id.multi_tag_layout);
        MultiIndicator multiIndicator = (MultiIndicator) findViewById(R.id.multi_indicator_layout);
        this.p = multiIndicator;
        multiIndicator.e(true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.multi_recycle_view);
        this.q = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.q.setHasFixedSize(true);
        MultiContentAdapter multiContentAdapter = new MultiContentAdapter();
        this.r = multiContentAdapter;
        this.q.setAdapter(multiContentAdapter);
        this.t = findViewById(R.id.multi_divide_line_view);
        this.s = (HorizontalScrollView) findViewById(R.id.multi_hor_scroll_view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(String str, boolean z) {
        r();
        v();
        w((this.x == 0 && z) ? null : str, new d(this, z, str));
    }

    private void r() {
        this.y = true;
    }

    private void u() {
        f fVar = this.z;
        if (fVar != null) {
            fVar.a();
        }
    }

    private void v() {
        f fVar = this.z;
        if (fVar != null) {
            fVar.b();
        }
    }

    private void w(String str, com.jd.lib.un.basewidget.widget.multi.b.a aVar) {
        com.jd.lib.un.basewidget.widget.multi.b.b bVar = this.f5712g;
        if (bVar == null) {
            n();
            C();
            return;
        }
        bVar.a(this.x, str, aVar);
        throw null;
    }

    private void x(List<String> list, String str) {
        this.r.r(str);
        this.r.n(list);
        this.r.notifyDataSetChanged();
    }

    private <T> void y(com.jd.lib.un.basewidget.widget.multi.a.a<T> aVar, int i2, int i3) {
        if (aVar == null || aVar.isEmpty() || i3 < i2 || i2 < 0 || i3 > aVar.size() - 1) {
            return;
        }
        aVar.removeRanges(i2, i3);
    }

    private <T> T z(com.jd.lib.un.basewidget.widget.multi.a.a<T> aVar, int i2) {
        if (aVar != null && !aVar.isEmpty()) {
            int size = aVar.size();
            if (i2 >= 0 && i2 <= size - 1) {
                return aVar.get(i2);
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.y;
    }

    public void s() {
        this.w.post(new e());
    }

    public void t(List<String> list, String str) {
        int indexOf;
        if (str == null || list == null || list.isEmpty() || (indexOf = list.indexOf(str)) == -1) {
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.q.getLayoutManager();
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (indexOf <= findFirstVisibleItemPosition) {
            this.q.scrollToPosition(indexOf);
        } else if (indexOf <= findLastVisibleItemPosition) {
            this.q.scrollBy(0, this.q.getChildAt(indexOf - findFirstVisibleItemPosition).getTop());
        } else {
            this.q.scrollToPosition(indexOf);
        }
    }

    public MultiSelectView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5713h = com.jd.lib.un.basewidget.widget.multi.c.a.b(13.0f);
        this.f5714i = com.jd.lib.un.basewidget.widget.multi.c.a.b(13.0f);
        this.f5715j = SupportMenu.CATEGORY_MASK;
        this.f5716k = -16777216;
        this.f5717l = -7829368;
        this.f5718m = 1;
        this.f5719n = 3;
        this.u = new com.jd.lib.un.basewidget.widget.multi.a.a<>();
        this.v = new com.jd.lib.un.basewidget.widget.multi.a.a<>();
        this.w = new Handler(Looper.getMainLooper());
        this.x = 0;
        this.y = false;
        o(attributeSet);
        p();
        k();
        B();
    }
}
