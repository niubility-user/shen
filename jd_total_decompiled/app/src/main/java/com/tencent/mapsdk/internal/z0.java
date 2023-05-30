package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;

/* loaded from: classes9.dex */
public final class z0 implements TileOverlay {

    /* renamed from: g */
    private final jg f17532g;

    public z0(jg jgVar) {
        this.f17532g = jgVar;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileOverlay
    public void clearTileCache() {
        jg jgVar = this.f17532g;
        if (jgVar == null) {
            return;
        }
        jgVar.clearTileCache();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof z0)) {
            return this.f17532g.equals(((z0) obj).f17532g);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileOverlay, com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    public String getId() {
        jg jgVar = this.f17532g;
        return jgVar == null ? "" : jgVar.y();
    }

    public int hashCode() {
        jg jgVar = this.f17532g;
        if (jgVar == null) {
            return 0;
        }
        return jgVar.hashCode();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileOverlay
    public void reload() {
        jg jgVar = this.f17532g;
        if (jgVar == null) {
            return;
        }
        jgVar.reload();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileOverlay
    public void remove() {
        jg jgVar = this.f17532g;
        if (jgVar == null) {
            return;
        }
        jgVar.remove();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileOverlay
    public void setDiskCacheDir(String str) {
        jg jgVar = this.f17532g;
        if (jgVar == null) {
            return;
        }
        jgVar.a(str);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileOverlay
    public void setZindex(int i2) {
        jg jgVar = this.f17532g;
        if (jgVar == null) {
            return;
        }
        jgVar.c(i2);
    }
}
