package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.vg;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import java.lang.reflect.Field;

/* loaded from: classes9.dex */
public class tg implements vg.b {

    /* renamed from: e  reason: collision with root package name */
    public static final String f17284e = "rastermap/handdraw";

    /* renamed from: f  reason: collision with root package name */
    private static final int f17285f = 4;

    /* renamed from: g  reason: collision with root package name */
    private static final int f17286g = 20;
    private xi b;
    private TileOverlayOptions d;
    private final Object a = new Object();

    /* renamed from: c  reason: collision with root package name */
    private TileOverlay f17287c = null;

    public tg(xi xiVar) {
        this.b = null;
        this.b = xiVar;
        if (xiVar != null) {
            sg.a(xiVar.getContext());
            new vg(this.b.getContext(), this).a();
        }
    }

    @Override // com.tencent.mapsdk.internal.vg.b
    public void a() {
        d();
    }

    public void b() {
        xi xiVar;
        if (this.f17287c != null || (xiVar = this.b) == null || xiVar.getMap() == null) {
            return;
        }
        VectorMap map = this.b.getMap();
        if (this.d == null) {
            this.d = new TileOverlayOptions();
            this.d.tileProvider(new ug(this.d)).diskCacheDir(f17284e).zIndex(2);
        }
        map.k(19);
        this.f17287c = map.addTileOverlay(this.d);
        f();
    }

    public boolean c() {
        return this.f17287c != null;
    }

    public void d() {
        TileOverlayOptions tileOverlayOptions = this.d;
        if (tileOverlayOptions != null) {
            ((ug) tileOverlayOptions.getTileProvider()).b();
        }
        synchronized (this.a) {
            TileOverlay tileOverlay = this.f17287c;
            if (tileOverlay != null) {
                tileOverlay.clearTileCache();
                this.f17287c.reload();
            }
        }
    }

    public void e() {
        synchronized (this.a) {
            TileOverlay tileOverlay = this.f17287c;
            if (tileOverlay == null) {
                return;
            }
            tileOverlay.remove();
            this.f17287c = null;
        }
    }

    public void f() {
        synchronized (this.a) {
            TileOverlay tileOverlay = this.f17287c;
            if (tileOverlay == null) {
                return;
            }
            for (Field field : tileOverlay.getClass().getDeclaredFields()) {
                if (field.getType() == jg.class) {
                    try {
                        field.setAccessible(true);
                        ((jg) field.get(this.f17287c)).b(4, 20);
                        field.setAccessible(false);
                        return;
                    } catch (IllegalAccessException e2) {
                        ma.b("SketchOverlayManager set data level with reflect", e2);
                        return;
                    }
                }
            }
        }
    }
}
