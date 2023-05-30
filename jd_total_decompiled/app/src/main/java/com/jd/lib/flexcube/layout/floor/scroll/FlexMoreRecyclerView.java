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

    /* JADX WARN: Code restructure failed: missing block: B:157:0x002e, code lost:
        if (r0 != 6) goto L161;
     */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f4385j) {
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Throwable unused) {
                return false;
            }
        }
        if (this.f4387l == -1.0f) {
            this.f4387l = motionEvent.getRawX();
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action == 3) {
                        resetMoreView(0);
                    } else if (action == 5) {
                        this.f4387l = motionEvent.getRawX();
                        this.o = 2;
                    }
                    resetMoreView(0);
                    this.f4387l = -1.0f;
                    this.f4388m = 1.0f;
                } else {
                    if (this.o == 1) {
                        motionEvent.getRawX();
                    } else if (motionEvent.getPointerCount() == 2) {
                        Math.min(motionEvent.getX(0) - this.f4387l, motionEvent.getX(1) - this.f4387l);
                    }
                    float rawX = motionEvent.getRawX() - this.f4387l;
                    String str = "=> getRawX : " + motionEvent.getRawX() + " | mLastX : " + this.f4387l + " | deltaX \uff1a" + rawX;
                    this.f4387l = motionEvent.getRawX();
                    try {
                        if (getLayoutManager() != null && ((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1) {
                            float f2 = this.f4388m + ((-rawX) / 1.5f);
                            this.f4388m = f2;
                            int i2 = this.f4386k;
                            if (f2 > i2) {
                                this.f4388m = i2;
                            }
                            resetMoreView((int) this.f4388m);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            try {
                if (getLayoutManager() != null) {
                    if (((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition() == getAdapter().getItemCount() - 1 && this.f4388m >= this.f4386k) {
                        jump();
                    }
                    resetMoreView(0);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.f4388m = 1.0f;
            this.f4387l = -1.0f;
        } else {
            this.f4387l = motionEvent.getRawX();
            this.o = 1;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable unused2) {
            return false;
        }
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
