package com.jingdong.manto.jsapi.refact;

import android.os.Bundle;
import android.text.TextUtils;
import com.jdjr.mobilecert.MobileCertConstants;
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
public abstract class JsApiPrivateAddContact extends AbstractMantoModule {
    public static final String JSAPI_NAME = "private_addContact";
    static final String MODULE_NAME = "JsApiPrivateAddContact";
    static final String TAG = "JsApiPrivateAddContact";

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JsApiPrivateAddContact";
    }

    public abstract void handleAddContact(Bundle bundle, MantoResultCallBack mantoResultCallBack);

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        int optInt = jSONObject.optInt(BaseEvent.SCENE);
        String optString = jSONObject.optString(MobileCertConstants.USERNAME);
        if (TextUtils.isEmpty(optString)) {
            MantoLog.i("JsApiPrivateAddContact", "username nil");
            bundle2.putString("message", "username nil");
            mantoResultCallBack.onFailed(bundle2);
            return;
        }
        bundle3.putInt(BaseEvent.SCENE, optInt);
        bundle3.putString("userName", optString);
        bundle3.putInt("extra", 1);
        handleAddContact(bundle3, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(JSAPI_NAME, 0));
    }
}
