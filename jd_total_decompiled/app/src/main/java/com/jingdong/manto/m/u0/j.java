package com.jingdong.manto.m.u0;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class j extends l0 {
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087 A[Catch: all -> 0x00bb, TRY_LEAVE, TryCatch #0 {all -> 0x00bb, blocks: (B:10:0x0029, B:14:0x004f, B:17:0x005c, B:18:0x0062, B:24:0x0076, B:25:0x0079, B:27:0x0087, B:30:0x008e, B:32:0x0096, B:35:0x00a6, B:37:0x00ad, B:34:0x009e, B:21:0x0069, B:23:0x0071, B:16:0x0057), top: B:41:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
    @Override // com.jingdong.manto.m.l0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        String str;
        String lowerCase;
        String optString = jSONObject.optString("text");
        if (TextUtils.isEmpty(optString)) {
            str = "fail:data is null";
        } else {
            double optDouble = jSONObject.optDouble(ViewProps.FONT_SIZE, MantoDensityUtils.dip2pixel(16));
            if (optDouble < 0.0d) {
                str = "fail:param is illegal";
            } else {
                try {
                    String optString2 = jSONObject.optString(ViewProps.FONT_FAMILY);
                    String optString3 = jSONObject.optString(ViewProps.FONT_WEIGHT);
                    String optString4 = jSONObject.optString(ViewProps.FONT_STYLE);
                    n nVar = new n();
                    nVar.a(optString2);
                    if (TextUtils.equals(optString4, "oblique") || TextUtils.equals(optString4, "italic")) {
                        nVar.a(2);
                    } else {
                        TextUtils.equals(optString4, "normal");
                        nVar.a(0);
                    }
                    if (!TextUtils.equals(optString3, "normal") && TextUtils.equals(optString3, "bold")) {
                        nVar.setFakeBoldText(true);
                        nVar.setTextSize((float) optDouble);
                        float measureText = nVar.measureText(optString);
                        String deviceBrand = BaseInfo.getDeviceBrand();
                        lowerCase = deviceBrand == null ? deviceBrand.toLowerCase() : "";
                        if (!lowerCase.contains("vivo") || lowerCase.contains("huawei")) {
                            measureText = (measureText * 1.01f) + 4.0f;
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("width", Float.valueOf(measureText));
                        return putErrMsg(IMantoBaseModule.SUCCESS, hashMap);
                    }
                    nVar.setFakeBoldText(false);
                    nVar.setTextSize((float) optDouble);
                    float measureText2 = nVar.measureText(optString);
                    String deviceBrand2 = BaseInfo.getDeviceBrand();
                    if (deviceBrand2 == null) {
                    }
                    if (!lowerCase.contains("vivo")) {
                    }
                    measureText2 = (measureText2 * 1.01f) + 4.0f;
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("width", Float.valueOf(measureText2));
                    return putErrMsg(IMantoBaseModule.SUCCESS, hashMap2);
                } catch (Throwable th) {
                    th.printStackTrace();
                    str = "fail: measureText error";
                }
            }
        }
        return putErrMsg(str);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "measureText";
    }
}
