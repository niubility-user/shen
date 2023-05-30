package com.jingdong.manto.widget.input;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.jingdong.manto.R;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes16.dex */
public class x extends FrameLayout {
    private final int[] a;
    public com.jingdong.manto.widget.i.a b;

    /* renamed from: c  reason: collision with root package name */
    private WeakHashMap<View, d> f14494c;
    private Runnable d;

    /* renamed from: e  reason: collision with root package name */
    private final Runnable f14495e;

    /* renamed from: f  reason: collision with root package name */
    public final Set<c> f14496f;

    /* renamed from: g  reason: collision with root package name */
    public View f14497g;

    /* renamed from: h  reason: collision with root package name */
    private View f14498h;

    /* renamed from: i  reason: collision with root package name */
    int f14499i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f14500j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (x.this.f14497g != null) {
                for (int i2 = 0; i2 < x.this.getChildCount(); i2++) {
                    View childAt = x.this.getChildAt(i2);
                    if (childAt != null && childAt != x.this.f14498h && childAt != x.this.f14497g) {
                        x.setViewGone(childAt);
                    }
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (x.this.f14496f.size() > 0) {
                Iterator<c> it = x.this.f14496f.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public final class d {
        boolean a;
        int b;

        private d(x xVar) {
            this.b = 8;
        }

        /* synthetic */ d(x xVar, a aVar) {
            this(xVar);
        }
    }

    public x(Context context, View view) {
        super(context);
        this.a = new int[2];
        this.f14494c = new WeakHashMap<>();
        this.d = new a();
        this.f14495e = new b();
        this.f14496f = new HashSet();
        this.f14499i = -1;
        this.f14500j = false;
        super.setId(R.id.manto_ui_root);
        this.f14498h = view;
        super.addView(view);
    }

    public static x a(Activity activity) {
        if (activity == null) {
            return null;
        }
        return (x) activity.findViewById(R.id.manto_ui_root);
    }

    public static x b(View view) {
        if (view == null) {
            return null;
        }
        return (x) view.getRootView().findViewById(R.id.manto_ui_root);
    }

    static synchronized void setViewGone(View view) {
        synchronized (x.class) {
            if (view != null) {
                if (view.getVisibility() != 8) {
                    view.setVisibility(8);
                }
            }
        }
    }

    public final void a(View view) {
        a(view, false);
    }

    public final void a(View view, boolean z) {
        View view2 = this.f14497g;
        a aVar = null;
        if (view2 != null) {
            view2.setVisibility(8);
            this.f14497g = null;
        }
        if (this != view.getParent()) {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.f14497g = view;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 80;
            super.addView(view, layoutParams);
            d dVar = new d(this, aVar);
            dVar.a = z;
            this.f14494c.put(view, dVar);
        }
    }

    public boolean a() {
        View view = this.f14497g;
        return view != null && view.getVisibility() == 0;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (view == this.f14498h || view == this.f14497g) {
            super.addView(view);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2) {
        if (view == this.f14498h || view == this.f14497g) {
            super.addView(view, i2);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2, int i3) {
        if (view == this.f14498h || view == this.f14497g) {
            super.addView(view, i2, i3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            View view = this.f14497g;
            if (view == null || !view.isShown()) {
                com.jingdong.manto.utils.e.b(com.jingdong.manto.utils.h.a(getContext()));
            }
            View view2 = this.f14497g;
            if (view2 != null) {
                view2.setVisibility(8);
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View view;
        d dVar = this.f14494c.get(this.f14497g);
        if (dVar != null && dVar.a && (view = this.f14497g) != null && view.isShown() && motionEvent.getAction() == 0) {
            float rawY = motionEvent.getRawY();
            this.f14497g.getLocationOnScreen(this.a);
            float f2 = this.a[1];
            float height = this.f14497g.getHeight() + f2;
            if (rawY < f2 || rawY > height) {
                this.f14497g.setVisibility(8);
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        d dVar;
        this.f14500j = true;
        if (ViewCompat.isLaidOut(this)) {
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = true;
            for (int i6 = 0; i6 < getChildCount(); i6++) {
                View childAt = getChildAt(i6);
                if (childAt != null && childAt != this.f14498h && (dVar = this.f14494c.get(childAt)) != null) {
                    if (!z2 && dVar.b != 0 && childAt.getVisibility() == 0) {
                        this.f14497g = childAt;
                        post(this.d);
                        z2 = true;
                    }
                    z3 |= dVar.b == 0;
                    z4 &= childAt.getVisibility() != 0;
                    dVar.b = childAt.getVisibility();
                }
            }
            if (z2 || (z3 && z4)) {
                post(this.f14495e);
            }
        }
        super.onLayout(z, i2, i3, i4, i5);
        com.jingdong.manto.widget.i.a aVar = this.b;
        if (aVar != null) {
            aVar.a(this);
        }
        this.f14500j = false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i2, int i3) {
        int i4 = this.f14499i;
        if (i4 > 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        super.onMeasure(i2, i3);
    }

    public final void setForceHeight(int i2) {
        Integer num = i2 != this.f14499i ? 1 : null;
        this.f14499i = i2;
        if (num == null || !ViewCompat.isLaidOut(this) || this.f14500j) {
            return;
        }
        requestLayout();
    }

    @Override // android.view.View
    public final void setId(int i2) {
    }
}
