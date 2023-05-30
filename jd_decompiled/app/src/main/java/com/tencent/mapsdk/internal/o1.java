package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.map.sdk.comps.offlinemap.OfflineMapComponent;
import com.tencent.mapsdk.core.MapDelegate;
import com.tencent.mapsdk.internal.m5;
import com.tencent.tencentmap.mapsdk.maps.TencentMapComponent;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.TencentMapProtocol;
import com.tencent.tencentmap.mapsdk.maps.TencentMapResource;
import com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.MapViewType;
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaSource;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public abstract class o1 implements TencentMapContext {

    /* renamed from: i  reason: collision with root package name */
    private static final Map<Class<? extends TencentMapComponent.Component>, TencentMapComponent.Component> f16890i = new ConcurrentHashMap();

    /* renamed from: j  reason: collision with root package name */
    private static final Set<a> f16891j;

    /* renamed from: k  reason: collision with root package name */
    private static final String f16892k = "map-context.cache";

    /* renamed from: l  reason: collision with root package name */
    private static final String f16893l = "navi_marker_location.png";

    /* renamed from: m  reason: collision with root package name */
    private static final String f16894m = "color_texture_flat_style.png";
    private final Context a;
    private final TencentMapOptions b;

    /* renamed from: c  reason: collision with root package name */
    private final p1 f16895c;
    private e6 d;

    /* renamed from: e  reason: collision with root package name */
    private v6 f16896e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f16897f = false;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f16898g = true;

    /* renamed from: h  reason: collision with root package name */
    private lc f16899h;

    /* loaded from: classes9.dex */
    public static class a {
        public Class<? extends TencentMapComponent.Component> a;
        public Class<? extends TencentMapComponent.Component> b;

        public a(Class<? extends TencentMapComponent.Component> cls, Class<? extends TencentMapComponent.Component> cls2) {
            this.a = cls;
            this.b = cls2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            Class<? extends TencentMapComponent.Component> cls = this.a;
            if (cls == null ? aVar.a == null : cls.equals(aVar.a)) {
                Class<? extends TencentMapComponent.Component> cls2 = this.b;
                Class<? extends TencentMapComponent.Component> cls3 = aVar.b;
                return cls2 != null ? cls2.equals(cls3) : cls3 == null;
            }
            return false;
        }

        public int hashCode() {
            Class<? extends TencentMapComponent.Component> cls = this.a;
            int hashCode = (cls != null ? cls.hashCode() : 0) * 31;
            Class<? extends TencentMapComponent.Component> cls2 = this.b;
            return hashCode + (cls2 != null ? cls2.hashCode() : 0);
        }
    }

    /* loaded from: classes9.dex */
    public static class b {
        public String a;

        /* renamed from: c  reason: collision with root package name */
        public String f16900c;
        public String d;

        /* renamed from: f  reason: collision with root package name */
        public String f16902f;
        public String b = b7.N();

        /* renamed from: e  reason: collision with root package name */
        public String f16901e = b7.E();

        public b(TencentMapOptions tencentMapOptions) {
            this.f16902f = "undefined";
            this.a = b7.t();
            if (tencentMapOptions != null) {
                if (!TextUtils.isEmpty(tencentMapOptions.getMapKey())) {
                    this.a = tencentMapOptions.getMapKey();
                }
                if (!TextUtils.isEmpty(tencentMapOptions.getSubKey())) {
                    this.f16900c = tencentMapOptions.getSubKey();
                }
                if (!TextUtils.isEmpty(tencentMapOptions.getSubId())) {
                    this.d = tencentMapOptions.getSubId();
                }
                this.f16902f = tencentMapOptions.getCustomUserId();
            }
        }

        public String a() {
            return this.f16902f;
        }

        public String b() {
            return TextUtils.isEmpty(this.f16900c) ? this.a : this.f16900c;
        }

        public String c() {
            return wa.a(e());
        }

        public String d() {
            return wa.a(f());
        }

        public String e() {
            return this.a + "-" + this.b + "-" + this.f16900c + "-" + this.d;
        }

        public String f() {
            return this.f16901e + "-" + this.a + "-" + this.b + "-" + this.f16900c + "-" + this.d;
        }

        public String g() {
            return this.a;
        }

        public String h() {
            return this.b;
        }

        public String i() {
            return this.f16901e;
        }

        public String j() {
            return this.f16900c;
        }

        public String k() {
            return this.d;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        f16891j = hashSet;
        hashSet.add(new a(TencentMapProtocol.class, l2.class));
        hashSet.add(new a(OfflineMapComponent.class, b2.class));
    }

    public o1(Context context, TencentMapOptions tencentMapOptions, p1 p1Var) {
        this.a = context.getApplicationContext();
        this.b = tencentMapOptions;
        this.f16895c = p1Var;
        BitmapDescriptorFactory.attachMapContext(this);
        ic.a(tencentMapOptions);
    }

    private void F() {
        Map<Class<? extends TencentMapComponent.Component>, Class<? extends TencentMapComponent.Component>> C = C();
        if (C != null) {
            for (Map.Entry<Class<? extends TencentMapComponent.Component>, Class<? extends TencentMapComponent.Component>> entry : C.entrySet()) {
                f16891j.add(new a(entry.getKey(), entry.getValue()));
            }
        }
        for (a aVar : f16891j) {
            Class<? extends TencentMapComponent.Component> cls = aVar.a;
            Map<Class<? extends TencentMapComponent.Component>, TencentMapComponent.Component> map = f16890i;
            TencentMapComponent.Component component = map.get(cls);
            Class<? extends TencentMapComponent.Component> cls2 = aVar.b;
            if (component == null && cls2 != null && cls.isAssignableFrom(cls2)) {
                component = (TencentMapComponent.Component) d7.a(cls2, new Object[0]);
                if (component instanceof n1) {
                    ((n1) component).a(getContext());
                }
                map.put(cls, component);
            }
            if (component instanceof n1) {
                ((n1) component).a(this);
            }
        }
    }

    private void G() {
        for (Map.Entry<Class<? extends TencentMapComponent.Component>, TencentMapComponent.Component> entry : f16890i.entrySet()) {
            TencentMapComponent.Component value = entry.getValue();
            if (value instanceof n1) {
                n1 n1Var = (n1) value;
                n1Var.b(this);
                if (n1Var.getMapContext() == null) {
                    f16890i.remove(entry.getKey());
                }
            }
        }
        b7.P();
    }

    private void b(Bundle bundle) {
        a(bundle);
        if (bundle == null || bundle.size() <= 0) {
            return;
        }
        Parcel obtain = Parcel.obtain();
        bundle.writeToParcel(obtain, 0);
        fa.b(fa.b(fa.f16514e, f16892k), obtain.marshall());
        obtain.recycle();
    }

    private void z() {
        e6 e6Var = new e6(this);
        this.d = e6Var;
        e6Var.c();
        this.f16896e = this.d.a();
    }

    public boolean A() {
        return this.f16897f;
    }

    public boolean B() {
        return this.f16898g;
    }

    public Map<Class<? extends TencentMapComponent.Component>, Class<? extends TencentMapComponent.Component>> C() {
        return null;
    }

    public void D() {
        b q = q();
        b7.a(this.a, q.a, q.b, q.f16902f);
        z();
        F();
    }

    public void E() {
        this.d.a(this.f16896e.s());
        b(new Bundle());
        G();
        BitmapDescriptorFactory.detachMapContext(this);
    }

    public Bundle a(Context context) {
        byte[] h2;
        File file = new File(fa.f16514e, f16892k);
        if (!file.exists() || (h2 = fa.h(file)) == null || h2.length <= 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(h2, 0, h2.length);
        Bundle bundle = new Bundle();
        bundle.readFromParcel(obtain);
        fa.d(file);
        obtain.recycle();
        return bundle;
    }

    public <T extends TencentMapComponent.Component> T a(Class<T> cls, Bundle bundle) {
        T t = (T) f16890i.get(cls);
        if (t instanceof n1) {
            ((n1) t).a(this, bundle);
        }
        return t;
    }

    public abstract void a(Bundle bundle);

    public void a(boolean z) {
        this.f16897f = z;
    }

    public abstract boolean a();

    public void b(boolean z) {
        this.f16898g = z;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(float f2, int i2) {
        return new BitmapDescriptor(new m5(getContext(), i2).b(f2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(int i2) {
        m5 m5Var = new m5(getContext(), i2);
        if (i2 == 5) {
            return new BitmapDescriptor(m5Var);
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(int i2, int i3) {
        return new BitmapDescriptor(new m5(getContext(), i3).a(i2));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(Bitmap bitmap, int i2) {
        return new BitmapDescriptor(new m5(getContext(), i2).b(bitmap));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(Parcelable parcelable, int i2) {
        m5 m5Var = new m5(getContext(), i2);
        if (i2 == 9) {
            if (parcelable instanceof m5.a) {
                return new BitmapDescriptor(m5Var.a((m5.a) parcelable));
            }
            return null;
        } else if (i2 == 7 && (parcelable instanceof Bitmap)) {
            return new BitmapDescriptor(m5Var.b((Bitmap) parcelable));
        } else {
            return null;
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(String str, int i2) {
        m5 m5Var = new m5(getContext(), i2);
        if (i2 == 2) {
            return new BitmapDescriptor(m5Var.b(str));
        }
        if (i2 == 3) {
            return new BitmapDescriptor(m5Var.c(str));
        }
        if (i2 == 4) {
            return new BitmapDescriptor(m5Var.d(str));
        }
        if (i2 == 8) {
            return new BitmapDescriptor(m5Var.e(str));
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public BitmapDescriptor createBitmapDescriptor(Bitmap[] bitmapArr, int i2) {
        m5 m5Var = new m5(getContext(), i2);
        m5Var.a(bitmapArr);
        return new BitmapDescriptor(m5Var);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapContext
    public Context getContext() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public MyLocationStyle getDefaultMyLocationStyle() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.icon(createBitmapDescriptor(f16893l, 2));
        return myLocationStyle;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public <T extends TencentMapComponent.Component> T getMapComponent(Class<T> cls) {
        return (T) a(cls, null);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapContext
    public TencentMapComponent getMapComponent() {
        return this;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapContext
    public TencentMapResource getMapResource() {
        return this;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapComponent
    public TencentMapServiceProtocol getMapServiceProtocol() {
        return (TencentMapServiceProtocol) getMapComponent(TencentMapProtocol.class);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public int getScreenPixels() {
        return b7.L();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMapResource
    public Typeface getTypeface() {
        return this.b.getTypeface();
    }

    public MapDelegate j() {
        return this.f16895c;
    }

    public File m() {
        return x().b();
    }

    public abstract String n();

    public abstract t1 o();

    public abstract String p();

    public b q() {
        return new b(r());
    }

    public TencentMapOptions r() {
        return this.b;
    }

    public TencentMapProtocol s() {
        return (TencentMapProtocol) getMapComponent(TencentMapProtocol.class);
    }

    public MapViewType t() {
        return this.b.getMapViewType();
    }

    public OverSeaSource u() {
        return this.b.getOverSeaSource();
    }

    public n2 v() {
        TencentMapProtocol s = s();
        return s instanceof l2 ? ((l2) s).h().a() : l2.g();
    }

    public v6 w() {
        return this.f16896e;
    }

    public lc x() {
        if (this.f16899h == null) {
            this.f16899h = lc.a(this.a, this.b);
        }
        return this.f16899h;
    }

    public abstract String y();
}
