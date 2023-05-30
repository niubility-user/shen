package com.jingdong.manto.m.t0.c;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BleHelpExt;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends d0 {

    /* loaded from: classes15.dex */
    class a implements com.jingdong.manto.m.t0.d.d.d {
        final /* synthetic */ int a;
        final /* synthetic */ com.jingdong.manto.h b;

        a(String str, int i2, com.jingdong.manto.h hVar) {
            this.a = i2;
            this.b = hVar;
        }

        @Override // com.jingdong.manto.m.t0.d.d.d
        public void a(com.jingdong.manto.m.t0.d.d.e eVar) {
            int i2 = eVar.a;
            if (i2 != 0) {
                BleHelpExt.callFail(e.this, this.a, this.b, i2, eVar.b);
                return;
            }
            Object obj = eVar.f13674c;
            if (!(obj instanceof Integer)) {
                obj = null;
            }
            Integer num = (Integer) obj;
            if (num != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("RSSI", num);
                BleHelpExt.callSuccess(e.this, this.a, this.b, hashMap);
                return;
            }
            e eVar2 = e.this;
            int i3 = this.a;
            com.jingdong.manto.h hVar = this.b;
            com.jingdong.manto.m.t0.d.d.e eVar3 = com.jingdong.manto.m.t0.d.d.e.f13664e;
            BleHelpExt.callFail(eVar2, i3, hVar, eVar3.a, eVar3.b);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        if (hVar == null) {
            return;
        }
        if (jSONObject == null || !jSONObject.has("deviceId")) {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", 10013);
            hVar.a(i2, putErrMsg("fail:invalid data", hashMap));
            return;
        }
        String a2 = hVar.a();
        com.jingdong.manto.m.t0.b a3 = com.jingdong.manto.m.t0.a.a(a2);
        if (a3 == null) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 10000);
            hVar.a(i2, putErrMsg("fail:not init", hashMap2));
        }
        String optString = jSONObject.optString("deviceId");
        if (!TextUtils.isEmpty(optString)) {
            a3.a(optString, new com.jingdong.manto.m.t0.d.c.g.c(), new a(a2, i2, hVar));
            return;
        }
        MantoLog.w("BT.GetBLEDeviceRSSI", "deviceId is null");
        HashMap hashMap3 = new HashMap();
        hashMap3.put("errCode", 10013);
        hVar.a(i2, putErrMsg("fail:invalid data", hashMap3));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBLEDeviceRSSI";
    }
}
