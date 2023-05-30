package com.jd.lib.un.basewidget.widget.simple.b;

import android.graphics.PointF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import com.jd.lib.un.basewidget.widget.simple.c.g;

/* loaded from: classes16.dex */
public class b implements g {
    public PointF a;
    public g b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f5780c = true;

    @Override // com.jd.lib.un.basewidget.widget.simple.c.g
    public boolean a(View view) {
        g gVar = this.b;
        if (gVar != null) {
            return gVar.a(view);
        }
        return d(view, this.a);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.g
    public boolean b(View view) {
        g gVar = this.b;
        if (gVar != null) {
            return gVar.b(view);
        }
        return c(view, this.a, this.f5780c);
    }

    public boolean c(@NonNull View view, PointF pointF, boolean z) {
        if (e(view) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && pointF != null && !com.jd.lib.un.basewidget.widget.simple.e.b.d(view)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (g(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean c2 = c(childAt, pointF, z);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return c2;
                }
            }
        }
        return z || f(view);
    }

    public boolean d(@NonNull View view, PointF pointF) {
        if (f(view) && view.getVisibility() == 0) {
            return false;
        }
        if (!(view instanceof ViewGroup) || pointF == null) {
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        PointF pointF2 = new PointF();
        for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount - 1);
            if (g(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                pointF.offset(pointF2.x, pointF2.y);
                boolean d = d(childAt, pointF);
                pointF.offset(-pointF2.x, -pointF2.y);
                return d;
            }
        }
        return true;
    }

    public boolean e(@NonNull View view) {
        int i2;
        if (Build.VERSION.SDK_INT < 14) {
            if (!(view instanceof AbsListView)) {
                return view.getScrollY() < 0;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            AbsListView absListView = (AbsListView) view;
            int childCount = viewGroup.getChildCount();
            return childCount > 0 && (absListView.getLastVisiblePosition() < (i2 = childCount - 1) || viewGroup.getChildAt(i2).getBottom() > view.getPaddingBottom());
        }
        return view.canScrollVertically(1);
    }

    public boolean f(@NonNull View view) {
        if (Build.VERSION.SDK_INT < 14) {
            if (!(view instanceof AbsListView)) {
                return view.getScrollY() > 0;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            return viewGroup.getChildCount() > 0 && (((AbsListView) view).getFirstVisiblePosition() > 0 || viewGroup.getChildAt(0).getTop() < view.getPaddingTop());
        }
        return view.canScrollVertically(-1);
    }

    public boolean g(@NonNull View view, @NonNull View view2, float f2, float f3, PointF pointF) {
        if (view2.getVisibility() != 0) {
            return false;
        }
        float[] fArr = {f2, f3};
        fArr[0] = fArr[0] + (view.getScrollX() - view2.getLeft());
        fArr[1] = fArr[1] + (view.getScrollY() - view2.getTop());
        boolean z = fArr[0] >= 0.0f && fArr[1] >= 0.0f && fArr[0] < ((float) view2.getWidth()) && fArr[1] < ((float) view2.getHeight());
        if (z && pointF != null) {
            pointF.set(fArr[0] - f2, fArr[1] - f3);
        }
        return z;
    }
}
