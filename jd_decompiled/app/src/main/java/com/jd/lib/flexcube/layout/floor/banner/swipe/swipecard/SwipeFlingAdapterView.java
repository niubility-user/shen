package com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.FrameLayout;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.flexcube.R;
import com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a;
import java.util.ArrayList;

/* loaded from: classes14.dex */
public class SwipeFlingAdapterView extends BaseFlingAdapterView {
    private com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a A;
    private int B;
    private boolean C;
    private float D;
    private float E;
    private boolean F;
    private Runnable G;
    private Runnable H;

    /* renamed from: i  reason: collision with root package name */
    protected float f4342i;

    /* renamed from: j  reason: collision with root package name */
    protected boolean f4343j;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f4344k;

    /* renamed from: l  reason: collision with root package name */
    protected int f4345l;

    /* renamed from: m  reason: collision with root package name */
    protected int f4346m;

    /* renamed from: n  reason: collision with root package name */
    protected int f4347n;
    protected int o;
    float p;
    private ArrayList<View> q;
    protected int r;
    private int s;
    private float t;
    private Adapter u;
    private f v;
    private d w;
    private boolean x;
    private View y;
    private e z;

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SwipeFlingAdapterView swipeFlingAdapterView = SwipeFlingAdapterView.this;
            swipeFlingAdapterView.m(swipeFlingAdapterView.p);
            SwipeFlingAdapterView.this.v.onScroll(SwipeFlingAdapterView.this.p, 0.0f);
            SwipeFlingAdapterView swipeFlingAdapterView2 = SwipeFlingAdapterView.this;
            float f2 = swipeFlingAdapterView2.p;
            if (f2 > 0.0f) {
                float f3 = f2 - 0.125f;
                swipeFlingAdapterView2.p = f3;
                if (f3 < 0.0f) {
                    swipeFlingAdapterView2.p = 0.0f;
                }
                swipeFlingAdapterView2.postDelayed(this, swipeFlingAdapterView2.B / 30);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SwipeFlingAdapterView swipeFlingAdapterView = SwipeFlingAdapterView.this;
            swipeFlingAdapterView.m(swipeFlingAdapterView.p);
            SwipeFlingAdapterView.this.v.onScroll(SwipeFlingAdapterView.this.p, 0.0f);
            SwipeFlingAdapterView swipeFlingAdapterView2 = SwipeFlingAdapterView.this;
            float f2 = swipeFlingAdapterView2.p;
            if (f2 < 1.0f) {
                float f3 = f2 + 0.125f;
                swipeFlingAdapterView2.p = f3;
                if (f3 > 1.0f) {
                    swipeFlingAdapterView2.p = 1.0f;
                }
                swipeFlingAdapterView2.postDelayed(this, swipeFlingAdapterView2.B / 30);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c implements a.b {
        c() {
        }

        @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a.b
        public void leftExit(Object obj) {
            SwipeFlingAdapterView.this.v.onLeftCardExit(obj);
        }

        @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a.b
        public void onCardExited(boolean z) {
            SwipeFlingAdapterView.this.C = false;
            SwipeFlingAdapterView swipeFlingAdapterView = SwipeFlingAdapterView.this;
            swipeFlingAdapterView.removeViewInLayout(swipeFlingAdapterView.y);
            SwipeFlingAdapterView.this.q.add(SwipeFlingAdapterView.this.y);
            SwipeFlingAdapterView.this.y = null;
            SwipeFlingAdapterView.this.v.removeFirstObjectInAdapter(z);
        }

        @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a.b
        public void onClick(MotionEvent motionEvent, View view, Object obj) {
            if (SwipeFlingAdapterView.this.z != null) {
                SwipeFlingAdapterView.this.z.onItemClicked(motionEvent, view, obj);
            }
        }

        @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a.b
        public void rightExit(Object obj) {
            SwipeFlingAdapterView.this.v.onRightCardExit(obj);
        }

        @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a.b
        public void swipe(boolean z) {
            if (SwipeFlingAdapterView.this.u.getCount() > 1 && !SwipeFlingAdapterView.this.C) {
                SwipeFlingAdapterView.this.C = true;
                if (z) {
                    SwipeFlingAdapterView.this.B();
                } else {
                    SwipeFlingAdapterView.this.C();
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    private class d extends DataSetObserver {
        private d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            SwipeFlingAdapterView.this.requestLayout();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            SwipeFlingAdapterView.this.requestLayout();
        }

        /* synthetic */ d(SwipeFlingAdapterView swipeFlingAdapterView, a aVar) {
            this();
        }
    }

    /* loaded from: classes14.dex */
    public interface e {
        void onItemClicked(MotionEvent motionEvent, View view, Object obj);
    }

    /* loaded from: classes14.dex */
    public interface f {
        void onAdapterAboutToEmpty(int i2);

        void onLeftCardExit(Object obj);

        void onRightCardExit(Object obj);

        void onScroll(float f2, float f3);

        void removeFirstObjectInAdapter(boolean z);
    }

    public SwipeFlingAdapterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void A() {
        if (getChildCount() > 0) {
            View childAt = getChildAt(this.f4346m);
            this.y = childAt;
            if (childAt == null || this.v == null) {
                return;
            }
            com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a aVar = new com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a(childAt, this.u.getItem(0), new c());
            this.A = aVar;
            setOnTouchListener(aVar);
        }
    }

    private View o(int i2) {
        View view;
        int size = this.q.size();
        for (int i3 = 0; i3 < size && i2 >= 0; i3++) {
            View view2 = this.q.get(i3);
            try {
                int i4 = R.id.FLEXCUBE_INTERNAL_CONTENT_ID;
                if (view2.getTag(i4) != null && ((Integer) view2.getTag(i4)).intValue() == i2) {
                    this.q.remove(view2);
                    view2.setTranslationX(0.0f);
                    view2.setScaleX(1.0f);
                    view2.setScaleY(1.0f);
                    view2.setAlpha(1.0f);
                    return view2;
                }
            } catch (Exception unused) {
            }
        }
        if (size <= 0 || (view = this.q.get(0)) == null) {
            return null;
        }
        view.setTranslationX(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setAlpha(1.0f);
        this.q.remove(view);
        return view;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void p(android.view.View r8) {
        /*
            r7 = this;
            android.view.ViewGroup$LayoutParams r0 = r8.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r0 = (android.widget.FrameLayout.LayoutParams) r0
            boolean r1 = r8.isLayoutRequested()
            if (r1 == 0) goto L42
            int r1 = r7.b()
            int r2 = r7.getPaddingLeft()
            int r3 = r7.getPaddingRight()
            int r2 = r2 + r3
            int r3 = r0.leftMargin
            int r2 = r2 + r3
            int r3 = r0.rightMargin
            int r2 = r2 + r3
            int r3 = r0.width
            int r1 = android.widget.AdapterView.getChildMeasureSpec(r1, r2, r3)
            int r2 = r7.a()
            int r3 = r7.getPaddingTop()
            int r4 = r7.getPaddingBottom()
            int r3 = r3 + r4
            int r4 = r0.topMargin
            int r3 = r3 + r4
            int r4 = r0.bottomMargin
            int r3 = r3 + r4
            int r4 = r0.height
            int r2 = android.widget.AdapterView.getChildMeasureSpec(r2, r3, r4)
            r8.measure(r1, r2)
            goto L45
        L42:
            r7.cleanupLayoutState(r8)
        L45:
            int r1 = r8.getMeasuredWidth()
            int r2 = r8.getMeasuredHeight()
            int r3 = r0.gravity
            r4 = -1
            if (r3 != r4) goto L55
            r3 = 8388659(0x800033, float:1.1755015E-38)
        L55:
            r4 = 0
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 16
            if (r5 <= r6) goto L60
            int r4 = r7.getLayoutDirection()
        L60:
            int r4 = android.view.Gravity.getAbsoluteGravity(r3, r4)
            r3 = r3 & 112(0x70, float:1.57E-43)
            r4 = r4 & 7
            r5 = 1
            if (r4 == r5) goto L85
            r5 = 8388613(0x800005, float:1.175495E-38)
            if (r4 == r5) goto L78
            int r4 = r7.getPaddingLeft()
            int r5 = r0.leftMargin
            int r4 = r4 + r5
            goto L9c
        L78:
            int r4 = r7.getWidth()
            int r5 = r7.getPaddingRight()
            int r4 = r4 + r5
            int r4 = r4 - r1
            int r5 = r0.rightMargin
            goto L9b
        L85:
            int r4 = r7.getWidth()
            int r5 = r7.getPaddingLeft()
            int r4 = r4 + r5
            int r5 = r7.getPaddingRight()
            int r4 = r4 - r5
            int r4 = r4 - r1
            int r4 = r4 / 2
            int r5 = r0.leftMargin
            int r4 = r4 + r5
            int r5 = r0.rightMargin
        L9b:
            int r4 = r4 - r5
        L9c:
            if (r3 == r6) goto Lb7
            r5 = 80
            if (r3 == r5) goto Laa
            int r3 = r7.getPaddingTop()
            int r0 = r0.topMargin
            int r3 = r3 + r0
            goto Lce
        Laa:
            int r3 = r7.getHeight()
            int r5 = r7.getPaddingBottom()
            int r3 = r3 - r5
            int r3 = r3 - r2
            int r0 = r0.bottomMargin
            goto Lcd
        Lb7:
            int r3 = r7.getHeight()
            int r5 = r7.getPaddingTop()
            int r3 = r3 + r5
            int r5 = r7.getPaddingBottom()
            int r3 = r3 - r5
            int r3 = r3 - r2
            int r3 = r3 / 2
            int r5 = r0.topMargin
            int r3 = r3 + r5
            int r0 = r0.bottomMargin
        Lcd:
            int r3 = r3 - r0
        Lce:
            int r1 = r1 + r4
            int r2 = r2 + r3
            r8.layout(r4, r3, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.p(android.view.View):void");
    }

    private void q(int i2, int i3) {
        while (i2 < Math.min(i3, this.f4345l)) {
            int itemViewType = this.u.getItemViewType(i2);
            View view = this.u.getView(i2, o(itemViewType), this);
            view.setTag(R.id.FLEXCUBE_INTERNAL_CONTENT_ID, Integer.valueOf(itemViewType));
            if (view.getVisibility() != 8) {
                r(view, i2, false);
                this.f4346m = i2;
            }
            i2++;
        }
    }

    @TargetApi(14)
    private void r(View view, int i2, boolean z) {
        view.setTranslationX(0.0f);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (z) {
            addViewInLayout(view, -1, layoutParams, true);
        } else {
            addViewInLayout(view, 0, layoutParams, true);
        }
        p(view);
        if (z) {
            return;
        }
        l(view, i2);
    }

    private void s(int i2) {
        while (getChildCount() - i2 > 0) {
            View childAt = getChildAt(0);
            removeViewInLayout(childAt);
            this.q.add(childAt);
        }
    }

    private void t(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void B() {
        n().g(this.B);
        this.p = 0.0f;
        postDelayed(this.H, 0L);
    }

    public void C() {
        View view = this.u.getView(this.u.getCount() - 1, o(-1), this);
        view.setTag(R.id.FLEXCUBE_INTERNAL_CONTENT_ID, null);
        if (view.getVisibility() != 8) {
            r(view, -1, true);
            view.setTranslationX(-DPIUtil.getWidth(getContext()));
        }
        this.f4346m = getChildCount() - 1;
        A();
        n().h(this.B);
        this.p = 1.0f;
        postDelayed(this.G, 0L);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 2 && Math.abs(x - this.D) < Math.abs(y - this.E)) {
                t(false);
            }
        } else {
            t(true);
        }
        this.D = x;
        this.E = y;
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new FrameLayout.LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.AdapterView
    public Adapter getAdapter() {
        return this.u;
    }

    @Override // android.widget.AdapterView
    public View getSelectedView() {
        return this.y;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l(View view, int i2) {
        if (i2 <= -1 || i2 >= this.f4345l) {
            return;
        }
        if (i2 > 3) {
            i2 = 3;
        }
        if (this.f4343j) {
            view.offsetTopAndBottom(this.r * i2);
        } else {
            view.offsetLeftAndRight(this.r * i2);
        }
        float f2 = i2;
        view.setScaleX(1.0f - (this.f4342i * f2));
        view.setScaleY(1.0f - (this.f4342i * f2));
        if (this.f4344k) {
            view.setAlpha(1.0f - ((this.f4342i * f2) * ((int) Math.pow(2.0d, (double) (i2 - 1)))));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void m(float f2) {
        int i2;
        int childCount = getChildCount();
        int i3 = 1;
        if (childCount > 1) {
            if (childCount == 2) {
                i2 = this.f4346m - 1;
            } else if (childCount == 3) {
                i2 = this.f4346m - 2;
                i3 = 2;
            } else {
                i2 = this.f4346m - 3;
                i3 = 3;
            }
            float abs = Math.abs(f2);
            while (i2 < this.f4346m) {
                View childAt = getChildAt(i2);
                float f3 = i3;
                float f4 = f3 - abs;
                int i4 = (int) (this.r * f4);
                if (this.f4343j) {
                    childAt.offsetTopAndBottom((i4 - childAt.getTop()) + this.f4347n);
                } else {
                    childAt.offsetLeftAndRight((i4 - childAt.getLeft()) + this.o);
                }
                float f5 = this.f4342i;
                childAt.setScaleX((1.0f - (f5 * f3)) + (f5 * abs));
                float f6 = this.f4342i;
                childAt.setScaleY((1.0f - (f3 * f6)) + (f6 * abs));
                if (this.f4344k) {
                    childAt.setAlpha(1.0f - ((this.f4342i * f4) * ((int) Math.pow(2.0d, (double) (i3 - 1)))));
                }
                i2++;
                i3--;
            }
        }
    }

    public com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a n() throws NullPointerException {
        com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a aVar = this.A;
        if (aVar != null) {
            return aVar;
        }
        throw new NullPointerException("flingCardListener is null");
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        f fVar;
        View view;
        super.onLayout(z, i2, i3, i4, i5);
        Adapter adapter = this.u;
        if (adapter == null) {
            return;
        }
        this.x = true;
        int count = adapter.getCount();
        if (count == 0) {
            s(0);
        } else {
            View childAt = getChildAt(this.f4346m);
            View view2 = this.y;
            if (view2 != null && childAt != null && childAt == view2) {
                if (this.F) {
                    p(view2);
                }
                s(1);
                q(1, count);
            } else {
                s(0);
                q(0, count);
                A();
            }
        }
        this.x = false;
        if (this.f4347n == 0 && this.o == 0 && (view = this.y) != null) {
            this.f4347n = view.getTop();
            this.o = this.y.getLeft();
        }
        if (count >= this.s || (fVar = this.v) == null) {
            return;
        }
        fVar.onAdapterAboutToEmpty(count);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.x) {
            return;
        }
        super.requestLayout();
    }

    @Override // android.widget.AdapterView
    public void setAdapter(Adapter adapter) {
        d dVar;
        a aVar = null;
        this.y = null;
        Adapter adapter2 = this.u;
        if (adapter2 != null && (dVar = this.w) != null) {
            adapter2.unregisterDataSetObserver(dVar);
            this.w = null;
        }
        this.u = adapter;
        if (adapter != null && this.w == null) {
            d dVar2 = new d(this, aVar);
            this.w = dVar2;
            this.u.registerDataSetObserver(dVar2);
        }
        requestLayout();
    }

    public void u(f fVar) {
        this.v = fVar;
    }

    public void v(boolean z) {
        this.F = z;
    }

    public void w(boolean z) {
    }

    public void x(int i2) {
        this.f4345l = i2;
    }

    public void y(e eVar) {
        this.z = eVar;
    }

    public void z(boolean z) {
        this.f4344k = z;
    }

    public SwipeFlingAdapterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4342i = 0.04f;
        this.f4345l = 4;
        this.f4346m = 0;
        this.p = 1.0f;
        this.q = new ArrayList<>();
        this.s = 6;
        this.t = 2.0f;
        this.x = false;
        this.y = null;
        this.B = 300;
        this.C = false;
        this.F = false;
        this.G = new a();
        this.H = new b();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexCubeSwipeFlingAdapterView, i2, 0);
        this.f4345l = obtainStyledAttributes.getInt(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_max_visible, this.f4345l);
        this.s = obtainStyledAttributes.getInt(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_min_adapter_stack, this.s);
        this.t = obtainStyledAttributes.getFloat(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_rotation_degrees, this.t);
        this.f4342i = obtainStyledAttributes.getFloat(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_scale_step, this.f4342i);
        this.r = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_offset_step, 0);
        this.f4343j = obtainStyledAttributes.getBoolean(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_show_other_in_bottom, false);
        this.f4344k = obtainStyledAttributes.getBoolean(R.styleable.FlexCubeSwipeFlingAdapterView_flexcube_show_other_with_alpha, true);
        obtainStyledAttributes.recycle();
    }
}
