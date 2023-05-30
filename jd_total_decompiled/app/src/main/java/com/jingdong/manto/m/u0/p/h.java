package com.jingdong.manto.m.u0.p;

import android.graphics.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h {
    public Map<String, e> a = new HashMap();

    /* loaded from: classes15.dex */
    public static class a {
        public static h a = new h();
    }

    public h() {
        a(new g());
        a(new d());
        a(new f());
        a(new i());
        a(new c());
        a(new com.jingdong.manto.m.u0.p.a());
        a(new b());
        a(new j());
    }

    private void a(e eVar) {
        this.a.put(eVar.a(), eVar);
    }

    private boolean a(Path path, com.jingdong.manto.m.u0.o.k0.d0.a aVar) {
        e eVar = this.a.get(aVar.a);
        if (eVar == null) {
            return false;
        }
        return eVar.a(path, aVar);
    }

    private boolean a(Path path, JSONObject jSONObject) {
        String optString = jSONObject.optString("method");
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        e eVar = this.a.get(optString);
        if (eVar == null) {
            return false;
        }
        return eVar.a(path, optJSONArray);
    }

    public final Path a(com.jingdong.manto.m.u0.o.k0.d0.d dVar) {
        Path path = new Path();
        List<com.jingdong.manto.m.u0.o.k0.d0.a> list = dVar.b;
        if (list != null && list.size() != 0) {
            for (com.jingdong.manto.m.u0.o.k0.d0.a aVar : list) {
                if (aVar != null) {
                    a(path, aVar);
                }
            }
        }
        return path;
    }

    public final Path a(JSONArray jSONArray) {
        Path path = new Path();
        if (jSONArray != null && jSONArray.length() != 0) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    a(path, optJSONObject);
                }
            }
        }
        return path;
    }
}
