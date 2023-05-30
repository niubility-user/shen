package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.ui.view.a;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes4.dex */
public class WheelView extends View {
    private static final int[] y = {-15658735, 11184810, 11184810};

    /* renamed from: g  reason: collision with root package name */
    private int f11994g;

    /* renamed from: h  reason: collision with root package name */
    private int f11995h;

    /* renamed from: i  reason: collision with root package name */
    private int f11996i;

    /* renamed from: j  reason: collision with root package name */
    private Drawable f11997j;

    /* renamed from: k  reason: collision with root package name */
    private GradientDrawable f11998k;

    /* renamed from: l  reason: collision with root package name */
    private GradientDrawable f11999l;

    /* renamed from: m  reason: collision with root package name */
    private com.jingdong.app.mall.utils.ui.view.a f12000m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f12001n;
    private int o;
    boolean p;
    private LinearLayout q;
    private int r;
    private com.jingdong.app.mall.utils.ui.view.b s;
    private g t;
    private List<d> u;
    private List<f> v;
    private List<e> w;
    a.c x;

    /* loaded from: classes4.dex */
    class a implements a.c {
        a() {
        }

        @Override // com.jingdong.app.mall.utils.ui.view.a.c
        public void a() {
            if (Math.abs(WheelView.this.o) > 1) {
                WheelView.this.f12000m.l(WheelView.this.o, 0);
            }
        }

        @Override // com.jingdong.app.mall.utils.ui.view.a.c
        public void b() {
            WheelView.this.f12001n = true;
            WheelView.this.B();
        }

        @Override // com.jingdong.app.mall.utils.ui.view.a.c
        public void c(int i2) {
            WheelView.this.k(i2);
            int height = WheelView.this.getHeight();
            if (WheelView.this.o > height) {
                WheelView.this.o = height;
                WheelView.this.f12000m.o();
                return;
            }
            int i3 = -height;
            if (WheelView.this.o < i3) {
                WheelView.this.o = i3;
                WheelView.this.f12000m.o();
            }
        }

        @Override // com.jingdong.app.mall.utils.ui.view.a.c
        public void onFinished() {
            if (WheelView.this.f12001n) {
                WheelView.this.A();
                WheelView.this.f12001n = false;
            }
            WheelView.this.o = 0;
            WheelView.this.invalidate();
        }
    }

    /* loaded from: classes4.dex */
    class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            WheelView.this.u(false);
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            WheelView.this.u(true);
        }
    }

    /* loaded from: classes4.dex */
    public class c {
        private int a;
        private int b;

        public c(WheelView wheelView) {
            this(wheelView, 0, 0);
        }

        public boolean a(int i2) {
            return i2 >= c() && i2 <= d();
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.a;
        }

        public int d() {
            return (c() + b()) - 1;
        }

        public c(WheelView wheelView, int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }
    }

    /* loaded from: classes4.dex */
    public interface d {
        void a(WheelView wheelView, int i2, int i3);
    }

    /* loaded from: classes4.dex */
    public interface e {
        void a(WheelView wheelView, int i2);
    }

    /* loaded from: classes4.dex */
    public interface f {
        void a(WheelView wheelView);

        void b(WheelView wheelView);
    }

    /* loaded from: classes4.dex */
    public class g {
        private List<View> a;
        private List<View> b;

        /* renamed from: c  reason: collision with root package name */
        private WheelView f12002c;

        public g(WheelView wheelView, WheelView wheelView2) {
            this.f12002c = wheelView2;
        }

        private List<View> a(View view, List<View> list) {
            if (list == null) {
                list = new LinkedList<>();
            }
            list.add(view);
            return list;
        }

        private View c(List<View> list) {
            if (list == null || list.size() <= 0) {
                return null;
            }
            View view = list.get(0);
            list.remove(0);
            return view;
        }

        private void g(View view, int i2) {
            int itemsCount = this.f12002c.r().getItemsCount();
            if ((i2 < 0 || i2 >= itemsCount) && !this.f12002c.v()) {
                this.b = a(view, this.b);
                return;
            }
            while (i2 < 0) {
                i2 += itemsCount;
            }
            this.a = a(view, this.a);
        }

        public void b() {
            List<View> list = this.a;
            if (list != null) {
                list.clear();
            }
            List<View> list2 = this.b;
            if (list2 != null) {
                list2.clear();
            }
        }

        public View d() {
            return c(this.b);
        }

        public View e() {
            return c(this.a);
        }

        public int f(LinearLayout linearLayout, int i2, c cVar) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < linearLayout.getChildCount()) {
                if (cVar.a(i3)) {
                    i4++;
                } else {
                    g(linearLayout.getChildAt(i4), i3);
                    linearLayout.removeViewAt(i4);
                    if (i4 == 0) {
                        i2++;
                    }
                }
                i3++;
            }
            return i2;
        }
    }

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11994g = 0;
        this.f11995h = 5;
        this.f11996i = 0;
        this.p = false;
        this.t = new g(this, this);
        this.u = new LinkedList();
        this.v = new LinkedList();
        this.w = new LinkedList();
        this.x = new a();
        new b();
        s(context);
    }

    private boolean C() {
        boolean z;
        c q = q();
        LinearLayout linearLayout = this.q;
        if (linearLayout != null) {
            int f2 = this.t.f(linearLayout, this.r, q);
            z = this.r != f2;
            this.r = f2;
        } else {
            j();
            z = true;
        }
        if (q == null) {
            return z;
        }
        if (!z) {
            z = (this.r == q.c() && this.q.getChildCount() == q.b()) ? false : true;
        }
        if (this.r > q.c() && this.r <= q.d()) {
            for (int i2 = this.r - 1; i2 >= q.c() && g(i2, true); i2--) {
                this.r = i2;
            }
        } else {
            this.r = q.c();
        }
        int i3 = this.r;
        for (int childCount = this.q.getChildCount(); childCount < q.b(); childCount++) {
            if (!g(this.r + childCount, false) && this.q.getChildCount() == 0) {
                i3++;
            }
        }
        this.r = i3;
        return z;
    }

    private void F() {
        if (C()) {
            i(getWidth(), 1073741824);
            x(getWidth(), getHeight());
        }
    }

    private boolean g(int i2, boolean z) {
        View p = p(i2);
        if (p != null) {
            if (z) {
                this.q.addView(p, 0);
                return true;
            }
            this.q.addView(p);
            return true;
        }
        return false;
    }

    private void h() {
        LinearLayout linearLayout = this.q;
        if (linearLayout != null) {
            this.t.f(linearLayout, this.r, new c(this));
        } else {
            j();
        }
        int i2 = this.f11995h >> 1;
        for (int i3 = this.f11994g + i2; i3 >= this.f11994g - i2; i3--) {
            if (g(i3, true)) {
                this.r = i3;
            }
        }
    }

    private int i(int i2, int i3) {
        t();
        this.q.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.q.measure(View.MeasureSpec.makeMeasureSpec(i2, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.q.getMeasuredWidth();
        if (i3 != 1073741824) {
            int max = Math.max(measuredWidth + 10, getSuggestedMinimumWidth());
            if (i3 != Integer.MIN_VALUE || i2 >= max) {
                i2 = max;
            }
        }
        this.q.measure(View.MeasureSpec.makeMeasureSpec(i2 - 10, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return i2;
    }

    private void j() {
        if (this.q == null) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.q = linearLayout;
            linearLayout.setOrientation(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(int i2) {
        this.o += i2;
        int o = o();
        int i3 = this.o / o;
        int i4 = this.f11994g - i3;
        int itemsCount = this.s.getItemsCount();
        int i5 = this.o % o;
        if (Math.abs(i5) <= (o >> 1)) {
            i5 = 0;
        }
        if (this.p && itemsCount > 0) {
            if (i5 > 0) {
                i4--;
                i3++;
            } else if (i5 < 0) {
                i4++;
                i3--;
            }
            while (i4 < 0) {
                i4 += itemsCount;
            }
            i4 %= itemsCount;
        } else if (i4 < 0) {
            i3 = this.f11994g;
            i4 = 0;
        } else if (i4 >= itemsCount) {
            i3 = (this.f11994g - itemsCount) + 1;
            i4 = itemsCount - 1;
        } else if (i4 > 0 && i5 > 0) {
            i4--;
            i3++;
        } else if (i4 < itemsCount - 1 && i5 < 0) {
            i4++;
            i3--;
        }
        int i6 = this.o;
        if (i4 != this.f11994g) {
            E(i4, false);
        } else {
            invalidate();
        }
        int i7 = i6 - (i3 * o);
        this.o = i7;
        if (i7 > getHeight()) {
            this.o = (this.o % getHeight()) + getHeight();
        }
    }

    private void l(Canvas canvas) {
        int height = getHeight() >> 1;
        double o = o() >> 1;
        Double.isNaN(o);
        int i2 = (int) (o * 1.2d);
        this.f11997j.setBounds(-1, height - i2, getWidth() + 1, height + i2);
        this.f11997j.draw(canvas);
    }

    private void m(Canvas canvas) {
        canvas.save();
        canvas.translate(5.0f, (-(((this.f11994g - this.r) * o()) + ((o() - getHeight()) >> 1))) + this.o);
        this.q.draw(canvas);
        canvas.restore();
    }

    private int n(LinearLayout linearLayout) {
        if (linearLayout != null && linearLayout.getChildAt(0) != null) {
            this.f11996i = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        int i2 = this.f11996i;
        return Math.max((this.f11995h * i2) - ((i2 * 10) / 50), getSuggestedMinimumHeight());
    }

    private int o() {
        int i2 = this.f11996i;
        if (i2 != 0) {
            return i2;
        }
        LinearLayout linearLayout = this.q;
        if (linearLayout != null && linearLayout.getChildAt(0) != null) {
            int height = this.q.getChildAt(0).getHeight();
            this.f11996i = height;
            return height;
        }
        return getHeight() / this.f11995h;
    }

    private View p(int i2) {
        com.jingdong.app.mall.utils.ui.view.b bVar = this.s;
        if (bVar == null || bVar.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.s.getItemsCount();
        if (!w(i2)) {
            return this.s.b(this.t.d(), this.q);
        }
        while (i2 < 0) {
            i2 += itemsCount;
        }
        return this.s.a(i2 % itemsCount, this.t.e(), this.q);
    }

    private c q() {
        if (o() == 0) {
            return null;
        }
        int i2 = this.f11994g;
        int i3 = 1;
        while (o() * i3 < getHeight()) {
            i2--;
            i3 += 2;
        }
        int i4 = this.o;
        if (i4 != 0) {
            if (i4 > 0) {
                i2--;
            }
            int o = i4 / o();
            i2 -= o;
            double d2 = i3 + 1;
            double asin = Math.asin(o);
            Double.isNaN(d2);
            i3 = (int) (d2 + asin);
        }
        return new c(this, i2, i3);
    }

    private void s(Context context) {
        this.f12000m = new com.jingdong.app.mall.utils.ui.view.a(getContext(), this.x);
    }

    private void t() {
        if (this.f11997j == null) {
            this.f11997j = getContext().getResources().getDrawable(R.drawable.wheel_val);
        }
        if (this.f11998k == null) {
            this.f11998k = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, y);
        }
        if (this.f11999l == null) {
            this.f11999l = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, y);
        }
        setBackgroundColor(getResources().getColor(R.color.lx));
    }

    private boolean w(int i2) {
        com.jingdong.app.mall.utils.ui.view.b bVar = this.s;
        return bVar != null && bVar.getItemsCount() > 0 && (this.p || (i2 >= 0 && i2 < this.s.getItemsCount()));
    }

    private void x(int i2, int i3) {
        this.q.layout(0, 0, i2 - 10, i3);
    }

    protected void A() {
        Iterator<f> it = this.v.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    protected void B() {
        Iterator<f> it = this.v.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    public void D(int i2, int i3) {
        this.f12000m.l((i2 * o()) - this.o, i3);
    }

    public void E(int i2, boolean z) {
        int min;
        com.jingdong.app.mall.utils.ui.view.b bVar = this.s;
        if (bVar == null || bVar.getItemsCount() == 0) {
            return;
        }
        int itemsCount = this.s.getItemsCount();
        if (i2 < 0 || i2 >= itemsCount) {
            if (!this.p) {
                return;
            }
            while (i2 < 0) {
                i2 += itemsCount;
            }
            i2 %= itemsCount;
        }
        int i3 = this.f11994g;
        if (i2 != i3) {
            if (z) {
                int i4 = i2 - i3;
                if (this.p && (min = (itemsCount + Math.min(i2, i3)) - Math.max(i2, this.f11994g)) < Math.abs(i4)) {
                    i4 = i4 < 0 ? min : -min;
                }
                D(i4, 0);
                return;
            }
            this.o = 0;
            this.f11994g = i2;
            y(i3, i2);
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        com.jingdong.app.mall.utils.ui.view.b bVar = this.s;
        if (bVar == null || bVar.getItemsCount() <= 0) {
            return;
        }
        F();
        m(canvas);
        l(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        x(i4 - i2, i5 - i3);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        h();
        int i4 = i(size, mode);
        if (mode2 != 1073741824) {
            int n2 = n(this.q);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(n2, size2) : n2;
        }
        setMeasuredDimension(i4, size2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int o;
        if (!isEnabled() || r() == null) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2 && getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (!this.f12001n) {
            int y2 = ((int) motionEvent.getY()) - (getHeight() >> 1);
            if (y2 > 0) {
                o = y2 + (o() >> 1);
            } else {
                o = y2 - (o() >> 1);
            }
            int o2 = o / o();
            if (o2 != 0 && w(this.f11994g + o2)) {
                z(this.f11994g + o2);
            }
        }
        return this.f12000m.k(motionEvent);
    }

    public com.jingdong.app.mall.utils.ui.view.b r() {
        return this.s;
    }

    public void u(boolean z) {
        if (z) {
            this.t.b();
            LinearLayout linearLayout = this.q;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
            this.o = 0;
        } else {
            LinearLayout linearLayout2 = this.q;
            if (linearLayout2 != null) {
                this.t.f(linearLayout2, this.r, new c(this));
            }
        }
        invalidate();
    }

    public boolean v() {
        return this.p;
    }

    protected void y(int i2, int i3) {
        Iterator<d> it = this.u.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2, i3);
        }
    }

    protected void z(int i2) {
        Iterator<e> it = this.w.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2);
        }
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11994g = 0;
        this.f11995h = 5;
        this.f11996i = 0;
        this.p = false;
        this.t = new g(this, this);
        this.u = new LinkedList();
        this.v = new LinkedList();
        this.w = new LinkedList();
        this.x = new a();
        new b();
        s(context);
    }
}
