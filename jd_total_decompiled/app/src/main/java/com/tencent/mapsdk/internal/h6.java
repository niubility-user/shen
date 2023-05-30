package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class h6 extends w6 {
    @Json(name = "showCount")
    private int b;

    public h6(long j2) {
        super(j2);
        this.b = 0;
    }

    public int b() {
        int i2 = this.b + 1;
        this.b = i2;
        return i2;
    }
}
