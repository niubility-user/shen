package com.jingdong.manto.m.n1;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ g a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13459c;
        final /* synthetic */ String d;

        a(g gVar, com.jingdong.manto.h hVar, int i2, String str) {
            this.a = gVar;
            this.b = hVar;
            this.f13459c = i2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            HashMap hashMap = new HashMap();
            ArrayList<String> arrayList = this.a.f13464g;
            if (arrayList != null && arrayList.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<String> it = this.a.f13464g.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                hashMap.put("keys", jSONArray);
            }
            hashMap.put("currentSize", Integer.valueOf(this.a.f13465h));
            hashMap.put("limitSize", Integer.valueOf(this.a.f13461c));
            this.b.a(this.f13459c, e.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, this.d));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        g gVar = new g();
        gVar.f13462e = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
        gVar.d = hVar.a();
        gVar.f13463f = new a(gVar, hVar, i2, str);
        gVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getStorageInfo";
    }
}
