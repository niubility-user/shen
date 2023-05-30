package com.jingdong.app.mall.home.category.floor.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.category.floor.base.c;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.e;
import com.jingdong.app.mall.home.o.a.f;

/* loaded from: classes4.dex */
public class CaFloatDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: e  reason: collision with root package name */
    public static int f8689e;
    private Pair<Integer, c> a;
    private RelativeLayout b;

    /* renamed from: c  reason: collision with root package name */
    private int f8690c;
    private int d;

    public CaFloatDecoration(RelativeLayout relativeLayout) {
        this.b = relativeLayout;
        relativeLayout.setVisibility(8);
    }

    private void d() {
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.animate().cancel();
            this.b.setTranslationY(0.0f);
        }
        this.d = 0;
        this.f8690c = 1;
    }

    private void e(int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.d = 0;
        if (i2 == this.f8690c) {
            return;
        }
        this.f8690c = i2;
        this.b.setClickable(i2 == 1);
        viewPropertyAnimator.cancel();
        viewPropertyAnimator.translationY(i2 == 1 ? 0.0f : -this.b.getHeight());
    }

    public boolean a() {
        Pair<Integer, c> pair = this.a;
        return pair != null && ((c) pair.second).h(this.b);
    }

    public void b(int i2, int i3) {
        int d = e.a.get() + (d.d(260) << 2);
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null && i3 >= d) {
            ViewPropertyAnimator animate = relativeLayout.animate();
            if (this.b.getVisibility() != 0) {
                d();
                return;
            }
            this.d += i2;
            int d2 = d.d(120);
            if (i2 > 0 && this.d > d2) {
                e(2, animate);
            }
            if (i2 >= 0 || this.d >= (-d2)) {
                return;
            }
            e(1, animate);
            return;
        }
        d();
    }

    public void c() {
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        Pair<Integer, c> pair = this.a;
        if (pair != null) {
            f8689e = 0;
            ((c) pair.second).g(this.b);
            this.a = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (view instanceof c) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            f.n(layoutParams);
            c cVar = (c) view;
            this.a = new Pair<>(Integer.valueOf(((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition()), cVar);
            f8689e = cVar.b();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        if (this.a == null) {
            return;
        }
        if (recyclerView.getChildCount() <= 0) {
            ((c) this.a.second).e(this.b);
            return;
        }
        ViewGroup.LayoutParams layoutParams = recyclerView.getChildAt(0).getLayoutParams();
        f.n(layoutParams);
        if (((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition() < ((Integer) this.a.first).intValue() && !((c) this.a.second).f()) {
            ((c) this.a.second).e(this.b);
        } else {
            ((c) this.a.second).a(this.b);
        }
    }
}
