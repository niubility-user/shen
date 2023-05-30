package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.CustomLayer;

/* loaded from: classes9.dex */
public final class u0 implements CustomLayer {

    /* renamed from: g  reason: collision with root package name */
    private final jg f17294g;

    public u0(jg jgVar) {
        this.f17294g = jgVar;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.CustomLayer
    public void clearDiskCache() {
        jg jgVar = this.f17294g;
        if (jgVar == null) {
            return;
        }
        jgVar.clearTileCache();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof u0)) {
            return this.f17294g.equals(((u0) obj).f17294g);
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    public String getId() {
        jg jgVar = this.f17294g;
        return jgVar == null ? "" : jgVar.y();
    }

    public int hashCode() {
        jg jgVar = this.f17294g;
        if (jgVar == null) {
            return 0;
        }
        return jgVar.hashCode();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public boolean isRemoved() {
        jg jgVar = this.f17294g;
        if (jgVar != null) {
            return jgVar.isRemoved();
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void releaseData() {
        jg jgVar = this.f17294g;
        if (jgVar != null) {
            jgVar.releaseData();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.CustomLayer
    public void reload() {
        jg jgVar = this.f17294g;
        if (jgVar == null) {
            return;
        }
        jgVar.reload();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void remove() {
        jg jgVar = this.f17294g;
        if (jgVar == null) {
            return;
        }
        jgVar.remove();
        qa.i(la.a);
    }
}
