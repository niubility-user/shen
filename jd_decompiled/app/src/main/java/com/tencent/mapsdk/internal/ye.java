package com.tencent.mapsdk.internal;

import android.graphics.Color;
import android.graphics.Rect;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.mapsdk.internal.p0;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public abstract class ye<D extends p0> extends v0<D> implements p4 {
    private static final AtomicInteger A = new AtomicInteger(1);

    /* renamed from: i  reason: collision with root package name */
    public IndoorInfo f17509i;
    private boolean r;
    private Object s;
    private boolean t;
    private Selectable.OnSelectedListener u;
    private float v;
    private boolean w;
    private boolean x;
    private final a1 y;
    private volatile boolean z;

    /* renamed from: g  reason: collision with root package name */
    private final String f17507g = String.valueOf(A.incrementAndGet());

    /* renamed from: h  reason: collision with root package name */
    private boolean f17508h = false;

    /* renamed from: j  reason: collision with root package name */
    public float f17510j = 1.0f;

    /* renamed from: k  reason: collision with root package name */
    public int f17511k = Color.argb(17, 0, (int) R2.anim.pop_center_out, 255);

    /* renamed from: l  reason: collision with root package name */
    public int f17512l = Color.argb(255, 0, (int) R2.anim.pop_center_out, 255);

    /* renamed from: m  reason: collision with root package name */
    public float f17513m = 0.0f;

    /* renamed from: n  reason: collision with root package name */
    public boolean f17514n = true;
    public boolean o = false;
    public int p = 2;
    private int q = -1;

    public ye(a1 a1Var) {
        this.y = a1Var;
    }

    public Selectable.OnSelectedListener A() {
        return this.u;
    }

    public void B() {
        if (this.z) {
            return;
        }
        this.o = true;
    }

    public boolean C() {
        return this.o;
    }

    public void D() {
        this.z = true;
    }

    public void E() {
    }

    public void F() {
    }

    public void G() {
    }

    public void H() {
    }

    public void I() {
        this.z = false;
        B();
    }

    public int a() {
        return this.q;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        return new Rect();
    }

    public void a(int i2) {
        this.q = i2;
    }

    @Override // com.tencent.mapsdk.internal.p4
    public void a(int i2, int i3) {
    }

    public void a(IndoorBuilding indoorBuilding) {
        IndoorInfo indoorInfo = this.f17509i;
        if (indoorInfo != null) {
            this.f17508h = indoorInfo.toString().equals(indoorBuilding.toString());
            B();
        }
        n();
    }

    @Override // com.tencent.mapsdk.internal.r4
    public void a(IndoorInfo indoorInfo) {
        this.f17509i = indoorInfo;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.mapsdk.internal.n4
    public final void a(GL10 gl10) {
        a1 a1Var;
        F();
        boolean C = C();
        E();
        if (C && (a1Var = this.y) != null) {
            a1Var.a();
        }
        this.o = false;
        G();
    }

    @Override // com.tencent.mapsdk.internal.r4
    public boolean d() {
        return this.f17508h;
    }

    @Override // com.tencent.mapsdk.internal.r4
    public IndoorInfo f() {
        return this.f17509i;
    }

    public int getFillColor() {
        return this.f17511k;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    public List<Boundable<s4>> getGroupBounds() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(this);
        return arrayList;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    public String getId() {
        return y();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getLevel() {
        return this.p;
    }

    public float getRotation() {
        return this.v;
    }

    public int getStrokeColor() {
        return this.f17512l;
    }

    public float getStrokeWidth() {
        return this.f17510j;
    }

    public Object getTag() {
        return this.s;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getZIndex() {
        return (int) this.f17513m;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean handleOnTap() {
        return false;
    }

    public boolean isClickable() {
        return this.r;
    }

    public boolean isDraggable() {
        return this.w;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public boolean isRemoved() {
        return this.x;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public boolean isSelected() {
        return this.t;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public boolean isVisible() {
        return f() != null ? this.f17514n && d() : this.f17514n;
    }

    public void l() {
        if (this.f17509i != null) {
            this.f17508h = false;
            B();
        }
        n();
    }

    public void n() {
    }

    public boolean q() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void releaseData() {
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public final void remove() {
        this.u = null;
        this.y.b(getId());
        H();
        this.x = true;
    }

    public void setClickable(boolean z) {
        this.r = z;
    }

    public void setDraggable(boolean z) {
        this.w = z;
    }

    public void setFillColor(int i2) {
        this.f17511k = i2;
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setLevel(int i2) {
        this.p = i2;
        B();
    }

    public void setRotation(float f2) {
        this.v = f2;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelected(boolean z) {
        this.t = z;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelectedListener(Selectable.OnSelectedListener onSelectedListener) {
        this.u = onSelectedListener;
    }

    public void setStrokeColor(int i2) {
        this.f17512l = i2;
        B();
    }

    public void setStrokeWidth(float f2) {
        this.f17510j = f2;
        B();
    }

    public void setTag(Object obj) {
        this.s = obj;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public void setVisible(boolean z) {
        this.f17514n = z;
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(float f2) {
        this.f17513m = f2;
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(int i2) {
        setZIndex(i2);
    }

    public final String y() {
        return this.f17507g;
    }

    public a1 z() {
        return this.y;
    }
}
