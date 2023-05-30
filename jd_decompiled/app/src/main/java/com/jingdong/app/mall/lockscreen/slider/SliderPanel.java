package com.jingdong.app.mall.lockscreen.slider;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.customview.widget.ViewDragHelper;
import com.jingdong.app.mall.lockscreen.slider.b;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes4.dex */
public class SliderPanel extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    private int f11164g;

    /* renamed from: h  reason: collision with root package name */
    private int f11165h;

    /* renamed from: i  reason: collision with root package name */
    private View f11166i;

    /* renamed from: j  reason: collision with root package name */
    private View f11167j;

    /* renamed from: k  reason: collision with root package name */
    private ViewDragHelper f11168k;

    /* renamed from: l  reason: collision with root package name */
    private i f11169l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f11170m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f11171n;
    private int o;
    private com.jingdong.app.mall.lockscreen.slider.b p;
    private ViewDragHelper.Callback q;
    private ViewDragHelper.Callback r;
    private ViewDragHelper.Callback s;
    private ViewDragHelper.Callback t;
    private ViewDragHelper.Callback u;
    private ViewDragHelper.Callback v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SliderPanel sliderPanel = SliderPanel.this;
            sliderPanel.f11165h = sliderPanel.getHeight();
        }
    }

    /* loaded from: classes4.dex */
    class b extends ViewDragHelper.Callback {
        b() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return SliderPanel.l(i2, 0, SliderPanel.this.f11164g);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return SliderPanel.this.f11164g;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            super.onViewDragStateChanged(i2);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onStateChanged(i2);
            }
            if (i2 != 0) {
                return;
            }
            if (SliderPanel.this.f11167j.getLeft() == 0) {
                if (SliderPanel.this.f11169l != null) {
                    SliderPanel.this.f11169l.b();
                }
            } else if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onClosed();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            super.onViewPositionChanged(view, i2, i3, i4, i5);
            float f2 = 1.0f - (i2 / SliderPanel.this.f11164g);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.a(f2);
            }
            SliderPanel.this.j(f2);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            super.onViewReleased(view, f2, f3);
            int left = view.getLeft();
            int width = (int) (SliderPanel.this.getWidth() * SliderPanel.this.p.i());
            int i2 = 0;
            boolean z = Math.abs(f3) > SliderPanel.this.p.s();
            if (f2 > 0.0f) {
                if (Math.abs(f2) > SliderPanel.this.p.s() && !z) {
                    i2 = SliderPanel.this.f11164g;
                } else if (left > width) {
                    i2 = SliderPanel.this.f11164g;
                }
            } else if (f2 == 0.0f && left > width) {
                i2 = SliderPanel.this.f11164g;
            }
            SliderPanel.this.f11168k.settleCapturedViewAt(i2, view.getTop());
            SliderPanel.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return view.getId() == SliderPanel.this.f11167j.getId() && (!SliderPanel.this.p.t() || SliderPanel.this.f11168k.isEdgeTouched(SliderPanel.this.o, i2));
        }
    }

    /* loaded from: classes4.dex */
    class c extends ViewDragHelper.Callback {
        c() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return SliderPanel.l(i2, -SliderPanel.this.f11164g, 0);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return SliderPanel.this.f11164g;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            super.onViewDragStateChanged(i2);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onStateChanged(i2);
            }
            if (i2 != 0) {
                return;
            }
            if (SliderPanel.this.f11167j.getLeft() == 0) {
                if (SliderPanel.this.f11169l != null) {
                    SliderPanel.this.f11169l.b();
                }
            } else if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onClosed();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            super.onViewPositionChanged(view, i2, i3, i4, i5);
            float abs = 1.0f - (Math.abs(i2) / SliderPanel.this.f11164g);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.a(abs);
            }
            SliderPanel.this.j(abs);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            int i2;
            super.onViewReleased(view, f2, f3);
            int left = view.getLeft();
            int width = (int) (SliderPanel.this.getWidth() * SliderPanel.this.p.i());
            int i3 = 0;
            boolean z = Math.abs(f3) > SliderPanel.this.p.s();
            if (f2 < 0.0f) {
                if (Math.abs(f2) > SliderPanel.this.p.s() && !z) {
                    i2 = SliderPanel.this.f11164g;
                } else if (left < (-width)) {
                    i2 = SliderPanel.this.f11164g;
                }
                i3 = -i2;
            } else if (f2 == 0.0f && left < (-width)) {
                i2 = SliderPanel.this.f11164g;
                i3 = -i2;
            }
            SliderPanel.this.f11168k.settleCapturedViewAt(i3, view.getTop());
            SliderPanel.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return view.getId() == SliderPanel.this.f11167j.getId() && (!SliderPanel.this.p.t() || SliderPanel.this.f11168k.isEdgeTouched(SliderPanel.this.o, i2));
        }
    }

    /* loaded from: classes4.dex */
    class d extends ViewDragHelper.Callback {
        d() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return SliderPanel.l(i2, 0, SliderPanel.this.f11165h);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View view) {
            return SliderPanel.this.f11165h;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            super.onViewDragStateChanged(i2);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onStateChanged(i2);
            }
            if (i2 != 0) {
                return;
            }
            if (SliderPanel.this.f11167j.getTop() == 0) {
                if (SliderPanel.this.f11169l != null) {
                    SliderPanel.this.f11169l.b();
                }
            } else if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onClosed();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            super.onViewPositionChanged(view, i2, i3, i4, i5);
            float abs = 1.0f - (Math.abs(i3) / SliderPanel.this.f11165h);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.a(abs);
            }
            SliderPanel.this.j(abs);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            super.onViewReleased(view, f2, f3);
            int top = view.getTop();
            int height = (int) (SliderPanel.this.getHeight() * SliderPanel.this.p.i());
            int i2 = 0;
            boolean z = Math.abs(f2) > SliderPanel.this.p.s();
            if (f3 > 0.0f) {
                if (Math.abs(f3) > SliderPanel.this.p.s() && !z) {
                    i2 = SliderPanel.this.f11165h;
                } else if (top > height) {
                    i2 = SliderPanel.this.f11165h;
                }
            } else if (f3 == 0.0f && top > height) {
                i2 = SliderPanel.this.f11165h;
            }
            SliderPanel.this.f11168k.settleCapturedViewAt(view.getLeft(), i2);
            SliderPanel.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return view.getId() == SliderPanel.this.f11167j.getId() && (!SliderPanel.this.p.t() || SliderPanel.this.f11171n);
        }
    }

    /* loaded from: classes4.dex */
    class e extends ViewDragHelper.Callback {
        e() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return SliderPanel.l(i2, -SliderPanel.this.f11165h, 0);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View view) {
            return SliderPanel.this.f11165h;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            super.onViewDragStateChanged(i2);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onStateChanged(i2);
            }
            if (i2 != 0) {
                return;
            }
            if (SliderPanel.this.f11167j.getTop() == 0) {
                if (SliderPanel.this.f11169l != null) {
                    SliderPanel.this.f11169l.b();
                }
            } else if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onClosed();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            super.onViewPositionChanged(view, i2, i3, i4, i5);
            float abs = 1.0f - (Math.abs(i3) / SliderPanel.this.f11165h);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.a(abs);
            }
            SliderPanel.this.j(abs);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            int i2;
            super.onViewReleased(view, f2, f3);
            int top = view.getTop();
            int height = (int) (SliderPanel.this.getHeight() * SliderPanel.this.p.i());
            int i3 = 0;
            boolean z = Math.abs(f2) > SliderPanel.this.p.s();
            if (f3 < 0.0f) {
                if (Math.abs(f3) > SliderPanel.this.p.s() && !z) {
                    i2 = SliderPanel.this.f11165h;
                } else if (top < (-height)) {
                    i2 = SliderPanel.this.f11165h;
                }
                i3 = -i2;
            } else if (f3 == 0.0f && top < (-height)) {
                i2 = SliderPanel.this.f11165h;
                i3 = -i2;
            }
            SliderPanel.this.f11168k.settleCapturedViewAt(view.getLeft(), i3);
            SliderPanel.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return view.getId() == SliderPanel.this.f11167j.getId() && (!SliderPanel.this.p.t() || SliderPanel.this.f11171n);
        }
    }

    /* loaded from: classes4.dex */
    class f extends ViewDragHelper.Callback {
        f() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return SliderPanel.l(i2, -SliderPanel.this.f11165h, SliderPanel.this.f11165h);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View view) {
            return SliderPanel.this.f11165h;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            super.onViewDragStateChanged(i2);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onStateChanged(i2);
            }
            if (i2 != 0) {
                return;
            }
            if (SliderPanel.this.f11167j.getTop() == 0) {
                if (SliderPanel.this.f11169l != null) {
                    SliderPanel.this.f11169l.b();
                }
            } else if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onClosed();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            super.onViewPositionChanged(view, i2, i3, i4, i5);
            float abs = 1.0f - (Math.abs(i3) / SliderPanel.this.f11165h);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.a(abs);
            }
            SliderPanel.this.j(abs);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            int i2;
            super.onViewReleased(view, f2, f3);
            int top = view.getTop();
            int height = (int) (SliderPanel.this.getHeight() * SliderPanel.this.p.i());
            int i3 = 0;
            boolean z = Math.abs(f2) > SliderPanel.this.p.s();
            if (f3 > 0.0f) {
                if (Math.abs(f3) > SliderPanel.this.p.s() && !z) {
                    i3 = SliderPanel.this.f11165h;
                } else if (top > height) {
                    i3 = SliderPanel.this.f11165h;
                }
            } else if (f3 < 0.0f) {
                if (Math.abs(f3) > SliderPanel.this.p.s() && !z) {
                    i2 = SliderPanel.this.f11165h;
                } else if (top < (-height)) {
                    i2 = SliderPanel.this.f11165h;
                }
                i3 = -i2;
            } else if (top > height) {
                i3 = SliderPanel.this.f11165h;
            } else if (top < (-height)) {
                i2 = SliderPanel.this.f11165h;
                i3 = -i2;
            }
            SliderPanel.this.f11168k.settleCapturedViewAt(view.getLeft(), i3);
            SliderPanel.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return view.getId() == SliderPanel.this.f11167j.getId() && (!SliderPanel.this.p.t() || SliderPanel.this.f11171n);
        }
    }

    /* loaded from: classes4.dex */
    class g extends ViewDragHelper.Callback {
        g() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            return SliderPanel.l(i2, -SliderPanel.this.f11164g, SliderPanel.this.f11164g);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return SliderPanel.this.f11164g;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            super.onViewDragStateChanged(i2);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onStateChanged(i2);
            }
            if (i2 != 0) {
                return;
            }
            if (SliderPanel.this.f11167j.getLeft() == 0) {
                if (SliderPanel.this.f11169l != null) {
                    SliderPanel.this.f11169l.b();
                }
            } else if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.onClosed();
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            super.onViewPositionChanged(view, i2, i3, i4, i5);
            float abs = 1.0f - (Math.abs(i2) / SliderPanel.this.f11164g);
            if (SliderPanel.this.f11169l != null) {
                SliderPanel.this.f11169l.a(abs);
            }
            SliderPanel.this.j(abs);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f2, float f3) {
            int i2;
            super.onViewReleased(view, f2, f3);
            int left = view.getLeft();
            int width = (int) (SliderPanel.this.getWidth() * SliderPanel.this.p.i());
            int i3 = 0;
            boolean z = Math.abs(f3) > SliderPanel.this.p.s();
            if (f2 > 0.0f) {
                if (Math.abs(f2) > SliderPanel.this.p.s() && !z) {
                    i3 = SliderPanel.this.f11164g;
                } else if (left > width) {
                    i3 = SliderPanel.this.f11164g;
                }
            } else if (f2 < 0.0f) {
                if (Math.abs(f2) > SliderPanel.this.p.s() && !z) {
                    i2 = SliderPanel.this.f11164g;
                } else if (left < (-width)) {
                    i2 = SliderPanel.this.f11164g;
                }
                i3 = -i2;
            } else if (left > width) {
                i3 = SliderPanel.this.f11164g;
            } else if (left < (-width)) {
                i2 = SliderPanel.this.f11164g;
                i3 = -i2;
            }
            SliderPanel.this.f11168k.settleCapturedViewAt(i3, view.getTop());
            SliderPanel.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return view.getId() == SliderPanel.this.f11167j.getId() && (!SliderPanel.this.p.t() || SliderPanel.this.f11168k.isEdgeTouched(SliderPanel.this.o, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class h {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jingdong.app.mall.lockscreen.slider.e.values().length];
            a = iArr;
            try {
                iArr[com.jingdong.app.mall.lockscreen.slider.e.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jingdong.app.mall.lockscreen.slider.e.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.jingdong.app.mall.lockscreen.slider.e.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.jingdong.app.mall.lockscreen.slider.e.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.jingdong.app.mall.lockscreen.slider.e.VERTICAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.jingdong.app.mall.lockscreen.slider.e.HORIZONTAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface i {
        void a(float f2);

        void b();

        void onClosed();

        void onStateChanged(int i2);
    }

    public SliderPanel(Context context, View view, com.jingdong.app.mall.lockscreen.slider.b bVar) {
        super(context);
        this.f11170m = false;
        this.f11171n = false;
        this.q = new b();
        this.r = new c();
        this.s = new d();
        this.t = new e();
        this.u = new f();
        this.v = new g();
        this.f11167j = view;
        this.p = bVar == null ? new b.C0344b().a() : bVar;
        m();
    }

    private boolean k(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (h.a[this.p.l().ordinal()]) {
            case 1:
                return x < this.p.j((float) getWidth());
            case 2:
                return x > ((float) getWidth()) - this.p.j((float) getWidth());
            case 3:
                return y < this.p.j((float) getHeight());
            case 4:
                return y > ((float) getHeight()) - this.p.j((float) getHeight());
            case 5:
                return y < this.p.j((float) getHeight()) || y > ((float) getHeight()) - this.p.j((float) getHeight());
            case 6:
                return x < this.p.j((float) getWidth()) || x > ((float) getWidth()) - this.p.j((float) getWidth());
            default:
                return false;
        }
    }

    public static int l(int i2, int i3, int i4) {
        return Math.max(i3, Math.min(i4, i2));
    }

    private void m() {
        ViewDragHelper.Callback callback;
        this.f11164g = BaseInfo.getScreenWidth();
        float density = BaseInfo.getDensity() * 400.0f;
        switch (h.a[this.p.l().ordinal()]) {
            case 1:
                callback = this.q;
                this.o = 1;
                break;
            case 2:
                callback = this.r;
                this.o = 2;
                break;
            case 3:
                callback = this.s;
                this.o = 4;
                break;
            case 4:
                callback = this.t;
                this.o = 8;
                break;
            case 5:
                callback = this.u;
                this.o = 12;
                break;
            case 6:
                callback = this.v;
                this.o = 3;
                break;
            default:
                callback = this.q;
                this.o = 1;
                break;
        }
        ViewDragHelper create = ViewDragHelper.create(this, this.p.r(), callback);
        this.f11168k = create;
        create.setMinVelocity(density);
        this.f11168k.setEdgeTrackingEnabled(this.o);
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        View view = new View(getContext());
        this.f11166i = view;
        view.setBackgroundColor(this.p.n());
        this.f11166i.setAlpha(this.p.p());
        addView(this.f11166i);
        post(new a());
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.f11168k.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void j(float f2) {
        this.f11166i.setAlpha((f2 * (this.p.p() - this.p.o())) + this.p.o());
    }

    public void n(i iVar) {
        this.f11169l = iVar;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.f11170m) {
            return false;
        }
        if (this.p.t()) {
            this.f11171n = k(motionEvent);
        }
        try {
            z = this.f11168k.shouldInterceptTouchEvent(motionEvent);
        } catch (Exception unused) {
            z = false;
        }
        return z && !this.f11170m;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f11170m) {
            return false;
        }
        try {
            this.f11168k.processTouchEvent(motionEvent);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
