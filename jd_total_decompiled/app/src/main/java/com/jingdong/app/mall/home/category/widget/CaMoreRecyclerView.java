package com.jingdong.app.mall.home.category.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.category.adapter.CaItemAdapter;
import com.jingdong.app.mall.home.category.floor.floorsub.CaMoreSubFloor;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.g.x.l;
import com.jingdong.app.mall.home.o.a.e;

/* loaded from: classes4.dex */
public class CaMoreRecyclerView extends RecyclerView {

    /* renamed from: g */
    private float f8826g;

    /* renamed from: h */
    private float f8827h;

    /* renamed from: i */
    private View f8828i;

    /* renamed from: j */
    private boolean f8829j;

    public CaMoreRecyclerView(Context context) {
        super(context);
        setNestedScrollingEnabled(false);
        e.a(this);
    }

    private void a(float f2, boolean z) {
        View view;
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() < adapter.getItemCount() - 1 && ((view = this.f8828i) == null || view.getPaddingRight() == 0)) {
                return;
            }
            if (z) {
                if (this.f8827h >= d.d(60)) {
                    e();
                    d(b());
                }
                f(0);
                return;
            }
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                if (childAt instanceof CaMoreSubFloor) {
                    this.f8828i = childAt;
                }
            }
            float abs = this.f8827h + (f2 / (Math.abs(f2 / 120.0f) + 1.0f));
            this.f8827h = abs;
            if (abs > d.d(120)) {
                this.f8827h = d.d(120);
            }
            f((int) this.f8827h);
        }
    }

    private l b() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof CaItemAdapter) {
            com.jingdong.app.mall.home.n.g.u.e a = ((CaItemAdapter) adapter).a(adapter.getItemCount() - 1);
            if (a instanceof l) {
                return (l) a;
            }
            return null;
        }
        return null;
    }

    private boolean c() {
        return b() != null;
    }

    private void e() {
        this.f8827h = 1.0f;
        this.f8826g = -1.0f;
    }

    private void f(int i2) {
        View view = this.f8828i;
        if (view != null) {
            if (i2 < 0) {
                i2 = 0;
            }
            view.setPadding(0, 0, i2, 0);
        }
    }

    protected void d(l lVar) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 5) {
            this.f8829j = c();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.f8829j) {
                if (this.f8826g == -1.0f) {
                    this.f8826g = motionEvent.getRawX();
                }
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action != 1) {
                        if (action == 2) {
                            float rawX = motionEvent.getRawX() - this.f8826g;
                            this.f8826g = motionEvent.getRawX();
                            a(-rawX, false);
                        } else if (action != 5) {
                            if (action != 6) {
                                f(0);
                                e();
                            }
                        }
                    }
                    a(0.0f, true);
                    e();
                }
                this.f8826g = motionEvent.getRawX();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return false;
        }
    }
}
