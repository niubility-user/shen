package com.jingdong.manto.m.t0.c;

import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        HashMap hashMap;
        String str2;
        com.jingdong.manto.m.t0.d.e.f fVar;
        super.exec(hVar, jSONObject, i2, str);
        com.jingdong.manto.m.t0.b a = com.jingdong.manto.m.t0.a.a(hVar.a());
        if (a == null) {
            hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            str2 = "fail:not init";
        } else {
            boolean btEnabled = BTHelper.btEnabled();
            boolean z = false;
            com.jingdong.manto.m.t0.d.b bVar = a.b;
            if (bVar != null && (fVar = bVar.a) != null) {
                z = fVar.f13712f.get();
            }
            hashMap = new HashMap();
            hashMap.put(Constant.KEY_PROMOTION_AVAILABLE, Boolean.valueOf(btEnabled));
            hashMap.put("discovering", Boolean.valueOf(z));
            str2 = IMantoBaseModule.SUCCESS;
        }
        hVar.a(i2, putErrMsg(str2, hashMap));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getBluetoothAdapterState";
    }
}
