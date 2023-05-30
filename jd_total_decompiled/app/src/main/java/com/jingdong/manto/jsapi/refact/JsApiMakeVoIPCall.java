package com.jingdong.manto.jsapi.refact;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class JsApiMakeVoIPCall extends AbstractMantoModule {
    public static final String JSAPI_NAME = "makeVoIPCall";
    static final String MODULE_NAME = "JsApiMakeVoIPCall";
    static final String TAG = "JsApiMakeVoIPCall";

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "JsApiMakeVoIPCall";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        Bundle bundle2 = new Bundle();
        String optString = jSONObject.optString(AnnoConst.Constructor_Context);
        String optString2 = jSONObject.optString("avatarUrl");
        boolean optBoolean = jSONObject.optBoolean("showOther");
        boolean optBoolean2 = jSONObject.optBoolean("allowBackCamera");
        String optString3 = jSONObject.optString("toUserName");
        String optString4 = jSONObject.optString(BaseEvent.SCENE);
        String optString5 = jSONObject.optString("type");
        String string = bundle.getString("appid");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(optString3)) {
            MantoLog.e("JsApiMakeVoIPCall", "appId or toUserName invalid!");
            mantoResultCallBack.onFailed(bundle2);
            return;
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("voipCSBizId", optString3);
        bundle3.putString("voipCSAppId", string);
        bundle3.putBoolean("voipCSAllowBackCamera", optBoolean2);
        bundle3.putBoolean("voipCSShowOther", optBoolean);
        bundle3.putString("voipCSAvatarUrl", optString2);
        bundle3.putString("voipCSContext", optString);
        bundle3.putString("voipCSScene", optString4);
        bundle3.putString("voipCSType", optString5);
        bundle3.putBoolean("launch_from_manto", true);
        makeVoIPCall(bundle3, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(JSAPI_NAME, 1));
    }

    public abstract void makeVoIPCall(Bundle bundle, MantoResultCallBack mantoResultCallBack);
}
