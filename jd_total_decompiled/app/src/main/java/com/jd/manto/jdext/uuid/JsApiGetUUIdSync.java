package com.jd.manto.jdext.uuid;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.a;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.sdk.api.IGlobalParam;
import com.jingdong.manto.utils.MantoMd5Utils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiGetUUIdSync extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "uuidGroup";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        IGlobalParam iGlobalParam = (IGlobalParam) a.n(IGlobalParam.class);
        if (iGlobalParam == null) {
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            bundle.putString("message", "IGlobalParam not registered!");
            return bundle;
        } else if (TextUtils.equals(str, "requestUUIDSync")) {
            String uuid = iGlobalParam.getUUID(a.g());
            if (TextUtils.isEmpty(uuid)) {
                uuid = "";
            }
            String md5OfString = MantoMd5Utils.md5OfString(MantoMd5Utils.md5OfString(uuid) + bundle.getString("appid"));
            bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("encryptedUUId", md5OfString);
            bundle.putBundle(IMantoBaseModule.BUNDLE_REAL_RESULT, bundle2);
            return bundle;
        } else {
            if (TextUtils.equals(str, "getUUIdSync")) {
                String uuid2 = iGlobalParam.getUUID(a.g());
                bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
                Bundle bundle3 = new Bundle(2);
                bundle3.putString("deviceUUId", uuid2);
                bundle3.putString("randomUUId", iGlobalParam.getRandomCartUUID(a.g()));
                Map encryptUUID = iGlobalParam.getEncryptUUID(a.g());
                if (encryptUUID != null) {
                    for (String str2 : encryptUUID.keySet()) {
                        bundle3.putString(str2, (String) encryptUUID.get(str2));
                    }
                }
                bundle.putBundle(IMantoBaseModule.BUNDLE_REAL_RESULT, bundle3);
            }
            return bundle;
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getUUIdSync", R2.anim.slide_in_from_left, 4));
        list.add(new JsApiMethod("requestUUIDSync", R2.anim.slide_in_from_left, 4));
    }
}
