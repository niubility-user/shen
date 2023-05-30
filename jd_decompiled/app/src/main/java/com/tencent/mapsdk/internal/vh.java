package com.tencent.mapsdk.internal;

import android.content.Context;
import com.tencent.map.tools.Callback;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaSource;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import java.util.List;

/* loaded from: classes9.dex */
public class vh {

    /* renamed from: h */
    private static final int f17403h = 7;
    private xi a;

    /* renamed from: c */
    private TileOverlayOptions f17404c;
    private uh d;

    /* renamed from: e */
    private OverSeaTileProvider f17405e;

    /* renamed from: g */
    private boolean f17407g;
    private TileOverlay b = null;

    /* renamed from: f */
    private volatile boolean f17406f = false;

    public vh(xi xiVar) {
        this.a = null;
        this.a = xiVar;
        d();
    }

    private void a() {
        xi xiVar;
        di k2;
        if (this.b != null || (xiVar = this.a) == null || xiVar.getMap() == null || this.a.A() == null || (k2 = this.d.k()) == null) {
            return;
        }
        ma.c(la.f16821h, "\u83b7\u53d6\u6d77\u5916\u56fe\u56fe\u6e90\uff1a" + k2);
        qc A = this.a.A();
        A.e(false);
        A.c(false);
        this.f17405e = new wh(k2, this.d.d(), A.w());
        String f2 = this.d.f();
        String g2 = this.d.g();
        ma.c(la.f16821h, "\u6d77\u5916\u74e6\u7247\u7f13\u5b58\u76ee\u5f55\uff1a" + g2);
        this.f17404c = new TileOverlayOptions().tileProvider(this.f17405e).betterQuality(false).versionInfo(f2).zIndex(1).diskCacheDir(g2);
        this.b = A.d0().a(this.f17404c);
        ma.c(la.f16821h, "\u5f00\u542f\u6d77\u5916\u56fe");
    }

    private boolean a(w5[] w5VarArr) {
        w5[] m0;
        xi xiVar = this.a;
        if (xiVar == null || (m0 = xiVar.m0()) == null || w5VarArr == null) {
            return true;
        }
        return sh.a(m0, w5VarArr);
    }

    private void d() {
        if (x9.c("4.5.10", "4.0.9", 3)) {
            ga.a(lc.b(this.a.getContext()).h() + "/tencentmapsdk/rastermap/unmainland");
            ga.a(lc.b(this.a.getContext()).c().getPath() + "/rastermap/taiwan");
        }
    }

    private void i() {
        xi xiVar = this.a;
        TileOverlay tileOverlay = this.b;
        if (xiVar == null || xiVar.getMap() == null || xiVar.A() == null || tileOverlay == null) {
            return;
        }
        qc A = xiVar.A();
        A.e(xiVar.getMap().h0());
        A.c(true);
        tileOverlay.remove();
        this.b = null;
        this.f17404c = null;
    }

    public void a(Context context, OverSeaSource overSeaSource, Callback<Boolean> callback) {
        uh uhVar = new uh();
        this.d = uhVar;
        uhVar.a(context, overSeaSource, callback);
    }

    public void a(Language language) {
        if (language == null || this.d.b() == language) {
            return;
        }
        this.d.a(language);
        OverSeaTileProvider overSeaTileProvider = this.f17405e;
        if (overSeaTileProvider != null) {
            overSeaTileProvider.onLanguageChange(language);
        }
        h();
    }

    public void a(OverSeaTileProvider overSeaTileProvider) {
        if (this.f17405e != overSeaTileProvider) {
            ma.c(la.f16821h, "\u8bbe\u7f6e\u81ea\u5b9a\u4e49\u6d77\u5916\u56fe\u6e90\uff0cold[" + this.f17405e + "] to new[" + overSeaTileProvider + "]");
            this.f17405e = overSeaTileProvider;
            this.f17407g = true;
            this.d.a(overSeaTileProvider);
            List<yh> h2 = this.d.h();
            xi xiVar = this.a;
            if (xiVar != null) {
                xiVar.a(false, h2);
            }
            h();
        }
    }

    public void a(boolean z) {
        this.f17406f = z;
    }

    public void b() {
        String str;
        ma.c(la.f16821h, "\u68c0\u67e5\u6d77\u5916\u56fe\u72b6\u6001");
        xi xiVar = this.a;
        if (xiVar == null || xiVar.getMap() == null || this.a.getMapContext() == null) {
            return;
        }
        qc mapContext = this.a.getMapContext();
        if (this.a.getMap().V() < 7) {
            i();
            str = "\u7ea7\u522b\u65e0\u6548";
        } else {
            ma.c(la.f16821h, "\u7ea7\u522b\u6709\u6548");
            if (this.d.m() && mapContext.B()) {
                ma.c(la.f16821h, "\u6743\u9650\u6709\u6548");
                if (mapContext.A()) {
                    ma.c(la.f16821h, "\u8fb9\u754c\u7ebf\u6709\u6548");
                    boolean l2 = this.d.l();
                    StringBuilder sb = new StringBuilder();
                    sb.append("\u6570\u636e\u914d\u7f6e\u6a21\u5f0f\uff1a");
                    sb.append(l2 ? "\u6697\u8272" : "\u4eae\u8272");
                    ma.c(la.f16821h, sb.toString());
                    boolean a = this.a.getMapContext().a();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\u5f53\u524d\u5730\u56fe\u6a21\u5f0f\uff1a");
                    sb2.append(a ? "\u6697\u8272" : "\u4eae\u8272");
                    ma.c(la.f16821h, sb2.toString());
                    if (a != l2) {
                        ma.c(la.f16821h, "\u66f4\u65b0\u6697\u8272\u6a21\u5f0f\uff1a" + a);
                        this.d.a(a);
                        i();
                        OverSeaTileProvider overSeaTileProvider = this.f17405e;
                        if (overSeaTileProvider != null) {
                            overSeaTileProvider.onDayNightChange(l2);
                        }
                    }
                    if (this.f17407g) {
                        this.f17407g = false;
                        i();
                    }
                    if (this.b == null) {
                        a();
                        return;
                    }
                    return;
                }
                if (this.b != null) {
                    i();
                }
                str = "\u8fb9\u754c\u7ebf\u65e0\u6548";
            } else {
                if (this.b != null) {
                    i();
                }
                str = "\u6743\u9650\u65e0\u6548";
            }
        }
        ma.c(la.f16821h, str);
    }

    public void c() {
        TileOverlay tileOverlay = this.b;
        if (tileOverlay == null) {
            return;
        }
        tileOverlay.clearTileCache();
    }

    public uh e() {
        return this.d;
    }

    public boolean f() {
        return this.f17406f;
    }

    public boolean g() {
        return this.d.m();
    }

    public void h() {
        b();
        TileOverlayOptions tileOverlayOptions = this.f17404c;
        if (tileOverlayOptions != null) {
            tileOverlayOptions.versionInfo(this.d.f()).diskCacheDir(this.d.g());
        }
        TileOverlay tileOverlay = this.b;
        if (tileOverlay != null) {
            tileOverlay.reload();
        }
    }
}
