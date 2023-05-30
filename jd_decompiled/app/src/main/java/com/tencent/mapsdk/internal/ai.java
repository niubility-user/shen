package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class ai extends JsonComposer {
    @Json(name = "version")
    private int a = 0;
    @Json(name = "path")
    private String b;

    public String a() {
        return this.b;
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void a(String str) {
        this.b = str;
    }

    public int b() {
        return this.a;
    }
}
