package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class xh extends JsonComposer {

    /* renamed from: c  reason: collision with root package name */
    public static final int f17462c = 1;
    public static final int d = 2;

    /* renamed from: e  reason: collision with root package name */
    public static final int f17463e = 3;
    @Json(name = "scenetype")
    private int a;
    @Json(name = "tilesrc")
    private di b;

    public int a() {
        return this.a;
    }

    public void a(int i2) {
        this.a = i2;
    }

    public void a(di diVar) {
        this.b = diVar;
    }

    public di b() {
        return this.b;
    }
}
