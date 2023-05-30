package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.annotation.Json;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class k6 extends w6 {
    @Json(name = "styles")
    private List<a> b;

    /* loaded from: classes9.dex */
    public static class a extends w6 {
        @Json(name = "id")
        private int b;
        @Json(name = "showCount")

        /* renamed from: c  reason: collision with root package name */
        private int f16761c;

        public a(long j2, int i2) {
            super(j2);
            this.b = 0;
            this.f16761c = 0;
            this.b = i2;
            this.f16761c = 1;
        }

        public static /* synthetic */ int b(a aVar) {
            int i2 = aVar.f16761c + 1;
            aVar.f16761c = i2;
            return i2;
        }
    }

    public k6(long j2) {
        super(j2);
        this.b = new ArrayList();
    }

    public int a(int i2) {
        for (a aVar : this.b) {
            if (aVar.b == i2) {
                return a.b(aVar);
            }
        }
        this.b.add(new a(this.a, i2));
        return 1;
    }
}
