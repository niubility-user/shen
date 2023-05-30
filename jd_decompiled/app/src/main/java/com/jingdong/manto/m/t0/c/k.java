package com.jingdong.manto.m.t0.c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class k extends d0 {
    public static String a = "indication";

    /* loaded from: classes15.dex */
    class a implements com.jingdong.manto.m.t0.d.d.d {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        a(com.jingdong.manto.h hVar, int i2) {
            this.a = hVar;
            this.b = i2;
        }

        @Override // com.jingdong.manto.m.t0.d.d.d
        public void a(com.jingdong.manto.m.t0.d.d.e eVar) {
            if (eVar.a != 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(eVar.a));
                this.a.a(this.b, k.this.putErrMsg(eVar.b, hashMap));
                return;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 0);
            this.a.a(this.b, k.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2));
        }
    }

    @Override // com.jingdong.manto.m.d0
    @TargetApi(18)
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        com.jingdong.manto.m.t0.d.c.e eVar;
        com.jingdong.manto.m.t0.d.c.d dVar;
        BluetoothGattService service;
        BluetoothGattCharacteristic characteristic;
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:invalid data", null));
            return;
        }
        com.jingdong.manto.m.t0.b a2 = com.jingdong.manto.m.t0.a.a(hVar.a());
        if (a2 == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", 10000);
            hVar.a(i2, putErrMsg("fail:not init", hashMap));
        } else if (!BTHelper.btEnabled()) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("errCode", 10001);
            hVar.a(i2, putErrMsg("fail:not available", hashMap2));
        } else {
            String optString = jSONObject.optString("deviceId");
            String optString2 = jSONObject.optString("serviceId");
            String optString3 = jSONObject.optString("characteristicId");
            String optString4 = jSONObject.optString(XView2Constants.STATE);
            String lowerCase = jSONObject.optString("type").toLowerCase();
            boolean optBoolean = jSONObject.optBoolean("debug", false);
            boolean optBoolean2 = jSONObject.optBoolean("mainThread", false);
            boolean optBoolean3 = jSONObject.optBoolean("serial", true);
            com.jingdong.manto.m.t0.d.b bVar = a2.b;
            if (bVar != null && (eVar = bVar.b) != null && (dVar = eVar.b.get(optString)) != null) {
                BluetoothGatt bluetoothGatt = dVar.b;
                if (bluetoothGatt == null) {
                    HashMap hashMap3 = new HashMap();
                    hashMap3.put("errCode", 10005);
                    hVar.a(i2, putErrMsg("fail:no characteristic", hashMap3));
                    return;
                } else if (!TextUtils.isEmpty(optString2) && BTHelper.isServiceValid(optString2) && (service = bluetoothGatt.getService(UUID.fromString(optString2))) != null && BTHelper.isServiceValid(optString3) && (characteristic = service.getCharacteristic(UUID.fromString(optString3))) != null) {
                    com.jingdong.manto.m.t0.d.d.b bVar2 = new com.jingdong.manto.m.t0.d.d.b();
                    bVar2.a = characteristic.getUuid().toString().toUpperCase();
                    int properties = characteristic.getProperties();
                    bVar2.f13650c = BTHelper.supportRead(properties);
                    bVar2.d = BTHelper.supportWrite(properties);
                    bVar2.f13651e = BTHelper.supportWriteWithoutResponse(properties);
                    bVar2.b = BTHelper.supportNotify(properties);
                    boolean supportIndicate = BTHelper.supportIndicate(properties);
                    bVar2.f13652f = supportIndicate;
                    if (!bVar2.b && !supportIndicate) {
                        HashMap hashMap4 = new HashMap();
                        hashMap4.put("errCode", 10007);
                        hVar.a(i2, putErrMsg("fail:internal error", hashMap4));
                        return;
                    }
                    boolean equals = "TRUE".equals(optString4.toUpperCase());
                    com.jingdong.manto.m.t0.d.d.c dVar2 = TextUtils.equals(a, lowerCase) ? new com.jingdong.manto.m.t0.d.c.g.d(optString2, optString3, equals) : new com.jingdong.manto.m.t0.d.c.g.e(optString2, optString3, equals);
                    dVar2.f13655e = optBoolean3;
                    dVar2.d = optBoolean2;
                    dVar2.a = optBoolean;
                    a2.a(optString, dVar2, new a(hVar, i2));
                    return;
                }
            }
            HashMap hashMap5 = new HashMap();
            hashMap5.put("errCode", 10007);
            hVar.a(i2, putErrMsg("fail:internal error", hashMap5));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "notifyBLECharacteristicValueChanged";
    }
}
