package com.jingdong.manto.m.c1;

import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c1.d;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        MantoLog.d("JsApiOperateRequestTask", "JsApiOperateRequestTask");
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null", null, str));
            MantoLog.e("JsApiOperateRequestTask", "data is null");
            return;
        }
        String optString = jSONObject.optString("requestTaskId");
        if (TextUtils.isEmpty(optString)) {
            MantoLog.e("JsApiOperateRequestTask", "requestTaskId is null");
            hVar.a(i2, putErrMsg("fail:requestTaskId is null or nil", null, str));
            return;
        }
        String optString2 = jSONObject.optString(CartConstant.KEY_OPERATIONTYPE);
        if (TextUtils.isEmpty(optString2)) {
            MantoLog.e("JsApiOperateRequestTask", "operationType is null");
            str2 = "fail:operationType is null or nil";
        } else if (optString2.equals("abort")) {
            com.jingdong.manto.p.g.c a = com.jingdong.manto.p.g.a.b().a(hVar.c());
            if (a == null) {
                hVar.a(i2, putErrMsg("fail:no task", null, str));
                MantoLog.w("JsApiOperateRequestTask", "request is null");
                return;
            }
            com.jingdong.manto.p.g.b a2 = a.a(optString);
            if (a2 == null) {
                hVar.a(i2, putErrMsg("fail:no task", null, str));
                MantoLog.w("JsApiOperateRequestTask", String.format("requestInfo is null %s", optString));
                return;
            }
            a.a(a2);
            hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
            HashMap hashMap = new HashMap();
            hashMap.put("requestTaskId", optString);
            hashMap.put(XView2Constants.STATE, "fail");
            hashMap.put("errMsg", "abort");
            String jSONObject2 = new JSONObject(hashMap).toString();
            com.jingdong.manto.m.d a3 = new d.b().a(hVar);
            a3.f13315c = jSONObject2;
            a3.a();
            MantoLog.d("JsApiOperateRequestTask", String.format("abortTask finish %s", optString));
            return;
        } else {
            str2 = "fail:unknown operationType";
        }
        hVar.a(i2, putErrMsg(str2, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateRequestTask";
    }
}
