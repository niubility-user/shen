package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class y6 extends w6 {
    @Json(name = "customLayerTimes")
    private int b;

    public y6(long j2) {
        super(j2);
    }

    public int b() {
        int i2 = this.b + 1;
        this.b = i2;
        return i2;
    }
}
