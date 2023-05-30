package com.jingdong.manto.m.c1;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class a extends l0 {

    /* renamed from: com.jingdong.manto.m.c1.a$a */
    /* loaded from: classes15.dex */
    class RunnableC0551a implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c */
        final /* synthetic */ String f13308c;

        RunnableC0551a(com.jingdong.manto.h hVar, JSONObject jSONObject, String str) {
            a.this = r1;
            this.a = hVar;
            this.b = jSONObject;
            this.f13308c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a(this.a, this.b, this.f13308c);
        }
    }

    @Override // com.jingdong.manto.m.l0
    public final String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        String c2 = c();
        HashMap hashMap = new HashMap();
        hashMap.put(b(), c2);
        com.jingdong.manto.b.d().networkIO().execute(new RunnableC0551a(hVar, jSONObject, c2));
        return putErrMsg(IMantoBaseModule.SUCCESS, hashMap);
    }

    public abstract void a(com.jingdong.manto.h hVar, JSONObject jSONObject, String str);

    protected abstract String b();

    protected abstract String c();
}
