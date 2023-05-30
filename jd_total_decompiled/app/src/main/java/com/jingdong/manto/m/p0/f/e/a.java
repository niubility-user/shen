package com.jingdong.manto.m.p0.f.e;

import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.h;
import com.jingdong.manto.m.d0;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {
    private d a;

    /* renamed from: com.jingdong.manto.m.p0.f.e.a$a */
    /* loaded from: classes15.dex */
    class C0598a implements d {
        C0598a(a aVar) {
        }

        @Override // com.jingdong.manto.m.p0.f.e.a.d
        public void a() {
            com.jingdong.manto.m.p0.f.d.a().d();
        }

        @Override // com.jingdong.manto.m.p0.f.e.a.d
        public void b() {
            com.jingdong.manto.m.p0.f.d.a().b();
        }
    }

    /* loaded from: classes15.dex */
    class b extends AppLifeCycle.Listener {
        final /* synthetic */ String a;

        b(String str) {
            a.this = r1;
            this.a = str;
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            super.onAppDestroy();
            if (a.this.a != null) {
                a.this.a.a();
            }
            AppLifeCycle.remove(this.a, this);
        }

        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppPause() {
            super.onAppPause();
            if (a.this.a != null) {
                a.this.a.b();
            }
        }
    }

    /* loaded from: classes15.dex */
    public static class c extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onRecorderStateChange";
        }
    }

    /* loaded from: classes15.dex */
    private interface d {
        void a();

        void b();
    }

    /* loaded from: classes15.dex */
    public static class e {
        public JSONObject a;
        private d0 b;

        /* renamed from: c */
        public h f13542c;
        private int d;

        public e(d0 d0Var, h hVar, int i2) {
            this.b = d0Var;
            this.f13542c = hVar;
            this.d = i2;
        }

        public void a(String str) {
            this.f13542c.a(this.d, this.b.putErrMsg("fail:" + str));
        }

        public void a(String str, String str2) {
            HashMap hashMap = new HashMap();
            if ("error".equalsIgnoreCase(str)) {
                hashMap.put("errMsg", str2);
            }
            hashMap.put(XView2Constants.STATE, str);
            new c().a(this.f13542c).a(hashMap).a();
        }

        public void a(String str, String str2, int i2, long j2) {
            HashMap hashMap = new HashMap();
            hashMap.put(XView2Constants.STATE, str);
            hashMap.put("tempFilePath", str2);
            hashMap.put("duration", Integer.valueOf(i2));
            hashMap.put("fileSize", Long.valueOf(j2));
            new c().a(this.f13542c).a(hashMap).a();
        }

        public void b(String str) {
            a(str, (String) null);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null"));
            return;
        }
        String a = hVar.a();
        e eVar = new e(this, hVar, i2);
        eVar.d = i2;
        eVar.a = jSONObject;
        if (this.a == null) {
            this.a = new C0598a(this);
        }
        if ("start".equalsIgnoreCase(jSONObject.optString(CartConstant.KEY_OPERATIONTYPE))) {
            AppLifeCycle.add(a, new b(a));
        }
        com.jingdong.manto.m.p0.f.d.a().a(eVar);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateRecorder";
    }
}
