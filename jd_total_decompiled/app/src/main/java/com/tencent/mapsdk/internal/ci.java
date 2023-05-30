package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class ci extends JsonComposer {
    @Json(name = "error")
    private int a = Integer.MIN_VALUE;
    @Json(name = "info")
    private bi b;

    public int a() {
        return this.a;
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void a(bi biVar) {
        this.b = biVar;
    }

    public bi b() {
        return this.b;
    }
}
