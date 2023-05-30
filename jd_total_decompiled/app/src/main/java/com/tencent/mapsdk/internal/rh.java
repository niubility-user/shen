package com.tencent.mapsdk.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ZoomControls;
import com.tencent.mapsdk.internal.l1;
import com.tencent.mapsdk.internal.o4;

/* loaded from: classes9.dex */
public class rh extends m4 {
    private Context d;

    /* renamed from: e  reason: collision with root package name */
    private ZoomControls f17204e;

    /* renamed from: i  reason: collision with root package name */
    private xi f17208i;

    /* renamed from: k  reason: collision with root package name */
    private boolean f17210k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f17211l;

    /* renamed from: m  reason: collision with root package name */
    private LinearLayout f17212m;

    /* renamed from: n  reason: collision with root package name */
    private ViewGroup f17213n;
    public Bitmap o;
    public Bitmap p;
    public Bitmap q;
    public Bitmap r;

    /* renamed from: f  reason: collision with root package name */
    private qh f17205f = null;

    /* renamed from: g  reason: collision with root package name */
    private o4.b f17206g = o4.b.RIGHT_BOTTOM;

    /* renamed from: h  reason: collision with root package name */
    private l1.d f17207h = null;

    /* renamed from: j  reason: collision with root package name */
    private int f17209j = 0;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            rh.this.f17208i.getMap().a((Runnable) null);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            rh.this.f17208i.getMap().b((Runnable) null);
        }
    }

    /* loaded from: classes9.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (rh.this.f17207h != null) {
                rh.this.f17207h.a();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ boolean b;

        public d(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            rh.this.f17204e.setIsZoomInEnabled(this.a);
            rh.this.f17204e.setIsZoomOutEnabled(this.b);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class e {
        public static final /* synthetic */ int[] a;

        static {
            o4.b.values();
            int[] iArr = new int[6];
            a = iArr;
            try {
                o4.b bVar = o4.b.LEFT_BOTTOM;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                o4.b bVar2 = o4.b.CENTER_BOTTOM;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                o4.b bVar3 = o4.b.RIGHT_BOTTOM;
                iArr3[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = a;
                o4.b bVar4 = o4.b.LEFT_TOP;
                iArr4[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = a;
                o4.b bVar5 = o4.b.CENTER_TOP;
                iArr5[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = a;
                o4.b bVar6 = o4.b.RIGHT_TOP;
                iArr6[5] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public rh(Context context, xi xiVar) {
        this.d = context;
        this.f17208i = xiVar;
    }

    private void a(Context context) {
        this.f17205f = new qh(context);
        Bitmap a2 = a7.a(a7.c(this.d, "location_enable.png"));
        this.f17205f.setScaleType(ImageView.ScaleType.CENTER);
        this.f17205f.setImageBitmap(a2);
        this.f17205f.setOnClickListener(new c());
        g();
    }

    @SuppressLint({"ResourceType"})
    private void b(Context context) {
        try {
            ZoomControls zoomControls = new ZoomControls(context);
            this.f17204e = zoomControls;
            if (Build.VERSION.SDK_INT >= 17) {
                zoomControls.setId(View.generateViewId());
            } else {
                zoomControls.setId(-570425343);
            }
            this.f17204e.setOnZoomInClickListener(new a());
            this.f17204e.setOnZoomOutClickListener(new b());
            h();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c(boolean z) {
        if (this.f17205f == null) {
            return;
        }
        if (z) {
            Bitmap bitmap = this.q;
            if (bitmap == null || bitmap.isRecycled()) {
                this.q = a7.a(a7.c(this.d, "location_state_dark_normal.png"));
            }
            Bitmap bitmap2 = this.r;
            if (bitmap2 == null || bitmap2.isRecycled()) {
                this.r = a7.a(a7.c(this.d, "location_state_dark_selected.png"));
            }
        } else {
            Bitmap bitmap3 = this.o;
            if (bitmap3 == null || bitmap3.isRecycled()) {
                this.o = a7.a(a7.c(this.d, "location_state_normal.png"));
            }
            Bitmap bitmap4 = this.p;
            if (bitmap4 == null || bitmap4.isRecycled()) {
                this.p = a7.a(a7.c(this.d, "location_state_selected.png"));
            }
        }
        this.f17205f.a(this.d, z ? this.q : this.o, z ? this.r : this.p);
        this.f17205f.setVisibility(this.f17210k ? 0 : 8);
    }

    private FrameLayout.LayoutParams e() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int a2 = y9.a(this.d, 5);
        int ordinal = this.f17206g.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    layoutParams.gravity = 85;
                    layoutParams.bottomMargin = a2 * 6;
                } else if (ordinal == 3) {
                    layoutParams.gravity = 51;
                    layoutParams.topMargin = a2;
                } else if (ordinal == 4) {
                    layoutParams.gravity = 49;
                    layoutParams.topMargin = a2;
                } else if (ordinal != 5) {
                    ma.b("Unknown position:" + this.f17206g);
                } else {
                    layoutParams.gravity = 53;
                    layoutParams.topMargin = a2;
                }
                layoutParams.rightMargin = a2;
            } else {
                layoutParams.gravity = 81;
                layoutParams.bottomMargin = a2;
            }
            return layoutParams;
        }
        layoutParams.gravity = 83;
        layoutParams.bottomMargin = a2 * 2;
        layoutParams.leftMargin = a2;
        return layoutParams;
    }

    private void g() {
        xi xiVar;
        LinearLayout linearLayout;
        View view;
        if (this.f17212m == null || this.f17205f == null || (xiVar = this.f17208i) == null || xiVar.getMap() == null) {
            return;
        }
        c(this.f17208i.getMapContext().a());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (this.f17212m.indexOfChild(this.f17205f) >= 0) {
            this.f17212m.updateViewLayout(this.f17205f, layoutParams);
            return;
        }
        ZoomControls zoomControls = this.f17204e;
        if (zoomControls == null || this.f17212m.indexOfChild(zoomControls) < 0) {
            linearLayout = this.f17212m;
            view = this.f17205f;
        } else {
            this.f17212m.removeViewInLayout(this.f17204e);
            this.f17212m.addView(this.f17205f, layoutParams);
            linearLayout = this.f17212m;
            view = this.f17204e;
        }
        linearLayout.addView(view, layoutParams);
    }

    private void h() {
        if (this.f17212m == null || this.f17204e == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (this.f17212m.indexOfChild(this.f17204e) < 0) {
            this.f17212m.addView(this.f17204e, layoutParams);
        } else {
            this.f17212m.updateViewLayout(this.f17204e, layoutParams);
        }
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a() {
        qh qhVar = this.f17205f;
        if (qhVar != null) {
            qhVar.a();
        }
    }

    @Override // com.tencent.mapsdk.internal.h5
    public void a(int i2, int i3) {
    }

    public void a(l1.d dVar) {
        this.f17207h = dVar;
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a(o4.b bVar) {
        if (this.f17206g != bVar) {
            this.f17206g = bVar;
            a(this.f17213n, (Bundle) null);
        }
    }

    public void a(boolean z) {
        this.f17210k = z;
        if (z && this.f17205f == null) {
            a(this.d);
        }
        qh qhVar = this.f17205f;
        if (qhVar != null) {
            qhVar.setVisibility(z ? 0 : 8);
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.f17204e != null) {
            ba.b(new d(z, z2));
        }
    }

    @Override // com.tencent.mapsdk.internal.o4
    public boolean a(ViewGroup viewGroup, Bundle bundle) {
        if (viewGroup == null) {
            return false;
        }
        this.f17213n = viewGroup;
        LinearLayout linearLayout = this.f17212m;
        if (linearLayout == null || linearLayout.getParent() != viewGroup) {
            LinearLayout linearLayout2 = new LinearLayout(this.d);
            this.f17212m = linearLayout2;
            linearLayout2.setOrientation(1);
            viewGroup.addView(this.f17212m);
        }
        FrameLayout.LayoutParams e2 = e();
        this.f17212m.setGravity(e2.gravity);
        this.f17212m.setLayoutParams(e2);
        if (this.f17211l && this.f17204e == null) {
            b(this.d);
        } else {
            h();
        }
        if (this.f17210k && this.f17205f == null) {
            a(this.d);
        } else {
            g();
        }
        c(this.f17208i.getMapContext().d(bundle != null ? bundle.getInt(l1.q, -1) : -1));
        this.f17212m.requestLayout();
        return true;
    }

    public void b(boolean z) {
        this.f17211l = z;
        if (z && this.f17204e == null) {
            b(this.d);
        }
        ZoomControls zoomControls = this.f17204e;
        if (zoomControls != null) {
            zoomControls.setVisibility(z ? 0 : 8);
        }
    }

    @Override // com.tencent.mapsdk.internal.m4
    public View[] c() {
        return new View[]{this.f17204e, this.f17205f};
    }

    @Override // com.tencent.mapsdk.internal.m4, com.tencent.mapsdk.internal.o4
    public Rect d() {
        Rect rect = new Rect();
        ZoomControls zoomControls = this.f17204e;
        if (zoomControls != null && this.f17205f != null) {
            rect.bottom = Math.min(zoomControls.getBottom(), this.f17205f.getBottom());
            rect.right = Math.max(this.f17204e.getRight(), this.f17205f.getRight());
            rect.left = Math.min(this.f17204e.getLeft(), this.f17205f.getLeft());
            rect.top = Math.min(this.f17204e.getTop(), this.f17205f.getTop());
        }
        return rect;
    }

    public boolean f() {
        ZoomControls zoomControls = this.f17204e;
        return zoomControls != null && zoomControls.getVisibility() == 0;
    }

    @Override // com.tencent.mapsdk.internal.o4
    public o4.b getPosition() {
        return this.f17206g;
    }
}
