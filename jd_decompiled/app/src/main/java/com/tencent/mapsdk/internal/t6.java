package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class t6 extends w6 {
    @Json(name = "wtc")
    private int b;
    @Json(name = "bwtc")

    /* renamed from: c  reason: collision with root package name */
    private int f17267c;

    public t6(long j2) {
        super(j2);
    }

    public void a(int i2) {
        this.f17267c += i2;
    }

    public void b(int i2) {
        this.b += i2;
    }
}
