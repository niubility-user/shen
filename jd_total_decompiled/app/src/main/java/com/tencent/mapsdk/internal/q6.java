package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class q6 extends w6 {
    @Json(name = "ubs")
    private a b;

    /* loaded from: classes9.dex */
    public static class a extends w6 {
        @Json(name = "showCount")
        private int b;

        public a(long j2) {
            super(j2);
            this.b = 0;
        }

        public int b() {
            int i2 = this.b + 1;
            this.b = i2;
            return i2;
        }
    }

    public q6(long j2) {
        super(j2);
        this.b = new a(j2);
    }

    public a b() {
        return this.b;
    }
}
