package com.meizu.cloud.pushsdk.f.b;

import com.meizu.cloud.pushsdk.f.g.d;
import com.meizu.cloud.pushsdk.f.g.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class b implements a {
    private final String a = b.class.getSimpleName();
    private final HashMap<String, Object> b = new HashMap<>();

    public b(String str, Object obj) {
        b(str);
        a(obj);
    }

    public b a(Object obj) {
        if (obj == null) {
            return this;
        }
        this.b.put("dt", obj);
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.f.b.a
    public Map<String, Object> a() {
        return this.b;
    }

    @Override // com.meizu.cloud.pushsdk.f.b.a
    @Deprecated
    public void a(String str, String str2) {
        com.meizu.cloud.pushsdk.f.g.c.g(this.a, "Payload: add(String, String) method called - Doing nothing.", new Object[0]);
    }

    @Override // com.meizu.cloud.pushsdk.f.b.a
    public long b() {
        return e.a(toString());
    }

    public b b(String str) {
        d.b(str, "schema cannot be null");
        d.c(!str.isEmpty(), "schema cannot be empty.");
        this.b.put("sa", str);
        return this;
    }

    public String toString() {
        return e.e(this.b).toString();
    }
}
