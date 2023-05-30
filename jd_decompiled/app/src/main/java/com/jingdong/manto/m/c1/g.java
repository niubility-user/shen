package com.jingdong.manto.m.c1;

import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c1.c;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        MantoLog.d("operateDownloadTask", "JsApiOperateDownloadTask");
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null", null, str));
            MantoLog.e("operateDownloadTask", "data is null");
            return;
        }
        String optString = jSONObject.optString("downloadTaskId");
        if (TextUtils.isEmpty(optString)) {
            MantoLog.e("operateDownloadTask", "downloadTaskId is null");
            hVar.a(i2, putErrMsg("fail:downloadTaskId is null or nil", null, str));
            return;
        }
        String optString2 = jSONObject.optString(CartConstant.KEY_OPERATIONTYPE);
        if (TextUtils.isEmpty(optString2)) {
            MantoLog.e("operateDownloadTask", "operationType is null");
            str2 = "fail:operationType is null or nil";
        } else if (optString2.equals("abort")) {
            com.jingdong.manto.p.e.c a = com.jingdong.manto.p.e.a.b().a(hVar.c());
            if (a == null) {
                hVar.a(i2, putErrMsg("fail:no task", null, str));
                MantoLog.w("operateDownloadTask", "download is null");
                return;
            }
            com.jingdong.manto.p.e.b a2 = a.a(optString);
            if (a2 == null) {
                hVar.a(i2, putErrMsg("fail:no task", null, str));
                MantoLog.w("operateDownloadTask", String.format("downloadWorker is null %s", optString));
                return;
            }
            a.a(a2);
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
            HashMap hashMap = new HashMap();
            hashMap.put("downloadTaskId", optString);
            hashMap.put(XView2Constants.STATE, "fail");
            hashMap.put("errMsg", "abort");
            String jSONObject2 = new JSONObject(hashMap).toString();
            com.jingdong.manto.m.d a3 = new c.b().a(hVar);
            a3.f13315c = jSONObject2;
            a3.a();
            MantoLog.d("operateDownloadTask", String.format("abortTask finish %s", optString));
            return;
        } else {
            str2 = "fail:unknown operationType";
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateDownloadTask";
    }
}
