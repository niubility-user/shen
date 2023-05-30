package com.jingdong.manto.m.u0;

import android.graphics.Canvas;
import com.jingdong.manto.m.u0.o.a0;
import com.jingdong.manto.m.u0.o.b0;
import com.jingdong.manto.m.u0.o.c0;
import com.jingdong.manto.m.u0.o.d0;
import com.jingdong.manto.m.u0.o.e0;
import com.jingdong.manto.m.u0.o.f0;
import com.jingdong.manto.m.u0.o.g0;
import com.jingdong.manto.m.u0.o.h0;
import com.jingdong.manto.m.u0.o.i0;
import com.jingdong.manto.m.u0.o.j0;
import com.jingdong.manto.m.u0.o.o;
import com.jingdong.manto.m.u0.o.p;
import com.jingdong.manto.m.u0.o.q;
import com.jingdong.manto.m.u0.o.r;
import com.jingdong.manto.m.u0.o.s;
import com.jingdong.manto.m.u0.o.t;
import com.jingdong.manto.m.u0.o.u;
import com.jingdong.manto.m.u0.o.v;
import com.jingdong.manto.m.u0.o.w;
import com.jingdong.manto.m.u0.o.x;
import com.jingdong.manto.m.u0.o.y;
import com.jingdong.manto.m.u0.o.z;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class b {
    public Map<String, com.jingdong.manto.m.u0.o.i> a = new HashMap();

    public b() {
        a(new a0());
        a(new com.jingdong.manto.m.u0.o.n());
        a(new b0());
        a(new s());
        a(new com.jingdong.manto.m.u0.o.g());
        a(new com.jingdong.manto.m.u0.o.b());
        a(new g0());
        a(new com.jingdong.manto.m.u0.o.a());
        a(new com.jingdong.manto.m.u0.o.h());
        a(new h0());
        a(new com.jingdong.manto.m.u0.o.e());
        a(new z());
        a(new f0());
        a(new com.jingdong.manto.m.u0.o.f());
        a(new com.jingdong.manto.m.u0.o.d());
        a(new com.jingdong.manto.m.u0.o.c());
        a(new c0());
        a(new d0());
        a(new u());
        a(new w());
        a(new x());
        a(new y());
        a(new p());
        a(new v());
        a(new q());
        a(new r());
        a(new o());
        a(new e0());
        a(new t());
        a(new com.jingdong.manto.m.u0.o.m());
        a(new com.jingdong.manto.m.u0.o.k());
        a(new j0());
        a(new com.jingdong.manto.m.u0.o.l());
        a(new com.jingdong.manto.m.u0.o.j());
        a(new i0());
    }

    private void a(com.jingdong.manto.m.u0.o.i iVar) {
        this.a.put(iVar.a(), iVar);
    }

    public final boolean a(c cVar, Canvas canvas, JSONObject jSONObject) {
        String optString = jSONObject.optString("method");
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        com.jingdong.manto.m.u0.o.i iVar = this.a.get(optString);
        if (iVar == null) {
            return false;
        }
        return iVar.a(cVar, canvas, optJSONArray);
    }
}
