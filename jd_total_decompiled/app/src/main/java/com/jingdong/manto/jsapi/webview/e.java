package com.jingdong.manto.jsapi.webview;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "wxH5Pay";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle2;
        JSONObject optJSONObject;
        String str2;
        String string = bundle.getString("params");
        if ("requestWXH5Payment".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                MantoLog.d("wxH5Pay", "handleMethod: " + string);
                optJSONObject = jSONObject.optJSONObject("orderInfo");
            } catch (Exception unused) {
                bundle2 = null;
            }
            if (optJSONObject == null) {
                bundle2 = new Bundle();
                mantoResultCallBack.onFailed(bundle2);
                return;
            }
            String optString = optJSONObject.optString("payInfo");
            String optString2 = optJSONObject.optString("orderId");
            String optString3 = optJSONObject.optString("merchantDomain");
            String string2 = bundle.getString("appid");
            String string3 = bundle.getString("type");
            if (TextUtils.isEmpty(optString3)) {
                str2 = optString;
            } else {
                str2 = optString + "%26redirect_url%3D" + optString3 + "%3A%2F%2F";
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("orderId", optString2);
                jSONObject2.put("payInfo", optString);
                jSONObject2.put("vapp_type", string3);
                jSONObject2.put("host_id", com.jingdong.a.b);
            } catch (Throwable unused2) {
            }
            if (WxH5PayActivity.a(mantoCore.getActivity(), string2, string3, str2, jSONObject2, optString3)) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("orderInfo", optJSONObject.toString());
                mantoResultCallBack.onSuccess(bundle3);
            } else {
                Bundle bundle4 = new Bundle();
                bundle4.putString("orderInfo", optJSONObject.toString());
                mantoResultCallBack.onFailed(bundle4);
            }
            MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u7528\u6237\u5524\u8d77\u5fae\u4fe1H5\u652f\u4ed8API", "applets_WXH5Payment_open", string2, "", "", jSONObject2.toString(), "", null);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("requestWXH5Payment", 1));
    }
}
