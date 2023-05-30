package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.util.List;

/* loaded from: classes9.dex */
public class e4 extends JsonComposer {
    @Json(name = "enable")
    private int a;
    @Json(name = "layers")
    private List<d4> b;

    public List<d4> a() {
        return this.b;
    }

    public boolean b() {
        return this.a == 1;
    }

    public String toString() {
        return "DataLayerInfo{enable=" + this.a + ", layers=" + this.b + '}';
    }
}
