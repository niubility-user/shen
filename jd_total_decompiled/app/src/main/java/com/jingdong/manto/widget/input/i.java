package com.jingdong.manto.widget.input;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.q.g;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes16.dex */
public class i extends AbsoluteLayout implements com.jingdong.manto.q.g, com.jingdong.manto.q.h {
    private final com.jingdong.manto.q.n a;
    private final List<g.a> b;

    /* renamed from: c */
    private final AbsoluteLayout f14458c;
    private boolean d;

    /* renamed from: e */
    private boolean f14459e;

    /* renamed from: f */
    private MotionEvent f14460f;

    public i(Context context, com.jingdong.manto.q.n nVar) {
        super(context);
        this.b = new LinkedList();
        this.d = false;
        this.f14459e = false;
        super.setId(R.id.manto_input_container);
        this.a = nVar;
        this.f14458c = this;
    }

    private void a(com.jingdong.manto.q.r rVar) {
        if (this.f14458c.getWidth() != rVar.getWidth() || this.f14458c.getHeight() != rVar.getHeight()) {
            ViewGroup.LayoutParams layoutParams = this.f14458c.getLayoutParams();
            layoutParams.width = rVar.getWidth();
            layoutParams.height = rVar.getHeight();
            this.f14458c.setLayoutParams(layoutParams);
        }
        if (this.f14458c.getScrollX() == rVar.getWebScrollX() && this.f14458c.getScrollY() == rVar.getWebScrollY()) {
            return;
        }
        this.f14458c.scrollTo(rVar.getWebScrollX(), rVar.getWebScrollY());
    }

    public final <Input extends View> void a(Input input) {
        if (input != null) {
            input.setVisibility(8);
            this.f14458c.removeView(input);
            ((com.jingdong.manto.widget.input.z.d) input).b(this.a);
        }
    }

    public final void a(View view, MotionEvent motionEvent, boolean z) {
        if (view instanceof CoverViewContainer) {
            this.d = z;
            this.f14459e = true;
            if (z || this.a.t == null || this.f14460f == null) {
                return;
            }
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(0);
            obtain.setLocation(this.f14460f.getX(), this.f14460f.getY());
            this.a.t.dispatchTouchEvent(obtain);
            obtain.recycle();
        }
    }

    @Override // com.jingdong.manto.q.g
    public final void a(g.a aVar) {
        if (aVar != null) {
            this.b.remove(aVar);
        }
    }

    public final <Input extends View & com.jingdong.manto.widget.input.z.d> boolean a(com.jingdong.manto.q.r rVar, Input input, int i2, int i3, int i4, int i5) {
        boolean z;
        if (rVar != null && input != null) {
            if (input != null && this.f14458c != null) {
                for (int i6 = 0; i6 < this.f14458c.getChildCount(); i6++) {
                    if (input == this.f14458c.getChildAt(i6)) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
                return false;
            }
            a(rVar);
            if (input.getLayoutParams() != null && (input.getLayoutParams() instanceof AbsoluteLayout.LayoutParams)) {
                if (input.getWidth() != i2 || input.getHeight() != i3 || input.getLeft() != i4 || input.getTop() != i5) {
                    AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) input.getLayoutParams();
                    layoutParams.x = i4;
                    layoutParams.y = i5;
                    layoutParams.width = i2;
                    layoutParams.height = i3;
                    input.setLayoutParams(layoutParams);
                }
                return true;
            }
        }
        return false;
    }

    public final <Input extends View> boolean a(com.jingdong.manto.q.r rVar, Input input, int i2, int i3, int i4, int i5, boolean z) {
        if (rVar == null || rVar.getView() == null || input == null) {
            return false;
        }
        a(rVar);
        this.f14458c.addView(input, z ? new AbsoluteLayout.LayoutParams(i2, i3, i4, i5 + this.f14458c.getScrollY()) : new AbsoluteLayout.LayoutParams(i2, i3, i4, i5));
        ((com.jingdong.manto.widget.input.z.d) input).a(this.a);
        return true;
    }

    @Override // com.jingdong.manto.q.g
    public final void b(g.a aVar) {
        if (aVar == null || this.b.contains(aVar)) {
            return;
        }
        this.b.add(aVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        com.jingdong.manto.q.r rVar;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f14460f = motionEvent;
        }
        if (actionMasked != 0 && !this.d && (rVar = this.a.t) != null && this.f14459e) {
            rVar.dispatchTouchEvent(motionEvent);
        }
        if (actionMasked == 3 || actionMasked == 1 || actionMasked == 0) {
            this.d = false;
            this.f14459e = false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.manto.q.h
    public final void onScrollChanged(int i2, int i3, int i4, int i5, View view) {
        AbsoluteLayout absoluteLayout = this.f14458c;
        if (absoluteLayout != null) {
            ViewGroup.LayoutParams layoutParams = absoluteLayout.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(view.getWidth(), view.getHeight());
                this.f14458c.setLayoutParams(layoutParams);
            }
            if (view.getWidth() != layoutParams.width || view.getHeight() != layoutParams.height) {
                layoutParams.width = view.getWidth();
                layoutParams.height = view.getHeight();
                this.f14458c.setLayoutParams(layoutParams);
            }
            this.f14458c.scrollTo(i2, i3);
        }
        Iterator<g.a> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a(i2, i3);
        }
    }

    @Override // android.widget.AbsoluteLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
