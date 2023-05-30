package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.util.List;

/* loaded from: classes9.dex */
public class yh extends JsonComposer {
    @Json(name = "level")
    private int[] a;
    @Json(name = "districts")
    private List<zh> b;

    public void a(List<zh> list) {
        this.b = list;
    }

    public void a(int[] iArr) {
        this.a = iArr;
    }

    public int[] a() {
        return this.a;
    }

    public List<zh> b() {
        return this.b;
    }
}
