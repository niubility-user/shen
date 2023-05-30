package com.jingdong.manto.m;

import android.os.Parcelable;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.mainproc.MainProcMessage;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class l extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
            MantoLog.e("JsApiMessageToMain", "data is null");
            return;
        }
        MainProcMessage mainProcMessage = new MainProcMessage();
        mainProcMessage.appId = hVar.a();
        mainProcMessage.messageName = jSONObject.optString("MessageName");
        mainProcMessage.data = MantoUtils.formatJson(jSONObject);
        MantoLog.d("JsApiMessageToMain", "MessageName " + mainProcMessage.messageName + ", appId " + mainProcMessage.appId + ", data " + mainProcMessage.data);
        hVar.h().p().a((Parcelable) mainProcMessage);
        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "postMessageToNative";
    }
}
