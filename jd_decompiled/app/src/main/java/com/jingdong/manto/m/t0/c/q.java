package com.jingdong.manto.m.t0.c;

import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class q extends d0 {

    /* loaded from: classes15.dex */
    class a implements com.jingdong.manto.m.t0.d.d.d {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        a(com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.m.t0.d.d.d
        public final void a(com.jingdong.manto.m.t0.d.d.e eVar) {
            if (eVar.a != 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                this.a.a(this.b, q.this.putErrMsg(eVar.b, hashMap));
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 0);
            this.a.a(this.b, q.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        HashMap hashMap;
        String str2;
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:invalid data", null));
            return;
        }
        com.jingdong.manto.m.t0.b a2 = com.jingdong.manto.m.t0.a.a(hVar.a());
        if (a2 == null) {
            hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            str2 = "fail:not init";
        } else if (BTHelper.btEnabled()) {
            String optString = jSONObject.optString("deviceId");
            String optString2 = jSONObject.optString("serviceId");
            String optString3 = jSONObject.optString("characteristicId");
            String optString4 = jSONObject.optString("value");
            boolean optBoolean = jSONObject.optBoolean("debug", false);
            boolean optBoolean2 = jSONObject.optBoolean("serial", true);
            com.jingdong.manto.m.t0.d.c.g.i iVar = new com.jingdong.manto.m.t0.d.c.g.i(optString2, optString3, optString4, jSONObject.optString("writeType"));
            iVar.a = optBoolean;
            iVar.d = false;
            iVar.f13655e = optBoolean2;
            a2.a(optString, iVar, new a(hVar, i2));
            return;
        } else {
            hashMap = new HashMap();
            hashMap.put("errCode", 10001);
            str2 = "fail:not available";
        }
        hVar.a(i2, putErrMsg(str2, hashMap));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "writeBLECharacteristicValue";
    }
}
