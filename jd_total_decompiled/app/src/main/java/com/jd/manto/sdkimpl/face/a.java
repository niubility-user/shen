package com.jd.manto.sdkimpl.face;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "FaceRecognition";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        FaceRecognitionProxyActivity.c(mantoCore.getActivity(), bundle, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("businessId", jSONObject.optString("businessId"));
        bundle.putString("appName", jSONObject.optString("appName"));
        bundle.putString("appAuthorityKey", jSONObject.optString("appAuthorityKey"));
        bundle.putString("verifyToken", jSONObject.optString("verifyToken"));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("faceRecognition", 6001, 1));
    }
}
