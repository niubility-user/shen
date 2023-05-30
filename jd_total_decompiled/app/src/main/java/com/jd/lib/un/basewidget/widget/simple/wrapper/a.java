package com.jd.lib.un.basewidget.widget.simple.wrapper;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingParent;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.un.basewidget.widget.simple.b.b;
import com.jd.lib.un.basewidget.widget.simple.c.e;
import com.jd.lib.un.basewidget.widget.simple.c.g;
import java.util.Collections;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class a implements com.jd.lib.un.basewidget.widget.simple.c.a, com.jd.lib.un.basewidget.widget.simple.d.a, ValueAnimator.AnimatorUpdateListener {

    /* renamed from: g  reason: collision with root package name */
    private View f5796g;

    /* renamed from: h  reason: collision with root package name */
    private View f5797h;

    /* renamed from: i  reason: collision with root package name */
    private View f5798i;

    /* renamed from: j  reason: collision with root package name */
    private View f5799j;

    /* renamed from: k  reason: collision with root package name */
    private View f5800k;

    /* renamed from: l  reason: collision with root package name */
    private int f5801l = 0;

    /* renamed from: m  reason: collision with root package name */
    private boolean f5802m = true;

    /* renamed from: n  reason: collision with root package name */
    private boolean f5803n = true;
    protected b o = new b();

    public a(@NonNull View view) {
        this.f5798i = view;
        this.f5797h = view;
        this.f5796g = view;
    }

    private void j(View view, e eVar) {
        boolean isInEditMode = this.f5796g.isInEditMode();
        View view2 = null;
        while (true) {
            if (view2 != null && (!(view2 instanceof NestedScrollingParent) || (view2 instanceof NestedScrollingChild))) {
                break;
            }
            view = l(view, view2 == null);
            if (view == view2) {
                break;
            }
            if (!isInEditMode) {
                com.jd.lib.un.basewidget.widget.simple.e.b.a(view, eVar, this);
            }
            view2 = view;
        }
        if (view2 != null) {
            this.f5798i = view2;
        }
    }

    private View l(View view, boolean z) {
        LinkedList linkedList = new LinkedList(Collections.singletonList(view));
        View view2 = null;
        while (!linkedList.isEmpty() && view2 == null) {
            View view3 = (View) linkedList.poll();
            if (view3 != null) {
                if ((z || view3 != view) && com.jd.lib.un.basewidget.widget.simple.e.b.c(view3)) {
                    view2 = view3;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        linkedList.add(viewGroup.getChildAt(i2));
                    }
                }
            }
        }
        return view2 == null ? view : view2;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public void a(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        pointF.offset(-this.f5796g.getLeft(), -this.f5796g.getTop());
        View view = this.f5798i;
        View view2 = this.f5796g;
        if (view != view2) {
            this.f5798i = k(view2, pointF, view);
        }
        if (this.f5798i == this.f5796g) {
            this.o.a = null;
        } else {
            this.o.a = pointF;
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public void b(boolean z) {
        this.o.f5780c = z;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public ValueAnimator.AnimatorUpdateListener c(int i2) {
        View view = this.f5798i;
        if (view == null || i2 == 0) {
            return null;
        }
        if ((i2 >= 0 || !this.o.e(view)) && (i2 <= 0 || !this.o.f(this.f5798i))) {
            return null;
        }
        this.f5801l = i2;
        return this;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    @NonNull
    public View d() {
        return this.f5798i;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public boolean e() {
        return this.f5802m && this.o.a(this.f5796g);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public void f(e eVar, View view, View view2) {
        j(this.f5796g, eVar);
        if (view == null && view2 == null) {
            return;
        }
        this.f5799j = view;
        this.f5800k = view2;
        FrameLayout frameLayout = new FrameLayout(this.f5796g.getContext());
        eVar.c().getLayout().removeView(this.f5796g);
        ViewGroup.LayoutParams layoutParams = this.f5796g.getLayoutParams();
        frameLayout.addView(this.f5796g, -1, -1);
        eVar.c().getLayout().addView(frameLayout, layoutParams);
        this.f5796g = frameLayout;
        if (view != null) {
            view.setClickable(true);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            layoutParams2.height = com.jd.lib.un.basewidget.widget.simple.e.b.e(view);
            viewGroup.addView(new Space(this.f5796g.getContext()), indexOfChild, layoutParams2);
            frameLayout.addView(view);
        }
        if (view2 != null) {
            view2.setClickable(true);
            ViewGroup.LayoutParams layoutParams3 = view2.getLayoutParams();
            ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
            int indexOfChild2 = viewGroup2.indexOfChild(view2);
            viewGroup2.removeView(view2);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(layoutParams3);
            layoutParams3.height = com.jd.lib.un.basewidget.widget.simple.e.b.e(view2);
            viewGroup2.addView(new Space(this.f5796g.getContext()), indexOfChild2, layoutParams3);
            layoutParams4.gravity = 80;
            frameLayout.addView(view2, layoutParams4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void g(int i2, int i3, int i4) {
        boolean z;
        View view;
        View view2;
        View findViewById;
        View findViewById2;
        boolean z2 = true;
        if (i3 != -1 && (findViewById2 = this.f5797h.findViewById(i3)) != null) {
            if (i2 > 0) {
                findViewById2.setTranslationY(i2);
                z = true;
                if (i4 != -1 && (findViewById = this.f5797h.findViewById(i4)) != null) {
                    if (i2 >= 0) {
                        findViewById.setTranslationY(i2);
                        if (!z2) {
                            this.f5797h.setTranslationY(i2);
                        } else {
                            this.f5797h.setTranslationY(0.0f);
                        }
                        view = this.f5799j;
                        if (view != null) {
                            view.setTranslationY(Math.max(0, i2));
                        }
                        view2 = this.f5800k;
                        if (view2 != null) {
                            view2.setTranslationY(Math.min(0, i2));
                            return;
                        }
                        return;
                    } else if (findViewById.getTranslationY() < 0.0f) {
                        findViewById.setTranslationY(0.0f);
                    }
                }
                z2 = z;
                if (!z2) {
                }
                view = this.f5799j;
                if (view != null) {
                }
                view2 = this.f5800k;
                if (view2 != null) {
                }
            } else if (findViewById2.getTranslationY() > 0.0f) {
                findViewById2.setTranslationY(0.0f);
            }
        }
        z = false;
        if (i4 != -1) {
            if (i2 >= 0) {
            }
        }
        z2 = z;
        if (!z2) {
        }
        view = this.f5799j;
        if (view != null) {
        }
        view2 = this.f5800k;
        if (view2 != null) {
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    @NonNull
    public View getView() {
        return this.f5796g;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public void h(g gVar) {
        if (gVar instanceof b) {
            this.o = (b) gVar;
        } else {
            this.o.b = gVar;
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.a
    public boolean i() {
        return this.f5803n && this.o.b(this.f5796g);
    }

    protected View k(View view, PointF pointF, View view2) {
        if ((view instanceof ViewGroup) && pointF != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            PointF pointF2 = new PointF();
            for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (this.o.g(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    if ((childAt instanceof ViewPager) || !com.jd.lib.un.basewidget.widget.simple.e.b.c(childAt)) {
                        pointF.offset(pointF2.x, pointF2.y);
                        View k2 = k(childAt, pointF, view2);
                        pointF.offset(-pointF2.x, -pointF2.y);
                        return k2;
                    }
                    return childAt;
                }
            }
        }
        return view2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        try {
            View view = this.f5798i;
            if (view instanceof AbsListView) {
                com.jd.lib.un.basewidget.widget.simple.e.b.f((AbsListView) view, intValue - this.f5801l);
            } else {
                view.scrollBy(0, intValue - this.f5801l);
            }
        } catch (Throwable unused) {
        }
        this.f5801l = intValue;
    }
}
