package com.jdjr.risk.device.b;

import android.content.Context;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public final class g {
    private List<a> a = new ArrayList();

    private void a() {
        this.a.add(new c());
        this.a.add(new f());
        this.a.add(new h());
        this.a.add(new m());
        this.a.add(new l());
        this.a.add(new e());
        this.a.add(new d());
        this.a.add(new b());
        this.a.add(new k());
        this.a.add(new n());
        this.a.add(new i());
        this.a.add(new j());
    }

    public LinkedHashMap<String, Object> a(Context context, com.jdjr.risk.biometric.a.a aVar) {
        Map<String, Object> a;
        a();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        try {
            for (a aVar2 : this.a) {
                JSONObject a2 = com.jdjr.risk.biometric.core.b.a(context).a(aVar, aVar2.a());
                if (a2 != null && (a = aVar2.a(context, a2)) != null) {
                    linkedHashMap.putAll(a);
                }
            }
        } catch (Throwable unused) {
        }
        this.a.clear();
        return linkedHashMap;
    }

    public LinkedHashMap<String, Object> a(Context context, JSONObject jSONObject) {
        a();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        try {
            for (a aVar : this.a) {
                if (jSONObject != null) {
                    aVar.a(jSONObject, "");
                    Map<String, Object> a = aVar.a(context, jSONObject);
                    if (a != null) {
                        linkedHashMap.putAll(a);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        this.a.clear();
        return linkedHashMap;
    }
}
