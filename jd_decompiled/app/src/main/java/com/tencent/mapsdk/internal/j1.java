package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.mapsdk.internal.ca;
import com.tencent.mapsdk.internal.lf;
import com.tencent.mapsdk.internal.ph;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class j1 implements c1, lf.a, ph.i {
    private final e1 a;
    private Marker d;

    /* renamed from: e  reason: collision with root package name */
    private Marker f16714e;

    /* renamed from: f  reason: collision with root package name */
    private Marker f16715f;

    /* renamed from: g  reason: collision with root package name */
    private ca.a f16716g;

    /* renamed from: h  reason: collision with root package name */
    private TencentMap.OnMarkerDragListener f16717h;

    /* renamed from: i  reason: collision with root package name */
    private final i1 f16718i;

    /* renamed from: j  reason: collision with root package name */
    private x4 f16719j;

    /* renamed from: k  reason: collision with root package name */
    private List<w4> f16720k;

    /* renamed from: l  reason: collision with root package name */
    private z4 f16721l;

    /* renamed from: m  reason: collision with root package name */
    private u4 f16722m;
    private Marker b = null;

    /* renamed from: c  reason: collision with root package name */
    private boolean f16713c = false;

    /* renamed from: n  reason: collision with root package name */
    private final Map<Class<? extends uc>, tc> f16723n = new HashMap();

    /* loaded from: classes9.dex */
    public class a implements TencentMap.OnMarkerClickListener {
        public a() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
        public boolean onMarkerClick(Marker marker) {
            if (j1.this.f16716g == null) {
                j1 j1Var = j1.this;
                j1Var.f16716g = ca.a(j1Var.a.getContext());
            }
            ma.a(j1.this.a.getContext(), j1.this.f16716g);
            return false;
        }
    }

    public j1(i1 i1Var, e1 e1Var) {
        this.a = e1Var;
        this.f16718i = i1Var;
    }

    private boolean b(float f2, float f3) {
        List<w4> list;
        x4 x4Var;
        TappedElement a2 = this.a.f().a(f2, f3);
        if (a2 == null) {
            return false;
        }
        int i2 = a2.type;
        if (i2 == 1 && (x4Var = this.f16719j) != null) {
            x4Var.a(new t5(a2.name, ea.b(a2.pixelX, a2.pixelY)));
            return true;
        } else if (i2 != 6 || (list = this.f16720k) == null) {
            z4 z4Var = this.f16721l;
            if (z4Var != null) {
                z4Var.a();
            }
            return false;
        } else {
            for (w4 w4Var : list) {
                if (w4Var != null) {
                    w4Var.a();
                }
            }
            return true;
        }
    }

    public Pair<VectorOverlay, TencentMap.IClickedObject> a(LatLng latLng, long j2, String str, String str2) {
        tc tcVar = this.f16723n.get(dd.class);
        return tcVar != null ? tcVar.a(latLng, j2, str, str2) : new Pair<>(null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <O extends uc, L extends sc<O>> L a(O o) {
        e1 e1Var = this.a;
        if (o == null || e1Var == null) {
            return null;
        }
        tc tcVar = this.f16723n.get(o.getClass());
        if (tcVar == null) {
            if (o.getClass() == qd.class) {
                tcVar = new pd(e1Var.f());
            } else if (o.getClass() == bd.class) {
                tcVar = new ad(e1Var.f());
            } else if (o.getClass() == xc.class) {
                tcVar = new yc(e1Var.f());
            } else if (o.getClass() == hd.class) {
                tcVar = new gd(e1Var.f());
            } else if (o.getClass() == kd.class) {
                tcVar = new jd(e1Var.f());
            } else if (o.getClass() == nd.class) {
                tcVar = new ld(e1Var.f());
            } else if (o.getClass() == td.class) {
                tcVar = new sd(e1Var.f());
            } else if (o.getClass() == wd.class) {
                tcVar = new vd(e1Var.f());
            } else if (o.getClass() == dd.class) {
                tcVar = new cd(e1Var.f());
            }
            this.f16723n.put(o.getClass(), tcVar);
        }
        return (L) tcVar.a((tc) o);
    }

    public <O extends uc, L extends sc<O>> L a(Class<L> cls, int i2) {
        L l2;
        tc tcVar = this.f16723n.get(((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0]);
        if (tcVar == null || (l2 = (L) tcVar.a(i2)) == null) {
            return null;
        }
        return l2;
    }

    @Override // com.tencent.mapsdk.internal.c1
    public void a() {
        this.f16713c = false;
        this.b = null;
    }

    public <O extends uc, L extends sc<O>> void a(int i2, O o) {
        sc a2;
        tc tcVar = this.f16723n.get(o.getClass());
        if (tcVar == null || (a2 = tcVar.a(i2)) == null) {
            return;
        }
        a2.a((sc) o);
        tcVar.c(a2);
    }

    @Override // com.tencent.mapsdk.internal.lf.a
    public void a(Bitmap bitmap, int i2, int i3) {
        Marker marker = this.f16715f;
        if (marker == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.tag(lf.f16840j);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(this.a, bitmap));
            Marker a2 = this.f16718i.a(markerOptions);
            this.f16715f = a2;
            a2.setFixingPoint(i2 / 2, i3 / 2);
            this.f16715f.setClickable(false);
        } else {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(this.a, bitmap));
            this.f16715f.setFixingPoint(i2 / 2, i3 / 2);
        }
        ga.a(bitmap);
    }

    @Override // com.tencent.mapsdk.internal.c1
    public void a(MotionEvent motionEvent) {
        if (this.a == null || !this.f16713c || this.b == null) {
            return;
        }
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                this.b.setPosition(ea.d(this.a.getProjection().a(new o5((int) motionEvent.getX(), (int) motionEvent.getY()))));
                TencentMap.OnMarkerDragListener onMarkerDragListener = this.f16717h;
                if (onMarkerDragListener != null) {
                    onMarkerDragListener.onMarkerDrag(this.b);
                    return;
                }
                return;
            } else if (action != 3 && action != 4) {
                return;
            }
        }
        this.f16713c = false;
        TencentMap.OnMarkerDragListener onMarkerDragListener2 = this.f16717h;
        if (onMarkerDragListener2 != null) {
            onMarkerDragListener2.onMarkerDragEnd(this.b);
        }
        this.b = null;
    }

    @Override // com.tencent.mapsdk.internal.ph.i
    public void a(View view, Rect rect, boolean z) {
        Bitmap a2;
        if (view == null || rect == null || (a2 = a7.a(view)) == null) {
            return;
        }
        if (this.f16714e == null) {
            MarkerOptions visible = new MarkerOptions().anchor(0.0f, 0.0f).visible(false);
            visible.tag(lf.f16840j);
            visible.fastLoad(true);
            Marker a3 = this.f16718i.a(visible);
            this.f16714e = a3;
            a3.setClickable(false);
        }
        this.f16714e.setFixingPoint(rect.left, rect.top);
        this.f16714e.setIcon(BitmapDescriptorFactory.fromBitmap(this.a, a2));
        this.f16714e.setVisible(z);
        ga.a(a2);
    }

    @Override // com.tencent.mapsdk.internal.ph.i
    public void a(ph phVar) {
        Marker marker = this.f16714e;
        if (marker != null) {
            marker.setVisible(true);
        }
        Marker marker2 = this.d;
        if (marker2 != null) {
            marker2.setVisible(true);
        }
    }

    public void a(t4 t4Var) {
        b(t4Var);
    }

    public void a(t4 t4Var, t4 t4Var2) {
        b(t4Var);
    }

    public void a(u4 u4Var) {
        this.f16722m = u4Var;
    }

    public void a(w4 w4Var) {
        if (w4Var == null) {
            return;
        }
        if (this.f16720k == null) {
            this.f16720k = new ArrayList();
        }
        this.f16720k.add(w4Var);
    }

    public void a(x4 x4Var) {
        this.f16719j = x4Var;
    }

    public void a(z4 z4Var) {
        this.f16721l = z4Var;
    }

    public void a(TencentMap.OnMarkerDragListener onMarkerDragListener) {
        this.f16717h = onMarkerDragListener;
    }

    @Override // com.tencent.mapsdk.internal.c1
    public void a(String str) {
        if (str.trim().length() != 0) {
            Marker marker = (Marker) this.f16718i.a(str, w0.class);
            this.b = marker;
            if (marker == null) {
                return;
            }
            e1 e1Var = this.a;
            if ((e1Var instanceof o1) && a((o1) e1Var, marker)) {
                return;
            }
            if (this.b.isDraggable()) {
                this.f16713c = true;
                TencentMap.OnMarkerDragListener onMarkerDragListener = this.f16717h;
                if (onMarkerDragListener != null) {
                    onMarkerDragListener.onMarkerDragStart(this.b);
                    return;
                }
                return;
            }
        }
        this.b = null;
        this.f16713c = false;
    }

    public boolean a(float f2, float f3) {
        if (this.f16718i.a(f2, f3)) {
            return true;
        }
        return b(f2, f3);
    }

    public <O extends uc, L extends sc<O>> boolean a(int i2, Class<L> cls) {
        sc a2 = a(cls, i2);
        if (a2 != null) {
            a2.remove();
            return true;
        }
        return false;
    }

    public boolean a(o1 o1Var, Marker marker) {
        if (marker == null || marker != this.d) {
            return false;
        }
        return f6.a(o1Var);
    }

    @Deprecated
    public boolean a(GL10 gl10) {
        this.f16718i.a(gl10);
        return true;
    }

    public void b() {
        Collection<tc> values;
        Map<Class<? extends uc>, tc> map = this.f16723n;
        if (map == null || (values = map.values()) == null || values.isEmpty()) {
            return;
        }
        Iterator<tc> it = values.iterator();
        while (it.hasNext()) {
            it.next().c();
        }
    }

    @Override // com.tencent.mapsdk.internal.ph.i
    public void b(View view, Rect rect, boolean z) {
        Bitmap a2;
        if (view == null || rect == null || (a2 = a7.a(view)) == null) {
            return;
        }
        if (this.d == null) {
            MarkerOptions visible = new MarkerOptions().anchor(0.0f, 0.0f).visible(false);
            visible.tag(lf.f16840j);
            visible.fastLoad(true);
            Marker a3 = this.f16718i.a(visible);
            this.d = a3;
            w0 w0Var = (w0) this.f16718i.a(a3.getId(), w0.class);
            if (w0Var != null) {
                w0Var.x().a(new a());
            }
        }
        this.d.setFixingPoint(rect.left, rect.top);
        this.d.setIcon(BitmapDescriptorFactory.fromBitmap(this.a, a2));
        this.d.setVisible(z);
        ga.a(a2);
    }

    @Override // com.tencent.mapsdk.internal.ph.i
    public void b(ph phVar) {
        Marker marker = this.f16714e;
        if (marker != null) {
            marker.setVisible(false);
        }
        if (phVar == null || this.d == null || phVar.u()) {
            return;
        }
        this.d.setVisible(false);
    }

    public void b(t4 t4Var) {
        this.f16718i.a(t4Var);
    }

    public void b(t4 t4Var, t4 t4Var2) {
        b(t4Var);
    }

    public void b(w4 w4Var) {
        List<w4> list;
        if (w4Var == null || (list = this.f16720k) == null) {
            return;
        }
        list.remove(w4Var);
    }

    public i1 c() {
        return this.f16718i;
    }

    public void c(t4 t4Var) {
        b(t4Var);
    }

    public boolean c(float f2, float f3) {
        TappedElement a2 = this.a.f().a(f2, f3);
        if (a2 != null && a2.type == 3) {
            u4 u4Var = this.f16722m;
            if (u4Var != null) {
                u4Var.j();
                return true;
            }
            return true;
        }
        return false;
    }

    public void d(t4 t4Var) {
        this.f16718i.b(t4Var);
    }

    public boolean d() {
        tc tcVar = this.f16723n.get(dd.class);
        if (tcVar != null) {
            return tcVar.b();
        }
        return false;
    }

    public boolean d(float f2, float f3) {
        TappedElement a2 = this.a.f().a(f2, f3);
        return a2 != null && a2.type == 6;
    }

    public void e() {
        Collection<tc> values;
        Map<Class<? extends uc>, tc> map = this.f16723n;
        if (map != null && (values = map.values()) != null && !values.isEmpty()) {
            Iterator<tc> it = values.iterator();
            while (it.hasNext()) {
                it.next().d();
            }
        }
        a();
    }
}
