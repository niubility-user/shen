package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TileProvider;

/* loaded from: classes9.dex */
public class qg {
    private og a;

    public qg(og ogVar) {
        this.a = ogVar;
    }

    public jg a(TileOverlayOptions tileOverlayOptions) {
        TileProvider tileProvider = tileOverlayOptions.getTileProvider();
        jg pgVar = tileProvider != null ? tileProvider instanceof ug ? new pg(this.a, tileOverlayOptions) : tileProvider instanceof wh ? new rg(this.a, tileOverlayOptions) : new jg(this.a, tileOverlayOptions) : null;
        this.a.a(pgVar);
        return pgVar;
    }
}
