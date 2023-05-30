package com.tencent.mapsdk.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tencent.mapsdk.internal.lf;
import com.tencent.mapsdk.internal.o4;
import com.tencent.mapsdk.internal.ph;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class l1 implements b5, d1, h5, l0, l5 {
    public static final String q = "key_change_style";
    private ViewGroup d;

    /* renamed from: e */
    private TencentMapOptions f16773e;

    /* renamed from: f */
    private xi f16774f;

    /* renamed from: g */
    private rh f16775g;

    /* renamed from: h */
    private ph f16776h;

    /* renamed from: i */
    private dg f16777i;

    /* renamed from: j */
    private kf f16778j;

    /* renamed from: k */
    private lf.a f16779k;

    /* renamed from: l */
    private int f16780l;

    /* renamed from: m */
    private Bundle f16781m;

    /* renamed from: n */
    private List<o4> f16782n = new CopyOnWriteArrayList();
    private boolean o = false;
    public Handler p = new a(ba.a("gesture"));

    /* loaded from: classes9.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(looper);
            l1.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object obj;
            if (message == null || (obj = message.obj) == null) {
                return;
            }
            u5 u5Var = (u5) obj;
            int i2 = u5Var.a;
            if (i2 == 0) {
                if (l1.this.f16775g != null) {
                    l1.this.f16775g.a(u5Var.b, u5Var.f17300c);
                }
            } else if (i2 == 1) {
                if (l1.this.f16776h != null) {
                    l1.this.f16776h.a(u5Var.d, u5Var.f17301e);
                }
            } else if (i2 != 3 || b7.D != 1) {
                if (i2 == 2 && l1.this.isCompassEnabled()) {
                    l1.this.f16774f.getMap().c(l1.this.f16774f.getMapContext().d(u5Var.f17303g) ? yd.b : yd.a);
                }
            } else if (TextUtils.equals(li.f16848c, ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE) || l1.this.d == null) {
            } else {
                if (l1.this.f16778j == null) {
                    l1 l1Var = l1.this;
                    l1Var.f16778j = new kf(l1Var.d.getContext().getApplicationContext(), l1.this.f16774f.getMapContext());
                    l1.this.f16778j.a(l1.this.f16779k);
                }
                l1.this.f16778j.a(l1.this.d, (Bundle) null);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b() {
            l1.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            l1 l1Var = l1.this;
            l1Var.a(l1Var.f16781m);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            o4.b.values();
            int[] iArr = new int[6];
            a = iArr;
            try {
                o4.b bVar = o4.b.LEFT_TOP;
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                o4.b bVar2 = o4.b.LEFT_BOTTOM;
                iArr2[0] = 2;
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
                o4.b bVar4 = o4.b.RIGHT_TOP;
                iArr4[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface d {
        void a();
    }

    public l1(e1 e1Var, ViewGroup viewGroup, v1 v1Var) {
        this.d = null;
        this.d = viewGroup;
        if (v1Var == null) {
            return;
        }
        xi xiVar = (xi) e1Var.j();
        this.f16774f = xiVar;
        this.f16773e = xiVar.l();
        this.f16774f.getMap().a(this);
        if (v1Var instanceof View) {
            View view = (View) v1Var;
            if (this.d.indexOfChild(view) < 0) {
                this.d.addView(view, 0, new FrameLayout.LayoutParams(-1, -1));
                this.d.requestLayout();
            }
        }
        ph phVar = new ph(this.d.getContext().getApplicationContext(), this.f16774f, this.f16774f.getMap().R());
        this.f16776h = phVar;
        this.f16774f.a(phVar);
        dg dgVar = new dg(e1Var);
        this.f16777i = dgVar;
        this.f16776h.a(dgVar);
        this.f16774f.a(this.f16777i);
        this.f16775g = new rh(this.d.getContext(), this.f16774f);
        this.f16782n.add(this.f16776h);
        this.f16782n.add(this.f16775g);
        this.f16782n.add(this.f16777i);
        this.f16774f.b((b5) this);
        this.f16774f.a((d1) this);
        this.f16774f.a((h5) this);
    }

    public void a(Bundle bundle) {
        Iterator<o4> it = this.f16782n.iterator();
        while (it.hasNext()) {
            it.next().a(this.d, bundle);
        }
    }

    @Override // com.tencent.mapsdk.internal.l5
    public void a(int i2) {
        if (this.f16781m == null) {
            this.f16781m = new Bundle();
        }
        this.f16781m.putInt(q, i2);
        e();
        u5 u5Var = new u5();
        u5Var.a = 2;
        u5Var.f17303g = i2;
        a(u5Var);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void a(int i2, float f2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(o4.a.a(i2), f2);
            e();
        }
    }

    @Override // com.tencent.mapsdk.internal.h5
    public void a(int i2, int i3) {
        this.f16780l = i3;
        Iterator<o4> it = this.f16782n.iterator();
        while (it.hasNext()) {
            it.next().a(i2, i3);
        }
        e();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void a(int i2, int i3, int i4, int i5, int i6) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(o4.b.a(i2));
            this.f16776h.a(o4.a.TOP, i3);
            this.f16776h.a(o4.a.BOTTOM, i4);
            this.f16776h.a(o4.a.LEFT, i5);
            this.f16776h.a(o4.a.RIGHT, i6);
            this.f16776h.q();
            e();
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void a(int i2, int[] iArr) {
        ph phVar;
        o4.a aVar;
        int i3;
        if (this.f16776h != null) {
            o4.b a2 = o4.b.a(i2);
            this.f16776h.a(a2);
            int ordinal = a2.ordinal();
            if (ordinal == 0) {
                this.f16776h.a(o4.a.BOTTOM, iArr[0]);
                phVar = this.f16776h;
                aVar = o4.a.LEFT;
                i3 = iArr[1];
            } else if (ordinal == 5) {
                this.f16776h.a(o4.a.TOP, iArr[0]);
                phVar = this.f16776h;
                aVar = o4.a.RIGHT;
                i3 = iArr[1];
            } else if (ordinal != 2) {
                if (ordinal == 3) {
                    this.f16776h.a(o4.a.TOP, iArr[0]);
                    phVar = this.f16776h;
                    aVar = o4.a.LEFT;
                    i3 = iArr[1];
                }
                this.f16776h.q();
                e();
            } else {
                this.f16776h.a(o4.a.BOTTOM, iArr[0]);
                phVar = this.f16776h;
                aVar = o4.a.RIGHT;
                i3 = iArr[1];
            }
            phVar.a(aVar, i3);
            this.f16776h.q();
            e();
        }
    }

    public void a(d dVar) {
        rh rhVar = this.f16775g;
        if (rhVar != null) {
            rhVar.a(dVar);
        }
    }

    public void a(lf.a aVar, TencentMapOptions tencentMapOptions) {
        if (tencentMapOptions == null || tencentMapOptions.getExtSurface() == null) {
            return;
        }
        this.f16779k = aVar;
        int extSurfaceWidth = tencentMapOptions.getExtSurfaceWidth();
        int extSurfaceHeight = tencentMapOptions.getExtSurfaceHeight();
        this.d.measure(View.MeasureSpec.makeMeasureSpec(extSurfaceWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(extSurfaceHeight, 1073741824));
        a(extSurfaceWidth, extSurfaceHeight);
    }

    public void a(ph.i iVar, TencentMapOptions tencentMapOptions) {
        ph phVar;
        if (tencentMapOptions == null || tencentMapOptions.getExtSurface() == null || (phVar = this.f16776h) == null) {
            return;
        }
        phVar.a(iVar);
        int extSurfaceWidth = tencentMapOptions.getExtSurfaceWidth();
        int extSurfaceHeight = tencentMapOptions.getExtSurfaceHeight();
        this.d.measure(View.MeasureSpec.makeMeasureSpec(extSurfaceWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(extSurfaceHeight, 1073741824));
        a(extSurfaceWidth, extSurfaceHeight);
    }

    @Override // com.tencent.mapsdk.internal.b5
    public void a(u5 u5Var) {
        int i2;
        if (u5Var == null || (i2 = u5Var.a) == -1) {
            return;
        }
        this.p.sendMessage(this.p.obtainMessage(i2, u5Var));
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void a(boolean z) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.d1
    public void a(boolean z, List<yh> list) {
        b(z, list);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void b(int i2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(o4.a.BOTTOM, i2);
            this.f16776h.q();
            e();
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void b(int i2, int i3, int i4, int i5, int i6) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.b(o4.b.a(i2));
            this.f16776h.b(o4.a.TOP, i3);
            this.f16776h.b(o4.a.BOTTOM, i4);
            this.f16776h.b(o4.a.LEFT, i5);
            this.f16776h.b(o4.a.RIGHT, i6);
            this.f16776h.s();
            e();
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void b(boolean z) {
        this.f16774f.setFlingGestureEnabled(z);
    }

    public void b(boolean z, List<yh> list) {
        xi xiVar;
        if (this.f16776h == null || (xiVar = this.f16774f) == null || xiVar.getMap() == null) {
            return;
        }
        this.f16776h.a(list);
        if (z) {
            this.f16776h.e();
        }
        this.f16776h.a(this.f16774f.i0(), this.f16774f.getMapContext().a());
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean b() {
        ph phVar = this.f16776h;
        if (phVar != null) {
            return phVar.l();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.l0
    public float c(int i2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            return phVar.b(o4.a.a(i2));
        }
        return 0.0f;
    }

    public void c() {
        ViewGroup viewGroup = this.d;
        xi xiVar = this.f16774f;
        if (viewGroup == null || xiVar == null) {
            return;
        }
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        xiVar.getMap().b(this);
        xiVar.b((d1) this);
        xiVar.a((b5) this);
        viewGroup.removeAllViews();
        Iterator<o4> it = this.f16782n.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f16782n.clear();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void d(int i2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.b(o4.b.a(i2));
            e();
        }
    }

    public void e() {
        ba.b(new b());
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void e(int i2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(o4.a.LEFT, i2);
            this.f16776h.q();
            e();
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void f(int i2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(o4.b.a(i2));
            e();
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isCompassEnabled() {
        return this.f16774f.D();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isIndoorLevelPickerEnabled() {
        dg dgVar = this.f16777i;
        if (dgVar != null) {
            return dgVar.h();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isMyLocationButtonEnabled() {
        return this.o;
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isRotateGesturesEnabled() {
        return this.f16774f.M();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isScrollGesturesEnabled() {
        return this.f16774f.O();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isTiltGesturesEnabled() {
        return this.f16774f.f();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isZoomControlsEnabled() {
        return this.f16775g.f();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public boolean isZoomGesturesEnabled() {
        return this.f16774f.G();
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setAllGesturesEnabled(boolean z) {
        this.f16774f.j(z);
        setScrollGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setRotateGesturesEnabled(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setCompassEnabled(boolean z) {
        this.f16774f.c(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setCompassExtraPadding(int i2) {
        xi xiVar = this.f16774f;
        if (xiVar != null) {
            xiVar.setCompassExtraPadding(i2);
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setCompassExtraPadding(int i2, int i3) {
        xi xiVar = this.f16774f;
        if (xiVar != null) {
            xiVar.setCompassExtraPadding(i2, i3);
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setGestureRotateByMapCenter(boolean z) {
        xi xiVar = this.f16774f;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        this.f16774f.getMap().f(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setGestureScaleByMapCenter(boolean z) {
        xi xiVar = this.f16774f;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        this.f16774f.getMap().g(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setIndoorLevelPickerEnabled(boolean z) {
        this.f16777i.a(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setLogoScale(float f2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.b(f2);
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setLogoSize(int i2) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.a(i2);
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setMyLocationButtonEnabled(boolean z) {
        this.o = z;
        this.f16775g.a(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setRotateGesturesEnabled(boolean z) {
        this.f16774f.d(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setScaleViewEnabled(boolean z) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.b(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setScaleViewFadeEnable(boolean z) {
        ph phVar = this.f16776h;
        if (phVar != null) {
            phVar.c(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setScrollGesturesEnabled(boolean z) {
        this.f16774f.e(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setTiltGesturesEnabled(boolean z) {
        this.f16774f.b(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setZoomControlsEnabled(boolean z) {
        this.f16775g.b(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setZoomGesturesEnabled(boolean z) {
        this.f16774f.h(z);
    }

    @Override // com.tencent.mapsdk.internal.l0
    public void setZoomPosition(int i2) {
        rh rhVar = this.f16775g;
        if (rhVar != null) {
            rhVar.a(o4.b.a(i2));
        }
    }
}
