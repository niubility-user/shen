package com.jd.lib.flexcube.layout.floor.banner.focus.layoutmanager;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* loaded from: classes14.dex */
public class FlexFocusBannerLayoutManager extends RecyclerView.LayoutManager {
    private float A;
    private SparseArray<View> a;
    protected int b;

    /* renamed from: c  reason: collision with root package name */
    protected int f4294c;
    int d;

    /* renamed from: e  reason: collision with root package name */
    protected int f4295e;

    /* renamed from: f  reason: collision with root package name */
    protected int f4296f;

    /* renamed from: g  reason: collision with root package name */
    protected float f4297g;

    /* renamed from: h  reason: collision with root package name */
    protected OrientationHelper f4298h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f4299i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f4300j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f4301k;

    /* renamed from: l  reason: collision with root package name */
    private int f4302l;

    /* renamed from: m  reason: collision with root package name */
    private SavedState f4303m;

    /* renamed from: n  reason: collision with root package name */
    protected float f4304n;
    a o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private Interpolator v;
    private int w;
    private View x;
    private int y;
    private float z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: g  reason: collision with root package name */
        int f4305g;

        /* renamed from: h  reason: collision with root package name */
        float f4306h;

        /* renamed from: i  reason: collision with root package name */
        boolean f4307i;

        /* loaded from: classes14.dex */
        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b  reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f4305g);
            parcel.writeFloat(this.f4306h);
            parcel.writeInt(this.f4307i ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f4305g = parcel.readInt();
            this.f4306h = parcel.readFloat();
            this.f4307i = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f4305g = savedState.f4305g;
            this.f4306h = savedState.f4306h;
            this.f4307i = savedState.f4307i;
        }
    }

    /* loaded from: classes14.dex */
    public interface a {
        void onPageScrollStateChanged(int i2);

        void onPageSelected(int i2);
    }

    public FlexFocusBannerLayoutManager(Context context) {
        this(context, 0, false);
    }

    private boolean I() {
        return this.u != -1;
    }

    private float c(float f2) {
        float abs = Math.abs(f2 - ((this.f4298h.getTotalSpace() - this.b) / 2.0f));
        int i2 = this.b;
        float f3 = ((float) i2) - abs > 0.0f ? i2 - abs : 0.0f;
        float f4 = this.z;
        return (((1.0f - f4) / i2) * f3) + f4;
    }

    private int d() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (this.f4301k) {
            return (int) this.f4304n;
        }
        return 1;
    }

    private int e() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.f4301k) {
            return !this.f4300j ? g() : (getItemCount() - g()) - 1;
        }
        float n2 = n();
        return !this.f4300j ? (int) n2 : (int) ((((float) (getItemCount() - 1)) * this.f4304n) + n2);
    }

    private int f() {
        if (getChildCount() == 0) {
            return 0;
        }
        if (!this.f4301k) {
            return getItemCount();
        }
        return (int) (getItemCount() * this.f4304n);
    }

    private int h() {
        return Math.round(this.f4297g / this.f4304n);
    }

    private int m(int i2) {
        if (this.d == 1) {
            if (i2 == 33) {
                return !this.f4300j ? 1 : 0;
            }
            if (i2 == 130) {
                return this.f4300j ? 1 : 0;
            }
            return -1;
        } else if (i2 == 17) {
            return !this.f4300j ? 1 : 0;
        } else {
            if (i2 == 66) {
                return this.f4300j ? 1 : 0;
            }
            return -1;
        }
    }

    private float n() {
        if (this.f4300j) {
            if (this.q) {
                float f2 = this.f4297g;
                if (f2 <= 0.0f) {
                    return f2 % (this.f4304n * getItemCount());
                }
                float f3 = this.f4304n;
                return (getItemCount() * (-f3)) + (this.f4297g % (f3 * getItemCount()));
            }
            return this.f4297g;
        } else if (this.q) {
            float f4 = this.f4297g;
            if (f4 >= 0.0f) {
                return f4 % (this.f4304n * getItemCount());
            }
            float f5 = this.f4304n;
            return (getItemCount() * f5) + (this.f4297g % (f5 * getItemCount()));
        } else {
            return this.f4297g;
        }
    }

    private float q(int i2) {
        return i2 * (this.f4300j ? -this.f4304n : this.f4304n);
    }

    private void resolveShouldLayoutReverse() {
        if (this.d == 0 && getLayoutDirection() == 1) {
            this.f4299i = !this.f4299i;
        }
    }

    private void s(RecyclerView.Recycler recycler) {
        int i2;
        int i3;
        int i4;
        detachAndScrapAttachedViews(recycler);
        this.a.clear();
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return;
        }
        int h2 = this.f4300j ? -h() : h();
        int i5 = h2 - this.s;
        int i6 = this.t + h2;
        if (I()) {
            int i7 = this.u;
            if (i7 % 2 == 0) {
                i3 = i7 / 2;
                i4 = (h2 - i3) + 1;
            } else {
                i3 = (i7 - 1) / 2;
                i4 = h2 - i3;
            }
            int i8 = i4;
            i6 = i3 + h2 + 1;
            i5 = i8;
        }
        if (!this.q) {
            if (i5 < 0) {
                if (I()) {
                    i6 = this.u;
                }
                i5 = 0;
            }
            if (i6 > itemCount) {
                i6 = itemCount;
            }
        }
        float f2 = Float.MIN_VALUE;
        int i9 = 0;
        while (i5 < i6) {
            if (I() || !w(q(i5) - this.f4297g)) {
                if (i5 >= itemCount) {
                    i2 = i5 % itemCount;
                } else if (i5 < 0) {
                    int i10 = (-i5) % itemCount;
                    if (i10 == 0) {
                        i10 = itemCount;
                    }
                    i2 = itemCount - i10;
                } else {
                    i2 = i5;
                }
                View viewForPosition = recycler.getViewForPosition(i2);
                measureChildWithMargins(viewForPosition, 0, 0);
                x(viewForPosition, this.z);
                float q = q(i5) - this.f4297g;
                float H = this.r ? H(viewForPosition, q) : i2;
                if (H > f2) {
                    addView(viewForPosition);
                } else {
                    addView(viewForPosition, 0);
                }
                i9 = t(viewForPosition, q, i9);
                if (i5 == h2) {
                    this.x = viewForPosition;
                }
                this.a.put(i5, viewForPosition);
                f2 = H;
            }
            i5++;
        }
        this.x.requestFocus();
    }

    private int scrollBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        ensureLayoutState();
        float f2 = i2;
        float i3 = f2 / i();
        if (Math.abs(i3) < 1.0E-8f) {
            return 0;
        }
        float f3 = this.f4297g + i3;
        if (!this.q && f3 < l()) {
            i2 = (int) (f2 - ((f3 - l()) * i()));
        } else if (!this.q && f3 > k()) {
            i2 = (int) ((k() - this.f4297g) * i());
        }
        this.f4297g += i2 / i();
        s(recycler);
        return i2;
    }

    private int t(View view, float f2, int i2) {
        if (i2 == 0) {
            float f3 = this.z;
            int i3 = this.b;
            int i4 = this.y;
            i2 = (int) ((((int) (this.f4295e + (a(view, f2) * f3))) - ((int) (((i3 + i4) * f3) / 2.0f))) - (((i3 + i4) * (1.0f - f3)) / 2.0f));
        }
        int b = b(view, f2);
        float c2 = c(f2 + this.f4295e);
        C(view, c2);
        if (this.d == 1) {
            int i5 = this.f4296f;
            int i6 = this.f4295e;
            layoutDecorated(view, i5 + i2, i6 + b, i5 + i2 + this.f4294c, i6 + b + this.b);
            return i2;
        }
        float f4 = i2;
        int i7 = this.b;
        int i8 = (int) (((i7 * c2) / 2.0f) + f4);
        int i9 = this.f4296f;
        layoutDecorated(view, i8, i9 + b, i8 + i7, i9 + b + this.f4294c);
        return (int) (f4 + (this.b * c2) + this.y);
    }

    private boolean w(float f2) {
        return f2 > u() || f2 < v();
    }

    private void x(View view, float f2) {
        view.setRotation(0.0f);
        view.setRotationY(0.0f);
        view.setRotationX(0.0f);
        view.setScaleX(f2);
        view.setScaleY(f2);
        view.setAlpha(1.0f);
    }

    protected float A() {
        return this.b + this.y;
    }

    public void B(int i2) {
        this.y = i2;
    }

    protected void C(View view, float f2) {
        try {
            view.setScaleX(f2);
            view.setScaleY(f2);
        } catch (Throwable unused) {
        }
    }

    public void D(int i2) {
        assertNotInLayoutOrScroll(null);
        if (this.u == i2) {
            return;
        }
        this.u = i2;
        removeAllViews();
    }

    public void E(float f2) {
        assertNotInLayoutOrScroll(null);
        if (this.A == f2) {
            return;
        }
        this.A = f2;
    }

    public void F(float f2) {
        this.z = f2;
    }

    protected void G() {
    }

    protected float H(View view, float f2) {
        return 0.0f;
    }

    protected int a(View view, float f2) {
        if (this.d == 1) {
            return 0;
        }
        return (int) f2;
    }

    protected int b(View view, float f2) {
        if (this.d == 1) {
            return (int) f2;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.d == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.d == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return f();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return f();
    }

    void ensureLayoutState() {
        if (this.f4298h == null) {
            this.f4298h = OrientationHelper.createOrientationHelper(this, this.d);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int i2) {
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return null;
        }
        for (int i3 = 0; i3 < this.a.size(); i3++) {
            int keyAt = this.a.keyAt(i3);
            if (keyAt >= 0) {
                if (i2 == keyAt % itemCount) {
                    return this.a.valueAt(i3);
                }
            } else {
                int i4 = keyAt % itemCount;
                if (i4 == 0) {
                    i4 = -itemCount;
                }
                if (i4 + itemCount == i2) {
                    return this.a.valueAt(i3);
                }
            }
        }
        return null;
    }

    public int g() {
        int itemCount;
        if (getItemCount() == 0) {
            return 0;
        }
        int h2 = h();
        if (!this.q) {
            return Math.abs(h2);
        }
        if (this.f4300j) {
            if (h2 > 0) {
                itemCount = getItemCount() - (h2 % getItemCount());
            } else {
                itemCount = (-h2) % getItemCount();
            }
        } else if (h2 >= 0) {
            itemCount = h2 % getItemCount();
        } else {
            itemCount = (h2 % getItemCount()) + getItemCount();
        }
        if (itemCount == getItemCount()) {
            return 0;
        }
        return itemCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getOrientation() {
        return this.d;
    }

    public boolean getReverseLayout() {
        return this.f4299i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float i() {
        float f2 = this.A;
        if (f2 == 0.0f) {
            return Float.MAX_VALUE;
        }
        return 1.0f / f2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean j() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float k() {
        if (this.f4300j) {
            return 0.0f;
        }
        return ((float) (getItemCount() - 1)) * this.f4304n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float l() {
        if (this.f4300j) {
            return (-(getItemCount() - 1)) * this.f4304n;
        }
        return 0.0f;
    }

    public int o() {
        float g2;
        float i2;
        if (this.q) {
            g2 = (h() * this.f4304n) - this.f4297g;
            i2 = i();
        } else {
            g2 = (g() * (!this.f4300j ? this.f4304n : -this.f4304n)) - this.f4297g;
            i2 = i();
        }
        return (int) (g2 * i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
        this.f4297g = 0.0f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i2, int i3) {
        int g2 = g();
        View findViewByPosition = findViewByPosition(g2);
        if (findViewByPosition == null) {
            return true;
        }
        if (recyclerView.hasFocus()) {
            int m2 = m(i2);
            if (m2 != -1) {
                recyclerView.smoothScrollToPosition(m2 == 1 ? g2 - 1 : g2 + 1);
            }
        } else {
            findViewByPosition.addFocusables(arrayList, i2, i3);
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.p) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        float f2;
        float f3;
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            this.f4297g = 0.0f;
            return;
        }
        ensureLayoutState();
        resolveShouldLayoutReverse();
        View viewForPosition = recycler.getViewForPosition(0);
        measureChildWithMargins(viewForPosition, 0, 0);
        this.b = this.f4298h.getDecoratedMeasurement(viewForPosition);
        this.f4294c = this.f4298h.getDecoratedMeasurementInOther(viewForPosition);
        this.f4295e = (this.f4298h.getTotalSpace() - this.b) / 2;
        if (this.w == Integer.MAX_VALUE) {
            this.f4296f = (r() - this.f4294c) / 2;
        } else {
            this.f4296f = (r() - this.f4294c) - this.w;
        }
        this.f4304n = A();
        G();
        this.s = ((int) Math.abs(v() / this.f4304n)) + 1;
        this.t = ((int) Math.abs(u() / this.f4304n)) + 1;
        SavedState savedState = this.f4303m;
        if (savedState != null) {
            this.f4300j = savedState.f4307i;
            this.f4302l = savedState.f4305g;
            this.f4297g = savedState.f4306h;
        }
        int i2 = this.f4302l;
        if (i2 != -1) {
            if (this.f4300j) {
                f2 = i2;
                f3 = -this.f4304n;
            } else {
                f2 = i2;
                f3 = this.f4304n;
            }
            this.f4297g = f2 * f3;
        }
        detachAndScrapAttachedViews(recycler);
        s(recycler);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.f4303m = null;
        this.f4302l = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f4303m = new SavedState((SavedState) parcelable);
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.f4303m != null) {
            return new SavedState(this.f4303m);
        }
        SavedState savedState = new SavedState();
        savedState.f4305g = this.f4302l;
        savedState.f4306h = this.f4297g;
        savedState.f4307i = this.f4300j;
        return savedState;
    }

    public int p(int i2) {
        float f2;
        float i3;
        if (this.q) {
            f2 = ((h() + (!this.f4300j ? i2 - g() : g() - i2)) * this.f4304n) - this.f4297g;
            i3 = i();
        } else {
            f2 = (i2 * (!this.f4300j ? this.f4304n : -this.f4304n)) - this.f4297g;
            i3 = i();
        }
        return (int) (f2 * i3);
    }

    public int r() {
        int width;
        int paddingRight;
        if (this.d == 0) {
            width = getHeight() - getPaddingTop();
            paddingRight = getPaddingBottom();
        } else {
            width = getWidth() - getPaddingLeft();
            paddingRight = getPaddingRight();
        }
        return width - paddingRight;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.d == 1) {
            return 0;
        }
        return scrollBy(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        if (this.q || (i2 >= 0 && i2 < getItemCount())) {
            this.f4302l = i2;
            this.f4297g = i2 * (this.f4300j ? -this.f4304n : this.f4304n);
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.d == 0) {
            return 0;
        }
        return scrollBy(i2, recycler, state);
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i2);
        }
        assertNotInLayoutOrScroll(null);
        if (i2 == this.d) {
            return;
        }
        this.d = i2;
        this.f4298h = null;
        this.w = Integer.MAX_VALUE;
        removeAllViews();
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.f4299i) {
            return;
        }
        this.f4299i = z;
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        int p = p(i2);
        if (this.d == 1) {
            recyclerView.smoothScrollBy(0, p, this.v);
        } else {
            recyclerView.smoothScrollBy(p, 0, this.v);
        }
    }

    protected float u() {
        return this.f4298h.getTotalSpace() - this.f4295e;
    }

    protected float v() {
        return ((-this.b) - this.f4298h.getStartAfterPadding()) - this.f4295e;
    }

    public void y(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.r == z) {
            return;
        }
        this.r = z;
        requestLayout();
    }

    public void z(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.q) {
            return;
        }
        this.q = z;
        requestLayout();
    }

    public FlexFocusBannerLayoutManager(Context context, int i2, boolean z) {
        this.a = new SparseArray<>();
        this.f4299i = false;
        this.f4300j = false;
        this.f4301k = true;
        this.f4302l = -1;
        this.f4303m = null;
        this.q = true;
        this.u = -1;
        this.w = Integer.MAX_VALUE;
        this.y = 20;
        this.z = 1.0f;
        this.A = 1.0f;
        y(true);
        D(3);
        setOrientation(i2);
        setReverseLayout(z);
        setAutoMeasureEnabled(true);
        setItemPrefetchEnabled(false);
    }
}
