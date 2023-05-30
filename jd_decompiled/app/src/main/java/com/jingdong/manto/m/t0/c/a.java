package com.jingdong.manto.m.t0.c;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends d0 {

    /* renamed from: com.jingdong.manto.m.t0.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0618a implements com.jingdong.manto.m.t0.d.d.d {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        C0618a(com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.m.t0.d.d.d
        public final void a(com.jingdong.manto.m.t0.d.d.e eVar) {
            if (eVar.a != 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                this.a.a(this.b, a.this.putErrMsg(eVar.b, hashMap));
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 0);
            this.a.a(this.b, a.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        HashMap hashMap;
        HashMap hashMap2;
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject != null) {
            com.jingdong.manto.m.t0.b a = com.jingdong.manto.m.t0.a.a(hVar.a());
            if (a == null) {
                hashMap2 = new HashMap();
                hashMap2.put("errCode", 10000);
                str2 = "fail:not init";
            } else if (BTHelper.btEnabled()) {
                String optString = jSONObject.optString("deviceId");
                if (!TextUtils.isEmpty(optString)) {
                    boolean optBoolean = jSONObject.optBoolean("debug", false);
                    boolean optBoolean2 = jSONObject.optBoolean("mainThread", true);
                    boolean optBoolean3 = jSONObject.optBoolean("serial", true);
                    com.jingdong.manto.m.t0.d.c.g.a aVar = new com.jingdong.manto.m.t0.d.c.g.a();
                    aVar.a = optBoolean;
                    aVar.d = optBoolean2;
                    aVar.f13655e = optBoolean3;
                    a.a(optString, aVar, new C0618a(hVar, i2));
                    return;
                }
                hashMap = new HashMap();
            } else {
                hashMap2 = new HashMap();
                hashMap2.put("errCode", 10001);
                str2 = "fail:not available";
            }
            hVar.a(i2, putErrMsg(str2, hashMap2));
            return;
        }
        hashMap = new HashMap();
        hashMap.put("errCode", 10013);
        hVar.a(i2, putErrMsg("fail:invalid data", hashMap));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "closeBLEConnection";
    }
}
