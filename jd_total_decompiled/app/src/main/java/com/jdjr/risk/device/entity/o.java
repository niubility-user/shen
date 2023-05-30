package com.jdjr.risk.device.entity;

import android.content.Context;
import com.jdcn.risk.cpp.LoadDoor;
import com.jdjr.risk.biometric.core.JdcnOaidManager;
import com.jdjr.risk.device.c.ae;
import com.jdjr.risk.device.c.ag;
import com.jdjr.risk.device.c.r;
import com.jdjr.risk.device.c.y;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class o {
    public static int a;

    public static JSONObject a(Context context, List<String> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (list.contains("pkgNm")) {
                jSONObject.put("package_name", BaseInfo.getAppPackageName());
            }
            if (list.contains(CustomThemeConstance.NAVI_MODEL)) {
                jSONObject.put("p_model", BaseInfo.getDeviceModel());
            }
            if (list.contains("ua")) {
                jSONObject.put("ua", r.f(context));
            }
            if (list.contains(WebPerfManager.FP)) {
                jSONObject.put("fingerprints", BaseInfo.getOSFingerprint());
            }
            if (list.contains("rt")) {
                jSONObject.put("root", ae.a());
            }
            if (list.contains("hk")) {
                jSONObject.put("hook", new JSONObject(com.jdjr.risk.device.c.i.a()).toString());
            }
            if (list.contains("eml")) {
                jSONObject.put("emulator", LoadDoor.e().a().toString());
            }
            if (list.contains("dk")) {
                jSONObject.put("dk", "{\"code\":\"0000000\",\"des\":\"\"}");
            }
            if (list.contains("modelJni")) {
                jSONObject.put("modelFromJni", LoadDoor.e().g(context));
            }
            if (list.contains("fingerJni")) {
                jSONObject.put("fingerprintFromJni", LoadDoor.e().d(context));
            }
            if (list.contains("checkS")) {
                jSONObject.put("checkSum", LoadDoor.e().c(context));
            }
            if (list.contains("mediaDpp")) {
                jSONObject.put("mediaDrmProp", r.h(context));
            }
            if (list.contains(Constant.KEY_MAC)) {
                jSONObject.put("mac_address", y.a(context));
            }
            if (list.contains("advId")) {
                jSONObject.put("advertisingId", com.jdjr.risk.device.c.a.a(context));
            }
            if (list.contains("serlNo")) {
                jSONObject.put("serialno", com.jdjr.risk.device.c.g.a(context));
            }
            if (list.contains("andId")) {
                jSONObject.put("android_id", com.jdjr.risk.device.c.b.a(context));
            }
            if (list.contains("oaId")) {
                jSONObject.put("oaId", JdcnOaidManager.getInstance().getOaid(context));
            }
            if (list.contains("imei")) {
                jSONObject.put("imei", ag.c(context));
            }
            if (list.contains("pltf")) {
                jSONObject.put(Constants.PARAM_PLATFORM, "android");
            }
            if (list.contains("cid")) {
                jSONObject.put("commonId", com.jdjr.risk.device.c.k.a().a(context));
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
