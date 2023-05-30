package com.jd.lib.flexcube.layout.floor.scroll;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.flexcube.layout.floor.scroll.FlexScrollLayout;

/* loaded from: classes14.dex */
public class FlexMoreRecyclerView extends RecyclerView {

    /* renamed from: g */
    private d f4382g;

    /* renamed from: h */
    private c f4383h;

    /* renamed from: i */
    protected int f4384i;

    /* renamed from: j */
    private boolean f4385j;

    /* renamed from: k */
    protected int f4386k;

    /* renamed from: l */
    float f4387l;

    /* renamed from: m */
    float f4388m;

    /* renamed from: n */
    private boolean f4389n;
    private int o;
    private float p;
    private float q;
    private boolean r;
    private RecyclerView.OnScrollListener s;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends RecyclerView.OnScrollListener {
        a() {
            FlexMoreRecyclerView.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            if (i2 == 0 && FlexMoreRecyclerView.this.f4389n) {
                FlexMoreRecyclerView.this.checkForReset();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
        }
    }

    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f4390g;

        b(int i2) {
            FlexMoreRecyclerView.this = r1;
            this.f4390g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView.Adapter adapter = FlexMoreRecyclerView.this.getAdapter();
            if (adapter instanceof FlexScrollLayout.MyRecyclerAdapter) {
                ((FlexScrollLayout.MyRecyclerAdapter) adapter).getView().setPadding(0, 0, this.f4390g, 0);
            }
        }
    }

    /* loaded from: classes14.dex */
    public interface c {
        void a();

        void stop();
    }

    /* loaded from: classes14.dex */
    public interface d {
        void a(View view);
    }

    public FlexMoreRecyclerView(@NonNull Context context) {
        super(context);
        this.f4385j = true;
        this.f4389n = false;
        this.o = 0;
        this.s = new a();
        initView();
    }

    public void checkForReset() {
        int left = getChildAt(0).getLeft();
        if (left == 0) {
            return;
        }
        if (Math.abs(left) > (this.f4384i >> 1)) {
            if (((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition() < getAdapter().getItemCount() - 1) {
                smoothScrollBy(this.f4384i - Math.abs(left), 0);
                return;
            }
            return;
        }
        smoothScrollBy(-Math.abs(left), 0);
    }

    private void initView() {
        setFocusable(false);
        setOnScrollListener(this.s);
        this.f4384i = 100;
        this.f4386k = 20;
    }

    private void jump() {
        d dVar = this.f4382g;
        if (dVar != null) {
            dVar.a(null);
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void resetMoreView(int i2) {
        post(new b(i2));
    }

    public void c(c cVar) {
        this.f4383h = cVar;
    }

    public void d(d dVar) {
        this.f4382g = dVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            c cVar = this.f4383h;
            if (cVar != null) {
                cVar.stop();
            }
            requestParentDisallowInterceptTouchEvent(true);
        } else if (action == 1) {
            this.r = false;
            c cVar2 = this.f4383h;
            if (cVar2 != null) {
                cVar2.a();
            }
        } else if (action == 2) {
            float abs = Math.abs(x - this.p);
            float abs2 = Math.abs(y - this.q);
            if (!this.r && abs <= abs2) {
                requestParentDisallowInterceptTouchEvent(false);
            }
            this.r = true;
        } else if (action == 3) {
            this.r = false;
            c cVar3 = this.f4383h;
            if (cVar3 != null) {
                cVar3.a();
            }
        }
        this.p = x;
        this.q = y;
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x002e, code lost:
        if (r0 != 6) goto L93;
     */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.flexcube.layout.floor.scroll.FlexMoreRecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setGoRedirect(boolean z) {
        this.f4385j = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        if (i2 == 1) {
            return false;
        }
        return super.startNestedScroll(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i2, int i3) {
        if (i2 == 1) {
            return false;
        }
        return super.startNestedScroll(i2, i3);
    }
}
