package com.jingdong.manto.m.t0.c;

import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BleHelpExt;
import com.jingdong.manto.m.d0;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class j extends d0 {

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
                BleHelpExt.callFail(j.this, this.a, this.b, i2, eVar.b);
            } else {
                BleHelpExt.callSuccess(j.this, this.a, this.b);
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        HashMap hashMap;
        super.exec(hVar, jSONObject, i2, str);
        if (hVar == null) {
            return;
        }
        String str2 = "fail:invalid data";
        if (jSONObject == null || !jSONObject.has("deviceId")) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 10013);
            hVar.a(i2, putErrMsg("fail:invalid data", hashMap2));
            return;
        }
        String a2 = hVar.a();
        com.jingdong.manto.m.t0.b a3 = com.jingdong.manto.m.t0.a.a(a2);
        if (a3 == null) {
            hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            str2 = "fail:not init";
        } else {
            byte[] bArr = null;
            String optString = jSONObject.optString("deviceId", null);
            if (!TextUtils.isEmpty(optString)) {
                String optString2 = jSONObject.optString("pin");
                if (!TextUtils.isEmpty(optString2)) {
                    try {
                        bArr = Base64.decode(optString2, 2);
                    } catch (Throwable unused) {
                        hashMap = new HashMap();
                    }
                }
                long optLong = jSONObject.optLong("timeout", 20000L);
                StringBuilder sb = new StringBuilder("deviceId: ");
                sb.append(optString);
                sb.append(", pin: ");
                if (bArr != null) {
                    Arrays.toString(bArr);
                }
                a3.a(optString, new com.jingdong.manto.m.t0.d.c.g.f(optString, bArr, optLong), new a(a2, i2, hVar));
                return;
            }
            hashMap = new HashMap();
            hashMap.put("errCode", 10013);
        }
        hVar.a(i2, putErrMsg(str2, hashMap));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "makeBluetoothPair";
    }
}
