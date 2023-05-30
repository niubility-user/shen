package com.jd.manto.hd.bluetooth.peripheral;

import androidx.annotation.RequiresApi;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BTHelper;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.unionpay.tsmservice.mi.data.Constant;
import org.json.JSONObject;

@RequiresApi(api = 21)
/* loaded from: classes17.dex */
public class a {
    public static void a(AbstractMantoModule abstractMantoModule, String str, String str2, boolean z, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceId", str);
            jSONObject.put("serverId", str2);
            jSONObject.put("connected", z);
            jSONObject.put(Constant.KEY_PROMOTION_AVAILABLE, BTHelper.btEnabled());
            abstractMantoModule.dispatchEvent(f.b().a(), "onBLEPeripheralConnectionStateChanged", jSONObject, i2);
        } catch (Throwable unused) {
        }
    }

    public static boolean a(AbstractMantoModule abstractMantoModule, long j2, String str, String str2, long j3, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("serverId", j2);
            jSONObject.put("serviceId", str);
            jSONObject.put("characteristicId", str2);
            jSONObject.put("callbackId", j3);
            abstractMantoModule.dispatchEvent(f.b().a(), "onCharacteristicReadRequest", jSONObject, i2);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(AbstractMantoModule abstractMantoModule, long j2, String str, String str2, long j3, String str3, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("serverId", j2);
            jSONObject.put("serviceId", str);
            jSONObject.put("characteristicId", str2);
            jSONObject.put("callbackId", j3);
            jSONObject.put("value", str3);
            abstractMantoModule.dispatchEvent(f.b().a(), "onCharacteristicWriteRequest", jSONObject, i2);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
