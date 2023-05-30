package com.jd.lib.un.basewidget.widget.simple.abs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout;
import com.jd.lib.un.basewidget.widget.simple.a.b;
import com.jd.lib.un.basewidget.widget.simple.a.c;
import com.jd.lib.un.basewidget.widget.simple.c.d;
import com.jd.lib.un.basewidget.widget.simple.c.e;
import com.jd.lib.un.basewidget.widget.simple.c.f;
import com.jd.lib.un.basewidget.widget.simple.wrapper.RefreshFooterWrapper;
import com.jd.lib.un.basewidget.widget.simple.wrapper.RefreshHeaderWrapper;

/* loaded from: classes16.dex */
public abstract class AbsRefreshInternal extends RelativeLayout implements d {

    /* renamed from: g  reason: collision with root package name */
    protected View f5775g;

    /* renamed from: h  reason: collision with root package name */
    protected b f5776h;

    /* renamed from: i  reason: collision with root package name */
    protected d f5777i;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbsRefreshInternal(@NonNull View view) {
        this(view, view instanceof d ? (d) view : null);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    @Deprecated
    public void c(@ColorInt int... iArr) {
        d dVar = this.f5777i;
        if (dVar == null || dVar == this) {
            return;
        }
        dVar.c(iArr);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    public void d(@NonNull e eVar, int i2, int i3) {
        d dVar = this.f5777i;
        if (dVar != null && dVar != this) {
            dVar.d(eVar, i2, i3);
            return;
        }
        View view = this.f5775g;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof SimpleRefreshLayout.LayoutParams) {
                eVar.e(this, ((SimpleRefreshLayout.LayoutParams) layoutParams).a);
            }
        }
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    public void e(@NonNull f fVar, int i2, int i3) {
        d dVar = this.f5777i;
        if (dVar == null || dVar == this) {
            return;
        }
        dVar.e(fVar, i2, i3);
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        return (obj instanceof d) && getView() == ((d) obj).getView();
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    @NonNull
    public View getView() {
        View view = this.f5775g;
        return view == null ? this : view;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    public void h(@NonNull f fVar, int i2, int i3) {
        d dVar = this.f5777i;
        if (dVar == null || dVar == this) {
            return;
        }
        dVar.h(fVar, i2, i3);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    @NonNull
    public b i() {
        int i2;
        b bVar = this.f5776h;
        if (bVar != null) {
            return bVar;
        }
        d dVar = this.f5777i;
        if (dVar != null && dVar != this) {
            return dVar.i();
        }
        View view = this.f5775g;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof SimpleRefreshLayout.LayoutParams) {
                b bVar2 = ((SimpleRefreshLayout.LayoutParams) layoutParams).b;
                this.f5776h = bVar2;
                if (bVar2 != null) {
                    return bVar2;
                }
            }
            if (layoutParams != null && ((i2 = layoutParams.height) == 0 || i2 == -1)) {
                b bVar3 = b.SCALE;
                this.f5776h = bVar3;
                return bVar3;
            }
        }
        b bVar4 = b.TRANSLATE;
        this.f5776h = bVar4;
        return bVar4;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.d.e
    public void m(@NonNull f fVar, @NonNull c cVar, @NonNull c cVar2) {
        d dVar = this.f5777i;
        if (dVar == null || dVar == this) {
            return;
        }
        if ((this instanceof RefreshFooterWrapper) && (dVar instanceof com.jd.lib.un.basewidget.widget.simple.c.c)) {
            if (cVar.isFooter) {
                cVar = cVar.toHeader();
            }
            if (cVar2.isFooter) {
                cVar2 = cVar2.toHeader();
            }
        } else if ((this instanceof RefreshHeaderWrapper) && (dVar instanceof com.jd.lib.un.basewidget.widget.simple.c.b)) {
            if (cVar.isHeader) {
                cVar = cVar.toFooter();
            }
            if (cVar2.isHeader) {
                cVar2 = cVar2.toFooter();
            }
        }
        this.f5777i.m(fVar, cVar, cVar2);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    public void o(boolean z, float f2, int i2, int i3, int i4) {
        d dVar = this.f5777i;
        if (dVar == null || dVar == this) {
            return;
        }
        dVar.o(z, f2, i2, i3, i4);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.d
    public int q(@NonNull f fVar, boolean z) {
        d dVar = this.f5777i;
        if (dVar == null || dVar == this) {
            return 0;
        }
        return dVar.q(fVar, z);
    }

    protected AbsRefreshInternal(@NonNull View view, @Nullable d dVar) {
        super(view.getContext(), null, 0);
        this.f5775g = view;
        this.f5777i = dVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbsRefreshInternal(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
