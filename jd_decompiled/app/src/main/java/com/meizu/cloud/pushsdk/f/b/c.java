package com.meizu.cloud.pushsdk.f.b;

import com.meizu.cloud.pushsdk.f.g.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class c implements a {
    private final String a = c.class.getSimpleName();
    private final HashMap<String, Object> b = new HashMap<>();

    @Override // com.meizu.cloud.pushsdk.f.b.a
    public Map a() {
        return this.b;
    }

    @Override // com.meizu.cloud.pushsdk.f.b.a
    public void a(String str, String str2) {
        if (str2 != null && !str2.isEmpty()) {
            this.b.put(str, str2);
            return;
        }
        com.meizu.cloud.pushsdk.f.g.c.g(this.a, "The keys value is empty, returning without adding key: " + str, new Object[0]);
    }

    @Override // com.meizu.cloud.pushsdk.f.b.a
    public long b() {
        return e.a(toString());
    }

    public void b(String str, Object obj) {
        if (obj != null) {
            this.b.put(str, obj);
            return;
        }
        com.meizu.cloud.pushsdk.f.g.c.g(this.a, "The keys value is empty, returning without adding key: " + str, new Object[0]);
    }

    public void c(Map<String, Object> map) {
        if (map == null) {
            com.meizu.cloud.pushsdk.f.g.c.g(this.a, "Map passed in is null, returning without adding map.", new Object[0]);
        } else {
            this.b.putAll(map);
        }
    }

    public String toString() {
        return e.e(this.b).toString();
    }
}
