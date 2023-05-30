package com.jingdong.manto.m.t0.c;

import android.os.Build;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.t0.c.l;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class p extends d0 {
    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        com.jingdong.manto.m.t0.d.d.e eVar;
        HashMap hashMap;
        String str2;
        com.jingdong.manto.m.t0.d.e.f fVar;
        IPermission iPermission;
        super.exec(hVar, jSONObject, i2, str);
        com.jingdong.manto.m.t0.b a = com.jingdong.manto.m.t0.a.a(hVar.a());
        if (a == null) {
            hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            str2 = "fail:not init";
        } else if (BTHelper.btEnabled()) {
            com.jingdong.manto.m.t0.d.b bVar = a.b;
            if (bVar == null || (fVar = bVar.a) == null) {
                eVar = com.jingdong.manto.m.t0.d.d.e.f13664e;
            } else {
                eVar = fVar.a();
                Map<String, com.jingdong.manto.m.t0.d.d.h> map = bVar.a.f13716j;
                if (map != null) {
                    map.clear();
                }
                List<com.jingdong.manto.m.t0.d.d.h> list = bVar.a.f13713g;
                if (list != null) {
                    list.clear();
                }
                if (Build.VERSION.SDK_INT < 31 ? !(BTHelper.getBTAdapter() == null || !BTHelper.getBTAdapter().isDiscovering()) : !((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) == null || !iPermission.hasPermission("android.permission.BLUETOOTH_SCAN") || BTHelper.getBTAdapter() == null || !BTHelper.getBTAdapter().isDiscovering())) {
                    BTHelper.getBTAdapter().cancelDiscovery();
                }
            }
            hashMap = new HashMap();
            if (eVar.a == 0) {
                hashMap.put("isDiscovering", Boolean.FALSE);
                hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, hashMap));
                l.i.a(hVar, true, false);
                return;
            }
            hashMap.put("isDiscovering", Boolean.FALSE);
            str2 = "fail";
        } else {
            hashMap = new HashMap();
            hashMap.put("errCode", 10001);
            str2 = "fail:not available";
        }
        hVar.a(i2, putErrMsg(str2, hashMap));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "stopBluetoothDevicesDiscovery";
    }
}
